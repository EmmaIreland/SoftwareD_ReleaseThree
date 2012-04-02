package survey.answers

import survey.*

class CheckboxAnswer extends Answer {
    Map responses = [:]
    static hasMany = [responses: boolean]

    static constraints = {
    }
}