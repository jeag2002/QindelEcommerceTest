package com.qindel.configuration;

import jakarta.annotation.PostConstruct;
import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;


/** LocaleConfig.*/
@Configuration
@Slf4j
public class LocaleConfig {

  @PostConstruct
  public void init() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    log.info("[LOCALE CONFIG] SET TIME TO UTC");
  }
}