package io.manco.maxim.sbmm.repository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.manco.maxim.sbmm.domain.DomainApplication;

@Configuration
@Import(DomainApplication.class)
public class RepositoryApplication {

}
