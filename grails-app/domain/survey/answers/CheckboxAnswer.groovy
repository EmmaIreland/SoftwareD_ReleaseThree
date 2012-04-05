package survey.answers

import survey.*

class CheckboxAnswer extends Answer {
    Map responses = [:]
    static hasMany = [responses: boolean]

    static constraints = {
    }
    
    String toString() {
        def toReturn = "Checkbox: "
        responses.each { key, value ->
            if (value) {
                toReturn += question.choices[key] + " "
            }
        }
        toReturn
    }
}