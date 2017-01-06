package com.spring.poc.component;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.spring.poc.dao.ImageRepository;
import com.spring.poc.dao.ProductRepository;
import com.spring.poc.model.Image;
import com.spring.poc.model.Product;

@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

	private Logger LOGGER = Logger.getLogger(ProductLoader.class);
	private ProductRepository productRepository;

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	public void setImageRepository(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		loadProduct();

		loadImage();
	}

	private void loadImage() {
		Image image1 = new Image("Office1", "jpeg");
		Image image2 = new Image("Office2", "jpeg");
		Image image3 = new Image("Office3", "jpeg");
		imageRepository.insert(image1);
		imageRepository.insert(image2);
		imageRepository.insert(image3);
		LOGGER.info("Total Image saved in Mongo DB.." + imageRepository.findAll().size());
	}

	private void loadProduct() {
		Product shirt = new Product();
		shirt.setDescription("Spring Framework Guru Shirt");
		shirt.setPrice(new BigDecimal("18.95"));
		shirt.setImageUrl(
				"https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
		shirt.setProductId("235268845711068308");
		productRepository.save(shirt);

		LOGGER.info("Saved Shirt - id: " + shirt.getId());

		Product mug = new Product();
		mug.setDescription("Spring Framework Guru Mug");
		mug.setImageUrl(
				"https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
		mug.setProductId("168639393495335947");
		productRepository.save(mug);

		LOGGER.info("Saved Mug - id:" + mug.getId());
	}
}