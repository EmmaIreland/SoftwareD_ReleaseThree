package pages

import geb.Page

class CourseListPage extends Page { 
	static url = "course/list"
	
	static at = {
		title =~ /Courses/
	}
	
	static content = {
		newCourseButton() { $("a", text: "New Course") }
		
	}
}
