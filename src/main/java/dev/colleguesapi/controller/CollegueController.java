package dev.colleguesapi.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.entite.CollegueGallerie;
import dev.colleguesapi.entite.CollegueModifie;
import dev.colleguesapi.entite.Note;
import dev.colleguesapi.exception.CollegueInvalideException;
import dev.colleguesapi.exception.CollegueNonTrouveException;
import dev.colleguesapi.service.CollegueService;

@RestController
@RequestMapping("/collegues")
@CrossOrigin
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
	@GetMapping(path = "/{matricule}")
	public Collegue trouverCollegueMatricule (@PathVariable String matricule) throws CollegueNonTrouveException
	{
		return servColl.rechercherParMatricule(matricule);
	}

	@GetMapping(path = "/gallerie")
	@ResponseBody
	public List<CollegueGallerie> retournerListePhoto ()
	{
		return servColl.retournerCollGall();
	}

	// - requete post - 
	@Secured("ROLE_ADMIN")
	@PostMapping
	public void creerCollegue(@RequestBody Collegue pers)  throws CollegueInvalideException
	{
		servColl.ajouterUnCollegue(pers);
	}

	@PostMapping(path = "/gallerie/{matricule}")
	public void creerNote(@PathVariable String matricule, @RequestBody Note comment)  throws CollegueNonTrouveException
	{
		comment.setTemps( LocalDateTime.now());
		servColl.ajouterUneNote(matricule, comment);
	}

	// - requete patch - 
	@PatchMapping(path = "/{matricule}")
	public void modiferCollegue (@PathVariable String matricule, @RequestBody CollegueModifie pers)  
			throws CollegueInvalideException, CollegueNonTrouveException
	{		
		servColl.modifierEmail(matricule, pers.getEmail());
		servColl.modifierPhotoUrl(matricule, pers.getPhotoUrl());
	}
	/*
	@RequestMapping(path = "/gallerie/{matricule}", method = RequestMethod.PATCH)
	public void enleverNote(@PathVariable String matricule, @RequestBody Note comment)  throws CollegueNonTrouveException
	{
		servColl.supprimerUneNote(matricule, comment);
	}*/

}


