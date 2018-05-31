package webdev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import webdev.models.Lesson;
import webdev.models.Widget;
import webdev.repositories.LessonRepository;
import webdev.repositories.WidgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	WidgetRepository widgetRepository;

	@GetMapping("/api/widget")
	public List<Widget> findAllCourses() {
		return (List<Widget>) widgetRepository.findAll(); 
	}

	@GetMapping("/api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if(data.isPresent())
			return data.get();
		return null;
	}

	@GetMapping("/api/lesson/{lessonId}/widget")
	public List<Widget> findAllWidgetsForLesson(
			@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		Lesson lesson = data.get();
		return lesson.getWidgets();	
	}

	@PostMapping("/api/lesson/{lessonId}/widget")
	public Widget createWidget(
			@PathVariable("lessonId") int lessonId,
			@RequestBody Widget newWidget) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		Widget widget = null;
		if(data.isPresent()) {
			Lesson lesson = data.get();
			newWidget.setLesson(lesson);
			widget = widgetRepository.save(newWidget);
			return widget;
		}
		return null;		
	}

	@PostMapping("/api/lesson/{lessonId}/widgets")
	public List<Widget> createWidgets(
			@PathVariable("lessonId") int lessonId,
			@RequestBody List<Widget> newWidgets) {
		widgetRepository.deleteWidgetsByLessonId(lessonId);
		List<Widget> output = new ArrayList<Widget>();
		for(Widget w : newWidgets) {
			output.add(createWidget(lessonId,w));
		}
		return output;
	}


	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId)
	{	
		widgetRepository.deleteById(widgetId);
	}

	@PutMapping("/api/widget/{widgetId}")
	public Widget updateWidget(
			@PathVariable("widgetId") int widgetId,
			@RequestBody Widget newWidget) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		Widget widget = null;
		if(data.isPresent()) {
			widget = data.get();
			widget.setName(newWidget.getName());
			widget.setwidgetOrder(newWidget.getwidgetOrder());
			widget.setText(newWidget.getText());
			widget.setWidgetType(newWidget.getWidgetType());
			widget.setStyle(newWidget.getStyle());
			widget.setWidth(newWidget.getWidth());
			widget.setHeight(newWidget.getHeight());
			widget.setSize(newWidget.getSize());
			widget.setHref(newWidget.getHref());
			widget.setSrc(newWidget.getSrc());
			widget.setWidth(newWidget.getWidth());
			widget.setListType(newWidget.getListType());
			widgetRepository.save(widget);
			return widget;
		}
		return null;		
	}
}