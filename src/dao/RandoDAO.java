package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
	
	public Rando getRandoById(Long id) {
		try {
			return em.createQuery("Select r From Rando r Where id = :id", Rando.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Rando> getRandosByVille(String ville) {
		try {
			return em.createQuery("Select r From Rando r Where ville = :ville", Rando.class).setParameter("ville", ville)
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Rando> getRandosPersonne(Personne p) {
		try {
			return em.createQuery("Select r From Rando r Where :p MEMBER OF r.persons", Rando.class).setParameter("p", p).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void remove(Long id) {
		Rando r = getRandoById(id);
		if(r != null)
			em.remove(r);
	}
	
	public void save(Rando r) {
		if(r.getId() == null) {
			em.persist(r);
		}
		else {
			em.merge(r);
		}
	}
	
	public void inscriptionRando(Rando rando, Personne personne) {
		rando.addPerson(personne);
		save(rando);
	}
	
	public void desinscriptionRando(Rando rando, Personne personne) {
		rando.removePerson(personne);
		save(rando);
	}
}
