package io.trydent.snowflake.ui;

import io.artoo.lance.query.One;
import io.trydent.snowflake.ui.Bind.Any;
import io.trydent.snowflake.ui.Bind.Binary;
import io.trydent.snowflake.ui.Bind.Numeric;
import io.trydent.snowflake.ui.Bind.Text;
import io.trydent.snowflake.ui.Bind.Timestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.time.ZoneOffset.UTC;

@FunctionalInterface
public interface Attribute<O> {
  O value();

  default One<Any<O>> hook(Any<?> binding) {
    return One.of(value())
      .select(value -> {

        if (value instanceof String eval)
          return new Text(eval);
        if (value instanceof Number eval)
          return new Numeric(eval);
        if (value instanceof Boolean eval)
          return new Binary(eval);
        if (value instanceof Instant eval)
          return new Timestamp(eval);
        if (value instanceof LocalDateTime eval)
          return new Timestamp(eval.toInstant(UTC));
        if (value instanceof LocalDate eval)
          return new Timestamp(eval.atStartOfDay(UTC).toInstant());
        if (value instanceof LocalTime eval)
          return new Timestamp(eval.atDate(LocalDate.now()).toInstant(UTC));

        return null;
      }).select(it -> (Any<O>) it);

  }

  interface any extends Attribute<Object> {
    static any val(Object value) { return () -> value; }
  }

  interface title extends Attribute<String> {
    static title val(String value) { return () -> value; }
  }

  interface visible extends Attribute<Boolean> {
    static visible val(Boolean value) { return () -> value; }
  }

  interface content extends Attribute<Element<?>> {
    static content val(Element<?> value) { return () -> value; }
  }

  interface contents extends Attribute<Element<?>[]> {
    static contents val(Element<?>[] value) { return () -> value; }
  }

  interface text extends Attribute<String> {
    static text val(String value) { return () -> value; }
  }

  interface value extends Attribute<String> {
    static value val(String value) { return () -> value; }
  }

  interface color extends Attribute<String> {
    static color val(String value) { return () -> value; }
  }

  interface top extends Attribute<Element<?>> {
    static top val(Element<?> value) { return () -> value; }
  }

  interface bottom extends Attribute<Element<?>> {
    static bottom val(Element<?> value) { return () -> value; }
  }

  interface left extends Attribute<Element<?>> {
    static left val(Element<?> value) { return () -> value; }
  }

  interface right extends Attribute<Element<?>> {
    static right val(Element<?> value) { return () -> value; }
  }

  interface center extends Attribute<Element<?>> {
    static center val(Element<?> value) { return () -> value; }
  }
}
