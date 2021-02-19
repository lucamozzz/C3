package it.unicam.Team151.C3;

import it.unicam.Team151.C3.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

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
/*		Categoria categoria1 = new Categoria("sport", "");
			Categoria categoria2 = new Categoria("divertimento", "");
			Categoria categoria3 = new Categoria("gioco", "");

			categoriaRepository.save(categoria1);
			categoriaRepository.save(categoria2);
			categoriaRepository.save(categoria3);

			List<String> paramCommerciante = new ArrayList<>();
			paramCommerciante.add("andrea");
			paramCommerciante.add("rossi");
			paramCommerciante.add("milano");
			paramCommerciante.add("Commerciante");
			paramCommerciante.add("andrea.rossi@gmail.it");
			paramCommerciante.add("ok");

			Commerciante matteoContestabile = new Commerciante(paramCommerciante);
			commercianteRepository.save(matteoContestabile);

			PuntoVendita puntoVendita1 = new PuntoVendita("cinesi", matteoContestabile, "italia");
			PuntoVendita puntoVendita2 = new PuntoVendita("brico", matteoContestabile, "Roma, (Cina)");
			puntoVenditaRepository.save(puntoVendita1);
			puntoVenditaRepository.save(puntoVendita2);

			DescrizioneArticolo descrizioneArticolo1 = new DescrizioneArticolo("cazzo in culo", "Bel cazzone di gomma", 15.99, 30, puntoVendita1, categoria2);
			DescrizioneArticolo descrizioneArticolo2 = new DescrizioneArticolo("tlou2", "Bel cazzone di roccia", 5.99, 33, puntoVendita1, categoria1);
			DescrizioneArticolo descrizioneArticolo3 = new DescrizioneArticolo("fifa", "Bel cazzone di vetro", 25.99, 4, puntoVendita2, categoria3);
			descrizioneArticoloRepository.save(descrizioneArticolo1);
			descrizioneArticoloRepository.save(descrizioneArticolo2);
			descrizioneArticoloRepository.save(descrizioneArticolo3);

			PuntoConsegna puntoConsegna1 = new PuntoConsegna("Punto di ritiro 3", 10);
			puntoConsegnaRepository.save(puntoConsegna1);

			List<String> paramCliente = new ArrayList<>();
			paramCommerciante.add("Giorgio");
			paramCommerciante.add("Paoletti");
			paramCommerciante.add("Camerino");
			paramCommerciante.add("Cliente");
			paramCommerciante.add("aaa");
			paramCommerciante.add("ded");

			Cliente cliente = new Cliente("mario","rossi", "Camerino", "Cliente", "mario.rossi@gmail.com", "ded");
			clienteRepository.save(cliente);

			Carrello carrello = new Carrello(cliente);
			carrelloRepository.save(carrello);

			ArticoloCarrello articoloCarrello1 = new ArticoloCarrello(descrizioneArticolo1, 5, carrello);
			ArticoloCarrello articoloCarrello2 = new ArticoloCarrello(descrizioneArticolo2, 3, carrello);
			articoloCarrelloRepository.save(articoloCarrello1);
			articoloCarrelloRepository.save(articoloCarrello2);*/
		//test
		//for(ArticoloCarrello a : articoloCarrelloRepository.findAll()){
		//	carrelloRepository.findById((long) 240).get().getArticoliCarrello().add(a);
		//}
	}
}
