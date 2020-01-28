package board.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring SecurityをJava codeで設定するクラス
 * 
 * @author Eonsu Bae(2020-01-28) 
 * @version 1.0 
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/* データベースからユーザの情報を持ってくるオブジェクトを注入されたメンバーバリアブル */
	@Autowired
	private BoardUserDetailsService boardUserDetailsService;

	/**
	 * 具体的なセキュリティ ポリシーを設定するメソッド
	 * 
	 * @param HttpSecurity http 全てのhttpリクエストにセキュリティの機能を適用
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// protected routes
		http.authorizeRequests()
		    .antMatchers("/").permitAll()
		    .antMatchers("/boards").permitAll()
			.antMatchers("/boards/**").authenticated()
			.antMatchers("/account").authenticated();
			
		/* login formの経路とリダイレクトの経路 
		 * logoutした時のセッションの管理とリダイレクトの経路
		 * CSRF Attackに対する設定 */
		http.formLogin().loginPage("/login").defaultSuccessUrl("/account", true)
		    .and().logout().invalidateHttpSession(true).logoutSuccessUrl("/")
			.and().csrf().disable().exceptionHandling().accessDeniedPage("/accessDenied");

		// データベースでユーザの情報を持ってくるUserDetailsServiceのオブジェクトを設定
		http.userDetailsService(boardUserDetailsService);
	}

	/**
	 * bcryptで暗号化ができるように設定するエンコーダー 
	 * 
	 * @return bcryptのエンコーダーのオブジェクト  
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
}
