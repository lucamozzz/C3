package it.unicam.Team151.C3;

import it.unicam.Team151.C3.articoli.ArticoloCarrello;
import it.unicam.Team151.C3.articoli.Carrello;
import it.unicam.Team151.C3.articoli.Categoria;
import it.unicam.Team151.C3.articoli.DescrizioneArticolo;
import it.unicam.Team151.C3.puntoConsegna.PuntoConsegna;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.repositories.*;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.utenti.Commerciante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Component
@EnableJpaRepositories("it.unicam.Team151.C3.repositories")
public class C3Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(C3Application.class, args);
	}


	@Autowired
	PuntoVenditaRepository puntoVenditaRepository;
	@Autowired
	PuntoConsegnaRepository puntoConsegnaRepository;
	@Autowired
	ArticoloCarrelloRepository articoloCarrelloRepository;
	@Autowired
	DescrizioneArticoloRepository descrizioneArticoloRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	CommercianteRepository commercianteRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	CarrelloRepository carrelloRepository;

	@Override
	public void run(String... args) throws Exception {
			Categoria categoria1 = new Categoria("Cucina", "");
			Categoria categoria2 = new Categoria("Fai da te", "");
			Categoria categoria3 = new Categoria("Ferramerda", "");

			categoriaRepository.save(categoria1);
			categoriaRepository.save(categoria2);
			categoriaRepository.save(categoria3);

			List<String> paramCommerciante = new ArrayList<>();
			paramCommerciante.add("Matteo");
			paramCommerciante.add("Contestabile");
			paramCommerciante.add("Eur");
			paramCommerciante.add("Commerciante");
			paramCommerciante.add("matteo.contestabile@matteocontestabile.contestabile");
			paramCommerciante.add("ok");

			Commerciante matteoContestabile = new Commerciante(paramCommerciante);
			commercianteRepository.save(matteoContestabile);

			PuntoVendita puntoVendita1 = new PuntoVendita("Tutto per Tutti", matteoContestabile, "Roma, (Italia)");
			PuntoVendita puntoVendita2 = new PuntoVendita("La nostra casa", matteoContestabile, "Roma, (Cina)");
			puntoVenditaRepository.save(puntoVendita1);
			puntoVenditaRepository.save(puntoVendita2);

			DescrizioneArticolo descrizioneArticolo1 = new DescrizioneArticolo("Dildo di gommma", "Bel cazzone di gomma", 15.99, 10, puntoVendita1, categoria2);
			DescrizioneArticolo descrizioneArticolo2 = new DescrizioneArticolo("Dildo di roccia", "Bel cazzone di roccia", 5.99, 69, puntoVendita1, categoria1);
			DescrizioneArticolo descrizioneArticolo3 = new DescrizioneArticolo("Dildo di vetro", "Bel cazzone di vetro", 25.99, 4, puntoVendita2, categoria3);
			descrizioneArticoloRepository.save(descrizioneArticolo1);
			descrizioneArticoloRepository.save(descrizioneArticolo2);
			descrizioneArticoloRepository.save(descrizioneArticolo3);

			PuntoConsegna puntoConsegna1 = new PuntoConsegna("Punto di ritiro 1", 10);
			puntoConsegnaRepository.save(puntoConsegna1);

			List<String> paramCliente = new ArrayList<>();
			paramCommerciante.add("Giorgio");
			paramCommerciante.add("Paoletti");
			paramCommerciante.add("Camerino");
			paramCommerciante.add("Cliente");
			paramCommerciante.add("succhia.cazzi@matteocontestabile.contestabile");
			paramCommerciante.add("ded");

			Cliente cliente = new Cliente("Giorgio","Paoletti", "Camerino", "Cliente", "succhia.cazzi@matteocontestabile.contestabile", "ded");
			clienteRepository.save(cliente);

			Carrello carrello = new Carrello(cliente);
			carrelloRepository.save(carrello);

			ArticoloCarrello articoloCarrello1 = new ArticoloCarrello(descrizioneArticolo1, 5, carrello);
			ArticoloCarrello articoloCarrello2 = new ArticoloCarrello(descrizioneArticolo2, 3, carrello);
			articoloCarrelloRepository.save(articoloCarrello1);
			articoloCarrelloRepository.save(articoloCarrello2);
		}
}
