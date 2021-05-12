package io.trydent.snowflake.ui;

import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.input.KeyStroke;
import io.artoo.lance.type.Tuple;

import java.util.concurrent.atomic.AtomicBoolean;

public enum Event {;

  public static record Input(
    Window window,
    KeyStroke keyStroke,
    AtomicBoolean deliver
  ) implements Tuple.Triple<Input, Window, KeyStroke, AtomicBoolean> {
    @Override
    public Class<Input> $type() {
      return Input.class;
    }
  }
}
