package io.trydent.snowflake;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import io.trydent.snowflake.ui.Canvas;
import io.trydent.snowflake.ui.Label;
import io.trydent.snowflake.ui.Modal;
import io.trydent.snowflake.ui.Section;
import io.trydent.snowflake.ui.text.Text;

import java.io.IOException;

import static com.googlecode.lanterna.screen.Screen.RefreshType.AUTOMATIC;
import static com.googlecode.lanterna.screen.TabBehaviour.CONVERT_TO_TWO_SPACES;
import static io.trydent.snowflake.ui.Attribute.*;
import static io.trydent.snowflake.ui.Attribute.title;

public class SnowflakeCli {
  public static void main(String[] args) {
    final var terminal = new DefaultTerminalFactory();
    try (final var screen = terminal.createScreen()) {
      screen.refresh(AUTOMATIC);
      screen.startScreen();

      screen.setTabBehaviour(CONVERT_TO_TWO_SPACES);

      Canvas.textUI(
        screen,
        Modal.fullscreen(
          title.val("Snowflake"),
          content.val(
            Section.borderLayout(
              top.val(
                Label.basic("Ciao a tutti")
              ),
              center.val(
                Text.area()
              )
            )
          )
        )
      );
/*
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
                  .addComponent(new Button("Close", () -> {
                    try {
                      Files.writeString(
                        Paths.get(
                          System.getProperty("user.home"),
                          UUID.randomUUID().toString() +
                            ".txt"
                        ),
                        textArea.ofType(TextBox.class).yield().getText()
                      );
                    } catch (IOException e) {
                      e.printStackTrace();
                    } finally {
                      window.close();
                    }
                  }))
              )
              .addComponent(textArea.yield())
          )
      );
      gui.addWindowAndWait(window);*/

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
