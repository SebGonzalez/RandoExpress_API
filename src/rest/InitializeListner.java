package rest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.PersonneDAO;
import dao.RandoDAO;
import models.Personne;
import models.Rando;

@WebListener
public class InitializeListner implements ServletContextListener {

	@EJB
	PersonneDAO personneDAO;
	
	@EJB
	RandoDAO randoDAO;
	
    @Override
    public final void contextInitialized(final ServletContextEvent sce) {
    	if(personneDAO.getAllPersons().size() == 0) {
    		System.out.println("INITIALIZE BD");
    		
    		Personne p1 = new Personne("Lamblino", "Sébastien", "lamblino@hotmail.fr", "azerty");
    		personneDAO.save(p1);
    		Personne p2 = new Personne("Vadym", "Vadym", "vadym@android.fr", "azerty");
    		personneDAO.save(p2);
    		
    		Rando r1 = new Rando("Calanque Luminy", "Magnifique randonné dans les calanques de Marseille", "Marseille", "20/02/2020", "16:15", "43.232230", "5.435990", p1);
    		r1.addPerson(p2);
    		randoDAO.save(r1);
    		
    		Rando r2 = new Rando("Randonné cool", "Magnifique randonné dans Marseille", "Marseille", "20/03/2020", "08:30", "43.288593", "5.370514", p1);
    		randoDAO.save(r2);
    		
    		
    	}
    }

    @Override
    public final void contextDestroyed(final ServletContextEvent sce) {

    }
}