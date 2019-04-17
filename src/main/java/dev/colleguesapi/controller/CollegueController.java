package dev.colleguesapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.exception.CollegueNonTrouveException;
import dev.colleguesapi.service.CollegueService;

@RestController
@RequestMapping("/collegues")
public class CollegueController
{
	private CollegueService servColl = new CollegueService();

	@GetMapping
	public List<String> trouverCollegue(String nom) throws CollegueNonTrouveException
	{
		List<String> out = new ArrayList<>();
		List<Collegue> tmp;

			tmp = servColl.rechercherParNom(nom);
			for (Collegue pers:tmp)
			{ out.add(pers.getMatricule()); }
			
			return out;

    }
	
	@RequestMapping(path = "/{matricule}", method = RequestMethod.GET)
	public Collegue trouverCollegueMatricule (@PathVariable String matricule) throws CollegueNonTrouveException
	{
			Collegue tmp = servColl.rechercherParMatricule(matricule);

			return tmp;
    }
	
	
	
	
	
}


