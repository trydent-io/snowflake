package io.trydent.snowflake;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Border;
import com.googlecode.lanterna.gui2.BorderLayout;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.gui2.WindowListenerAdapter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.googlecode.lanterna.gui2.BorderLayout.Location.CENTER;
import static com.googlecode.lanterna.gui2.BorderLayout.Location.LEFT;
import static com.googlecode.lanterna.gui2.BorderLayout.Location.TOP;
import static com.googlecode.lanterna.gui2.Direction.VERTICAL;
import static com.googlecode.lanterna.gui2.TextBox.Style.MULTI_LINE;
import static com.googlecode.lanterna.gui2.Window.Hint.FULL_SCREEN;
import static com.googlecode.lanterna.screen.Screen.RefreshType.AUTOMATIC;
import static com.googlecode.lanterna.screen.TabBehaviour.CONVERT_TO_TWO_SPACES;

public class SnowflakeCli {
  public static void main(String[] args) {
    final var terminalFactory = new DefaultTerminalFactory();
    try (final var screen = terminalFactory.createScreen()) {
      screen.refresh(AUTOMATIC);
      screen.startScreen();

      screen.setTabBehaviour(CONVERT_TO_TWO_SPACES);
      final WindowBasedTextGUI gui = new MultiWindowTextGUI(screen);
      final Window window = new BasicWindow("Snowflake");
      window.setHints(List.of(FULL_SCREEN));
      final var title = new Label("Size: ");

      final var text = new TextBox("", MULTI_LINE).setLayoutData(CENTER);
      text.withBorder(Borders.singleLine("Heading"));

      window.addWindowListener(new WindowListenerAdapter() {
        @Override
        public void onInput(final Window basePane, final KeyStroke keyStroke, final AtomicBoolean deliverEvent) {
          final var textWidth = text.getSize().getColumns();
          final var textX = text.getPosition().getColumn() + 1;
          final var textY = text.getPosition().getRow();
          final var position = screen.getCursorPosition();
          final var cursorX = position.getColumn();
          title.setText(
            String.format(
              "Columns: %d, cursor at: %d, position: %d",
              textWidth,
              cursorX,
              textY
            )
          );

          if (cursorX >= (textX + textWidth) - 1) {
            text.addLine("");
            text.setCaretPosition(text.getCaretPosition().getRow() + 1, textY + 1);
          }
        }
      });

      window.setComponent(
        new Panel()
          .setLayoutManager(new BorderLayout())
          .addComponent(
            new Panel(new LinearLayout(VERTICAL))
              .setLayoutData(TOP)
              .addComponent(title)
              .addComponent(new EmptySpace())
          )
          .addComponent(
            new Panel()
              .setLayoutManager(new BorderLayout())
              .addComponent(
                new Panel(new LinearLayout(VERTICAL))
                  .setLayoutData(LEFT)
                  .addComponent(new Label("Sono a sinistra"))
                  .addComponent(new Button("Close", window::close))
              )
              .addComponent(text)
          )
      );
      gui.addWindowAndWait(window);
      screen.setCharacter(10, 10, new TextCharacter('Æ', TextColor.ANSI.MAGENTA, TextColor.ANSI.GREEN));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
