package com.shhrrtnvr.smartaquaculture.io;

import java.time.Instant;

public enum Timeframe {
  DAY, WEEK, MONTH, YEAR;

  public Instant getStartDate() {
    var now = System.currentTimeMillis();
    return switch (this) {
      case DAY -> Instant.ofEpochMilli(now - 86400000);
      case WEEK -> Instant.ofEpochMilli(now - 604800000);
      case MONTH -> Instant.ofEpochMilli(now - 2629800000L);
      case YEAR -> Instant.ofEpochMilli(now - 31557600000L);
    };
  }

  public Instant getEndDate() {
    return Instant.now();
  }
}
