package br.fib.bancoteste;

import java.util.List;

public interface Dao {
	public void salvaConta(Conta conta);

	public List<Conta> getContas();

	public void atualizaConta(Conta conta);
}
