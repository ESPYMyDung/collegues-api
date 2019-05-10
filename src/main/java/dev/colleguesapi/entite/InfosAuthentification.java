package dev.colleguesapi.entite;

public class InfosAuthentification
{
	// attribut
	private String matriculeColl;
	private String motDePasse;

	//getter
	public String getMatriculeColl() {
		return matriculeColl;
	}
	
	public String getMotDePasse() {
		return motDePasse;
	}

	//setter
	public void setMatriculeColl(String matriculeColl) {
		this.matriculeColl = matriculeColl;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

}
