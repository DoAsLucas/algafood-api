package com.algaworks.algafood.model;

public class Cliente {

	private String nome;
	private String email;
	private String telefone;
	private Boolean ativo;

	public Cliente(String nome, String email, String telefone) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public String getTelefone() {
		return telefone;
	}
	public Boolean isAtivo() {
		return ativo;
	}

	public void ativar() {
		this.ativo = Boolean.TRUE;
	}
}
