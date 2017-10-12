package io.manco.maxim.sbmm.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.manco.maxim.sbmm.domain.DomainApplication;
import io.manco.maxim.sbmm.service.ServiceApplication;

@Configuration
@Import({ServiceApplication.class, DomainApplication.class})
public class ControllerApplication {

}
