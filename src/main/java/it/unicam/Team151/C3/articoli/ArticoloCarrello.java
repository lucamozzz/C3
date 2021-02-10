package it.unicam.Team151.C3.articoli;

public class ArticoloCarrello {

	private int quantita;
	private DescrizioneArticolo descrizioneArticolo;

	public DescrizioneArticolo getDescrizioneArticolo() {
		return this.descrizioneArticolo;
	}

	/**
	 * 
	 * @param idDescArticolo
	 * @param quantita
	 */
	public ArticoloCarrello(Long idDescArticolo, int quantita) {
		// TODO - implement ArticoloCarrello.ArticoloCarrello
		throw new UnsupportedOperationException();
	}

    public double getPrezzo() {
		return this.getDescrizioneArticolo().getPrezzo() * this.quantita;
    }

	public int getQuantita() {
		return this.quantita;
	}
}