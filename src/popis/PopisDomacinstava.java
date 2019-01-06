package popis;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import popis.izuzeci.StatistikaException;

public class PopisDomacinstava {

	private List<Domacinstvo> domacinstva;

	public PopisDomacinstava() {
		domacinstva = new LinkedList<>();
	}

	public void upisiDomacinstvaBezPrimanja() throws StatistikaException {
		if (domacinstva.isEmpty()) {
			throw new StatistikaException("Nema unetih domacinstava.");
		}
		
		try (PrintWriter out = new PrintWriter(
									new BufferedWriter(
											new FileWriter("domacinstva_bez_prihoda.txt")))) {
			
			
			for (int i = 0; i < domacinstva.size(); i++) {
				if (domacinstva.get(i).getMesecnaPrimanja() == 0) {
					out.print(domacinstva.get(i).getMesto());
					out.print("#");
					out.print(domacinstva.get(i).getBrojOdraslih());
					out.print("#");
					out.print(domacinstva.get(i).getBrojDece());
					out.println();
				}
			}
		} catch (Exception e) {
			System.out.println("Greska: " + e.getMessage());
		}
	}
	
	public void statistikaDomacinstava() {
		double ukupnaPrimanja = 0;
		int ukupanBrojOdraslih = 0;
		int ukupanBrojDece = 0;
		
		for (int i = 0; i < domacinstva.size(); i++) {
			ukupnaPrimanja += domacinstva.get(i).getMesecnaPrimanja();
			ukupanBrojOdraslih += domacinstva.get(i).getBrojOdraslih();
			ukupanBrojDece += domacinstva.get(i).getBrojDece();
		}
			
		try (PrintWriter out = new PrintWriter(
									new BufferedWriter(
											new FileWriter("izvestaj.txt")))) {
			
			out.println("Ukupan broj domacinstava je: " + domacinstva.size());
			out.println("Prosecan iznos mesecnih primanja po domacinstvu je: " + ukupnaPrimanja / domacinstva.size());
			
			// cast-ujemo u double da bismo izbegli celobrojno deljenje
			out.println("Prosecan broj odraslih po domacinstvu je: " + (double) ukupanBrojOdraslih / domacinstva.size());
			out.println("Prosecan broj dece po domacinstvu je: " + (double) ukupanBrojDece / domacinstva.size());
			out.println("Prosecan iznos mesecnih primanja po clanu domacinstva je: " + ukupnaPrimanja / (ukupanBrojOdraslih + ukupanBrojDece));
		} catch (Exception e) {
			System.out.println("Greska: " + e.getMessage());
		}
	}
	
	public void ucitajDomacinstvaSaTastature() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("Unesite broj domacinstava: ");
			int brojDomacinstava = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < brojDomacinstava; i++) {
				try {
					System.out.print("Unesite mesto: ");
					String mesto = br.readLine();
					
					System.out.print("Unesite broj odraslih: ");
					int brojOdraslih = Integer.parseInt(br.readLine());
					
					System.out.print("Unesite broj dece: ");
					int brojDece = Integer.parseInt(br.readLine());
					
					System.out.print("Unesite mesecna primanja: ");
					int mesecnaPrimanja = Integer.parseInt(br.readLine());
					
					Domacinstvo domacinstvo = new Domacinstvo();
					domacinstvo.setMesto(mesto);
					domacinstvo.setBrojOdraslih(brojOdraslih);
					domacinstvo.setBrojDece(brojDece);
					domacinstvo.setMesecnaPrimanja(mesecnaPrimanja);
					
					domacinstva.add(domacinstvo);
				} catch (Exception e) {
					System.out.println("Greska prilikom unosa domacinstva: " + e.getMessage());
					
					System.out.print("Unesite mesto: ");
					String mesto = br.readLine();
					
					System.out.print("Unesite broj odraslih: ");
					int brojOdraslih = Integer.parseInt(br.readLine());
					
					System.out.print("Unesite broj dece: ");
					int brojDece = Integer.parseInt(br.readLine());
					
					System.out.print("Unesite mesecna primanja: ");
					int mesecnaPrimanja = Integer.parseInt(br.readLine());
					
					Domacinstvo domacinstvo = new Domacinstvo();
					domacinstvo.setMesto(mesto);
					domacinstvo.setBrojOdraslih(brojOdraslih);
					domacinstvo.setBrojDece(brojDece);
					domacinstvo.setMesecnaPrimanja(mesecnaPrimanja);

					domacinstva.add(domacinstvo);
				}
			}
		} catch (Exception e) {
			System.out.println("Greska: " + e.getMessage());
		}
	}
	
}
