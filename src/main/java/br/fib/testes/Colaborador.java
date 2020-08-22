package br.fib.testes;

public class Colaborador {
	private int id;
	private String nome;
	private double salarioHora;
	private int qtdeDependentes;

	public Colaborador(String nome, double salarioHora, int qtdeDependentes) {
		this.nome = nome;
		this.salarioHora = salarioHora;
		this.qtdeDependentes = qtdeDependentes;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public double getSalarioHora() {
		return salarioHora;
	}

	public int getQtdeDependentes() {
		return qtdeDependentes;
	}
}

