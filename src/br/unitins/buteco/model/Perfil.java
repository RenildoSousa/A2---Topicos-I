package br.unitins.buteco.model;


public enum Perfil {
	
	ADMINISTRADOR(1, "Socio"), 
	GERENTE(2, "Gerente"), 
	CLIENTE(3, "Cliente"), 
	GARÇOM(4, "Garçom");

	private int value;
	private String label;
	
	Perfil(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	// retorna um perfil a partir de um valor inteiro
	public static Perfil valueOf(int value) {
		for (Perfil perfil : Perfil.values()) {
			if (perfil.getValue() == value) {
				return perfil;
			}
		}
		return null;
	}
	
}

