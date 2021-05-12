package io.trydent.snowflake.ui;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Window;
import io.artoo.lance.fetcher.Cursor;
import io.artoo.lance.query.One;

import java.util.List;

import static com.googlecode.lanterna.gui2.Window.Hint.FULL_SCREEN;
import static io.trydent.snowflake.ui.Attribute.content;
import static io.trydent.snowflake.ui.Attribute.title;
import static io.trydent.snowflake.ui.Attribute.visible;

public interface Modal extends Element<Window> {
  static Modal fullscreen(Attribute<?>... attributes) {
    return new Fullscreen(attributes);
  }
}

final class Fullscreen implements Modal {
  private final Attribute<?>[] attributes;

  public Fullscreen(final Attribute<?>[] attributes) {
    this.attributes = attributes;
  }

  @Override
  public Cursor<Window> cursor() {
    final var window = new BasicWindow();

    for (final var attribute : attributes) {
      if (attribute instanceof title title) window.setTitle(title.value());
      if (attribute instanceof visible visible) window.setVisible(visible.value());
      if (attribute instanceof content content && content.value() instanceof Component component) window.setComponent(component);
    }
    window.setHints(List.of(FULL_SCREEN));
    return Cursor.open(window);
  }

  @Override
  public One<Window> on(final Canvas canvas) {
    return null;
  }
}

