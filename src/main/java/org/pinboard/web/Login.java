package org.pinboard.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Current;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@SessionScoped
@Named
public class Login implements Serializable {


    /*@Current
    Credentials credentials;*/
    
    @Inject
    private Credentials credentials;

    @PersistenceContext
    EntityManager em;

    private User user;

	public void login() {
    	
        List<User> results =  em.createQuery(
           "select u from User u where u.username=:username and u.password=:password")
           .setParameter("username", credentials.getUsername())
           .setParameter("password", credentials.getPassword())
           .getResultList();

        if ( !results.isEmpty() ) {
           user = results.get(0);
        }
    }  

    public void logout() {
        user = null;
    }    

    public boolean isLoggedIn() {
       return user!=null;
    }   

    @Produces
    @LoggedIn User getCurrentUser() {
        return user;
    }

}
