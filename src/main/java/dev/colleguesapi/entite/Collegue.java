package dev.colleguesapi.entite;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Collegue
{
	//attribut
	@Id
	@Column
	private String matricule;
	@Column
	private String nom;
	@Column
	private String prenoms;
	@Column
	private String email;
	@Column
	private LocalDate dateDeNaissance;
	@Column
	private String photoUrl;
	
	//@Column
	@OneToMany(mappedBy="coll")
	private Set<Note> notes;
	
	/*
	@Column
	@Enumerated(EnumType.STRING)
	private List<Role> authorites;*/
	

	//constructeur
	public Collegue() {}
	
	public Collegue(String nom, String prenoms, String email, String dateNaiss, String photoUrl)
	{
		this.nom = nom;
		this.prenoms = prenoms;
		this.email = email;
		this.dateDeNaissance = LocalDate.parse(dateNaiss);
		this.photoUrl = photoUrl;
		this.setNotes(new HashSet<Note>());
	}
	
	public Collegue(String matricule, String nom, String prenoms, String email, String dateNaiss, String photoUrl)
	{
		this(nom, prenoms, email, dateNaiss, photoUrl);
		this.matricule = matricule;
	}
	
	//Override
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDeNaissance == null) ? 0 : dateDeNaissance.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((matricule == null) ? 0 : matricule.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((photoUrl == null) ? 0 : photoUrl.hashCode());
		result = prime * result + ((prenoms == null) ? 0 : prenoms.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collegue other = (Collegue) obj;
		if (dateDeNaissance == null) {
			if (other.dateDeNaissance != null)
				return false;
		} else if (!dateDeNaissance.equals(other.dateDeNaissance))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (matricule == null) {
			if (other.matricule != null)
				return false;
		} else if (!matricule.equals(other.matricule))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (photoUrl == null) {
			if (other.photoUrl != null)
				return false;
		} else if (!photoUrl.equals(other.photoUrl))
			return false;
		if (prenoms == null) {
			if (other.prenoms != null)
				return false;
		} else if (!prenoms.equals(other.prenoms))
			return false;
		return true;
	}
	
	
	//getter
	public String getMatricule() {
		return matricule;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getPrenoms() {
		return prenoms;
	}
	
	public String getEmail() {
		return email;
	}
	
	public LocalDate getDateDeNaissance() {
		return dateDeNaissance;
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}
	
	public Set<Note> getNotes() {
		return notes;
	}
	
	//setter
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = LocalDate.parse(dateDeNaissance);
	}
	
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	public void setNotes(Set<Note> tmp) {
		this.notes = tmp;
	}

}
