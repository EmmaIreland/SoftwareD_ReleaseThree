package pages

import geb.Page

class HomePage extends Page {
    static url = ""
    
    static at = {
        title.endsWith("Cevin's Cyqulists")
    }
    
    static content = { 
		coursesButton() { $("a", text: "Courses") }
		projectsButton() { $("a", text: "Projects") }
		peopleButton() { $("a", text: "People") }
		groupsButton() { $("a", text: "Groups") }
		surveysButton() { $("a", text: "Surveys")}
		questionsButton() { $("a", text: "Questions")}
		
    }
}