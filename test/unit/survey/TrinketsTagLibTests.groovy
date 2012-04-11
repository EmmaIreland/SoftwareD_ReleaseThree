package survey

import grails.test.*

class TrinketsTagLibTests extends TagLibUnitTestCase {
    StringWriter out
    protected void setUp() {
        super.setUp()
        out = new StringWriter()
        TrinketsTagLib.metaClass.out = out
        
    }

    protected void tearDown() {
        super.tearDown()
        def remove = GroovySystem.metaClassRegistry.&removeMetaClass
        remove TrinketsTagLib
    }

    void testCollapsibleDiv() {
        TrinketsTagLib ttl = new TrinketsTagLib()
        
        ttl.collapsibleDiv([title:'title', collapsed:'true'], {""})
        //RegEx below matches the proper format of the div tag that should be printed.
        def expected = /<div.*><div.*>.*<\/div><div.*\/><\/div><\/div>/
        assertTrue out.toString().matches(expected)
    }
    
    void testIfNullBlank() {
        TrinketsTagLib ttl = new TrinketsTagLib()
        def results = ttl.ifNullBlank('thing')
        assertEquals results, 'thing'
        results = ttl.ifNullBlank(null)
        assertEquals results, ''
       
    }
    
    void testEmptyButtonsBar() {
        TrinketsTagLib ttl = new TrinketsTagLib()
        
        def results = ttl.emptyButtonsBar([:], {""})
        assertEquals results, ttl.out
    }
}
