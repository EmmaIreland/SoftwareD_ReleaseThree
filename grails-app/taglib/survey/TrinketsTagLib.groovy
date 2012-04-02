package survey

class TrinketsTagLib {
    static namespace = "trinkets"
    
    /**
    * Renders an div with (+) button and text
    *
    * @attr id
    * @attr name
    * @attr title
    * @attr onclick
    * @attr width REQUIRED width of div (css)
    */
   def addButton = { attrs, body ->
       def id = ifNullBlank( attrs['id'] )
       def name = ifNullBlank( attrs['name'] )
       def title = ifNullBlank( attrs['title'] )
       def onclick = ifNullBlank( attrs['onclick'] )
       
       def divTagStart = '<div class="addButton" id=\'' + id + '\' name=\'' + name + '\' onclick=\'' + onclick + '\' title=\'' + title + '\' style=\'width: ' + attrs['width'] + '\'>'
       def buttonImg = '<img alt="add icon" src="' + resource(dir:'images', file:'add.png') + '"//>'
       out << divTagStart << buttonImg << '<span>' << body() << '</span>' << '</div>'
   }
   
   def ifNullBlank(attribute) {
       (attribute) ? attribute : ''
   }
}
