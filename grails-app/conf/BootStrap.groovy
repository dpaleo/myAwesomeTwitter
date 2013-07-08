import org.twittertest.auth.Authority
import org.twittertest.auth.Person
import org.twittertest.auth.PersonAuthority

class BootStrap {

	def springSecurityService
	
    def init = { servletContext ->	
		if(!Person.count()) {
			create()
		}
		println "Que cantidad de usuarios! " + Person.count()
    }
	
    def destroy = {
    }
	
	private void create() {
		def userRole = new Authority(authority: 'ROLE_USER').save()
		//def password = springSecurityService.encodePassword("pass")
		def password = "pass"
		
		[nico: 'Nicolas Romero', leo: 'Leonel Reinahuel', diego: 'Diego Paleo'].each {
			userName, realName ->
			def user = new Person(username: userName, realName: realName, password: password, enabled: true).save()
			PersonAuthority.create user, userRole, true
		}
	}	
}
