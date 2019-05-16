package dev.colleguesapi.entite;

import java.util.List;

public class CollegueConnect
{
	//attribut
	private String matricule;
	private String nom;
	private String prenoms;
	private List<Role> authorites;
	private String photoUrl;
	
	//constructeur
	public CollegueConnect() {}
	
	public CollegueConnect(String matricule, String nom, String prenoms,List<Role> authorites, String photoUrl)
	{
		this.matricule = matricule;
		this.nom = nom;
		this.prenoms = prenoms;
		this.authorites = authorites;
		this.photoUrl = photoUrl;
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
	
	public List<Role> getAuthorites() {
		return authorites;
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
	
	public void setAuthorites(List<Role> authorites) {
		this.authorites = authorites;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

}
