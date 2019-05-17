package dev.colleguesapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Bean //encriptage du motpass
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Override // pour l'authentification
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

	@Autowired //pour le cookie/token
	JWTAuthorizationFilter jwtAuthorizationFilter;

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable() //CSRF non utiliser ici
		.cors().and()
		//pour pouvoir utiliser Postman sans trop de probleme
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

		//requete authorise pour tt les utilisateurs, ici GET
		.authorizeRequests() 
		.antMatchers(HttpMethod.GET, "/collegues/**").permitAll() // authorise les GET
		.antMatchers(HttpMethod.GET, "/collegues/gallerie").permitAll()
		.antMatchers("/h2-console/**").permitAll() //acces h2 sans authentification [pas encore en place]
		.antMatchers("/auth").permitAll()

		//autre requete (POST et PATCH)
		.anyRequest().authenticated()	
		.and().headers().frameOptions().disable() // acces h2 sans authentification
		.and() //ajoute l'utilisation du filtre par token
		.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)


		// Gestion de la déconnexion
		// /POST /logout
		.logout()
		// en cas de succès un OK est envoyé (à la place d'une redirection vers /login)
		.logoutSuccessHandler((req, resp, auth) -> resp.setStatus(HttpStatus.OK.value()))
		// suppression du cookie d'authentification
		.deleteCookies(TOKEN_COOKIE); // Gestion de la déconnexion

	}

}
