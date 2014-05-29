package fr.onevu.gwt.uibinder.client.factory;

/**
 * This default implementation doesn't do any restrictions on the fields. If you
 * want to apply your own initialization strategy, replace the implementation of
 * {@link ContextSpecificWidgetCreator} with yours in your module xml file
 * <code>
 * <pre>
 * <replace-with class="yourpackage.YourContextSpecificWidgetCreator">
    <when-type-is class="fr.onevu.gwt.uibinder.client.factory.ContextSpecificWidgetCreator"/>
  </replace-with>
 * </pre>
 * </code>
 * 
 * @author Zied Hamdi - http://1vu.fr
 * 
 */
public class DefaultContextSpecificWidgetCreator implements ContextSpecificWidgetCreator {

	@Override
	public <T> void init(Class<T> clazz, String uiBinder, String fieldName, T field) {
		// does nothing by default
	}

}