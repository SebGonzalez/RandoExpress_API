package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.exception.ConstraintViolationException;

import models.Personne;
import models.Rando;

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

	public Personne getPersonById(Long id) {
		try {
			return em.createQuery("Select p From Personne p Where id = :id", Personne.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Personne getPersonByUuid(String uuid) {
		try {
			return em.createQuery("Select p From Personne p Where uuid = :uuid", Personne.class)
					.setParameter("uuid", uuid).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void remove(Long id) {
		Personne p = getPersonById(id);
		if(p != null)
			em.remove(p);
	}
	
	public boolean save(Personne p) {
		try {
			if (p.getId() == null) {
				em.persist(p);
			} else {
				em.merge(p);
			}
		} catch (ConstraintViolationException e) {
			return false;
		}
		return true;
	}
}
