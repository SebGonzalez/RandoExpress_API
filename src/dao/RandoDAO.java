package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Personne;
import models.Rando;

/**
 * Manager de l'application représenté par un EJB stateless. Contient nottemment
 * les méthodes CRUD des randonnées
 *
 */
@Stateless
public class RandoDAO {

	@PersistenceContext(unitName = "myData")
	EntityManager em;

	/**
	 * Retourne la liste des randonnées de la base de donnée
	 * 
	 * @return la liste des randonnées
	 */
	public List<Rando> getAllRandos() {
		return em.createQuery("Select r From Rando r", Rando.class).getResultList();
	}
	
	public void save(Rando r) {
		if(r.getId() == null) {
			em.persist(r);
		}
		else {
			em.merge(r);
		}
	}
}
