package com.spring.poc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Images")
public class Image {

	@Id
	private String id;
	private String imageName;
	private String imageType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public Image(String imageName, String imageType) {
		super();
		this.imageName = imageName;
		this.imageType = imageType;
	}

}
