package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.Widget;

public interface WidgetRepository
extends CrudRepository<Widget, Integer>{
	@Query("SELECT widgetType FROM Widget w WHERE w.id=:id")
	String checkIfExam(
		@Param("id") int id);
}
