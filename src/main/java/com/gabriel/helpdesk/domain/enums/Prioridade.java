package com.gabriel.helpdesk.domain.enums;

public enum Prioridade {

	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");
	
	private Integer codigo;
	private String descricao;
	
	private Prioridade(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Prioridade toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(Prioridade ePerfil : Prioridade.values()) {
			if(codigo.equals(ePerfil.getCodigo())) {
				return ePerfil;
			}
		}
		
		throw new IllegalArgumentException("Prioridade inválido");	
	}
		
}
