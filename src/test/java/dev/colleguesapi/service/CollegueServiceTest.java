package dev.colleguesapi.service;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.entite.CollegueRepository;
import dev.colleguesapi.exception.CollegueInvalideException;

public class CollegueServiceTest
{
	//creation des elements du test
	private CollegueRepository testRepo;
	private CollegueService testServ;
	
	@Before //initialisation
	public void init()
	{
		testRepo = Mockito.mock(CollegueRepository.class);
		testServ = new CollegueService(testRepo); //on indique quel est le repo a utiliser
	}
	
	
	@Test
	public void testAjouterCollegue() throws CollegueInvalideException
	{
		//CollegueService test = new CollegueService();
		//int t = testRepo.findAll().size();
		
		Collegue indv = new Collegue("Sicarii", "Lycoris", "manheym@myfantasy.com", "2000-12-21", "http://myphoto.jpg");
		testServ.ajouterUnCollegue(indv);
		
		Mockito.verify(testRepo).save(indv);
		
		//Assert.assertEquals(t+1, testRepo.findAll().size());
		
	}
	
	@Test
	public void testVerifTailleString() throws CollegueInvalideException
	{
		
		String testString ="oops";
		int x = 3;

		Assert.assertTrue(testServ.verifTailleString(testString, x));
	}

	@Test
	public void testVerifCharac() throws CollegueInvalideException 
	{	
		
		String testString ="oops";
		String testChar = "p";

		Assert.assertTrue(testServ.verifCharac(testString, testChar));
	}

	@Test
	public void testVerifAge() throws CollegueInvalideException 
	{
		LocalDate testDate = LocalDate.parse("2000-01-01");
		
		Assert.assertTrue(testServ.verifAge(testDate));
		
	}
	
	/*
	@Test
	public void testModifierEmail() throws CollegueInvalideException, CollegueNonTrouveException 
	{
		//CollegueService test = new CollegueService();
		
		List<Collegue> tmp = test.rechercherParNom("Snape");
		
		test.modifierEmail(tmp.get(0).getMatricule(), "dongonbat@awesome.com");

		Assert.assertTrue( test.getMap().containsValue(tmp.get(0)) );
		
	}
	
	@Test
	public void testModifierPhotoUrl() throws CollegueNonTrouveException, CollegueInvalideException 
	{
		//CollegueService test = new CollegueService();
		
		List<Collegue> tmp = test.rechercherParNom("Snape");
		
		test.modifierPhotoUrl(tmp.get(0).getMatricule(), "http://memories.png");

		Assert.assertTrue( test.getMap().containsValue(tmp.get(0)) );
		
	}*/
	
}
