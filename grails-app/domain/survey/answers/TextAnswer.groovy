package survey.answers

import survey.*

class TextAnswer extends Answer {
    String response

    static constraints = {
    }
    
    String toString() {
        response
    }
}
