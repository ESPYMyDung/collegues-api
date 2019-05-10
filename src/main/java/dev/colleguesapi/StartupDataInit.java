package dev.colleguesapi;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.entite.CollegueRepository;
import dev.colleguesapi.entite.Role;

@Component
public class StartupDataInit {

    @Autowired
    CollegueRepository collegueRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;


    // La méthode init va être invoquée au démarrage de l'application.
    @EventListener(ContextRefreshedEvent.class)
    public void init()
    {
    	collegueRepo.save(new Collegue(UUID.randomUUID().toString(),"Snape", "Severus", "ssnape@hogwart.uk", "1970-05-13", "a completer", passwordEncoder.encode("Beladone") ));
    	collegueRepo.save(new Collegue(UUID.randomUUID().toString(),"MacGonagal", "Minerva", "mmacgonagal@hogwart.uk", "1927-07-24", "a completer",passwordEncoder.encode("Gamp"), Role.ROLE_ADMIN));
    	collegueRepo.save(new Collegue(UUID.randomUUID().toString(),"Flitwick", "Filius", "fflitwick@hogwart.uk", "1913-10-29", "a completer", passwordEncoder.encode("gobblebabil") ));
    	collegueRepo.save(new Collegue(UUID.randomUUID().toString(),"Sprout", "Pomona", "psprout@hogwart.uk", "1954-08-03", "a completer", passwordEncoder.encode("mandrake") ));

    	collegueRepo.save(new Collegue("azigueguagua", "Lovegood", "Luna", "llovegood@rookery.com","1981-02-15", 
				"https://www.thesprucepets.com/thmb/0Y_9qW07-uYqkW9_kcasnwXqCi0=/450x0/filters:no_upscale():max_bytes(150000):strip_icc()/twenty20_d4afe7d2-ebe8-4288-a2ef-bcecbb99df88-5a8c4309c064710037e9965e.jpg"
    			, passwordEncoder.encode("nargles") ));
    	collegueRepo.save(new Collegue("azi", "Lovegood", "Xenophilius", "xlovegood@rookery.com","1951-09-20", 
				"https://www.thesprucepets.com/thmb/0Y_9qW07-uYqkW9_kcasnwXqCi0=/450x0/filters:no_upscale():max_bytes(150000):strip_icc()/twenty20_d4afe7d2-ebe8-4288-a2ef-bcecbb99df88-5a8c4309c064710037e9965e.jpg"
    			, passwordEncoder.encode("Hallows"), Role.ROLE_ADMIN) );
    	collegueRepo.save(new Collegue("guegua", "Lovegood", "Pandora", "plovegood@rookery.com","1952-01-31",  
				"https://www.thesprucepets.com/thmb/0Y_9qW07-uYqkW9_kcasnwXqCi0=/450x0/filters:no_upscale():max_bytes(150000):strip_icc()/twenty20_d4afe7d2-ebe8-4288-a2ef-bcecbb99df88-5a8c4309c064710037e9965e.jpg"
    			, passwordEncoder.encode("moonstruck") ));
    	collegueRepo.save(new Collegue("guagua", "Weasley", "Ginevra", "gweasley@burrow.com","1982-10-06", 
				"https://www.thesprucepets.com/thmb/0Y_9qW07-uYqkW9_kcasnwXqCi0=/450x0/filters:no_upscale():max_bytes(150000):strip_icc()/twenty20_d4afe7d2-ebe8-4288-a2ef-bcecbb99df88-5a8c4309c064710037e9965e.jpg"
    			, passwordEncoder.encode("boywholived") ));
	
    }
}