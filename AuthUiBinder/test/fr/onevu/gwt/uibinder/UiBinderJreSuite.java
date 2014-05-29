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
package fr.onevu.gwt.uibinder;

import fr.onevu.gwt.uibinder.attributeparsers.CssNameConverterTest;
import fr.onevu.gwt.uibinder.attributeparsers.FieldReferenceConverterTest;
import fr.onevu.gwt.uibinder.attributeparsers.HorizontalAlignmentConstantParserTest;
import fr.onevu.gwt.uibinder.attributeparsers.IntAttributeParserTest;
import fr.onevu.gwt.uibinder.attributeparsers.IntPairAttributeParserTest;
import fr.onevu.gwt.uibinder.attributeparsers.LengthAttributeParserTest;
import fr.onevu.gwt.uibinder.attributeparsers.SafeUriAttributeParserTest;
import fr.onevu.gwt.uibinder.attributeparsers.StrictAttributeParserTest;
import fr.onevu.gwt.uibinder.attributeparsers.StringAttributeParserTest;
import fr.onevu.gwt.uibinder.attributeparsers.TextAlignConstantParserTest;
import fr.onevu.gwt.uibinder.attributeparsers.VerticalAlignmentConstantParserTest;
import fr.onevu.gwt.uibinder.elementparsers.AbsolutePanelParserTest;
import fr.onevu.gwt.uibinder.elementparsers.DateLabelParserTest;
import fr.onevu.gwt.uibinder.elementparsers.DialogBoxParserTest;
import fr.onevu.gwt.uibinder.elementparsers.DisclosurePanelParserTest;
import fr.onevu.gwt.uibinder.elementparsers.DockLayoutPanelParserTest;
import fr.onevu.gwt.uibinder.elementparsers.GridParserTest;
import fr.onevu.gwt.uibinder.elementparsers.HasTreeItemsParserTest;
import fr.onevu.gwt.uibinder.elementparsers.ImageParserTest;
import fr.onevu.gwt.uibinder.elementparsers.IsEmptyParserTest;
import fr.onevu.gwt.uibinder.elementparsers.LayoutPanelParserTest;
import fr.onevu.gwt.uibinder.elementparsers.ListBoxParserTest;
import fr.onevu.gwt.uibinder.elementparsers.MenuBarParserTest;
import fr.onevu.gwt.uibinder.elementparsers.MenuItemParserTest;
import fr.onevu.gwt.uibinder.elementparsers.NumberLabelParserTest;
import fr.onevu.gwt.uibinder.elementparsers.StackLayoutPanelParserTest;
import fr.onevu.gwt.uibinder.elementparsers.StackPanelParserTest;
import fr.onevu.gwt.uibinder.elementparsers.TabLayoutPanelParserTest;
import fr.onevu.gwt.uibinder.elementparsers.TabPanelParserTest;
import fr.onevu.gwt.uibinder.elementparsers.UIObjectParserTest;
import fr.onevu.gwt.uibinder.elementparsers.UiChildParserTest;
import fr.onevu.gwt.uibinder.rebind.DesignTimeUtilsTest;
import fr.onevu.gwt.uibinder.rebind.FieldWriterOfExistingTypeTest;
import fr.onevu.gwt.uibinder.rebind.FieldWriterOfGeneratedCssResourceTest;
import fr.onevu.gwt.uibinder.rebind.FieldWriterOfLazyDomElementTest;
import fr.onevu.gwt.uibinder.rebind.GwtResourceEntityResolverTest;
import fr.onevu.gwt.uibinder.rebind.HandlerEvaluatorTest;
import fr.onevu.gwt.uibinder.rebind.TokenatorTest;
import fr.onevu.gwt.uibinder.rebind.TypeOracleUtilsTest;
import fr.onevu.gwt.uibinder.rebind.UiBinderParserUiWithTest;
import fr.onevu.gwt.uibinder.rebind.UiRendererEventValidationTest;
import fr.onevu.gwt.uibinder.rebind.UiRendererValidationTest;
import fr.onevu.gwt.uibinder.rebind.XMLElementTest;
import fr.onevu.gwt.uibinder.rebind.model.HtmlTemplatesTest;
import fr.onevu.gwt.uibinder.rebind.model.OwnerClassTest;
import fr.onevu.gwt.uibinder.rebind.model.OwnerFieldClassTest;
import fr.onevu.gwt.uibinder.rebind.model.OwnerFieldTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Suite of UiBinder tests that require the JRE.
 */
