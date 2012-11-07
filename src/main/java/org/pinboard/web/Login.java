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
<<<<<<< OURS
    Credentials credentials;*/  
    
    @Inject
    private Credentials credentials; 
=======
    Credentials credentials;*/
    
    @Inject
    private Credentials credentials;
>>>>>>> THEIRS

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

<<<<<<< OURS
<<<<<<< OURS
		if ("".equals(credentials.getUsername())) {
			message = new FacesMessage("You need to give a user");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "login.xhtml";
		}

		@SuppressWarnings("unchecked")
		List<User> results = em
				.createQuery(
						"select u from User u where u.username=:username and u.password=:password")
				.setParameter("username", credentials.getUsername())
				.setParameter("password", credentials.getPassword())
				.getResultList();

		if (!results.isEmpty()) {
			user = results.get(0);
		} else {

			message = new FacesMessage("Invalid email/password ");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "login.xhtml";

		}

		return "main.xhtml";
	}

	public void logout() {
		user = null;
	}
=======
    public void logout() {
        user = null;
    }    
>>>>>>> THEIRS
=======
    public void logout() {
        user = null;
    }    
>>>>>>> THEIRS

	public String register() throws Exception {
		
		User user = new User(credentials.getUsername(),
				credentials.getPassword());
		try {
			utx.begin();
			em.persist(user);
			this.user = user;
			utx.commit();
<<<<<<< OURS
<<<<<<< OURS
		} catch (javax.transaction.RollbackException nse) {
			FacesMessage message;
			message = new FacesMessage("User already exists, try again and be creative ;)");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "login.xhtml";

=======
		} catch (Exception nse) {
			throw new Exception(nse);
>>>>>>> THEIRS
=======
		} catch (Exception nse) {
			throw new Exception(nse);
>>>>>>> THEIRS
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
