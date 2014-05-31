/*
 * Copyright 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package fr.onevu.gwt.uibinder.rebind;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RenderablePanel;

import fr.onevu.gwt.uibinder.client.factory.UiBinderWidgetFactory;
import fr.onevu.gwt.uibinder.rebind.model.OwnerClass;
import fr.onevu.gwt.uibinder.rebind.model.OwnerField;

/**
 * Implementation of FieldWriter for fields whose type already exists (that is,
 * for whom we have a {@link JClassType}.
 */
public class FieldWriterOfExistingType extends AbstractFieldWriter {
	protected final JClassType type;

	protected FieldWriterOfExistingType(FieldManager manager, FieldWriterType fieldType, JClassType type, String name, MortalLogger logger) {
		super(manager, fieldType, name, logger);
		if (type == null) {
			throw new IllegalArgumentException("type cannot be null");
		}
		this.type = type;
	}

	public JClassType getAssignableType() {
		return type;
	}

	public JClassType getInstantiableType() {
		return type;
	}

	public String getQualifiedSourceName() {
		return type.getQualifiedSourceName();
	}

	// protected String generateInitilizerForField(TypeOracle typeOracle,
	// OwnerClass ownerClass, FieldWriter field) {
	// if (isInternal(field))
	// return String.format("(%1$s) GWT.create(%1$s.class)",
	// getQualifiedSourceName());
	//
	// JClassType widgetFactory =
	// typeOracle.findType(UiBinderWidgetFactory.class.getName());
	// String initilizer =
	// String.format("(%1$s) %2$s.create(%1$s.class, \"%3$s\", \"%4$s\")",
	// getQualifiedSourceName(), widgetFactory.getQualifiedSourceName(),
	// ownerClass.getOwnerType().getQualifiedSourceName(), name);
	// return initilizer;
	// }

	protected String generateAfterStatementsForField(TypeOracle typeOracle, OwnerClass ownerClass, FieldWriter field) {
		if (isInternal(field))
			return "";

		JClassType widgetFactory = typeOracle.findType(UiBinderWidgetFactory.class.getName());
		String initilizer = String.format("%1$s.init(%2$s.class, \"%3$s\", \"%4$s\", %4$s);", widgetFactory.getQualifiedSourceName(), getQualifiedSourceName(),
				ownerClass.getOwnerType().getQualifiedSourceName(), name);
		return initilizer;
	}

	@Override
	public void writeFieldDefinition(IndentedWriter w, TypeOracle typeOracle, OwnerClass ownerClass, FieldWriter field, DesignTimeUtils designTime,
			int getterCount, boolean useLazyWidgetBuilders) throws UnableToCompleteException {
		OwnerField ownerField = ownerClass.getUiField(field.getName());

		JClassType renderablePanelType = typeOracle.findType(RenderablePanel.class.getName());
		boolean outputAttachDetachCallbacks = useLazyWidgetBuilders && getAssignableType() != null && getAssignableType().isAssignableTo(renderablePanelType);

		// Check initializer. Provided value takes precedence over initializer.
		if (ownerField != null && ownerField.isProvided()) {
			initializer = String.format("owner.%s", name);
		} else if (initializer == null) {
			JClassType type = getInstantiableType();
			if (type != null) {
				if ((type.isInterface() == null) && (type.findConstructor(new JType[0]) == null)) {
					logger.die(NO_DEFAULT_CTOR_ERROR, type.getQualifiedSourceName(), type.getName());
				}
			}
			initializer = generateInitilizerForField(typeOracle, ownerClass, field);
		}

		w.newline();
		w.write("/**");
		w.write(" * Getter for %s called %s times. Type: %s. Build precedence: %s.", name, getterCount, getFieldType(), getBuildPrecedence());
		w.write(" */");
		if (getterCount > 1) {
			w.write("private %1$s %2$s;", getQualifiedSourceName(), name);
		}

		w.write("private %s %s {", getQualifiedSourceName(), FieldManager.getFieldGetter(name));
		w.indent();
		w.write("return %s;", (getterCount > 1) ? name : FieldManager.getFieldBuilder(name));
		w.outdent();
		w.write("}");

		w.write("private %s %s {", getQualifiedSourceName(), FieldManager.getFieldBuilder(name));
		w.indent();

		w.write("// Creation section.");
		if (getterCount > 1) {
			w.write("%s = %s;", name, initializer);
		} else {
			w.write("final %s %s = %s;", getQualifiedSourceName(), name, initializer);
		}
		if (ownerField != null && ownerField.isProvided() && !designTime.isDesignTime()) {
			w.write("assert %1$s != null : \"UiField %1$s with 'provided = true' was null\";", name);
		}

		addNullCheck(w, field);
		w.write("// Setup section.");
		for (String s : statements) {
			w.write(s);
		}
		w.write(generateAfterStatementsForField(typeOracle, ownerClass, field));
		afterNullCheck(w, field);

		String attachedVar = null;

		if (attachStatements.size() > 0) {
			w.newline();
			w.write("// Attach section.");
			if (outputAttachDetachCallbacks) {
				// TODO(rdcastro): This is too coupled with RenderablePanel.
				// Make this nicer.
				w.write("%s.wrapInitializationCallback = ", getName());
				w.indent();
				w.indent();
				w.write("new com.google.gwt.user.client.Command() {");
				w.outdent();
				w.write("@Override public void execute() {");
				w.indent();
			} else {
				attachedVar = getNextAttachVar();

				JClassType elementType = typeOracle.findType(Element.class.getName());

				String elementToAttach = getInstantiableType().isAssignableTo(elementType) ? name : name + ".getElement()";

				w.write("UiBinderUtil.TempAttachment %s = UiBinderUtil.attachToDom(%s);", attachedVar, elementToAttach);
			}

			for (String s : attachStatements) {
				w.write(s);
			}

			if (outputAttachDetachCallbacks) {
				w.outdent();
				w.write("}");
				w.outdent();
				w.write("};");
			}
		}

		w.newline();
		// If we forced an attach, we should always detach, regardless of whether
		// there are any detach statements.
		if (attachedVar != null) {
			w.write("// Detach section.");
			w.write("%s.detach();", attachedVar);
		}

		if (detachStatements.size() > 0) {
			if (outputAttachDetachCallbacks) {
				w.write("%s.detachedInitializationCallback = ", getName());
				w.indent();
				w.indent();
				w.write("new com.google.gwt.user.client.Command() {");
				w.outdent();
				w.write("@Override public void execute() {");
				w.indent();
			}

			for (String s : detachStatements) {
				w.write(s);
			}

			if (outputAttachDetachCallbacks) {
				w.outdent();
				w.write("}");
				w.outdent();
				w.write("};");
			}
		}

		if ((ownerField != null) && !ownerField.isProvided()) {
			w.newline();
			w.write("this.owner.%1$s = %1$s;", name);
		}

		w.newline();
		w.write("return %s;", name);
		w.outdent();
		w.write("}");
	}
}
