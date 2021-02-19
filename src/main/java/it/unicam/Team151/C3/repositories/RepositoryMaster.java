package it.unicam.Team151.C3.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryMaster implements IRepositoryMaster {

    private static RepositoryMaster instance = null;

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CommercianteRepository commercianteRepository;
    @Autowired
    private CorriereRepository corriereRepository;
    @Autowired
    private ArmadiettoRepository armadiettoRepository;
    @Autowired
    private ArticoloCarrelloRepository articoloCarrelloRepository;
    @Autowired
    private ArticoloRepository articoloRepository;
    @Autowired
    private CarrelloRepository carrelloRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private DescrizioneArticoloRepository descrizioneArticoloRepository;
    @Autowired
    private PaccoRepository paccoRepository;
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private PuntoConsegnaRepository puntoConsegnaRepository;
    @Autowired
    private PuntoVenditaRepository puntoVenditaRepository;
    @Autowired
    private RicevutaRepository ricevutaRepository;

    private RepositoryMaster() {}

    public static RepositoryMaster getInstance(){
        if (instance == null)
            instance = new RepositoryMaster();
        return instance;
    }

    @Override
    public ClienteRepository getClienteRepository() {
        return clienteRepository;
    }

    @Override
    public CommercianteRepository getCommercianteRepository() {
        return commercianteRepository;
    }

    @Override
    public CorriereRepository getCorriereRepository() {
        return corriereRepository;
    }

    @Override
    public ArmadiettoRepository getArmadiettoRepository() {
        return armadiettoRepository;
    }

    @Override
    public ArticoloCarrelloRepository getArticoloCarrelloRepository() {
        return articoloCarrelloRepository;
    }

    @Override
    public ArticoloRepository getArticoloRepository() {
        return articoloRepository;
    }

    @Override
    public CarrelloRepository getCarrelloRepository() {
        return carrelloRepository;
    }

    @Override
    public CategoriaRepository getCategoriaRepository() {
        return categoriaRepository;
    }

    @Override
    public DescrizioneArticoloRepository getDescrizioneArticoloRepository() {
        return descrizioneArticoloRepository;
    }

    @Override
    public PaccoRepository getPaccoRepository() {
        return paccoRepository;
    }

    @Override
    public PrenotazioneRepository getPrenotazioneRepository() {
        return prenotazioneRepository;
    }

    @Override
    public PuntoConsegnaRepository getPuntoConsegnaRepository() {
        return puntoConsegnaRepository;
    }

    @Override
    public PuntoVenditaRepository getPuntoVenditaRepository() {
        return puntoVenditaRepository;
    }

    @Override
    public RicevutaRepository getRicevutaRepository() {
        return ricevutaRepository;
    }
}