package dev.colleguesapi.service;

import java.time.LocalDate;

import org.junit.Assert;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.exception.CollegueInvalideException;

public class CollegueControllerTest
{
	public void testAjouterCollegue() throws CollegueInvalideException
	{
		CollegueService test = new CollegueService();
		int t = test.getMap().size();
		
		Collegue indv = new Collegue("Sicarii", "Lycoris", "manheym@myfantasy.com", "2000-12-21", "http://myphoto.jpg");
		test.ajouterUnCollegue(indv);
		
		Assert.assertEquals(t+1, test.getMap().size());
		
	}
	
	
	public void testVerifTailleString() throws CollegueInvalideException
	{
		String testString ="oops";
		int x = 3;

		Assert.assertTrue(CollegueService.verifTailleString(testString, x));
	}

	public void testVerifCharac() throws CollegueInvalideException 
	{	
		String testString ="oops";
		String testChar = "p";

		Assert.assertTrue(CollegueService.verifCharac(testString, testChar));
	}

	public void testVerifAge() throws CollegueInvalideException 
	{
		LocalDate testDate = LocalDate.parse("2000-01-01");
		
		Assert.assertTrue(CollegueService.verifAge(testDate));
		
	}
	
}
