package com.willystw.tabunganku.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SecureLongRandomGeneratorTest {

  private SecureLongRandomGenerator generator = new SecureLongRandomGenerator();

  @RepeatedTest(10)
  public void testGenerateId_generate18Digit() {
    String id = generator.nextId().toString();
    assertThat(id.length(), is(equalTo(18)));
  }
}
