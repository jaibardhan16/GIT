package com.spring.poc.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration

public class H2DatabaseConfig {

	@Bean
	public DataSource dataSource() {

		// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		// .H2 .DERBY HSQL
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).addScript("create-db.sql")
				.addScript("insert-data.sql").setName("h2localDb").build();
		return db;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	//Db Console
	//http://localhost:8080/SpringBoot/console/login.jsp?jsessionid=ed10fd2ec9024875e3b3a6c3b2f4f83f
	@Bean
	ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;

	}

	@PostConstruct
	public void startDBManager() {

		// hsqldb
		// DatabaseManagerSwing.main(new String[] { "--url",
		// "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" });

		// derby
		// DatabaseManagerSwing.main(new String[] { "--url",
		// "jdbc:derby:memory:testdb", "--user", "", "--password", "" });

		// h2
		// DatabaseManagerSwing.main(new String[] { "--url",
		// "jdbc:h2:mem:testdb", "--user", "sa", "--password", "" });

	}

}
