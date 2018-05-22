package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.Course;
import webdev.models.Module;

public interface ModuleRepository extends CrudRepository<Module, Integer>{
	@Query("SELECT course FROM Module m WHERE m.id=:moduleid")
	Iterable<Course> findCourseByModelId(
		@Param("moduleid") int moduleid);
}