package io.trydent.snowflake.ui;

import java.time.Instant;

import static io.trydent.snowflake.ui.Bind.Any;

public sealed interface Bind<T> permits Any  {
  T value();

  sealed interface Any<T> extends Bind<T>permits Text, Numeric, Binary, Trinary, Timestamp, Array {}

  record Text(String value) implements Any<String> {}

  record Numeric(Number value) implements Any<Number> {}

  record Binary(boolean value) implements Any<Boolean> {}

  record Trinary(Boolean value) implements Any<Boolean> {}

  record Timestamp(Instant value) implements Any<Instant> {}

  record Array<T>(T[] value) implements Any<T[]> {}

}
