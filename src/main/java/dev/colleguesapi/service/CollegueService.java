package dev.colleguesapi.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.entite.CollegueRepository;
import dev.colleguesapi.entite.Note;
import dev.colleguesapi.exception.CollegueInvalideException;
import dev.colleguesapi.exception.CollegueNonTrouveException;

@Service
public class CollegueService
{
	//attribut
	private CollegueRepository accesBDD;

	//constructeur
	@Autowired
	public CollegueService(CollegueRepository accesBDD)
	{
		this.accesBDD = accesBDD;
		accesBDD.save(new Collegue(UUID.randomUUID().toString(),"Snape", "Severus", "ssnape@hogwart.uk", "1970-05-13", "a completer"));
		accesBDD.save(new Collegue(UUID.randomUUID().toString(),"MacGonagal", "Minerva", "mmacgonagal@hogwart.uk", "1927-07-24", "a completer"));
		accesBDD.save(new Collegue(UUID.randomUUID().toString(),"Flitwick", "Filius", "fflitwick@hogwart.uk", "1913-10-29", "a completer"));
		accesBDD.save(new Collegue(UUID.randomUUID().toString(),"Sprout", "Pomona", "psprout@hogwart.uk", "1954-08-03", "a completer"));

		accesBDD.save(new Collegue("azigueguagua", "Lovegood", "Luna", "llovegood@rookery.com","1981-02-15", 
				"https://www.thesprucepets.com/thmb/0Y_9qW07-uYqkW9_kcasnwXqCi0=/450x0/filters:no_upscale():max_bytes(150000):strip_icc()/twenty20_d4afe7d2-ebe8-4288-a2ef-bcecbb99df88-5a8c4309c064710037e9965e.jpg") );
		accesBDD.save(new Collegue("azi", "Lovegood", "Xenophilius", "xlovegood@rookery.com","1951-09-20", 
				"https://www.thesprucepets.com/thmb/0Y_9qW07-uYqkW9_kcasnwXqCi0=/450x0/filters:no_upscale():max_bytes(150000):strip_icc()/twenty20_d4afe7d2-ebe8-4288-a2ef-bcecbb99df88-5a8c4309c064710037e9965e.jpg") );
		accesBDD.save(new Collegue("guegua", "Lovegood", "Pandora", "plovegood@rookery.com","1952-01-31",  
				"https://www.thesprucepets.com/thmb/0Y_9qW07-uYqkW9_kcasnwXqCi0=/450x0/filters:no_upscale():max_bytes(150000):strip_icc()/twenty20_d4afe7d2-ebe8-4288-a2ef-bcecbb99df88-5a8c4309c064710037e9965e.jpg") );
		accesBDD.save(new Collegue("guagua", "Weasley", "Ginevra", "gweasley@burrow.com","1982-10-06", 
				"https://www.thesprucepets.com/thmb/0Y_9qW07-uYqkW9_kcasnwXqCi0=/450x0/filters:no_upscale():max_bytes(150000):strip_icc()/twenty20_d4afe7d2-ebe8-4288-a2ef-bcecbb99df88-5a8c4309c064710037e9965e.jpg") );
	}


	// - rechercher - 
	public List<Collegue> rechercherParNom(String nomRecherche) throws CollegueNonTrouveException
	{
		List<Collegue> listeColl = new ArrayList<>();

		for (Collegue pers:accesBDD.findAll())
		{
			if (pers.getNom().equals(nomRecherche))
			{
				listeColl.add(pers);
			}
		}

		if (listeColl.isEmpty())
		{throw new CollegueNonTrouveException("Il n'y a personne à ce numéro");}
		else
		{return listeColl;}
	}

	public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException
	{

		Optional<Collegue> tmp = accesBDD.findById(matriculeRecherche);
		if (tmp.isPresent())
		{return tmp.get();}
		else
		{throw new CollegueNonTrouveException("Il n'y a personne à ce numéro");}
	}



	// - ajouter - 
	public void ajouterUnCollegue(Collegue collegueAAjouter) throws CollegueInvalideException
	{

		if (verifTailleString(collegueAAjouter.getNom(),2) &&
				verifTailleString(collegueAAjouter.getPrenoms(),2) &&
				verifTailleString(collegueAAjouter.getEmail(),3) &&
				verifCharac(collegueAAjouter.getEmail(),"@") &&
				verifCharac(collegueAAjouter.getPhotoUrl().substring(0, 4),"http") &&
				verifAge(collegueAAjouter.getDateDeNaissance())
				)
		{

			collegueAAjouter.setMatricule(UUID.randomUUID().toString());

			accesBDD.save(collegueAAjouter);

		}
		else
		{throw new CollegueInvalideException("Parametre collegue invalide");}

	}

	// - verification - 
	public boolean verifTailleString(String aTester, int i) throws CollegueInvalideException
	{
		return aTester.length()>=i;
	}

	public boolean verifCharac(String aTester, String characDemander) throws CollegueInvalideException
	{	
		return aTester.contains(characDemander);
	}

	public boolean verifAge(LocalDate datNaiss) throws CollegueInvalideException
	{
		Period p = Period.between(datNaiss,LocalDate.now());
		return p.getYears()>=18;
	}

	// - modifier - 
	public Collegue modifierEmail(String matricule, String email) throws CollegueNonTrouveException, CollegueInvalideException
	{
		Collegue pers = rechercherParMatricule(matricule);//this.rechercherParMatricule(matricule);
		verifCharac(email, "@");
		verifTailleString(email, 3);

		pers.setEmail(email);
		accesBDD.save(pers);

		return pers; // vraiment necessaire?
	}

	public Collegue modifierPhotoUrl(String matricule, String photoUrl) throws CollegueInvalideException, CollegueNonTrouveException
	{
		Collegue pers = rechercherParMatricule(matricule); //this.rechercherParMatricule(matricule);
		verifCharac(photoUrl.substring(0, 5), "http");

		pers.setPhotoUrl(photoUrl);
		accesBDD.save(pers);

		return pers;  // vraiment necessaire?
	}
	
	// methode concernant les notes
		public void ajouterUneNote(String matricule, Note comment) throws CollegueNonTrouveException
		{
			Collegue pers = rechercherParMatricule(matricule);
			Set<Note> tmp = pers.getNotes();
			tmp.add(comment);
			pers.setNotes(tmp);

			accesBDD.save(pers);
		}
		
		

		/*public void supprimerUneNote(String matricule, Note comment) throws CollegueNonTrouveException
		{
			Collegue pers = this.rechercherParMatricule(matricule);
			List<Note> tmp = pers.getNotes();
			tmp.remove(comment);

			String clef = (String) getKey(getData(), pers);
			pers.setNotes(tmp);
			getData().put(clef, pers);
		}*/


}
