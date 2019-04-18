package dev.colleguesapi.entite;

import java.time.LocalDate;

public class Collegue
{
	//attribut
	private String matricule;
	private String nom;
	private String prenoms;
	private String email;
	private LocalDate dateDeNaissance;
	private String photoUrl;
	
	//constructeur
	public Collegue() {}
	
	public Collegue(String nom, String prenoms, String email, String dateNaiss, String photoUrl)
	{
		this.nom = nom;
		this.prenoms = prenoms;
		this.email = email;
		this.dateDeNaissance = LocalDate.parse(dateNaiss);
		this.photoUrl = photoUrl;
	}
	
	public Collegue(String matricule, String nom, String prenoms, String email, String dateNaiss, String photoUrl)
	{
		this(nom, prenoms, email, dateNaiss, photoUrl);
		this.matricule = matricule;
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

}
