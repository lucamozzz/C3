package it.unicam.Team151.C3.repositories;

import javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryMaster {

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

    public ClienteRepository getClienteRepository() {
        return clienteRepository;
    }

    public CommercianteRepository getCommercianteRepository() {
        return commercianteRepository;
    }

    public CorriereRepository getCorriereRepository() {
        return corriereRepository;
    }

    public ArmadiettoRepository getArmadiettoRepository() {
        return armadiettoRepository;
    }

    public ArticoloCarrelloRepository getArticoloCarrelloRepository() {
        return articoloCarrelloRepository;
    }

    public ArticoloRepository getArticoloRepository() {
        return articoloRepository;
    }

    public CarrelloRepository getCarrelloRepository() {
        return carrelloRepository;
    }

    public CategoriaRepository getCategoriaRepository() {
        return categoriaRepository;
    }

    public DescrizioneArticoloRepository getDescrizioneArticoloRepository() {
        return descrizioneArticoloRepository;
    }

    public PaccoRepository getPaccoRepository() {
        return paccoRepository;
    }

    public PrenotazioneRepository getPrenotazioneRepository() {
        return prenotazioneRepository;
    }

    public PuntoConsegnaRepository getPuntoConsegnaRepository() {
        return puntoConsegnaRepository;
    }

    public PuntoVenditaRepository getPuntoVenditaRepository() {
        return puntoVenditaRepository;
    }

    public RicevutaRepository getRicevutaRepository() {
        return ricevutaRepository;
    }
}