package io.manco.maxim.sbmm.web;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import io.manco.maxim.sbmm.controller.ControllerApplication;

@SpringBootApplication
@Import({ ControllerApplication.class})
public class WebApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    new SpringApplicationBuilder()
        .bannerMode(Banner.Mode.CONSOLE).sources(WebApplication.class)
        .run(args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(WebApplication.class);
  }

}
