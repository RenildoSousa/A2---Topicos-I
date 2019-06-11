package br.unitins.buteco.model;

public enum Categoria {
	
	DESTILADOS(1, "Destilados"), 
	CERVEJA(2, "Cerveja"), 
	VINHO(3, "Vinhos"), 
	COQUETÉIS(4, "Coquetéis");
	
	private int value;
	private String label;
	
	Categoria(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	// retorna uma categoria a partir de um valor inteiro
	public static Categoria valueOf(int value) {
		for (Categoria cat : Categoria.values()) {
			if (cat.getValue() == value) {
				return cat;
			}
		}
		return null;
	}

}
