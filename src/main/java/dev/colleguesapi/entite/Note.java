package dev.colleguesapi.entite;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Note
{
	//attribut
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String message;
	@Column
	private LocalDateTime temps;

	@ManyToOne
	@JoinColumn(name="mat_coll")
	private Collegue coll;

	//constructeur
	public Note() {}

	//ne sert a rien car n'est pas utiliser par les requete http
	public Note(String message)
	{
		this.message = message;
		//this.setId((int)(Math.random() * 1000));
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
	
	public Collegue getColl() {
		return coll;
	}

	

	//setter
	/*public void setId(int id) {
		this.id = id;
	}*/

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTemps(LocalDateTime temps) {
		this.temps = temps;
	}
	
	public void setColl(Collegue coll) {
		this.coll = coll;
	}

}