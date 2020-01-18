package rest;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.PersonneDAO;
import models.Personne;

@WebListener
public class InitializeListner implements ServletContextListener {

	@EJB
	PersonneDAO personneDAO;
	
    @Override
    public final void contextInitialized(final ServletContextEvent sce) {
    	if(personneDAO.getAllPersons().size() == 0) {
    		System.out.println("INITIALIZE BD");
    		Personne p1 = new Personne("test");
    		personneDAO.save(p1);
    	}
    }

    @Override
    public final void contextDestroyed(final ServletContextEvent sce) {

    }
}