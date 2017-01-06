package com.spring.poc.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.poc.model.Image;

public interface ImageRepository extends MongoRepository<Image, String> {

	Image findOneById(String id);

	List<Image> findAll();

	void delete(Image b);

	void deleteAll();
}
