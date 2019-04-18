package dev.colleguesapi.exception;

@SuppressWarnings("serial")
public class CollegueInvalideException extends Exception
{
	public CollegueInvalideException() {}

	public CollegueInvalideException(String msg)
	{
		super(msg);
	}
}
