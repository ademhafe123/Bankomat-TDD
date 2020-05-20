package bankomat;

import java.io.Serializable;

public class Racun implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int getBrojRacuna() {
		return brojRacuna;
	}
	public void setBrojRacuna(int brojRacuna) {
		this.brojRacuna = brojRacuna;
	}
	public String getImeKorisnika() {
		return imeKorisnika;
	}
	public void setImeKorisnika(String imeKorisnika) {
		this.imeKorisnika = imeKorisnika;
	}
	public double getStanjeRacuna() {
		return stanjeRacuna;
	}
	public void setStanjeRacuna(double stanjeRacuna) {
		this.stanjeRacuna = stanjeRacuna;
	}
	
	int brojRacuna;
	String imeKorisnika;
	double stanjeRacuna;
	static int brojacRacuna = 0;
	
	public Racun(int brojRacuna, String imeKorisnika, double stanjeRacuna) {
		this.brojRacuna = brojRacuna;
		this.imeKorisnika = imeKorisnika;
		this.stanjeRacuna = stanjeRacuna;
	}
	
	public Racun() {
			
	}
	

}
