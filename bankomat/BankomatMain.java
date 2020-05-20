package bankomat;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BankomatMain {
	static Scanner unos = new Scanner(System.in);
	static File fajl = new File("Bankomat-racun.txt");
	static ArrayList<Racun> racuni = new ArrayList<Racun>();

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub	
		
		if(!fajl.exists()) {
			try {
				fajl.createNewFile();
			} catch (IOException e) {
			
			}
		}
		spremanjeUListu();
	
		menuInput();
		
	}
	
	public static void menuInput() throws IOException, ClassNotFoundException {
		System.out.println("\n 1 - kreiranje racuna");
		System.out.println(" 2 - prebacivanje novca sa jednog na drugi racun");
		System.out.println(" 3 - informacije o racunu");
		int unosKorisnika = unos.nextInt();
		menu(unosKorisnika);
		
	}
	
	public static void menu(int unosKorisnika) throws IOException, ClassNotFoundException {
		if(unosKorisnika < 4 && unosKorisnika > 0) {
			switch(unosKorisnika) {
				case 1:
					kreiranjeRacuna(inputImenaKorisnika(), dobijanjeBrojaRacuna(), inputStanjaRacuna());
					menuInput();
					break;
				case 2:
					transferNovca(inputOfSourceAccount(), inputOfTargetAccount(), inputOfAmountOfMoneyToBeTranfered());
					menuInput();
					break;	
				case 3:
					menuInput();
					informacijeORacunu(inputBrojaRacunaZaInformacije());
					break;
			}
		}else {
			System.out.println(" Greska u unosu! \n");
			menuInput();
		}
	}
	
	
	public static Racun kreiranjeRacuna(String imeKorisnika, int brojRacuna, double stanjeRacuna) throws IOException, ClassNotFoundException {
		Racun racun = new Racun(brojRacuna, imeKorisnika, stanjeRacuna);
		racuni.add(racun);
		System.out.println("Ime: " + racun.imeKorisnika + "\nBroj racuna: " + racun.brojRacuna + "\nStanje racuna: " + racun.stanjeRacuna);
		spremanjeUFajl();
		return racun;
	}	
	 public static String inputImenaKorisnika() {
		 System.out.println("Unesite ime korisnika: ");
		 unos.nextLine();
		 String imeKorisnika = unos.nextLine();
		 return imeKorisnika;
	 }
	 
	 public static double inputStanjaRacuna() {
		 System.out.println("Unesite stanje vaseg racuna: ");
		 double stanjeRacuna = unos.nextDouble();
		 while(stanjeRacuna < 0) {
			 System.out.println("Stanje vaseg racuna mora biti pozitivno! ");
			 stanjeRacuna = unos.nextDouble();
		 }
		 return stanjeRacuna;
	 }
	 
	 public static int dobijanjeBrojaRacuna() throws ClassNotFoundException, IOException {
		int brojRacuna = 0;
		for(int i = 0; i < racuni.size(); i++) {
			brojRacuna++;
		}
		return brojRacuna;
	 }
	 
	 
	 
	 public static void transferNovca(int sourceAccount, int targetAccount, double iznosNovcaZaPrebaciti) throws IOException, ClassNotFoundException {
		 while(provjeraKolicineNovcaZaPrebaciti(iznosNovcaZaPrebaciti, sourceAccount) == false) {
			 System.out.println("Nemate dovoljno sredstava na racunu! ");
			 iznosNovcaZaPrebaciti = inputOfAmountOfMoneyToBeTranfered();
		 }
		 
		 for(int i = 0; i < racuni.size(); i++) {
			 if(racuni.get(i).brojRacuna == targetAccount) {
				 racuni.get(i).stanjeRacuna += iznosNovcaZaPrebaciti;
			 }
		 }
		 for(int i = 0; i < racuni.size(); i++) {
			 if(racuni.get(i).brojRacuna == sourceAccount) {
				 racuni.get(i).stanjeRacuna = racuni.get(i).stanjeRacuna - iznosNovcaZaPrebaciti;
			 }
		 }
		 spremanjeUFajl();
		 System.out.println("Uspjesno ste prebacili novac u iznosu od: " + iznosNovcaZaPrebaciti);
	 }
	 
	 public static int inputOfSourceAccount() {
		 System.out.println("Unesite broj racuna sa kojeg zelite prebaciti novac: ");
		 int sourceAccount = unos.nextInt();
		 while(provjeraPostojanjaBrojaAccounta(sourceAccount) == false) {
			 sourceAccount = unos.nextInt();
		 }	 
		 return sourceAccount;
	 }
	 
	 public static int inputOfTargetAccount() {
		 System.out.println("Unesite broj racuna na koji zalite prebaciti novac: ");
		 int targetAccount = unos.nextInt();
		 while(provjeraPostojanjaBrojaAccounta(targetAccount) == false) {
			 targetAccount = unos.nextInt();
		 }
		 return targetAccount;
	 }
	 
	 public static boolean provjeraPostojanjaBrojaAccounta(int numOfAccount) {
		 boolean validacija = false;
		 for(int i = 0; i <  racuni.size(); i++) {
			 if(racuni.get(i).brojRacuna == numOfAccount) {
				 validacija = true;
			 }
		 }
		 return validacija;
	 }
	 
	 public static double inputOfAmountOfMoneyToBeTranfered() {
		 System.out.println("Unesite kolicinu novca koju zelite prebaciti: ");
		 double iznosNovcaZaPrebaciti = unos.nextDouble();
		 return iznosNovcaZaPrebaciti;
	 }
	 
	 public static boolean provjeraKolicineNovcaZaPrebaciti(double iznosNovcaZaPrebaciti, int sourceAccount) {
		 boolean validacija = true;
		 for(int i = 0; i < racuni.size(); i++) {
			 if(racuni.get(i).brojRacuna == sourceAccount) {
				 if(racuni.get(i).stanjeRacuna < iznosNovcaZaPrebaciti) {
					 validacija = false;
				 }
			 }
		 }
		 return validacija;
	 }
	 
	 public static void informacijeORacunu(int brojRacuna) throws ClassNotFoundException, IOException {
		 while(brojRacuna >= racuni.size()) {
			 System.out.println("Nema racuna sa tim brojem!");
			 
		 }
		 
		 System.out.println("\n Broj racuna: " + racuni.get(brojRacuna).brojRacuna + "\n Ime korisnika " + racuni.get(brojRacuna).imeKorisnika + "\n Stanje racuna: " + racuni.get(brojRacuna).stanjeRacuna);
	 }
	 
	 public static int inputBrojaRacunaZaInformacije() {
		 System.out.println("Unesite broj racuna: ");
		 int brojRacuna = unos.nextInt();
		 return brojRacuna;
	 }
	 
	 
	 
	 public static void spremanjeUListu() throws IOException, ClassNotFoundException {
		 FileInputStream fi = new FileInputStream(fajl);
		try {
			 ObjectInputStream oi = new ObjectInputStream(fi);
			 while(true) {
				 try {
					 racuni.add((Racun)(oi.readObject()));
				 }catch(EOFException e) {
					 break;
				 }
			 }
			 oi.close();
		} catch(EOFException e) {
			
		}
	 }
	 
	 public static void spremanjeUFajl() throws IOException {
		 FileOutputStream fo = new FileOutputStream(fajl);
		 ObjectOutputStream oo = new ObjectOutputStream(fo);
		 for(int i = 0; i < racuni.size(); i++) {
			 try{
				 oo.writeObject(racuni.get(i));
			 } catch(EOFException e) {
				 break;
			 }
		 }
		 oo.close();
	 }

}
