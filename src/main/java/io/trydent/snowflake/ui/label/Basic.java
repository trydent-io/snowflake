package io.trydent.snowflake.ui.label;

import io.artoo.lance.fetcher.Cursor;
import io.trydent.snowflake.ui.Label;

public final class Basic implements Label {
  private final String text;

  public Basic(final String text) {this.text = text;}

  @Override
  public Cursor<com.googlecode.lanterna.gui2.Label> cursor() {
    return Cursor.open(new com.googlecode.lanterna.gui2.Label(text));
  }
}
