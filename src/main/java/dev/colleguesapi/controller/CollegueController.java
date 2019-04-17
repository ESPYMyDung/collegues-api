package dev.colleguesapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.service.CollegueService;

@RestController
@RequestMapping("/collegues")
public class CollegueController
{
	private CollegueService servColl = new CollegueService();
	

	@GetMapping
	public List<String> trouverCollegue(String nom)
	{
		List<String> out = new ArrayList<>();
		List<Collegue> tmp = servColl.rechercherParNom(nom);
		
		for (Collegue pers:tmp)
		{ out.add(pers.getMatricule()); }
		
		return out;
    }
}
