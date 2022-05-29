package com.zerozawa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@SpringBootApplication
public class GatewayApp implements WebFluxConfigurer{

  public static void main(String[] args) {
    SpringApplication.run(GatewayApp.class, args);
  }

}
