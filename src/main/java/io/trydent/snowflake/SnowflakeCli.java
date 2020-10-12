package io.trydent.snowflake;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.util.List;

import static com.googlecode.lanterna.gui2.Direction.HORIZONTAL;
import static com.googlecode.lanterna.gui2.GridLayout.Alignment.BEGINNING;
import static com.googlecode.lanterna.gui2.GridLayout.Alignment.CENTER;
import static com.googlecode.lanterna.gui2.GridLayout.Alignment.END;
import static com.googlecode.lanterna.gui2.GridLayout.createHorizontallyEndAlignedLayoutData;
import static com.googlecode.lanterna.gui2.GridLayout.createHorizontallyFilledLayoutData;
import static com.googlecode.lanterna.gui2.GridLayout.createLayoutData;
import static com.googlecode.lanterna.gui2.Window.Hint.FULL_SCREEN;
import static com.googlecode.lanterna.gui2.dialogs.MessageDialog.showMessageDialog;
import static com.googlecode.lanterna.gui2.dialogs.MessageDialogButton.OK;

public class SnowflakeCli {
  public static void main(String[] args) {
    final var terminalFactory = new DefaultTerminalFactory();
    try (final var screen = terminalFactory.createScreen()) {
      screen.startScreen();
      final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
      final Window window = new BasicWindow("My Root Window");
      window.setHints(List.of(FULL_SCREEN));
      final var contentPanel = new Panel(new GridLayout(2));

      final var gridLayout = (GridLayout) contentPanel.getLayoutManager();
      gridLayout.setHorizontalSpacing(3);

      final var title = new Label("This is a label that spans two columns");
      title.setLayoutData(
        createLayoutData(
          BEGINNING, // Horizontal alignment in the grid cell if the cell is larger than the component's preferred size
          BEGINNING, // Vertical alignment in the grid cell if the cell is larger than the component's preferred size
          true,       // Give the component extra horizontal space if available
          false,        // Give the component extra vertical space if available
          2,                  // Horizontal span
          1)
      );                  // Vertical span
      contentPanel.addComponent(title);

      contentPanel.addComponent(new Label("Text Box (aligned)"));
      contentPanel.addComponent(new TextBox().setLayoutData(createLayoutData(BEGINNING, CENTER)));

      contentPanel.addComponent(new Label("Password Box (right aligned)"));
      contentPanel.addComponent(
        new TextBox()
          .setMask('*')
          .setLayoutData(createLayoutData(END, CENTER)));

      contentPanel.addComponent(new Label("Read-only Combo Box (forced size)"));

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

      contentPanel.addComponent(readOnlyComboBox);

      contentPanel.addComponent(new Label("Editable Combo Box (filled)"));
      contentPanel.addComponent(
        new ComboBox<>("Item #1", "Item #2", "Item #3", "Item #4")
          .setReadOnly(false)
          .setLayoutData(createHorizontallyFilledLayoutData(1)));

      contentPanel.addComponent(new Label("Button (centered)"));
      contentPanel.addComponent(new Button("Button", () -> showMessageDialog(textGUI, "MessageBox", "This is a message box", OK)).setLayoutData(createLayoutData(CENTER, CENTER)));

      contentPanel.addComponent(new EmptySpace().setLayoutData(createHorizontallyFilledLayoutData(2)));
      contentPanel.addComponent(new Separator(HORIZONTAL).setLayoutData(createHorizontallyFilledLayoutData(2)));
      contentPanel.addComponent(new Button("Close", window::close).setLayoutData(createHorizontallyEndAlignedLayoutData(2)));

      window.setComponent(contentPanel);
      textGUI.addWindowAndWait(window);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
