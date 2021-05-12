package io.trydent.snowflake.ui;

import io.trydent.snowflake.ui.label.Basic;

public interface Label extends Element<com.googlecode.lanterna.gui2.Label> {
  static Label basic(final String text) {
    return new Basic(text);
  }

}
