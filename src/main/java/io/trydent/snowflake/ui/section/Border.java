package io.trydent.snowflake.ui.section;

import com.googlecode.lanterna.gui2.BorderLayout;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Panel;
import io.artoo.lance.fetcher.Cursor;
import io.trydent.snowflake.ui.Attribute;
import io.trydent.snowflake.ui.Element;
import io.trydent.snowflake.ui.Section;

public final class Border implements Section {
  private final Attribute<?>[] attributes;

  public Border(final Attribute<?>[] attributes) {
    this.attributes = attributes;
  }

  @Override
  public Cursor<Panel> cursor() {
    final var panel = new Panel(new BorderLayout());

    for (final var attribute : attributes) {
      if (attribute instanceof Attribute.top top && top.value() instanceof Element<?> element) { mm
        element.ofType(Component.class).eventually(component -> component.setLayoutData(BorderLayout.Location.TOP));

      } else if (attribute instanceof Attribute.bottom bottom && bottom.value() instanceof Element<?> element) {
        element.ofType(Component.class).eventually(component -> component.setLayoutData(BorderLayout.Location.BOTTOM));

      } else if (attribute instanceof Attribute.left left && left.value() instanceof Element<?> element) {
        element.ofType(Component.class).eventually(component -> component.setLayoutData(BorderLayout.Location.LEFT));

      } else if (attribute instanceof Attribute.right right && right.value() instanceof Element<?> element) {
        element.ofType(Component.class).eventually(component -> component.setLayoutData(BorderLayout.Location.RIGHT));

      } else if (attribute instanceof Attribute.center center && center.value() instanceof Element<?> element) {
        element.ofType(Component.class).eventually(component -> component.setLayoutData(BorderLayout.Location.CENTER));
      }
    }

    return Cursor.open(panel);
  }
}
