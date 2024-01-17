package com.willystw.tabunganku.configuration;

import com.willystw.tabunganku.service.LongIdGenerator;
import com.willystw.tabunganku.service.SecureLongRandomGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

  @Bean
  public LongIdGenerator longIdGenerator() {
    return new SecureLongRandomGenerator(System.currentTimeMillis());
  }
}
