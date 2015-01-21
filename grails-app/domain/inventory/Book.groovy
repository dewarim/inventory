package inventory

class Book {

    static constraints = {
        title size: 1..255, blank: false
        authorName size: 1..255, blank: false
        tags size: 0..4000, blank: true
        comment size: 0..4000, blank: true
        
    }
    
    String title
    String authorName
    String comment
    String tags
    
}
