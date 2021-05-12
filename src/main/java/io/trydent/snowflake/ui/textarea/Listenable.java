package io.trydent.snowflake.ui.textarea;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.input.KeyStroke;
import io.trydent.snowflake.ui.text.Text;

import java.util.concurrent.atomic.AtomicBoolean;

public interface Listenable extends WindowListener {
  @Override
  default void onResized(Window window, TerminalSize oldSize, TerminalSize newSize) {}

  @Override
  default void onMoved(Window window, TerminalPosition oldPosition, TerminalPosition newPosition) {}

  @Override
  default void onUnhandledInput(Window basePane, KeyStroke keyStroke, AtomicBoolean hasBeenHandled) {}
}
