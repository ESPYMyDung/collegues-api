package dev.colleguesapi.entite;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CollegueRepository extends JpaRepository<Collegue, String>
{
	// findBy + "nom colomne bdd"
	Optional<Collegue> findBynom(String nom);
	Optional<Collegue> findBymatricule(String matricule);
}
