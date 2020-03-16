package br.com.cartao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * Created by andre on 15/03/20.
 */

@SpringBootApplication
@EnableScheduling
@EnableAsync
@EntityScan(basePackageClasses = {
		AnaliseCreditoApplication.class,
		Jsr310JpaConverters.class
})
public class AnaliseCreditoApplication {

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(AnaliseCreditoApplication.class, args);
	}


}
