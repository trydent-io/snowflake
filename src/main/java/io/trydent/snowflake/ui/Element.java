package io.trydent.snowflake.ui;

import io.artoo.lance.query.One;

import java.util.Objects;

public interface Element<C> extends One<C> {
  record Id(String value) {
    public Id {assert value != null;}

    public static One<Id> of(final String value) {
      return One.of(value)
        .where(Objects::nonNull)
        .select(Id::new);
    }
  }
}
