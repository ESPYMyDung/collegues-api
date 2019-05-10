package dev.colleguesapi.entite;

public class CollegueGallerie
{
	//attribut
	private String matricule;
	private String photoUrl;

	//constructeur
	public CollegueGallerie() {}

	public CollegueGallerie(String matricule, String photoUrl)
	{

		this.matricule = matricule;
		this.photoUrl = photoUrl;
	}

	//getter
	public String getMatricule() {
		return matricule;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	//setter
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

}
