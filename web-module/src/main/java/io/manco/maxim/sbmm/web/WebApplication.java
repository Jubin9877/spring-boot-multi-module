package io.manco.maxim.sbmm.web;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "io.manco.maxim.sbmm.repository")
@EntityScan(basePackages = "io.manco.maxim.sbmm.domain")
@ComponentScan("io.manco.maxim.sbmm.*")
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
