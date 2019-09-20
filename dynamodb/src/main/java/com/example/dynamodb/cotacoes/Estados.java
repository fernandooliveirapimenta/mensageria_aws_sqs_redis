package com.example.dynamodb.cotacoes;

public enum Estados {

	RO(11,"Rondonia"),
	AC(12,"Acre"),
	AM(13,"Amazonas"),
	RR(14,"Roraima"),
	PA(15,"Para"),
	AP(16,"Amapa"),
	TO(17,"Tocantins"),
	MA(21,"Maranhao"),
	PI(22,"Piaui"),
	CE(23,"Ceara"),
	RN(24,"Rio Grande do Norte"),
	PB(25,"Paraiba"),
	PE(26,"Pernambuco"),
	AL(27,"Alagoas"),
	SE(28,"Sergipe"),
	BA(29,"Bahia"),
	MG(31,"Minas Gerais"),
	ES(32,"Espirito Santo"),
	RJ(33,"Rio de Janeiro"),
	SP(35,"Sao Paulo"),
	PR(41,"Parana"),
	SC(42,"Santa Catarina"),
	RS(43,"Rio Grande do Sul"),
	MS(50,"Mato Grosso do Sul"),
	MT(51,"Mato Grosso"),
	GO(52,"Goias"),
	DF(53,"Distrito Federal");

	private final int codigoIbge;
	private final String nome;

	private Estados(int codigoIbge,String nome) {
		this.codigoIbge = codigoIbge;
		this.nome = nome;
	}

	public static Estados porNome(String nome) {
		final Estados[] values = Estados.values();
		for (Estados value : values) {
			if(value.getNome().toUpperCase().equals(nome.toUpperCase())) {
				return value;
			}
		}
		return Estados.SP;
	}
	
	public String getCodigo() {
		return Integer.toString(codigoIbge);
	}
	
	public String getNome(){
		return nome;
	}
	
	public int getCodigoIbge(){
		return codigoIbge;
	}

}