package com.example.ordermServico.entities.enums;

public enum Status {

	ABERTO(1, "Aberto"), 
	FINALIZADO(2, "Finalizado");

	private int cod;
	private String descricao;

	private Status(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Status toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Status x : Status.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
