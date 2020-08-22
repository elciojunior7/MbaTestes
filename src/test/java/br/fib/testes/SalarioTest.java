package br.fib.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.fib.salario.CalculadoraSalario;
import br.fib.salario.RegistroMes;
import br.fib.salario.SalariosBuilder;

public class SalarioTest {

	Colaborador john;
	Colaborador joseph;
	Colaborador mary;
	Colaborador margot;

	CalculadoraSalario calculadora;

	@Before
	public void init() {
		mary = new Colaborador("Mariah", 10.0, 0);
		john = new Colaborador("John", 32.0, 2);
		joseph = new Colaborador("Joseph", 80.0, 0);

		calculadora = new CalculadoraSalario();
	}

	@Test
	public void deveRetornarSalarioBruto() {
		List<RegistroMes> listReg = new SalariosBuilder()
				.para(john, 200.5)
				.para(joseph, 111.0)
				.constroi();

		Assert.assertEquals(6516.0, calculadora.salarioBruto(listReg.get(0)), 0.00001);
		Assert.assertEquals(8880.0, calculadora.salarioBruto(listReg.get(1)), 0.00001);
	}

	@Test
	public void deveRetornarINSS() {
		List<RegistroMes> listReg = new SalariosBuilder()
				.para(mary, 90.5)
				.para(joseph, 111.0)
				.constroi();

		Assert.assertEquals(76.9250, calculadora.descontoINSS(listReg.get(0)), 0.00001);
		Assert.assertEquals(799.2, calculadora.descontoINSS(listReg.get(1)), 0.00001);
	}

	@Test
	public void deveRetornarIR() {
		List<RegistroMes> listReg = new SalariosBuilder()
				.para(mary, 90.5)
				.para(joseph, 111.0)
				.para(john, 10.5)
				.constroi();

		Assert.assertEquals(45.25, calculadora.descontoIR(listReg.get(0)), 0.00001);
		Assert.assertEquals(621.6, calculadora.descontoIR(listReg.get(1)), 0.00001);
		Assert.assertEquals(0, calculadora.descontoIR(listReg.get(2)), 0.00001);
	}

	@Test
	public void deveRetornarSalarioLiquido() {
		List<RegistroMes> listReg = new SalariosBuilder()
				.para(mary, 220.5)
				.para(joseph, 111.0)
				.para(john, 110.5)
				.constroi();

		Assert.assertEquals(1852.2, calculadora.salarioLiquido(listReg.get(0)), 0.00001);
		Assert.assertEquals(7459.2, calculadora.salarioLiquido(listReg.get(1)), 0.00001);
		Assert.assertEquals(3054.240, calculadora.salarioLiquido(listReg.get(2)), 0.00001);
	}

}
