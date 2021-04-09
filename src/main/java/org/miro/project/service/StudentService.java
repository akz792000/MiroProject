package org.miro.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.miro.project.domain.StudentEntity;
import org.miro.project.repository.StudentRepository;

@Service
public class StudentService {

	// @Autowired annotation provides the automatic dependency injection.
	@Autowired
	StudentRepository repository;

	// Save student entity in the h2 database.
	public void save(final StudentEntity studentEntity) {
		repository.save(studentEntity);
	}

	// Get all students from the h2 database.
	public List<StudentEntity> getAll() {
		final List<StudentEntity> studentEntities = new ArrayList<>();
		repository.findAll().forEach(studentEntity -> studentEntities.add(studentEntity));
		return studentEntities;
	}
}
