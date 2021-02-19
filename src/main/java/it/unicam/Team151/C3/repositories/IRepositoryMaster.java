package it.unicam.Team151.C3.repositories;

public interface IRepositoryMaster {
    ClienteRepository getClienteRepository();

    CommercianteRepository getCommercianteRepository();

    CorriereRepository getCorriereRepository();

    ArmadiettoRepository getArmadiettoRepository();

    ArticoloCarrelloRepository getArticoloCarrelloRepository();

    ArticoloRepository getArticoloRepository();

    CarrelloRepository getCarrelloRepository();

    CategoriaRepository getCategoriaRepository();

    DescrizioneArticoloRepository getDescrizioneArticoloRepository();

    PaccoRepository getPaccoRepository();

    PrenotazioneRepository getPrenotazioneRepository();

    PuntoConsegnaRepository getPuntoConsegnaRepository();

    PuntoVenditaRepository getPuntoVenditaRepository();

    RicevutaRepository getRicevutaRepository();
}
