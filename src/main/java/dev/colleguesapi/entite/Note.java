package dev.colleguesapi.entite;

import java.time.LocalDateTime;

public class Note
{
	//attribut
	private int id;
	private String message;
	private LocalDateTime temps;
	
	//constructeur
	public Note() {}
	
	//ne sert a rien car n'est pas utiliser par les requete http
	public Note(String message)
	{
		this.message = message;
		this.setId((int)(Math.random() * 1000));
		this.setTemps( LocalDateTime.now());
	}

	//getter
	public int getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getTemps() {
		return temps;
	}

	//setter
	public void setId(int id) {
		this.id = id;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setTemps(LocalDateTime temps) {
		this.temps = temps;
	}
	

}
