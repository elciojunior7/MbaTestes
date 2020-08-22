package br.fib.bancoteste;

import java.util.ArrayList;
import java.util.List;

public class ContaBuilder {

	private List<Conta> contas;

	public ContaBuilder() {
		contas = new ArrayList<Conta>();
	}

	public ContaBuilder addConta(Conta conta, UsuarioBanco usuario) {
		conta.setUsuario(usuario);
		this.contas.add(conta);
		return this;
	}

	public List<Conta> constroi() {
		return this.contas;
	}

}
