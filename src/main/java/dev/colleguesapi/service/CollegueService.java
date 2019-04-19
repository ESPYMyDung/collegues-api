package dev.colleguesapi.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.exception.CollegueInvalideException;
import dev.colleguesapi.exception.CollegueNonTrouveException;

@Service
public class CollegueService
{
	//attribut
	private Map<String, Collegue> data = new HashMap<>();

	//constructeur
	public CollegueService()
	{
		getData().put("pers1", new Collegue(UUID.randomUUID().toString(),"Snape", "Severus", "ssnape@hogwart.uk", "1970-05-13", "a completer") );
		getData().put("pers2", new Collegue(UUID.randomUUID().toString(),"MacGonagal", "Minerva", "mmacgonagal@hogwart.uk", "1927-07-24", "a completer") );
		getData().put("pers3", new Collegue(UUID.randomUUID().toString(),"Flitwick", "Filius", "fflitwick@hogwart.uk", "1913-10-29", "a completer") );
		getData().put("pers4", new Collegue(UUID.randomUUID().toString(),"Sprout", "Pomona", "psprout@hogwart.uk", "1954-08-03", "a completer") );
	}
	
	//methode
	public Map<String, Collegue> getData() {
		return data;
	}

	public void setData(Map<String, Collegue> data) {
		this.data = data;
	}
	
	// - rechercher - 
	public List<Collegue> rechercherParNom(String nomRecherche) throws CollegueNonTrouveException
	{
		List<Collegue> listeColl = new ArrayList<>();
		
        for (Collegue pers:getData().values())
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
	
        for (Collegue pers:getData().values())
        {
        	if (pers.getMatricule().equals(matriculeRecherche))
        	{
        		return pers;
        	}
        }
        throw new CollegueNonTrouveException("Il n'y a personne à ce numéro");
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

			getData().put(collegueAAjouter.getMatricule().substring(0,5), collegueAAjouter);
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
		Collegue pers = this.rechercherParMatricule(matricule);
		verifCharac(email, "@");
		verifTailleString(email, 3);

		String clef = (String) getKey(getData(), pers);
		pers.setEmail(email);		
		getData().put(clef, pers);
		
		return pers; // vraiment necessaire?
	}

	public Collegue modifierPhotoUrl(String matricule, String photoUrl) throws CollegueInvalideException, CollegueNonTrouveException
	{
		Collegue pers = this.rechercherParMatricule(matricule);
		verifCharac(photoUrl.substring(0, 5), "http");
		
		String clef = (String) getKey(getData(), pers);
		pers.setPhotoUrl(photoUrl);
		getData().put(clef, pers);
		
		return pers;  // vraiment necessaire?
	}
	
	public <K, V> K getKey(Map<K, V> map, V value) {
	    for (Entry<K, V> entry : map.entrySet()) {
	        if (entry.getValue().equals(value)) {
	            K key = entry.getKey();
	            return key;
	            
	        }
	    }
		return null;
	    
	}


}
