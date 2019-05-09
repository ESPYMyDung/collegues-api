package dev.colleguesapi.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.entite.CollegueModifie;
import dev.colleguesapi.entite.Note;
import dev.colleguesapi.exception.CollegueInvalideException;
import dev.colleguesapi.exception.CollegueNonTrouveException;
import dev.colleguesapi.service.CollegueService;

@RestController
@RequestMapping("/collegues")
public class CollegueController
{
	@Autowired
	private CollegueService servColl;

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
	
	// - requete get - 
	@RequestMapping(path = "/{matricule}", method = RequestMethod.GET)
	public Collegue trouverCollegueMatricule (@PathVariable String matricule) throws CollegueNonTrouveException
	{
			Collegue tmp = servColl.rechercherParMatricule(matricule);

			return tmp;
    }
	
	// - requete post - 
	@RequestMapping(method = RequestMethod.POST)
	public void creerCollegue(@RequestBody Collegue pers)  throws CollegueInvalideException
	{
		servColl.ajouterUnCollegue(pers);
    }
	
	@RequestMapping(path = "/gallerie/{matricule}", method = RequestMethod.POST)
	public void creerNote(@PathVariable String matricule, @RequestBody Note comment)  throws CollegueNonTrouveException //@RequestBody Note
	{
		comment.setTemps( LocalDateTime.now());
		//comment.setId((int)(Math.random() * 1000));
		servColl.ajouterUneNote(matricule, comment);
    }
	
	// - requete patch - 
	@RequestMapping(path = "/{matricule}", method = RequestMethod.PATCH)
	public void modiferCollegue (@PathVariable String matricule, @RequestBody CollegueModifie pers)  
			throws CollegueInvalideException, CollegueNonTrouveException
	{		
		servColl.modifierEmail(matricule, pers.getEmail());
		servColl.modifierPhotoUrl(matricule, pers.getPhotoUrl());
    }
	
}


