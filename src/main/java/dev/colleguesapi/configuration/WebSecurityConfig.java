package dev.colleguesapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Override
	  protected void configure(HttpSecurity http) throws Exception
	{
	    http.csrf().disable() //CSRF non utiliser ici
	    //pour pouvoir utiliser Postman sans trop de probleme
	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

	    //requete authorise pour tt les utilisateurs, ici GET
	    .authorizeRequests() 
	    .antMatchers(HttpMethod.GET, "/exemples").permitAll() // authorise les GET
	    //.antMatchers("/h2-console/**").permitAll() //acces h2 sans authentification [pas encore en place]
	    .antMatchers("/auth").permitAll()

	    .antMatchers("/admin").hasRole("ADMIN") // get en acces seulement pour les admins
	    
	    //autre requete (POST et PATCH)
	    .anyRequest().authenticated()	
	    .and().headers().frameOptions().disable(); // acces h2 sans authentification
	    //.and() //ajoute l'utilisation du filtre par token
	    //.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
	    
	    /* A FAIRE PLUS TARD !!!
		 // Gestion de la déconnexion
		 // /POST /logout
		 .logout()
		 // en cas de succès un OK est envoyé (à la place d'une redirection vers /login)
		 .logoutSuccessHandler((req, resp, auth) -> resp.setStatus(HttpStatus.OK.value()))
		 // suppression du cookie d'authentification
		 .deleteCookies(TOKEN_COOKIE); // Gestion de la déconnexion
		 */

	  }

}
