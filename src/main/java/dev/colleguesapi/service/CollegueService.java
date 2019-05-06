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
import dev.colleguesapi.entite.CollegueGallerie;
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
		
		getData().put("pers5", new Collegue("azigueguagua", "Lovegood", "Luna", "llovegood@rookery.com","1981-02-15", 
		        "https://www.thesprucepets.com/thmb/0Y_9qW07-uYqkW9_kcasnwXqCi0=/450x0/filters:no_upscale():max_bytes(150000):strip_icc()/twenty20_d4afe7d2-ebe8-4288-a2ef-bcecbb99df88-5a8c4309c064710037e9965e.jpg")
			     );
		getData().put("pers6", new Collegue("azi", "Lovegood", "Xenophilius", "xlovegood@rookery.com","1951-09-20", 
		        "https://www.thesprucepets.com/thmb/0Y_9qW07-uYqkW9_kcasnwXqCi0=/450x0/filters:no_upscale():max_bytes(150000):strip_icc()/twenty20_d4afe7d2-ebe8-4288-a2ef-bcecbb99df88-5a8c4309c064710037e9965e.jpg")
			     );
		getData().put("pers7", new Collegue("guegua", "Lovegood", "Pandora", "plovegood@rookery.com","1952-01-31",  
		        "https://www.thesprucepets.com/thmb/0Y_9qW07-uYqkW9_kcasnwXqCi0=/450x0/filters:no_upscale():max_bytes(150000):strip_icc()/twenty20_d4afe7d2-ebe8-4288-a2ef-bcecbb99df88-5a8c4309c064710037e9965e.jpg")
			     );
		getData().put("pers8", new Collegue("guagua", "Weasley", "Ginevra", "gweasley@burrow.com","1982-10-06", 
		        "https://www.thesprucepets.com/thmb/0Y_9qW07-uYqkW9_kcasnwXqCi0=/450x0/filters:no_upscale():max_bytes(150000):strip_icc()/twenty20_d4afe7d2-ebe8-4288-a2ef-bcecbb99df88-5a8c4309c064710037e9965e.jpg")
				 );
	}
	
	//methode
	public Map<String, Collegue> getData() {
		return data;
	}
	
	public List<CollegueGallerie> retournerCollGall()
	{
		List<CollegueGallerie> listePhoto = new ArrayList<>();
		for (Collegue pers:getData().values() )
		{
			listePhoto.add(new CollegueGallerie(pers.getMatricule(), pers.getPhotoUrl() ) );
		}
			
		return listePhoto;
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
	public void modifierEmail(String matricule, String email) throws CollegueNonTrouveException, CollegueInvalideException
	{
		Collegue pers = rechercherParMatricule(matricule);
		verifCharac(email, "@");
		verifTailleString(email, 3);

		String clef = (String) getKey(getData(), pers);
		pers.setEmail(email);		
		getData().put(clef, pers);

	}

	public void modifierPhotoUrl(String matricule, String photoUrl) throws CollegueInvalideException, CollegueNonTrouveException
	{
		Collegue pers = this.rechercherParMatricule(matricule);
		verifCharac(photoUrl.substring(0, 5), "http");
		
		String clef = (String) getKey(getData(), pers);
		pers.setPhotoUrl(photoUrl);
		getData().put(clef, pers);

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
