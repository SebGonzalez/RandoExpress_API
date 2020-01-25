package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
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
	@Temporal(TemporalType.DATE)
	private Date dateDepart;

	@Basic(optional = false)
	@Column(nullable = false)
	private String latitude;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String longitude;

	@ManyToMany(mappedBy = "randos")
	private List<Personne> persons;

	public Rando() {

	}

	public Rando(String name, String description, String ville, Date dateDepart, String latitude, String longitude) {
		super();
		this.name = name;
		this.description = description;
		this.ville = ville;
		this.dateDepart = dateDepart;
		this.latitude = latitude;
		this.longitude = longitude;
		this.persons = new ArrayList<Personne>();
	}

	public Rando(String name, String description, String ville, Date dateDepart, String latitude, String longitude,
			List<Personne> persons) {
		super();
		this.name = name;
		this.description = description;
		this.ville = ville;
		this.dateDepart = dateDepart;
		this.latitude = latitude;
		this.longitude = longitude;
		this.persons = persons;
	}

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

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
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

	public List<Personne> getPersons() {
		return persons;
	}

	public void setPersons(List<Personne> persons) {
		this.persons = persons;
	}

}
