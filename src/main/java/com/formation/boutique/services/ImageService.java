package com.formation.boutique.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.boutique.entities.Image;
import com.formation.boutique.repositories.ImageRepository;

@Service
public class ImageService {

	private final ImageRepository imageRepository;

	@Autowired
	public ImageService(ImageRepository imageRepository) {

		this.imageRepository = imageRepository;
	}
	
	public Iterable<Image> getAll(){
		return imageRepository.findAll();
	}
	
	public Image save(Image image){
		return imageRepository.save(image);
	}

	public void delete(@Valid Image image) {
		imageRepository.deleteById(image.getId());
	}
	
}
