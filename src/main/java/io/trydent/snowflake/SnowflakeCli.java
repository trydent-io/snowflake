package io.trydent.snowflake;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.BorderLayout;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.util.List;

import static com.googlecode.lanterna.gui2.BorderLayout.Location.CENTER;
import static com.googlecode.lanterna.gui2.BorderLayout.Location.LEFT;
import static com.googlecode.lanterna.gui2.Direction.HORIZONTAL;
import static com.googlecode.lanterna.gui2.Direction.VERTICAL;
import static com.googlecode.lanterna.gui2.TextBox.Style.MULTI_LINE;
import static com.googlecode.lanterna.gui2.Window.Hint.FULL_SCREEN;
import static com.googlecode.lanterna.gui2.dialogs.MessageDialog.showMessageDialog;
import static com.googlecode.lanterna.gui2.dialogs.MessageDialogButton.OK;

public class SnowflakeCli {
  public static void main(String[] args) {
    final var terminalFactory = new DefaultTerminalFactory();
    try (final var screen = terminalFactory.createScreen()) {
      screen.startScreen();
      final WindowBasedTextGUI gui = new MultiWindowTextGUI(screen);
      final Window window = new BasicWindow(" * Snowflake * ");
      window.setHints(List.of(FULL_SCREEN));
      final var borderPanel = new Panel(new BorderLayout());
      final var linearPanel = new Panel()
        .setLayoutManager(new LinearLayout(VERTICAL).setSpacing(1))
        .setLayoutData(CENTER);

      final var title = new Label("This is a label on the left side");
      title.setLayoutData(LEFT);

      borderPanel.addComponent(title);
      borderPanel.addComponent(linearPanel);

      linearPanel.addComponent(new TextBox(screen.getTerminalSize(), MULTI_LINE)
        .setCaretWarp(true)
        .setVerticalFocusSwitching(false)
      );

      linearPanel.addComponent(new Label("Read-only Combo Box (forced size)"));

      final var readOnlyComboBox = new ComboBox<>(LanternaThemes.getRegisteredThemes());
      readOnlyComboBox.setReadOnly(true);
      readOnlyComboBox.setPreferredSize(new TerminalSize(20, 1));

      readOnlyComboBox.addListener((next, prev) ->
        window.setTheme(
          LanternaThemes.getRegisteredTheme(
            readOnlyComboBox.getItem(next)
          )
        )
      );

      linearPanel.addComponent(readOnlyComboBox);

      linearPanel.addComponent(new Button("Button", () -> showMessageDialog(gui, "MessageBox", "This is a message box", OK)));

      linearPanel.addComponent(new EmptySpace());
      linearPanel.addComponent(new Separator(HORIZONTAL));
      linearPanel.addComponent(new Button("Close", window::close));

      window.setComponent(borderPanel);
      gui.addWindowAndWait(window);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
