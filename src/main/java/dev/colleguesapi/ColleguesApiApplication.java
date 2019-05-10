package dev.colleguesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ColleguesApiApplication
{
	
	/* daje fait dans le startupinit
	@Autowired
    private CollegueRepository collegueRepository;

    @EventListener(ContextRefreshedEvent.class)
    public void init() {

        // insertion de 2 utilisateurs en base de données
        //utilisateurRepository.save(new Utilisateur("u1", "pass1", Arrays.asList("ROLE_ADMIN", "ROLE_USER")));
        //utilisateurRepository.save(new Utilisateur("u2",  "pass2", Arrays.asList("ROLE_USER")));

    }*/

    // -- le main est lààà!! --
	public static void main(String[] args) {
		SpringApplication.run(ColleguesApiApplication.class, args);
	}

}
