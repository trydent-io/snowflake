package io.trydent.snowflake.ui;

import io.artoo.lance.query.One;

public interface Template<C> {
  One<C> create();
}
