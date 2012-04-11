package pages

import geb.Page

class SurveyListPage extends Page{
    static url = "survey/list"
    
    static at = {
	title =~ /Survey List/
    }
    
    static content = {
	
    }

}