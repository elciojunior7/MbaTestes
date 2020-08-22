package br.fib.salario;

public class CalculadoraSalario {

	public double salarioBruto(RegistroMes registro) {
		return registro.getHorasTrabalhadas() * registro.getColaborador().getSalarioHora()
				+ (50 * registro.getColaborador().getQtdeDependentes());
	}

	public double descontoINSS(RegistroMes registro) {
		double bruto = salarioBruto(registro);
		if (bruto <= 1000.0)
			return bruto * 0.085;

		return bruto * 0.09;
	}

	public double descontoIR(RegistroMes registro) {
		double bruto = salarioBruto(registro);
		if (bruto <= 500.0)
			return 0;
		else if (bruto > 500.0 && bruto <= 1000.0)
			return bruto * 0.05;

		return bruto * 0.07;
	}

	public double salarioLiquido(RegistroMes registro) {
		double bruto = salarioBruto(registro);
		return bruto - descontoINSS(registro) - descontoIR(registro);
	}

}

