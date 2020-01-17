package board.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BoardUserDetailsService boardUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    .antMatchers("/").permitAll()
		    .antMatchers("/boards").permitAll()
			.antMatchers("/boards/**").authenticated()
			.antMatchers("/account").authenticated();
			
		http.formLogin().loginPage("/login").defaultSuccessUrl("/account", true)
		    .and().logout().invalidateHttpSession(true).logoutSuccessUrl("/")
			.and().csrf().disable().exceptionHandling().accessDeniedPage("/accessDenied");

		http.userDetailsService(boardUserDetailsService);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
}
