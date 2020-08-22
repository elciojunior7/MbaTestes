package br.fib.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AvaliadorTest {

	Avaliador avaliador;
	Usuario joao;
	Usuario jose;
	Usuario maria;
	Usuario margareth;
	String ps3;
	String ps5;
	Leilao leilaoPS3;
	Leilao leilaoPS5;

	@Before
	public void init() {
		joao = new Usuario("Joao");
		jose = new Usuario("José");
		maria = new Usuario("Maria");
		margareth = new Usuario("Margareth");
		avaliador = new Avaliador();
		ps3 = "Playstation 3 Novo";
		ps5 = "Playstation 5";
	}

	@Test
	public void deveRetornarOMaiorEMenorLance() {
		leilaoPS3 = new CriadorDeLeilao().para(ps3)
			.lance(joao, 300.0)
			.lance(maria, 250.0)
			.lance(jose, 400.0)
			.constroi();

		avaliador.avalia(leilaoPS3);

		Assert.assertEquals(400, avaliador.getMaiorLance(), 0.00001);
		Assert.assertEquals(250, avaliador.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescenteComOutrosValores() {
		leilaoPS3 = new CriadorDeLeilao().para(ps3)
			.lance(joao, 1000.0)
			.lance(maria, 2000.0)
			.lance(jose, 3000.0)
			.constroi();

		avaliador.avalia(leilaoPS3);

		Assert.assertEquals(3000, avaliador.getMaiorLance(), 0.00001);
		Assert.assertEquals(1000, avaliador.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEntenderLancesEmOrdemDecrescenteComOutrosValores() {
		leilaoPS3 = new CriadorDeLeilao().para(ps3)
			.lance(maria, 3000.0)
			.lance(jose, 2000.0)
			.lance(joao, 1000.0)
			.constroi();

		avaliador.avalia(leilaoPS3);

		Assert.assertEquals(3000, avaliador.getMaiorLance(), 0.00001);
		Assert.assertEquals(1000, avaliador.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEntenderUnicoLance() {
		leilaoPS3 = new CriadorDeLeilao().para(ps3).lance(jose, 2000.0).constroi();

		avaliador.avalia(leilaoPS3);

		Assert.assertEquals(2000, avaliador.getMaiorLance(), 0.00001);
		Assert.assertEquals(2000, avaliador.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		leilaoPS3 = new CriadorDeLeilao().para(ps3)
			.lance(maria, 3000.0)
			.lance(jose, 2000.0)
			.lance(joao, 1000.0)
			.lance(jose, 4000.0)
			.constroi();

		avaliador.avalia(leilaoPS3);

		List<Lance> maiores = avaliador.getTresMaiores();
		Assert.assertEquals(3, maiores.size());
	}

	@Test
	public void naoDeveAceitarDoisLancesSeguidosdoMesmoUsuario() {
		leilaoPS5 = new CriadorDeLeilao().para(ps5)
			.lance(jose, 2000.0)
			.lance(jose, 1000.0)
			.constroi();

		Assert.assertEquals(1, leilaoPS5.getLances().size());
		Assert.assertEquals(2000, leilaoPS5.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void naoDeveAceitarMaisQueCincoLancesDoMesmoUsuario() {
		leilaoPS5 = new CriadorDeLeilao().para(ps5)
			.lance(jose, 2000.0)
			.lance(margareth, 1000.0)
			.lance(jose, 4000.0)
			.lance(margareth, 5000.0)
			.lance(jose, 6000.0)
			.lance(margareth, 7000.0)
			.lance(margareth, 8000.0)
			.lance(jose, 9000.0)
			.lance(jose, 10000.0)
			.lance(margareth, 11000.0)
			.lance(jose, 2000.0)
			.lance(margareth, 13000.0)
			.lance(jose, 14000.0) // Nao deve ser incluso
			.constroi();

		Assert.assertEquals(10, leilaoPS5.getLances().size());
		int ultimo = leilaoPS5.getLances().size() - 1;
		Lance ultimoLance = leilaoPS5.getLances().get(ultimo);
		Assert.assertEquals(13000, ultimoLance.getValor(), 0.00001);
	}

}
