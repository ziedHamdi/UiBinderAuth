/*
 * Copyright 2011 Google Inc.
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
package fr.onevu.gwt.uibinder.test.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

/**
 * Test that UiBinder picks the correct template file for inner classes.
 */
public class InnerWidgetTest extends GWTTestCase {
	static class InnerWidget extends Composite {
		interface Binder extends UiBinder<Label, InnerWidget> {
		}

		static final Binder binder = GWT.create(Binder.class);

		@UiField
		Label greeting;

		InnerWidget() {
			initWidget(binder.createAndBindUi(this));
		}
	}

	@Override
	public String getModuleName() {
		return "fr.onevu.gwt.uibinder.test.UiBinderSuite";
	}

	public void testHappy() {
		InnerWidget widget = new InnerWidget();
		assertNotNull(widget.greeting);
	}
}