package ispravka_koda;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class UpisivanjeUTXTFajlTest {
	
	@Test
	public void metoda_ispisiParalelogram() throws IOException {
		String ocekivaniIspis = 
				"1\n" +
				"2\n" +
				"3\n" +
				"5\n" +
				"7\n" +
				"11\n" +
				"13\n" +
				"17\n" +
				"19\n" +
				"23\n" +
				"29\n" +
				"31\n" +
				"37\n" +
				"41\n" +
				"43\n" +
				"47\n" +
				"53\n" +
				"59\n" +
				"61\n" +
				"67\n" +
				"71\n" +
				"73\n" +
				"79\n" +
				"83\n" +
				"89\n" +
				"97\n";
		
		UpisivanjeUTXTFajl.upisiProsteBrojeve();

		String sadrzajFajla = ucitajIVratiTekst("brojevi.txt");
		
		assertEquals("U fajlu se ne nalaze svi prosti brojevi u posebnim redovima.", ocekivaniIspis, sadrzajFajla);
	
		// brisemo fajl
		new File("brojevi.txt").delete();
	}
	
	private static String ucitajIVratiTekst(String imeFajla) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(imeFajla));
		boolean kraj = false;
		String s = "";
		
		while (!kraj) {
			String pom = in.readLine();
			
			if (pom == null)
				kraj = true;
			else
				s = s + pom + "\n";
		}
		in.close();

		return s;
	}
}
