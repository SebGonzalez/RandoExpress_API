package dao;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import models.Admin;
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

	public Personne getPersonById(Long id) {
		try {
			return em.createQuery("Select p From Personne p Where id = :id", Personne.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Personne getPersonByMail(String mail) {
		try {
			return em.createQuery("Select p From Personne p Where mail = :mail", Personne.class)
					.setParameter("mail", mail).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Admin getAdminByMail(String mail) {
		try {
			return em.createQuery("Select a From Admin a Where mail = :mail", Admin.class)
					.setParameter("mail", mail).getSingleResult();
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
		} catch (PersistenceException e) {
			throw (EJBException) new EJBException(e).initCause(e);
		}
		return true;
	}
	
	public boolean saveAdmin(Admin a) {
		try {
			if (a.getId() == null) {
				em.persist(a);
			} else {
				em.merge(a);
			}
		} catch (PersistenceException e) {
			throw (EJBException) new EJBException(e).initCause(e);
		}
		return true;
	}
}
