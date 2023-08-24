package com.willystw.tabunganku.service;

import java.nio.ByteBuffer;
import java.security.SecureRandom;

public class SecureLongRandomGenerator implements LongIdGenerator {

  private static final Integer RANDOM_NUM_TOTAL = 5;
  private final SecureRandom secureRandom;

  public SecureLongRandomGenerator() {
    this.secureRandom = new SecureRandom();
  }

  public SecureLongRandomGenerator(long seed) {
    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    buffer.putLong(seed);

    this.secureRandom = new SecureRandom(buffer.array());
  }
  @Override
  public Long nextId() {
    StringBuilder sb = new StringBuilder();
    sb.append(System.currentTimeMillis());

    int[] randomNumberArr = secureRandom
        .ints(RANDOM_NUM_TOTAL, 0, 10).toArray();
    for (int num: randomNumberArr) {
      sb.append(num);
    }

    return Long.parseLong(sb.toString());
  }
}
