package com.spring.poc.config;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration

public class MongoDatabaseConfig {

	  @Bean
	    public MongoDbFactory mongoDbFactory() throws UnknownHostException{
	        return new SimpleMongoDbFactory(new MongoClient("localhost", 27017), "bootDb");
	    }
	  
	    @Bean
	    public MongoOperations mongoOperations() throws UnknownHostException{
	        return new MongoTemplate(mongoDbFactory());
	    }

}
