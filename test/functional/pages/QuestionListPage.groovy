package pages

import geb.Page

class QuestionListPage extends Page{
    static url = "question/list"
    
    static at = {
	title =~ /Question List/
    }
    
    static content = {
	
    }

}