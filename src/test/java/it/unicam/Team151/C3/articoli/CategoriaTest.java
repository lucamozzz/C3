package it.unicam.Team151.C3.articoli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {

    Categoria categoria1;
    Categoria categoria2;
    Categoria categoria3;

    @BeforeEach
    void init(){
        categoria1= new Categoria("Elettronica", "Robe elettroniche");
        categoria2= new Categoria("Sport", "Abbigliamenti sportivi, per allenamenti dalle 2 o 3 volte a settimana");
        categoria3= new Categoria("Nuoto", "Cuffie, costumi, occhialetti");
    }

    @Test
    void getNome() {
        assertEquals("Nuoto",categoria3.getNome());
        assertNotEquals("Elettronica", categoria2.getNome());
        assertEquals("Elettronica", categoria1.getNome());
    }

    @Test
    void getDescrizione() {
        assertEquals("Robe elettroniche",categoria1.getDescrizione());
    }
}