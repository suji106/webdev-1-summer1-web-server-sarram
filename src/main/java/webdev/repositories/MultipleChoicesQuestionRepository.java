package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.MultipleChoiceQuestion;

public interface MultipleChoicesQuestionRepository
	extends CrudRepository<MultipleChoiceQuestion, Integer> {
	
}