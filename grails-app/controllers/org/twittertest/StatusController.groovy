package org.twittertest

import grails.plugins.springsecurity.Secured

@Secured('IS_AUTHENTICATED_FULLY')
import org.twittertest.auth.Person

class StatusController {

	def springSecurityService

	def index = {
		
		def twits = currentUserTimeLine()
		//return!!!
		[ messages: twits ]
		
	}

	def updateStatus = {

		def status = new Status(twit: params.message)
		status.author = lookupPerson()
		status.save()

		def messages = currentUserTimeLine()

		render template: 'messages', collection: messages, var: 'message'

	}
	
	def follow = {
		
		def per = Person.get(params.id)
		if(per) {
			def currentUser = lookupPerson()
			currentUser.addToFollowed(per)
			currentUser.save()
		}
		redirect action: 'index'
		
	}

	private lookupPerson() {
		Person.get(springSecurityService.principal.id)
	}

	private currentUserTimeLine() {

		def user = lookupPerson()
		def messages = Status.withCriteria {
			or {
				author {
					eq 'username', springSecurityService.principal.username
				}
				if(user.followed) {
					inList 'author', user.followed
				}
			}
			// sin paréntesis no se entiende una goma!
			maxResults 10
			order 'dateCreated', 'desc'
		}
		// return!!!!!!!!
		messages
	}
}