package org.pinboard.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class DatabaseProducer {

    @Produces
    @PersistenceContext(unitName = "PinBoardPU")
    private EntityManager em; 
}
