package io.manco.maxim.sbmm.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.manco.maxim.sbmm.domain.DomainApplication;
import io.manco.maxim.sbmm.repository.RepositoryApplication;

@Configuration
@Import({RepositoryApplication.class, DomainApplication.class})
public class ServiceApplication {

}
