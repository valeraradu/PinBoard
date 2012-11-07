package org.pinboard.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
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
	
	private String keyword;
	
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

	public List<Pin> doFindPins() throws Exception {
		
		List<Pin> results = null;
		
		if (login.isLoggedIn()){
			results =  em.createQuery("select u from Pin u where u.CreatedBy=:username or u.visibility>=1")
	                .setParameter("username", login.getCurrentUser())
	                .getResultList();
        
		}else {
			results = em.createQuery("select u from Pin u where u.visibility=2")
	                .getResultList();
			}
		
		return results; 
		
	}
	
	public String doSearch() {

		List<Pin> results;

		/*
		 * //List<Pin> results = em.createQuery(
		 * "SELECT u FROM Pin u WHERE UPPER(u.message) LIKE :keyword OR UPPER(u.CreatedBy) LIKE :keyword"
		 * ) List<Pin> results = em.createQuery(
		 * "SELECT u FROM Pin u WHERE u.message LIKE :keyword OR (u.CreatedBy.username LIKE :keyword)"
		 * ) //.setParameter("keyword", "%" + keyword.toUpperCase() +
		 * "%").getResultList(); .setParameter("keyword", "%" + keyword +
		 * "%").getResultList(); pins = results;
		 */

		if (login.isLoggedIn()) {
			results = em
					.createQuery(
							"SELECT u FROM Pin u WHERE (u.message LIKE :keyword) and (u.CreatedBy=:username or u.visibility>=1)")
					.setParameter("keyword", "%" + keyword + "%")
					.setParameter("username", login.getCurrentUser())
					.getResultList();

		} else {
			results = em
					.createQuery("SELECT u FROM Pin u WHERE u.message LIKE :keyword and  u.visibility=2")
					.setParameter("keyword", "%" + keyword + "%")
					.getResultList();
		}

		pins = results;

		return "/search.xhtml";

	}

	 
	public List<Pin> getPins() {
		return pins;
	}

	public void setPins(List<Pin> pins) {
		this.pins = pins;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}