/*
 * Copyright 2020-2020 the ALttPJ Team @ https://github.com/alttpj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.alttpj.memeforcehunt.app.gui.actions;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public final class StaticGuiActions {

  private static final URI LINK = URI.create("https://github.com/alttpj/MemeforceHunt/releases");

  private StaticGuiActions() {
    // util class
  }

  public static void tryOpenAboutPage(final Label statusBarLabel) {
    try {
      final Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
      if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
        desktop.browse(LINK);
      }
    } catch (final IOException | UnsupportedOperationException | SecurityException urlOpenEx) {
      final Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Unable to open your browser");
      alert.setContentText("Unable to open your browser. Error message:\n[" + urlOpenEx.getMessage() + "].");
      alert.show();
      statusBarLabel.setText("Unable to open Browser: [" + urlOpenEx.getMessage() + "].");
    }
  }
}
