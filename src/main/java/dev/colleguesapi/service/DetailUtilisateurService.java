package dev.colleguesapi.service;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.colleguesapi.entite.Collegue;
import dev.colleguesapi.entite.CollegueRepository;
import dev.colleguesapi.entite.Role;

@Service
public class DetailUtilisateurService implements UserDetailsService
{

	  private CollegueRepository collegueRepository;

	  public DetailUtilisateurService(CollegueRepository collegueRepository) {
	    this.collegueRepository = collegueRepository;
	  }

	  // cette méthode va permettre à Spring Security d'avoir accès
	  // aux informations d'un utilisateur (mot de passe, roles) à partir
	  // d'un nom utilisateur
	  //
	  // L'interface UserDetails détaille le contrat attendu par Spring Security.
	  @Override
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    // Recherche d'utilisateur par nom utilisateur
	    Collegue collegueTrouve = this.collegueRepository.findBymatricule(username)
	      .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));


	    // Création d'un objet User (implémentant UserDetails)
	    return new User(collegueTrouve.getNom(), collegueTrouve.getMotDePasse(),collegueTrouve.getRoles().stream().map(Role::name).map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

	 // pour le SimpleGrantedAuthority, il prend un string or Role est une enum. il faut donc recupere son contnu et l'envoyer à SimpleGrantedAuthority
	  }

}
