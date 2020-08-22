package br.fib.testes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {
	private String descricao;
	private List<Lance> lances;

	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}

	public void propoe(Lance lance) {
		if (lances.isEmpty() || podeDarLance(lance)) {
			lances.add(lance);
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	private Lance ultimoLanceDado() {
		return lances.get(lances.size() - 1);
	}

	private boolean podeDarLance(Lance lance) {
		return !ultimoLanceDado().getUsuario().equals(lance.getUsuario()) && maxCincoLancesDoUsuario(lance) < 5;
	}

	private int maxCincoLancesDoUsuario(Lance lance) {
		return (int) lances.stream().filter(l -> l.getUsuario().equals(lance.getUsuario())).count();
	}
}
