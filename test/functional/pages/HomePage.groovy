package pages

import geb.Page

class HomePage extends Page {
    static url = ""
    
    static at = {
        title.endsWith("Cevin's Cyqulists")
    }
    
    static content = { 
		}
}