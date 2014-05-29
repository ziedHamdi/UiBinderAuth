/*
 * Copyright 2008 Google Inc.
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

import com.google.gwt.user.client.ui.InlineLabel;

/**
 * To prove that a bound Ui can use a widget that is not
 * default instantiable.
 */
public class StrictLabel extends InlineLabel {
  public StrictLabel(String text) {
    super(text);
  }
}
