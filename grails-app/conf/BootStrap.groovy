import inventory.Authority
import inventory.Person
import inventory.PersonAuthority

class BootStrap {

    def grailsApplication
    
    def init = { servletContext ->
        
        if (Person.list().size() > 0){
            log.debug("already initialized")
            return
        }
        log.debug("*** create person/authority")
        def author = new Person(username: grailsApplication.config.firstUserName, password: grailsApplication.config.firstUserPassword, enabled: true)
        author.save()
        def userRole = new Authority(authority: 'ROLE_USER')
        userRole.save()
        def adminRole = new Authority(authority: 'ROLE_ADMIN')
        adminRole.save()
        def authorUser = new PersonAuthority(authority: userRole, person: author)
        authorUser.save()
        def authorAdmin = new PersonAuthority(authority: adminRole, person: author)
        authorAdmin.save()
        
    }
    def destroy = {
        
    }
}
