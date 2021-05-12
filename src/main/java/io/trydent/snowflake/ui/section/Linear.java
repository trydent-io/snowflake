package io.trydent.snowflake.ui.section;

import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import io.artoo.lance.fetcher.Cursor;
import io.trydent.snowflake.ui.Attribute;
import io.trydent.snowflake.ui.Element;
import io.trydent.snowflake.ui.Section;

public final class Linear implements Section {
  private final Direction direction;
  private final Attribute<?> attribute;

  public Linear(final Direction direction, final Attribute<?> attribute) {
    this.direction = direction;
    this.attribute = attribute;
  }

  @Override
  public Cursor<Panel> cursor() {
    final var panel = new Panel(new LinearLayout(direction));

    if (attribute instanceof Attribute.contents contents && contents.value() instanceof Element<?>[] elements) {
      for (final var element : elements) {
        element.ofType(Component.class).eventually(panel::addComponent);
      }
    }

    return Cursor.open(panel);
  }
}
