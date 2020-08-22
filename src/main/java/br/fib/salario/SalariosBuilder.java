package br.fib.salario;

import java.util.ArrayList;
import java.util.List;

import br.fib.testes.Colaborador;

public class SalariosBuilder {
	private List<RegistroMes> listRegMes = new ArrayList<RegistroMes>();

	public SalariosBuilder() {
	}

	public SalariosBuilder para(Colaborador col, double horasTrabalhadas) {
		this.listRegMes.add(new RegistroMes(col, horasTrabalhadas));
		return this;
	}

	public List<RegistroMes> constroi() {
		return listRegMes;
	}
}
