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
        
        def results = ttl.collapsibleDiv([title:'title', collapsed:'true'], {""})
        println results.class
        assertEquals results, ttl.out
    }
    void testIfNullBlank() {
        TrinketsTagLib ttl = new TrinketsTagLib()
        def results = ttl.ifNullBlank('thing')
        assertEquals results, 'thing'
        results = ttl.ifNullBlank(null)
        assertEquals results, ''
       
    }
}
