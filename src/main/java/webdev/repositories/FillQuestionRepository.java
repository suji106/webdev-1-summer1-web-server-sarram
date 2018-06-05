package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.FillQuestion;

public interface FillQuestionRepository
	extends CrudRepository<FillQuestion, Integer> {
	
}