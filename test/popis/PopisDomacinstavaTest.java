package popis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import popis.izuzeci.StatistikaException;
import test.TestUtil;

public class PopisDomacinstavaTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final InputStream originalIn = System.in;

	private PopisDomacinstava instance;

	@Before
	public void setUp() throws Exception {
		instance = new PopisDomacinstava();
		
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
		
		System.setOut(originalOut);
		System.setIn(originalIn);
	}

	@Test
	public void atribut_domacinstva() {
		assertTrue("U klasi nije definisan atribut domacinstva", TestUtil.doesFieldExist(PopisDomacinstava.class, "domacinstva"));
	}

	@Test
	public void atribut_domacinstva_vidljivost() {
		assertTrue("Atribut domacinstva nije privatan", TestUtil.hasFieldModifier(PopisDomacinstava.class, "domacinstva", Modifier.PRIVATE));
	}
	
	@Test
	public void konstruktor_PopisDomacinstava() {
		instance = new PopisDomacinstava();
		
		@SuppressWarnings("unchecked")
		List<Domacinstvo> domacinstva = (List<Domacinstvo>) TestUtil.getFieldValue(instance, "domacinstva");
		
		assertNotNull("Nakon izvrsenja konstruktora, atribut domacinstva nije inicijalizovan.", domacinstva);
	}
	
	@Test
	public void metoda_upisiDomacinstvaBezPrimanja() throws StatistikaException, IOException {
		Domacinstvo d1 = new Domacinstvo();
		d1.setMesto("Kragujevac");
		d1.setBrojOdraslih(2);
		d1.setBrojDece(2);
		d1.setMesecnaPrimanja(100000);
		
		Domacinstvo d2 = new Domacinstvo();
		d2.setMesto("Uzice");
		d2.setBrojOdraslih(2);
		d2.setBrojDece(3);
		d2.setMesecnaPrimanja(0);
		
		Domacinstvo d3 = new Domacinstvo();
		d3.setMesto("Prijepolje");
		d3.setBrojOdraslih(2);
		d3.setBrojDece(4);
		d3.setMesecnaPrimanja(0);
		
		@SuppressWarnings("unchecked")
		List<Domacinstvo> domacinstva = (List<Domacinstvo>) TestUtil.getFieldValue(instance, "domacinstva");
		domacinstva.add(d1);
		domacinstva.add(d2);
		domacinstva.add(d3);
		
		instance.upisiDomacinstvaBezPrimanja();
		
		String sadrzajIzvestaja = ucitajIVratiTekst("domacinstva_bez_prihoda.txt");
		
		String ocekivaniSadrzaj = 
				"Uzice#2#3\n" + 
				"Prijepolje#2#4\n";
		
		assertEquals("Kada se izgenerise izvestaj bez primanja za popis u kojem se nalaze domacinstva: Kragujevac (odrasli: 2, dece: 2, primanja 100000), Uzice (odrasli: 2, dece: 3, primanja 0), Prijepolje (odrasli: 2, dece: 4, primanja 0), u izvestaju se ne nalaze drugi i treci grad.",
				ocekivaniSadrzaj,
				sadrzajIzvestaja);
		
		// brisemo kreirani fajl
		new File("domacinstva_bez_prihoda.txt").delete();
	}
	
	@Test(expected = StatistikaException.class)
	public void metoda_upisiDomacinstvaBezPrimanja_praznaLista() throws StatistikaException {
		instance.upisiDomacinstvaBezPrimanja();
	}
	
	@Test
	public void metoda_statistikaDomacinstava() throws IOException {
		Domacinstvo d1 = new Domacinstvo();
		d1.setMesto("Kragujevac");
		d1.setBrojOdraslih(2);
		d1.setBrojDece(2);
		d1.setMesecnaPrimanja(100000);
		
		Domacinstvo d2 = new Domacinstvo();
		d2.setMesto("Uzice");
		d2.setBrojOdraslih(2);
		d2.setBrojDece(3);
		d2.setMesecnaPrimanja(0);
		
		Domacinstvo d3 = new Domacinstvo();
		d3.setMesto("Prijepolje");
		d3.setBrojOdraslih(2);
		d3.setBrojDece(4);
		d3.setMesecnaPrimanja(0);
		
		@SuppressWarnings("unchecked")
		List<Domacinstvo> domacinstva = (List<Domacinstvo>) TestUtil.getFieldValue(instance, "domacinstva");
		domacinstva.add(d1);
		domacinstva.add(d2);
		domacinstva.add(d3);
		
		instance.statistikaDomacinstava();
		
		String sadrzajIzvestaja = ucitajIVratiTekst("izvestaj.txt");
		
		String[] redovi = sadrzajIzvestaja.split("\n");
		
		assertTrue("Kada se izgenerise izvestaj za popis u kojem se nalaze domacinstva: Kragujevac (odrasli: 2, dece: 2, primanja 100000), Uzice (odrasli: 2, dece: 3, primanja 0), Prijepolje (odrasli: 2, dece: 4, primanja 0), u prvom redu se ne nalazi ukupan broj domacinstava.",
				redovi[0].contains("3"));
		assertTrue("Kada se izgenerise izvestaj za popis u kojem se nalaze domacinstva: Kragujevac (odrasli: 2, dece: 2, primanja 100000), Uzice (odrasli: 2, dece: 3, primanja 0), Prijepolje (odrasli: 2, dece: 4, primanja 0), u drugom redu se ne nalazi prosecan iznos mesecnih primanja po domacinstvu.",
				redovi[1].contains("33333.333333333336"));
		assertTrue("Kada se izgenerise izvestaj za popis u kojem se nalaze domacinstva: Kragujevac (odrasli: 2, dece: 2, primanja 100000), Uzice (odrasli: 2, dece: 3, primanja 0), Prijepolje (odrasli: 2, dece: 4, primanja 0), u trecem redu se ne nalazi prosecan broj odraslih po domacinstvu.",
				redovi[2].contains("2.0"));
		assertTrue("Kada se izgenerise izvestaj za popis u kojem se nalaze domacinstva: Kragujevac (odrasli: 2, dece: 2, primanja 100000), Uzice (odrasli: 2, dece: 3, primanja 0), Prijepolje (odrasli: 2, dece: 4, primanja 0), u cetvrtom redu se ne nalazi prosecan broj dece po domacinstvu.",
				redovi[3].contains("3.0"));
		assertTrue("Kada se izgenerise izvestaj za popis u kojem se nalaze domacinstva: Kragujevac (odrasli: 2, dece: 2, primanja 100000), Uzice (odrasli: 2, dece: 3, primanja 0), Prijepolje (odrasli: 2, dece: 4, primanja 0), u petom redu se ne nalazi prosecan iznos mesecnih primanja po clanu domacinstva.",
				redovi[4].contains("6666.666666666667"));
		
		// brisemo kreirani fajl
		new File("izvestaj.txt").delete();
	}
	
	@Test
	public void metoda_ucitajDomacinstvaSaTastature() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("3\n");
		
		for (int i = 0; i < 3; i++) {
			buffer.append("Kragujevac\n2\n2\n100000\n");
		}
		
		ByteArrayInputStream in = new ByteArrayInputStream(buffer.toString().getBytes());
		System.setIn(in);
				
		instance.ucitajDomacinstvaSaTastature();
		
		@SuppressWarnings("unchecked")
		List<Domacinstvo> domacinstva = (List<Domacinstvo>) TestUtil.getFieldValue(instance, "domacinstva");
		
		assertEquals("Kada se 3 puta na tastaturi ispravno unesu podaci o domacinstvima, lista domacinstva nema 3 elementa.", 3, domacinstva.size());
	}
	
	@Test
	public void metoda_ucitajDomacinstvaSaTastature_saJednomGreskom() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("3\n");
		
		// ispravno
		buffer.append("Kragujevac\n2\n2\n100000\n");
		// greska
		buffer.append("Kragujevac\nbla\n");
		
		// ispravno
		buffer.append("Kragujevac\n2\n2\n100000\n");
		// ispravno
		buffer.append("Kragujevac\n2\n2\n100000\n");
		
		ByteArrayInputStream in = new ByteArrayInputStream(buffer.toString().getBytes());
		System.setIn(in);
		
		instance.ucitajDomacinstvaSaTastature();
		
		@SuppressWarnings("unchecked")
		List<Domacinstvo> domacinstva = (List<Domacinstvo>) TestUtil.getFieldValue(instance, "domacinstva");
		
		assertEquals("Kada se na tastaturi unose podaci o tri domacinstva i jednom se pogresi unos, a tri puta uspesno obavi, lista domacinstva nema 3 elementa.", 3, domacinstva.size());
	}
	
	@Test
	public void metoda_ucitajDomacinstvaSaTastature_saDveGreske() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("3\n");
		
		// ispravno
		buffer.append("Kragujevac\n2\n2\n100000\n");
		// greska
		buffer.append("Kragujevac\nbla\n");
		buffer.append("Kragujevac\nbla\n");
		
		// ispravno
		buffer.append("Kragujevac\n2\n2\n100000\n");
		
		ByteArrayInputStream in = new ByteArrayInputStream(buffer.toString().getBytes());
		System.setIn(in);
		
		instance.ucitajDomacinstvaSaTastature();
		
		@SuppressWarnings("unchecked")
		List<Domacinstvo> domacinstva = (List<Domacinstvo>) TestUtil.getFieldValue(instance, "domacinstva");
		
		assertEquals("Kada se na tastaturi unose podaci o tri domacinstva i dva puta se pogresi unos za isto domacinstvo, a jednom se uspesno obavi, lista domacinstva nema 1 element.", 1, domacinstva.size());
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
