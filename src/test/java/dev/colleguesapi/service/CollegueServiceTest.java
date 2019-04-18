package dev.colleguesapi.service;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.exception.CollegueInvalideException;
import dev.colleguesapi.exception.CollegueNonTrouveException;

public class CollegueServiceTest
{
	@Test
	public void testAjouterCollegue() throws CollegueInvalideException
	{
		CollegueService test = new CollegueService();
		int t = test.getMap().size();
		
		Collegue indv = new Collegue("Sicarii", "Lycoris", "manheym@myfantasy.com", "2000-12-21", "http://myphoto.jpg");
		test.ajouterUnCollegue(indv);
		
		Assert.assertEquals(t+1, test.getMap().size());
		
	}
	
	@Test
	public void testVerifTailleString() throws CollegueInvalideException
	{
		CollegueService test = new CollegueService();
		
		String testString ="oops";
		int x = 3;

		Assert.assertTrue(test.verifTailleString(testString, x));
	}

	@Test
	public void testVerifCharac() throws CollegueInvalideException 
	{	
		CollegueService test = new CollegueService();
		
		String testString ="oops";
		String testChar = "p";

		Assert.assertTrue(test.verifCharac(testString, testChar));
	}

	@Test
	public void testVerifAge() throws CollegueInvalideException 
	{
		
		CollegueService test = new CollegueService();
		LocalDate testDate = LocalDate.parse("2000-01-01");
		
		Assert.assertTrue(test.verifAge(testDate));
		
	}
	
	
	@Test
	public void testModifierEmail() throws CollegueInvalideException, CollegueNonTrouveException 
	{
		CollegueService test = new CollegueService();
		
		List<Collegue> tmp = test.rechercherParNom("Snape");
		
		test.modifierEmail(tmp.get(0).getMatricule(), "dongonbat@awesome.com");

		Assert.assertTrue( test.getMap().containsValue(tmp.get(0)) );
		
	}
	
	@Test
	public void testModifierPhotoUrl() throws CollegueNonTrouveException, CollegueInvalideException 
	{
		CollegueService test = new CollegueService();
		
		List<Collegue> tmp = test.rechercherParNom("Snape");
		
		test.modifierPhotoUrl(tmp.get(0).getMatricule(), "http://memories.png");

		Assert.assertTrue( test.getMap().containsValue(tmp.get(0)) );
		
	}
	
}
