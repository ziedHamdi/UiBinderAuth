
/**
 * This package contains classes responsible for widget creation. It comes as a proxy to {@link com.google.gwt.core.shared.GWT#create(Class)} 
 * to allow specific operations on field creation depending on the application context mainly the logged user (access rights, personalized styles, 
 * widget metadata, etc...)
 * 
 *  By default GWT provides a simple implementation that creates the widgets as expected without modification, to apply your modification, 
 *  replace the {@link fr.onevu.gwt.uibinder.client.factory.ContextSpecificWidgetCreator} with your implementation in your module.gwt.xml file
 *  
 * <code>
 * <pre>
 * <replace-with class="yourpackage.YourContextSpecificWidgetCreator">
 *   <when-type-is class="fr.onevu.gwt.uibinder.client.factory.ContextSpecificWidgetCreator"/>
 * </replace-with>
 * </pre>
 * </code>
 * 
 * @author Zied Hamdi - http://1vu.fr
 *
 */
package fr.onevu.gwt.uibinder.client.factory;