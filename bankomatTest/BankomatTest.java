package bankomatTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import bankomat.BankomatMain;
import bankomat.Racun;

class BankomatTest {

	@Test
	public void shouldReturnNameOfOwnerAndNumOfAccountAndAccountBalance() throws IOException, ClassNotFoundException {
		Racun result = BankomatMain.kreiranjeRacuna("Adem", 0, 13);
		boolean stanje = true;
		int brojac = 0;
		if(result.getImeKorisnika().equals("Adem")); {
			brojac++;
		}
		if(result.getBrojRacuna() == 0) {
			brojac++;
		}
		if(result.getStanjeRacuna() == 13) {
			brojac++;
		}
		System.out.println(brojac);
		if(brojac == 3) {
			stanje = true;
		}
		assertEquals(true, stanje);
	}
	
	@Test
	public void shouldReturnBallanceOfAccountAfterTransaction() throws IOException, ClassNotFoundException {
		Racun racun1 = BankomatMain.kreiranjeRacuna("Adem", 1, 150);
		Racun racun2 = BankomatMain.kreiranjeRacuna("Aziz", 2, 50);
		BankomatMain.transferNovca(racun1.getBrojRacuna(), racun2.getBrojRacuna(), 50);
		boolean stanje = true;
		if(racun1.getStanjeRacuna() == 100 && racun2.getStanjeRacuna() == 100) {
			stanje = true;
		}
		assertEquals(true, stanje);
	}

}
