package io.trydent.snowflake.ui.text;

import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import io.artoo.lance.fetcher.Cursor;
import io.trydent.snowflake.ui.Element;
import io.trydent.snowflake.ui.textarea.Listenable;

import static com.googlecode.lanterna.gui2.TextBox.Style.MULTI_LINE;

public interface Text extends Element<TextBox> {
  static Text area(Window window) {
    return new Area(window);
  }

  static Text input() {
    return new Input();
  }
}

final class Area implements Text {
  private final Window window;

  Area(final Window window) {this.window = window;}

  @Override
  public Cursor<TextBox> cursor() {
    final var text = new TextBox("", MULTI_LINE);

    window.addWindowListener((Listenable) (pane, stroke, deliverable) -> {
      final var textWidth = text.getSize().getColumns();
      final var textX = text.getPosition().getColumn();
      final var textY = text.getPosition().getRow();
      final var position = window.getCursorPosition();
      final var cursorX = position.getColumn();

      if (cursorX >= textX + textWidth) {
        text.addLine("");
        text.setCaretPosition(text.getCaretPosition().getRow() + 1, textY + 1);
      }
    });

    return Cursor.open(text);
  }
}

final class Input implements Text {
  @Override
  public Cursor<TextBox> cursor() {
    return Cursor.open(new TextBox());
  }
}

