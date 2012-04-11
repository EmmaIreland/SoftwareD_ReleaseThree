package pages

import geb.Page

class CourseShowPage extends Page {
    static url = "course/show"
    
    static at = {
	title =~ /Show Course/
    }
    
    static content = {
	
    }

}
