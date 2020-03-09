package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "rando")
public class Rando {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Basic(optional = false)
	@Column(nullable = false)
	private String name;

	@Basic(optional = false)
	@Column(nullable = false)
	private String description;

	@Basic(optional = false)
	@Column(nullable = false)
	private String ville;

	@Basic(optional = false)
	@Column(nullable = false)
	private String dateDepart;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String heureDepart;

	@Basic(optional = false)
	@Column(nullable = false)
	private String latitude;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String longitude;
	
	@ManyToOne
	private Personne owner;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Personne> persons;

	public Rando() {

	}

	public Rando(String name, String description, String ville, String dateDepart, String heureDepart, String latitude, String longitude, Personne owner) {
		super();
		this.name = name;
		this.description = description;
		this.ville = ville;
		this.dateDepart = dateDepart;
		this.heureDepart = heureDepart;
		this.latitude = latitude;
		this.longitude = longitude;
		this.owner = owner;
		this.persons = new ArrayList<Personne>();
	}

	/*public Rando(String name, String description, String ville, Date dateDepart, String latitude, String longitude,
			List<Personne> persons) {
		super();
		this.name = name;
		this.description = description;
		this.ville = ville;
		this.dateDepart = dateDepart;
		this.latitude = latitude;
		this.longitude = longitude;
		this.persons = persons;
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(String dateDepart) {
		this.dateDepart = dateDepart;
	}

	public String getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(String heureDepart) {
		this.heureDepart = heureDepart;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Personne getOwner() {
		return owner;
	}

	public void setOwner(Personne owner) {
		this.owner = owner;
	}

	public List<Personne> getPersons() {
		return persons;
	}

	public void setPersons(List<Personne> persons) {
		this.persons = persons;
	}
	
	public void addPerson(Personne p) {
		persons.add(p);
	}
	
	public void removePerson(Personne personne) {
		System.out.println("Personne : " + persons.size() + " " + persons);
		
		Iterator<Personne> itr = persons.iterator(); // remove all even numbers 
		while (itr.hasNext()) {
			Personne p = itr.next();
			if(p.getMail().equals(personne.getMail()))
				itr.remove();
		}

		System.out.println("Personne2 : " + persons.size() + " " + persons);
	}
	
	@Override
	public String toString() {
		System.out.println("ok");
		return id + " " + name + " " + description + " " + ville + " " + dateDepart + " " + longitude + " " + latitude + " " + persons;
	}
}
