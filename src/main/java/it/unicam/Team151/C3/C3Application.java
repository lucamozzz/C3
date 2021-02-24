package it.unicam.Team151.C3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
@EnableJpaRepositories("it.unicam.Team151.C3.repositories")
public class C3Application{

	public static void main(String[] args) {
		SpringApplication.run(C3Application.class, args);
	}

}
