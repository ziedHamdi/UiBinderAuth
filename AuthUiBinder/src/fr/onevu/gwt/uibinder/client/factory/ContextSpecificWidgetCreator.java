package fr.onevu.gwt.uibinder.client.factory;


/**
 * If you want to control the creation of widgets you can implement this class
 * and provide it in your module xml
 * 
 * Implementations of this interface can rely on static rules (read from a
 * configuration file or persistent format) or can be dynamically related to
 * context conditions. In this latter case, you should handle manually widget
 * recreation to handle suppression of already created elements and creation of
 * non existing ones
 * 
 * @author Zied Hamdi - http://1vu.fr
 * 
 */
public interface ContextSpecificWidgetCreator {

	/**
	 * This method must take into account the fact that the widget may be
	 * initialized with properties from the XML file.
	 * 
	 * To override the xml statements by the ones declared in your profiles, you
	 * should prefix and/or suffix the values, for example prefixed values with
	 * '~' could mean: override the xml value by this one
	 * 
	 * Initialization like styleName in UiBinder is written by statements in the
	 * generator). We give a chance here to add things on top of what was already
	 * set from the XML
	 * 
	 * @param clazz
	 *          the class is passed in case the field was not created (is null)
	 * @param uiBinder
	 * @param fieldName
	 * @param field
	 */
	<T> void init(Class<T> clazz, String uiBinder, String fieldName, T field);

}
