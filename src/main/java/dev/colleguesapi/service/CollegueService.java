package dev.colleguesapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.ResponseBody;

import dev.colleguesapi.entite.Collegue;

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
	@ResponseBody
	public List<Collegue> rechercherParNom(String nomRecherche)
	{
		List<Collegue> listeColl = new ArrayList<>();
		
        for (Collegue pers:data.values())
        {
        	if (pers.getNom().equals(nomRecherche))
        	{
        		listeColl.add(pers);
        	}
        }
        return listeColl;
    }
}
