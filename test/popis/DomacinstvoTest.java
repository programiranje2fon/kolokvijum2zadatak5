package popis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.TestUtil;

public class DomacinstvoTest {

	private Domacinstvo instance;

	@Before
	public void setUp() throws Exception {
		instance = new Domacinstvo();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}
	
	@Test
	public void atribut_mesto() {
		assertTrue("U klasi nije definisan atribut mesto", TestUtil.doesFieldExist(Domacinstvo.class, "mesto"));
	}
	
	@Test
	public void atribut_mesto_vidljivost() {
		assertTrue("Atribut mesto nije privatan", TestUtil.hasFieldModifier(Domacinstvo.class, "mesto", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_brojOdraslih() {
		assertTrue("U klasi nije definisan atribut brojOdraslih", TestUtil.doesFieldExist(Domacinstvo.class, "brojOdraslih"));
	}
	
	@Test
	public void atribut_brojOdraslih_vidljivost() {
		assertTrue("Atribut brojOdraslih nije privatan", TestUtil.hasFieldModifier(Domacinstvo.class, "brojOdraslih", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_brojDece() {
		assertTrue("U klasi nije definisan atribut brojDece", TestUtil.doesFieldExist(Domacinstvo.class, "brojDece"));
	}
	
	@Test
	public void atribut_brojDece_vidljivost() {
		assertTrue("Atribut brojDece nije privatan", TestUtil.hasFieldModifier(Domacinstvo.class, "brojDece", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_mesecnaPrimanja() {
		assertTrue("U klasi nije definisan atribut mesecnaPrimanja", TestUtil.doesFieldExist(Domacinstvo.class, "mesecnaPrimanja"));
	}
	
	@Test
	public void atribut_mesecnaPrimanja_vidljivost() {
		assertTrue("Atribut mesecnaPrimanja nije privatan", TestUtil.hasFieldModifier(Domacinstvo.class, "mesecnaPrimanja", Modifier.PRIVATE));
	}
	
	@Test
	public void metoda_setMesto() throws Exception {
		instance.setMesto("Kragujevac");
		String mesto = (String) TestUtil.getFieldValue(instance, "mesto");
		assertEquals("Nakon poziva metode setMesto(String) sa prosledjenim argumentom \"Kragujevac\", vrednost atributa mesto se nije promenila na tu vrednost", "Kragujevac", mesto);
	}
	
	@Test(expected = RuntimeException.class)
	public void metoda_setMesto_null() throws Exception {
		instance.setMesto(null);
	}
	
	@Test(expected = RuntimeException.class)
	public void metoda_setMesto_kraceOd5Karaktera() throws Exception {
		instance.setMesto("Sid");
	}
	
	@Test(expected = RuntimeException.class)
	public void metoda_setMesto_duzeOd13Karaktera() throws Exception {
		instance.setMesto("KragujevacKragujevac");
	}
	
	@Test
	public void metoda_getMesto() {
		String mesto = (String) TestUtil.getFieldValue(instance, "mesto");

		assertEquals("Metoda getMesto() ne vraca vrednost atributa mesto", mesto, instance.getMesto());
	}
	
	@Test
	public void metoda_setBrojOdraslih() throws Exception {
		instance.setBrojOdraslih(2);
		int brojOdraslih = (int) TestUtil.getFieldValue(instance, "brojOdraslih");
		assertEquals("Nakon poziva metode setBrojOdraslih(int) sa prosledjenim argumentom \"2\", vrednost atributa brojOdraslih se nije promenila na tu vrednost", 2, brojOdraslih);
	}
	
	@Test(expected = RuntimeException.class)
	public void metoda_setBrojOdraslih_manjeOdNula() throws Exception {
		instance.setBrojOdraslih(-1);
	}
	
	@Test
	public void metoda_getBrojOdraslih() {
		int brojOdraslih = (int) TestUtil.getFieldValue(instance, "brojOdraslih");

		assertEquals("Metoda getBrojOdraslih() ne vraca vrednost atributa brojOdraslih", brojOdraslih, instance.getBrojOdraslih());
	}
	
	@Test
	public void metoda_setBrojDece() throws Exception {
		instance.setBrojDece(2);
		int brojDece = (int) TestUtil.getFieldValue(instance, "brojDece");
		assertEquals("Nakon poziva metode setBrojDece(int) sa prosledjenim argumentom \"2\", vrednost atributa brojDece se nije promenila na tu vrednost", 2, brojDece);
	}
	
	@Test(expected = RuntimeException.class)
	public void metoda_setBrojDece_manjeOdNula() throws Exception {
		instance.setBrojDece(-1);
	}
	
	@Test
	public void metoda_getBrojDece() {
		int brojDece = (int) TestUtil.getFieldValue(instance, "brojDece");
		
		assertEquals("Metoda getBrojDece() ne vraca vrednost atributa brojDece", brojDece, instance.getBrojDece());
	}
	
	@Test
	public void metoda_setMesecnaPrimanja() throws Exception {
		instance.setMesecnaPrimanja(100000);
		double mesecnaPrimanja = (double) TestUtil.getFieldValue(instance, "mesecnaPrimanja");
		assertEquals("Nakon poziva metode setMesecnaPrimanja(int) sa prosledjenim argumentom \"100000\", vrednost atributa mesecnaPrimanja se nije promenila na tu vrednost", 100000, mesecnaPrimanja, 0.001);
	}
	
	@Test(expected = RuntimeException.class)
	public void metoda_setMesecnaPrimanja_manjeOdNula() throws Exception {
		instance.setMesecnaPrimanja(-1);
	}
	
	@Test
	public void metoda_getMesecnaPrimanja() {
		double mesecnaPrimanja = (double) TestUtil.getFieldValue(instance, "mesecnaPrimanja");
		
		assertEquals("Metoda getMesecnaPrimanja() ne vraca vrednost atributa mesecnaPrimanja", mesecnaPrimanja, instance.getMesecnaPrimanja(), 0.001);
	}
	
	@Test
	public void metoda_equals_nijeDobraKlasa() {
		assertFalse("Metoda equals() ne vraca false ako se prosledi objekat druge klase.", instance.equals(new Object()));
	}
	
	@Test
	public void metoda_equals_isti() {
		instance.setMesto("Kragujevac");
		instance.setBrojOdraslih(2);
		instance.setBrojDece(2);
		instance.setMesecnaPrimanja(100000);
		
		Domacinstvo d1 = new Domacinstvo();
		d1.setMesto("Kragujevac");
		d1.setBrojOdraslih(2);
		d1.setBrojDece(2);
		d1.setMesecnaPrimanja(100000);
		
		assertEquals("Metoda equals() ne vraca vrednost true za prosledjeno domacinstvo sa istim mestom, broj odraslih, brojem dece i iznosom mesecnih primanja.", d1, instance);
	}
	
	@Test
	public void metoda_equals_razlicitoMesto() {
		instance.setMesto("Kragujevac");
		instance.setBrojOdraslih(2);
		instance.setBrojDece(2);
		instance.setMesecnaPrimanja(100000);
		
		Domacinstvo d1 = new Domacinstvo();
		d1.setMesto("Novi Sad");
		d1.setBrojOdraslih(2);
		d1.setBrojDece(2);
		d1.setMesecnaPrimanja(100000);
		
		assertNotEquals("Metoda equals() ne vraca vrednost false za prosledjeno domacinstvo sa razlicitim mestom.", d1, instance);
	}
	
	
	@Test
	public void metoda_equals_razlicitBrojOdraslih() {
		instance.setMesto("Kragujevac");
		instance.setBrojOdraslih(2);
		instance.setBrojDece(2);
		instance.setMesecnaPrimanja(100000);
		
		Domacinstvo d1 = new Domacinstvo();
		d1.setMesto("Kragujevac");
		d1.setBrojOdraslih(1);
		d1.setBrojDece(2);
		d1.setMesecnaPrimanja(100000);
		
		assertNotEquals("Metoda equals() ne vraca vrednost false za prosledjeno domacinstvo sa razlicitim brojem odraslih.", d1, instance);
	}
	
	
	@Test
	public void metoda_equals_razlicitBrojDece() {
		instance.setMesto("Kragujevac");
		instance.setBrojOdraslih(2);
		instance.setBrojDece(2);
		instance.setMesecnaPrimanja(100000);
		
		Domacinstvo d1 = new Domacinstvo();
		d1.setMesto("Kragujevac");
		d1.setBrojOdraslih(2);
		d1.setBrojDece(3);
		d1.setMesecnaPrimanja(100000);
		
		assertNotEquals("Metoda equals() ne vraca vrednost false za prosledjeno domacinstvo sa razlicitim brojem dece.", d1, instance);
	}
	
	
	@Test
	public void metoda_equals_razlicitaMesecnaPrimanja() {
		instance.setMesto("Kragujevac");
		instance.setBrojOdraslih(2);
		instance.setBrojDece(2);
		instance.setMesecnaPrimanja(100000);
		
		Domacinstvo d1 = new Domacinstvo();
		d1.setMesto("Kragujevac");
		d1.setBrojOdraslih(2);
		d1.setBrojDece(2);
		d1.setMesecnaPrimanja(90000);
		
		assertNotEquals("Metoda equals() ne vraca vrednost false za prosledjeno domacinstvo sa razlicitim mesecnim primanjima.", d1, instance);
	}
}
