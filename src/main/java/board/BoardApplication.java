package board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Spring bootを実行するクラス
 * 
 * @author(最後の修正者) Eonsu Bae(2020-01-28)
 * @version 1.0
 */

@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

	/**
	 * thymeleafをtemplate engineで設定するメソッド　
	 * 
	 * @param ITemplateResolver templateResolver thymeleafの機能を持っているresolverのインプルメンテーション 
	 * @param SpringSecurityDialect sec Spring securityに関する機能を使うことができるように設定 
	 * @return template engineの情報を持っているオブジェクト
	 */
	@Bean
	public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver, SpringSecurityDialect sec) {
	    final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);
	    templateEngine.addDialect(sec); // Enable use of "sec"
	    return templateEngine;
	}
}
