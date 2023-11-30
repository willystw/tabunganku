package com.willystw.tabunganku;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@OpenAPIDefinition(
	servers = {
			@Server(url = "/", description = "Default Server URL")
	},
	info = @Info(
			title = "Tabunganku",
			description = "An application to track daily spending.",
			version = "0.1.0"
	)
)
@SpringBootApplication(exclude={UserDetailsServiceAutoConfiguration.class})
@EnableTransactionManagement
public class TabungankuApplication {

	public static void main(String[] args) {
		SpringApplication.run(TabungankuApplication.class, args);
	}

}
