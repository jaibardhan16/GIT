package com.spring.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.poc.model.Image;
import com.spring.poc.service.ImageService;

@Controller
public class ImageController {

	@Autowired
	ImageService imageService;

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public void createImage() throws Exception {
		Image image = new Image("OfficeImage", "jpeg");
		Image image2 = new Image("OfficeImage2", "gif");
		Image image3 = new Image("OfficeImage3", "bmp");
		try {

			imageService.insert(image);
			imageService.insert(image2);
			imageService.insert(image3);

			List<Image> listUser = imageService.getList();
			System.out.println(listUser.size());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

}