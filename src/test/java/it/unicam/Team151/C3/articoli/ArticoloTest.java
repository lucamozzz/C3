package it.unicam.Team151.C3.articoli;

import it.unicam.Team151.C3.prenotazione.Prenotazione;
import it.unicam.Team151.C3.prenotazione.PuntoConsegna;
import it.unicam.Team151.C3.puntoVendita.Pacco;
import it.unicam.Team151.C3.puntoVendita.PuntoVendita;
import it.unicam.Team151.C3.utenti.Cliente;
import it.unicam.Team151.C3.utenti.Commerciante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticoloTest {

    Cliente rossi;
    Carrello carrelloRossi;
    Categoria categoria1;
    Categoria categoria2;
    Commerciante verdi;
    PuntoVendita puntoVendita1;
    PuntoVendita puntoVendita2;
    DescrizioneArticolo descrizioneArticolo1;
    DescrizioneArticolo descrizioneArticolo2;
    ArticoloCarrello articoloCarrello1;
    ArticoloCarrello articoloCarrello2;
    PuntoConsegna puntoConsegna;
    Prenotazione prenotazione;
    Pacco pacco1;
    Pacco pacco2;
    Articolo articolo1;
    Articolo articolo2;
    Articolo articolo3;
    Articolo articolo4;
    Articolo articolo5;

    @BeforeEach
    void init(){
        rossi= new Cliente("Claudio","Rossi","Via Sonnino 17", "Cliente","claudioRossi@gmail.com", "termosifone");
        verdi= new Commerciante("Tommaso","Verdi","Viale Europa 104", "Commerciante","tommyVerdi@gmail.com", "scatola");
        carrelloRossi= new Carrello(rossi);
        categoria1= new Categoria("Ferramenta","Oggetti che si trovano in una ferramenta");
        categoria2= new Categoria("Elettronica","Oggettini elettronici");
        puntoVendita1= new PuntoVendita(verdi,"Da tommaso", "Via nazareth 33");
        puntoVendita2= new PuntoVendita(verdi, "Da rosa","Via recanati 56");
        descrizioneArticolo1= new DescrizioneArticolo("Cacciavite a stella","cacciavite",46.0, 3,puntoVendita1,categoria1);
        descrizioneArticolo2= new DescrizioneArticolo("Laptop Samsung","Samsung Galaxy Laptop 4", 450.0,10,puntoVendita2,categoria2);
        articoloCarrello1= new ArticoloCarrello(descrizioneArticolo1,2,carrelloRossi);
        articoloCarrello2= new ArticoloCarrello(descrizioneArticolo2,3,carrelloRossi);
        carrelloRossi.getArticoliCarrello().add(articoloCarrello1);
        carrelloRossi.getArticoliCarrello().add(articoloCarrello2);
        puntoConsegna= new PuntoConsegna("Le mosse",7);
        prenotazione= new Prenotazione(carrelloRossi,puntoConsegna);
        pacco1= prenotazione.getPacchi().get(0);
        pacco2= prenotazione.getPacchi().get(1);
        articolo1 = pacco1.getArticoli().get(0);
        articolo2 = pacco1.getArticoli().get(1);
        articolo3 = pacco2.getArticoli().get(0);
        articolo4 = pacco2.getArticoli().get(1);
        articolo5 = pacco2.getArticoli().get(2);
    }

    @Test
    void getDescrizioneArticolo() {
        assertEquals(descrizioneArticolo1, articolo1.getDescrizioneArticolo());
        assertEquals(descrizioneArticolo1, articolo2.getDescrizioneArticolo());

        assertEquals(descrizioneArticolo2, articolo3.getDescrizioneArticolo());
        assertEquals(descrizioneArticolo2, articolo4.getDescrizioneArticolo());
        assertEquals(descrizioneArticolo2, articolo5.getDescrizioneArticolo());
    }

    @Test
    void getPacco() {
        assertEquals(pacco1.getId(), articolo1.getPacco().getId());
        assertEquals(pacco1.getId(), articolo2.getPacco().getId());

        assertEquals(pacco2.getId(), articolo3.getPacco().getId());
        assertEquals(pacco2.getId(), articolo4.getPacco().getId());
        assertEquals(pacco2.getId(), articolo5.getPacco().getId());
    }
}