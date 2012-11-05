package org.pinboard.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@Named
@SessionScoped
public class PinManager implements Serializable{

	
	@Inject
    PinEditor pinEditor;
	
	@Inject
	Login login;

	@PersistenceContext
	EntityManager em;

	@Resource
	private UserTransaction utx;
	
	private List<Pin> pins;

	public String saveNewPin() throws Exception {

		try {
			utx.begin();
			em.persist(pinEditor.createNewPin());
			utx.commit();
		} catch (Exception nse) {
			throw new Exception(nse);
		}
		
		return "main.xhtml";

	}

	public List<Pin> getPins() throws Exception {
		
		List<Pin> results = null;
		
		if (!login.isLoggedIn()){
        results =  em.createQuery("select u from Pin u where u.visibility=2")
                .getResultList();
        
		}else {
	        results =  em.createQuery("select u from Pin u where u.CreatedBy=:username or u.visibility>=1")
	                .setParameter("username", login.getCurrentUser())
	                .getResultList();
			}
	
             return results;
	}

}