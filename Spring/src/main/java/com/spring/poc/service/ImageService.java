package com.spring.poc.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.poc.dao.ImageRepository;
import com.spring.poc.model.Image;

@Service("imageService")
public class ImageService {
 
    @Autowired
    private ImageRepository imageRepository;
 
    public Image findById(String id) {
        return imageRepository.findOne(id);
    }
 
    public List<Image> getList() {
        return imageRepository.findAll();
    }
 
    public Image insert(Image image) {
        //Make sure id is null, so mongo will generate an id, if it is not null, then mongo will use whatever you set it to, even an empty string
        image.setId(null);
        return imageRepository.save(image);
    }
 
    public Image update(Image image) {
        image.setImageType("bmp");
        return imageRepository.save(image);
    }
 
    public void delete(String id) {
        imageRepository.delete(id);
    }
}
