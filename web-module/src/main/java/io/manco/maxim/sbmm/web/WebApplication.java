package io.manco.maxim.sbmm.web;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import io.manco.maxim.sbmm.config.ConfigApplication;
import io.manco.maxim.sbmm.controller.ControllerApplication;
import io.manco.maxim.sbmm.domain.DomainApplication;
import io.manco.maxim.sbmm.repository.RepositoryApplication;
import io.manco.maxim.sbmm.service.ServiceApplication;

@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    new SpringApplicationBuilder()
        .bannerMode(Banner.Mode.CONSOLE).sources(DomainApplication.class, ControllerApplication.class,
            ServiceApplication.class, RepositoryApplication.class, WebApplication.class, ConfigApplication.class)
        .run(args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(WebApplication.class);
  }

}
