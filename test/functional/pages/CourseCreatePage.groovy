package pages

import geb.Page

class CourseCreatePage extends Page {
	static url = "course/create"
	
	static at = {
	    title =~ /Create Course/
	}
	
	static content = {
	    courseAbbreviationBox() { $('input', name: 'abbreviation') }
	    courseNameBox() { $('input', name: 'name') }
	    courseCreateButton(to: CourseShowPage) { $('input', value:'Create') }
	    
	}
    
}
