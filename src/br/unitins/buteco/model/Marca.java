package br.unitins.buteco.model;


public enum Marca {

	EISENBAHN(1, "Eisenbahn"), 
	VELHOBARREIRO(2, "Velhobarreiro"),
	GALLO(3, "Gallo"),
	REDLABEL(4, "RedLabel"),
	C51(5, "51"),
	SKOL(6, "Skol");
	
	private int value;
	private String label;
	
	Marca(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	// retorna uma marca a partir de um valor inteiro
	public static Marca valueOf(int value) {
		for (Marca marca : Marca.values()) {
			if (marca.getValue() == value) {
				return marca;
			}
		}
		return null;
	}
}
