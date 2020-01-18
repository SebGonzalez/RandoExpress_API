package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Personne;

/**
 * Manager de l'application représenté par un EJB stateless. Contient nottemment
 * les méthodes CRUD des personnes
 *
 */
@Stateless
public class PersonneDAO {

	@PersistenceContext(unitName = "myData")
	EntityManager em;

	/**
	 * Retourne la liste des personnes de la base de donnée
	 * 
	 * @return la liste des personnes
	 */
	public List<Personne> getAllPersons() {
		return em.createQuery("Select p From Personne p", Personne.class).getResultList();
	}

	public void save(Personne p) {
		if(p.getId() == null) {
			em.persist(p);
		}
		else {
			em.merge(p);
		}
	}
}
