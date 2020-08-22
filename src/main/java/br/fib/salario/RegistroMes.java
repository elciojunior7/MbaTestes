package br.fib.salario;

import br.fib.testes.Colaborador;

public class RegistroMes {
	private Colaborador colaborador;
	private double horasTrabalhadas;

	public RegistroMes(Colaborador colaborador, double horasTrabalhadas) {
		this.colaborador = colaborador;
		this.horasTrabalhadas = horasTrabalhadas;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public double getHorasTrabalhadas() {
		return horasTrabalhadas;
	}
}
