package br.fib.testes;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.fib.bancoteste.Banco;
import br.fib.bancoteste.Conta;
import br.fib.bancoteste.ContaBuilder;
import br.fib.bancoteste.ContaDao;
import br.fib.bancoteste.UsuarioBanco;

public class BancoTest {

	private Conta c1;
	private Conta c2;
	private UsuarioBanco joao;
	private UsuarioBanco manoel;

	@Before
	public void criarAmbiente() {
		c1 = new Conta(100.0, "Basica");
		c2 = new Conta(200.0, "Prime");
		joao = new UsuarioBanco("Joao da Silva", "111111111", "joaosilva@mailinator.com");
		manoel = new UsuarioBanco("Manoel da Silva", "22222222", "manoelsilva@mailinator.com");
	}

	@Test
	public void deveSomarTodasAsContasTrazendoOSaldoDoBanco() {
		List<Conta> contas = new ContaBuilder().addConta(c1, joao).addConta(c2, manoel).constroi();

		ContaDao dao = mock(ContaDao.class); // mockar a classe Dao para não chamar a Dao que na vdd chamaria uma conexao com BD que não existe
		when(dao.getContas()).thenReturn(contas);

		dao.salvaConta(contas.get(0));
		dao.salvaConta(contas.get(1));
		Banco banco = new Banco(dao);
		Assert.assertEquals(2, banco.getContas().size(), 0.00001);
		Assert.assertEquals(300, banco.totalSaldo(), 0.00001);
	}

	@Test
	public void deveAtualizarAsContasComJurosAplicados() {
		List<Conta> contas = new ContaBuilder().addConta(c1, joao).addConta(c2, manoel).constroi();

		ContaDao dao = mock(ContaDao.class);
		when(dao.getContas()).thenReturn(contas);

		Banco banco = new Banco(dao);
		banco.atualizaJuros(5);

		// verificando se o metodo atualizaConta foi realmente invocado!
		verify(dao, times(1)).atualizaConta(contas.get(0));
		Assert.assertEquals(615.0, banco.totalSaldo(), 0.00001);
	}

}
