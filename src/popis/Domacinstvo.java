package popis;

import java.io.Serializable;

public class Domacinstvo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String mesto;
	private int brojOdraslih;
	private int brojDece;
	private double mesecnaPrimanja;

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		if (mesto == null || mesto.length() < 5 || mesto.length() > 13) {
			throw new RuntimeException("Mesto ne moze biti null, kraci od pet znakova ili duzi od trinaest znakova.");
		}
		this.mesto = mesto;
	}

	public int getBrojOdraslih() {
		return brojOdraslih;
	}

	public void setBrojOdraslih(int brojOdraslih) {
		if (brojOdraslih < 0) {
			throw new RuntimeException("Broj odraslih ne moze biti manja od nule.");
		}
		this.brojOdraslih = brojOdraslih;
	}

	public int getBrojDece() {
		return brojDece;
	}

	public void setBrojDece(int brojDece) {
		if (brojDece < 0) {
			throw new RuntimeException("Broj dece ne moze biti manji od nule.");
		}
		this.brojDece = brojDece;
	}

	public double getMesecnaPrimanja() {
		return mesecnaPrimanja;
	}

	public void setMesecnaPrimanja(double mesecnaPrimanja) {
		if (mesecnaPrimanja < 0) {
			throw new RuntimeException("Mesecna primanja ne moze biti manji od nule.");
		}
		this.mesecnaPrimanja = mesecnaPrimanja;
	}

	@Override
	public String toString() {
		return "Mesto: " + mesto + "\t, broj odraslih: " + brojOdraslih + "\t, broj dece: " + brojDece
				+ "\t, mesecna primanja: " + mesecnaPrimanja;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Domacinstvo))
			return false;

		Domacinstvo d = (Domacinstvo) obj;

		return this.mesto.equals(d.mesto) && 
				this.brojOdraslih == d.brojOdraslih && 
				this.brojDece == d.brojDece && 
				this.mesecnaPrimanja == d.mesecnaPrimanja;
	}
}
