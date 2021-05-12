package io.trydent.snowflake.ui;

import com.googlecode.lanterna.gui2.Panel;
import io.trydent.snowflake.ui.section.Border;
import io.trydent.snowflake.ui.section.Linear;

import static com.googlecode.lanterna.gui2.Direction.HORIZONTAL;
import static com.googlecode.lanterna.gui2.Direction.VERTICAL;

public interface Section extends Element<Panel> {
  static Section borderLayout(Attribute<?>... attributes) {
    return new Border(attributes);
  }

  static Section vertical(Attribute<?>... attributes) {
    return new Linear(VERTICAL, attributes[0]);
  }

  static Section horizontal(Attribute<?>... attributes) {
    return new Linear(HORIZONTAL, attributes[0]);
  }
}

