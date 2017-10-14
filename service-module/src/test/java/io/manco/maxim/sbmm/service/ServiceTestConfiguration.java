package io.manco.maxim.sbmm.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.manco.maxim.sbmm.repository.AccountRepository;
import io.manco.maxim.sbmm.repository.BarRepository;
import io.manco.maxim.sbmm.repository.StockRepository;
import io.manco.maxim.sbmm.repository.UserRoleRepository;
import io.manco.maxim.sbmm.repository.WatchListRepository;
import io.manco.maxim.sbmm.service.stub.AccountRepositoryStub;
import io.manco.maxim.sbmm.service.stub.BarRepositoryStub;
import io.manco.maxim.sbmm.service.stub.StockRepositoryStub;
import io.manco.maxim.sbmm.service.stub.UserRepoStub;
import io.manco.maxim.sbmm.service.stub.WatchListDescRepositoryStub;

@Configuration
@ComponentScan(basePackages = "io.manco.maxim.sbmm.service")
public class ServiceTestConfiguration {

	@Bean
	public WatchListRepository watchListRepo() {
		return new WatchListDescRepositoryStub();
	}

	@Bean
	public AccountRepository accountRepo() {
		return new AccountRepositoryStub();
	}

	@Bean
	public UserRoleRepository userRepo() {
		return new UserRepoStub();
	}

	@Bean
	public BarRepository barRepo() {
		return new BarRepositoryStub();
	}

	@Bean
	public StockRepository stockRepository() {
		return new StockRepositoryStub();
	}

}
