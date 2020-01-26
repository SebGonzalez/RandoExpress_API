package models;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Basic(optional = false)
	@Column(nullable = false)
	private String name;
	@Basic(optional = false)
	@Column(nullable = false)
	private String firstName;
	@Basic(optional = false)
	@Column(nullable = false, unique=true)
	@JsonIgnore
	private String uuid;
	
	public Personne() {
		
	}

	public Personne(String name, String firstName, String uuid) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.uuid = uuid;
	}
	
	public Personne(String name, String firstName, String uuid, List<Rando> randos) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.uuid = uuid;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	@Override
	public String toString() {
		return id + " " + name + " " + firstName + " " + uuid;
	}
	
}
