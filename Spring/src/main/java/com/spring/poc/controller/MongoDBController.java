package com.spring.poc.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.poc.config.MongoDatabaseConfig;
import com.spring.poc.model.User;

@Controller
public class MongoDBController {

	@PostMapping(value = "/dbOps")
	public String dbOps(Model model) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDatabaseConfig.class);
		MongoOperations mongoTemplate = (MongoOperations) ctx.getBean("mongoOperations");

		insert(mongoTemplate);

		saveUpdate(mongoTemplate);

		((AnnotationConfigApplicationContext) ctx).close();

		model.addAttribute("msg", "DB Operation insert save sucessfull.");
		return "sucess";
	}

	private void saveUpdate(MongoOperations mongoTemplate) {
		User user = new User("Jim", "*****");
		mongoTemplate.save(user, "user");

		user = mongoTemplate.findOne(Query.query(Criteria.where("username").is("Jack")), User.class);
		user.setUsername("Jim");
		mongoTemplate.save(user, "user");
	}

	private void insert(MongoOperations mongoTemplate) {
		User user = new User("Michel", "*****");
		mongoTemplate.insert(user, "user");
	}

	@RequestMapping(value = "/mongo", method = RequestMethod.POST)
	public String db(Model model) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDatabaseConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoOperations");

		save(mongoOperation);

		Query searchUserQuery = find(mongoOperation);

		update(mongoOperation, searchUserQuery);

		List<User> listUser = delete(mongoOperation, searchUserQuery);
		System.out.println("4. Number of user = " + listUser.size());
		((AnnotationConfigApplicationContext) ctx).close();
		model.addAttribute("msg", "DB Operation insert save sucessfull.");
		return "sucess";
	}

	private void update(MongoOperations mongoOperation, Query searchUserQuery) {
		// update password
		mongoOperation.updateFirst(searchUserQuery, Update.update("password", "new password"), User.class);

		// find the updated user object
		User updatedUser = mongoOperation.findOne(searchUserQuery, User.class);

		System.out.println("3. updatedUser : " + updatedUser);
	}

	private List<User> delete(MongoOperations mongoOperation, Query searchUserQuery) {
		// delete
		mongoOperation.remove(searchUserQuery, User.class);

		// List, it should be empty now.
		List<User> listUser = mongoOperation.findAll(User.class);
		return listUser;
	}

	private Query find(MongoOperations mongoOperation) {
		// query to search user
		Query searchUserQuery = new Query(Criteria.where("username").is("MongoDb"));

		// find the saved user again.
		User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
		System.out.println("2. find - savedUser : " + savedUser);
		return searchUserQuery;
	}

	private void save(MongoOperations mongoOperation) {
		// save
		User user = new User("MongoDb", "password123");
		mongoOperation.save(user);

		// now user object got the created id.
		System.out.println("1. user : " + user);
	}

}