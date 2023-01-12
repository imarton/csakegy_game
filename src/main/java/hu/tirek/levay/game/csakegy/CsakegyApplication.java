package hu.tirek.levay.game.csakegy;

import lombok.extern.slf4j.Slf4j;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;

@SpringBootApplication
@Slf4j
public class CsakegyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsakegyApplication.class, args);
	}

//	@Bean
//	public SpringTemplateEngine templateEngine() {
//		log.info("template engine bean in action");
//		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//		templateEngine.addDialect(new LayoutDialect());
//		return templateEngine;
//	}


}
