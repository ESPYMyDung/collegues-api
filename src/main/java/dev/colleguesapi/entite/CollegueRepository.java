package dev.colleguesapi.entite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CollegueRepository extends JpaRepository<Collegue, String>
{

}
