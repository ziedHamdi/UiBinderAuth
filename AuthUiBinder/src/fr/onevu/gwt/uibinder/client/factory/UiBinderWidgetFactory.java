package fr.onevu.gwt.uibinder.client.factory;

import com.google.gwt.core.client.GWT;

/**
 * A central manager that holds an instance of
 * {@link ContextSpecificWidgetCreator} that decides whether a widget should be
 * created/readOnly or not.
 * 
 * @author Zied Hamdi - http://1vu.fr
 * 
 */
public class UiBinderWidgetFactory {
	protected static ContextSpecificWidgetCreator contextSpecificWidgetCreator;

	public static <T> void init(Class<T> widget, String uiBinder, String fieldName, T field) {
		getContextSpecificWidgetCreator().init(widget, uiBinder, fieldName, field);
	}

	public static ContextSpecificWidgetCreator getContextSpecificWidgetCreator() {
		if (contextSpecificWidgetCreator == null)
			contextSpecificWidgetCreator = GWT.create(ContextSpecificWidgetCreator.class);

		return contextSpecificWidgetCreator;
	}

	public static void setAuthorizationSpecifier(ContextSpecificWidgetCreator contextSpecificWidgetCreator) {
		UiBinderWidgetFactory.contextSpecificWidgetCreator = contextSpecificWidgetCreator;
	}
}
