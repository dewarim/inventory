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


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", comment='" + comment + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof Book)) return false

        Book book = (Book) o

        if (authorName != book.authorName) return false
        if (comment != book.comment) return false
        if (id != book.id) return false
        if (tags != book.tags) return false
        if (title != book.title) return false

        return true
    }

    int hashCode() {
        int result
        result = title.hashCode()
        result = 31 * result + authorName.hashCode()
        return result
    }
}
