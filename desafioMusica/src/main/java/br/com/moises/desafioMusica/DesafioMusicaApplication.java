package br.com.moises.desafioMusica;

import br.com.moises.desafioMusica.principal.Principal;
import br.com.moises.desafioMusica.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioMusicaApplication implements CommandLineRunner {
	@Autowired
	private ArtistaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DesafioMusicaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal(repository);
		principal.exibeMenu();

	}
}
