package com.spring.poc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;
import com.spring.poc.model.Tree;

public class NatureRepositoryImpl implements Repository<Tree> {

	@Autowired
	MongoTemplate mongoOperations;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoOperations = mongoTemplate;
	}

	/**
	 * Get all trees.
	 */
	public List<Tree> getAllObjects() {
		return mongoOperations.findAll(Tree.class);
	}

	/**
	 * Saves a {@link Tree}.
	 */
	public void saveObject(Tree tree) {
		mongoOperations.insert(tree);
	}

	/**
	 * Gets a {@link Tree} for a particular id.
	 */
	public Tree getObject(String id) {
		return mongoOperations.findOne(new Query(Criteria.where("id").is(id)), Tree.class);
	}

	/**
	 * Updates a {@link Tree} name for a particular id.
	 */
	public WriteResult updateObject(String id, String name) {
		return mongoOperations.updateFirst(new Query(Criteria.where("id").is(id)), Update.update("name", name),
				Tree.class);
	}

	/**
	 * Delete a {@link Tree} for a particular id.
	 */
	public void deleteObject(String id) {
		mongoOperations.remove(new Query(Criteria.where("id").is(id)), Tree.class);
	}

	/**
	 * Create a {@link Tree} collection if the collection does not already
	 * exists
	 */
	public void createCollection() {
		if (!mongoOperations.collectionExists(Tree.class)) {
			mongoOperations.createCollection(Tree.class);
		}
	}

	/**
	 * Drops the {@link Tree} collection if the collection does already exists
	 */
	public void dropCollection() {
		if (mongoOperations.collectionExists(Tree.class)) {
			mongoOperations.dropCollection(Tree.class);
		}
	}
}