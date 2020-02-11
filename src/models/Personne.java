package models;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private String mail;
	@Basic(optional = false)
	private String password;
	
	
	public Personne() {
		
	}

	public Personne(String name, String firstName, String mail, String password) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.mail = mail;
		this.password = password;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return id + " " + name + " " + firstName + " " + mail ;
	}
	
}
