package org.pinboard.web;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DocumentEditor {



    Document document;

    @LoggedIn
    User currentUser;

    @PersistenceContext EntityManager docDatabase;

    

    public void save() {

        document.setCreatedBy(currentUser);

        docDatabase.persist(document);

    }

    

}