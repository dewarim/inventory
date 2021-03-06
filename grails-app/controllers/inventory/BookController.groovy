package inventory

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.dao.DataIntegrityViolationException


class BookController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def index() {
        redirect(action: "list", params: params)
    }

    @Secured(["IS_AUTHENTICATED_ANONYMOUSLY"])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [bookInstanceList: Book.list(params), bookInstanceTotal: Book.count()]
    }

    @Secured(["ROLE_USER"])
    def create() {
        [bookInstance: new Book(params)]
    }

    @Secured(["ROLE_USER"])
    def save() {
        def bookInstance = new Book(title: params.title, authorName: params.authorName)
        bookInstance.tags = params.tags ?: "" // cannot put "" in constructor or it becomes null.
        bookInstance.comment = params.comment ?: ""
        
        if (!bookInstance.save(flush: true)) {
            render(view: "create", model: [bookInstance: bookInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'book.label', default: 'Book'), bookInstance.id])
        redirect(action: "create")
    }

    @Secured(["IS_AUTHENTICATED_ANONYMOUSLY"])
    def show(Long id) {
        def bookInstance = Book.get(id)
        if (!bookInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), id])
            redirect(action: "list")
            return
        }

        [bookInstance: bookInstance]
    }

    @Secured(["ROLE_USER"])
    def edit(Long id) {
        def bookInstance = Book.get(id)
        if (!bookInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), id])
            redirect(action: "list")
            return
        }

        [bookInstance: bookInstance]
    }

    @Secured(["ROLE_USER"])
    def update(Long id, Long version) {
        def bookInstance = Book.get(id)
        if (!bookInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (bookInstance.version > version) {
                bookInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'book.label', default: 'Book')] as Object[],
                          "Another user has updated this Book while you were editing")
                render(view: "edit", model: [bookInstance: bookInstance])
                return
            }
        }

        bookInstance.properties = params

        if (!bookInstance.save(flush: true)) {
            render(view: "edit", model: [bookInstance: bookInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'book.label', default: 'Book'), bookInstance.id])
        redirect(action: "show", id: bookInstance.id)
    }

    @Secured(["ROLE_ADMIN"])
    def delete(Long id) {
        def bookInstance = Book.get(id)
        if (!bookInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), id])
            redirect(action: "list")
            return
        }

        try {
            bookInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'book.label', default: 'Book'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'book.label', default: 'Book'), id])
            redirect(action: "show", id: id)
        }
    }

    @Secured(["ROLE_USER"])
    def search(String query){
        query = "%$query%" 
        def books = Book.findAllByTitleIlikeOrAuthorNameIlikeOrCommentIlike(query, query, query, [sort:'title'])
//        def books = Book.findAllByAuthorNameIlike(query)
        log.debug("found ${books.size()} books")
       [bookInstanceList: books, query:query.replaceAll('%','')]
    }
}
