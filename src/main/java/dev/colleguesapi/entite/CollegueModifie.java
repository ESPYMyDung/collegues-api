package dev.colleguesapi.entite;

public class CollegueModifie
{
	//attribut
	private String email;
	private String photoUrl;

	//constructeur
	public CollegueModifie() {}
	
	public CollegueModifie(String email, String photoUrl)
	{

		this.email = email;
		this.photoUrl = photoUrl;
	}
	
	//getter
	public String getEmail() {
		return email;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}
	
	//setter
	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
}
