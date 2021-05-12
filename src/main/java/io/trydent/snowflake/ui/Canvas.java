package io.trydent.snowflake.ui;

import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.TextGUI;
import com.googlecode.lanterna.screen.Screen;
import io.artoo.lance.fetcher.Cursor;
import io.artoo.lance.query.One;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface Canvas extends One<TextGUI> {
  static Canvas textUI(final Screen screen, final Modal modal) {
    return new Text(screen, modal);
  }

  void render();
}

final class Text implements Canvas {
  private final Map<String, Element<?>> elements = new ConcurrentHashMap<>();
  private final Screen screen;
  private final Modal modal;

  Text(final Screen screen, final Modal modal) {
    this.screen = screen;
    this.modal = modal;
  }

  @Override
  public void render() {
    this
      .ofType(MultiWindowTextGUI.class)
      .eventually(text -> modal.on(this).eventually(text::addWindowAndWait));
  }

  @Override
  public Cursor<TextGUI> cursor() {
    return Cursor.open(new MultiWindowTextGUI(screen));
  }
}
