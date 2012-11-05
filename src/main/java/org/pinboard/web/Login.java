package org.pinboard.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Current;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.transaction.UserTransaction;


@SessionScoped
@Named
public class Login implements Serializable {


    /*@Current
    Credentials credentials;*/
    
    @Inject
    private Credentials credentials;

    @PersistenceContext
    EntityManager em;

    @Resource
    private UserTransaction utx; 
    
    private User user;

	public String login() {
    	
        List<User> results =  em.createQuery(
           "select u from User u where u.username=:username and u.password=:password")
           .setParameter("username", credentials.getUsername())
           .setParameter("password", credentials.getPassword())
           .getResultList();

        if ( !results.isEmpty() ) {
           user = results.get(0);
        }
        
        return "main.xhtml";
    }  

    public void logout() {
        user = null;
    }    

	public String register() throws Exception {
		
		User user = new User(credentials.getUsername(),
				credentials.getPassword());
		try {
			utx.begin();
			em.persist(user);
			this.user = user;
			utx.commit();
		} catch (Exception nse) {
			throw new Exception(nse);
		}
		
		return "main.xhtml";
	}
    
    public boolean isLoggedIn() {
       return user!=null;
    }   

    @Produces
    @LoggedIn
    User getCurrentUser() {
        return user;
    }

}