public class UiBinderJreSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("UiBinder tests that require the JRE");

    // rebind
    suite.addTestSuite(FieldWriterOfExistingTypeTest.class);
    suite.addTestSuite(FieldWriterOfGeneratedCssResourceTest.class);
    suite.addTestSuite(FieldWriterOfLazyDomElementTest.class);
    suite.addTestSuite(GwtResourceEntityResolverTest.class);
    suite.addTestSuite(HandlerEvaluatorTest.class);
    suite.addTestSuite(TokenatorTest.class);
    suite.addTestSuite(XMLElementTest.class);
    suite.addTestSuite(DesignTimeUtilsTest.class);
    suite.addTestSuite(TypeOracleUtilsTest.class);
    suite.addTestSuite(UiBinderParserUiWithTest.class);
    suite.addTestSuite(UiRendererEventValidationTest.class);
    suite.addTestSuite(UiRendererValidationTest.class);
    suite.addTestSuite(HtmlTemplatesTest.class);

    // model
    suite.addTestSuite(OwnerClassTest.class);
    suite.addTestSuite(OwnerFieldClassTest.class);
    suite.addTestSuite(OwnerFieldTest.class);

    // attributeparsers
    suite.addTestSuite(CssNameConverterTest.class);
    suite.addTestSuite(FieldReferenceConverterTest.class);
    suite.addTestSuite(IntAttributeParserTest.class);
    suite.addTestSuite(IntPairAttributeParserTest.class);
    suite.addTestSuite(HorizontalAlignmentConstantParserTest.class);
    suite.addTestSuite(LengthAttributeParserTest.class);
    suite.addTestSuite(SafeUriAttributeParserTest.class);
    suite.addTestSuite(StrictAttributeParserTest.class);
    suite.addTestSuite(StringAttributeParserTest.class);
    suite.addTestSuite(TextAlignConstantParserTest.class);
    suite.addTestSuite(VerticalAlignmentConstantParserTest.class);

    // elementparsers
    suite.addTestSuite(AbsolutePanelParserTest.class);
    suite.addTestSuite(DateLabelParserTest.class);
    suite.addTestSuite(DialogBoxParserTest.class);
    suite.addTestSuite(DisclosurePanelParserTest.class);
    suite.addTestSuite(DockLayoutPanelParserTest.class);
    suite.addTestSuite(GridParserTest.class);
    suite.addTestSuite(HasTreeItemsParserTest.class);
    suite.addTestSuite(ImageParserTest.class);
    suite.addTestSuite(IsEmptyParserTest.class);
    suite.addTestSuite(LayoutPanelParserTest.class);
    suite.addTestSuite(ListBoxParserTest.class);
    suite.addTestSuite(MenuBarParserTest.class);
    suite.addTestSuite(MenuItemParserTest.class);
    suite.addTestSuite(NumberLabelParserTest.class);
    suite.addTestSuite(StackLayoutPanelParserTest.class);
    suite.addTestSuite(StackPanelParserTest.class);
    suite.addTestSuite(TabLayoutPanelParserTest.class);
    suite.addTestSuite(TabPanelParserTest.class);
    suite.addTestSuite(UiChildParserTest.class);
    suite.addTestSuite(UIObjectParserTest.class);

    return suite;
  }

  private UiBinderJreSuite() {
  }
}
