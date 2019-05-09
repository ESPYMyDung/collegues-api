package dev.colleguesapi.entite;

public enum Role
{
	ROLE_USER("user"),
	ROLE_ADMIN("admin");
	
	private String status;
	
	private Role(String status)
	{
		this.setStatus(status);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
