package dev.colleguesapi.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.exception.CollegueInvalideException;
import dev.colleguesapi.exception.CollegueNonTrouveException;

public class CollegueService
{
	//attribut
	private Map<String, Collegue> data = new HashMap<>();

	//constructeur
	public CollegueService()
	{
		data.put("pers1", new Collegue(UUID.randomUUID().toString(),"Snape", "Severus", "ssnape@hogwart.uk", "1970-05-13", "a completer") );
		data.put("pers2", new Collegue(UUID.randomUUID().toString(),"MacGonagal", "Minerva", "mmacgonagal@hogwart.uk", "1927-07-24", "a completer") );
		data.put("pers3", new Collegue(UUID.randomUUID().toString(),"Flitwick", "Filius", "fflitwick@hogwart.uk", "1913-10-29", "a completer") );
		data.put("pers4", new Collegue(UUID.randomUUID().toString(),"Sprout", "Pomona", "psprout@hogwart.uk", "1954-08-03", "a completer") );
	}
	
	//methode
	public List<Collegue> rechercherParNom(String nomRecherche) throws CollegueNonTrouveException
	{
		List<Collegue> listeColl = new ArrayList<>();
		
        for (Collegue pers:data.values())
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
	
        for (Collegue pers:data.values())
        {
        	if (pers.getMatricule().equals(matriculeRecherche))
        	{
        		return pers;
        	}
        }
        throw new CollegueNonTrouveException("Il n'y a personne à ce numéro");
    }
	
	public void ajouterUnCollegue(Collegue collegueAAjouter) throws CollegueInvalideException
	{
		
		if (CollegueService.verifTailleString(collegueAAjouter.getNom(),2) &&
				CollegueService.verifTailleString(collegueAAjouter.getPrenoms(),2) &&
				CollegueService.verifTailleString(collegueAAjouter.getEmail(),3) &&
				CollegueService.verifCharac(collegueAAjouter.getEmail(),"@") &&
				CollegueService.verifCharac(collegueAAjouter.getPhotoUrl().substring(0, 4),"http") &&
				CollegueService.verifAge(collegueAAjouter.getDateDeNaissance())
				)
		{

			collegueAAjouter.setMatricule(UUID.randomUUID().toString());

			data.put(collegueAAjouter.getMatricule().substring(0,5), collegueAAjouter);
		}
		else
		{throw new CollegueInvalideException("Parametre collegue invalide");}

    }
	
	public Map<String, Collegue> getMap()
	{return data;}
	
	
	//verification
	public static boolean verifTailleString(String aTester, int i) throws CollegueInvalideException
	{
		return aTester.length()>=i;
	}

	public static boolean verifCharac(String aTester, String characDemander) throws CollegueInvalideException
	{	
		return aTester.contains(characDemander);
	}

	public static boolean verifAge(LocalDate datNaiss) throws CollegueInvalideException
	{
		Period p = Period.between(datNaiss,LocalDate.now());
		return p.getYears()>=18;
	}


}
