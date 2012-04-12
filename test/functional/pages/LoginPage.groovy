package pages

import geb.Page

class LoginPage extends Page {
    static url = "person/login"
    
    static at = {
        title =~ /Login/
    }
    
    static content = {
        loginEmailField() { $('input', name: 'email') }
        loginPasswordField() { $('input', name: 'password') }
        loginButton() { $('input', value:'Login') } // No 'to:' b/c it varies based on login success/failure
    }
}