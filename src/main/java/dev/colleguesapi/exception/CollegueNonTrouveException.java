package dev.colleguesapi.exception;

@SuppressWarnings("serial")
public class CollegueNonTrouveException extends Exception
{
	public CollegueNonTrouveException() {}

	public CollegueNonTrouveException(String msg)
	{
		super(msg);
	}
}
