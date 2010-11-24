/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Service;

/**
 * Implementacao das operacoes de servico
 * de sistema de contas
 * 
 * @author camilo
 *
 */
@Service("sistemaContaService")
public class SistemaContaServiceImpl implements ISistemaContaService,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4660537375580065860L;
	
	/*
	 * (non-Javadoc)
	 * @see br.com.bufunfa.finance.conta.ISistemaContaService#getSaldoOperacionalCaixa(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public RelatorioSaldoCaixa getSaldoOperacionalCaixa(Long idSistemaConta, Date inicio, Date fim) {
		return null; //FIXME Implementar saldo de caixa
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.bufunfa.finance.conta.ISistemaContaService#getSaldoOperacional(java.lang.Long, java.util.Date)
	 */
	public BigDecimal getSaldoOperacional(Long idSistemaConta, Date referenceDate) {
		//FIXME Validar entradas (id do sistema, se o sistema existe, intervalo de datas etc..
		
		SistemaConta sistema = SistemaConta.findSistemaConta(idSistemaConta);
		if(sistema != null) {
			return sistema.getSaldoOperacional(referenceDate);
		}
		return null;//FIXME tratar validacao se nao achar o sistema. lancar erro
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.bufunfa.finance.conta.ISistemaContaService#getSaldoOperacional(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public BigDecimal getSaldoOperacional(Long idSistemaConta, Date inicio, Date fim) {
		//FIXME Validar entradas (id do sistema, se o sistema existe, intervalo de datas etc..
		
		SistemaConta sistema = SistemaConta.findSistemaConta(idSistemaConta);
		if(sistema != null) {
			return sistema.getSaldoOperacional(inicio, fim);
		}
		return null;//FIXME tratar validacao se nao achar o sistema. lancar erro
		
	}

	/* (non-Javadoc)
	 * @see br.com.bufunfa.finance.conta.ISistemaContaService#addSistemaConta(java.lang.String)
	 */
	@Override
	public void addSistemaConta(String nomeSistema) {
		SistemaConta novoSistema = new SistemaConta();
		novoSistema.setNome(nomeSistema);
		
		Conta rootConta = createHierarquiaInicial(nomeSistema);
		
		novoSistema.setIdContaRoot(rootConta.getId());
		
		novoSistema.persist();
	}
	
	/**
	 * Constroi a hierarquia inicial de contas de um sistema 
	 * de contas. A hierarquia consiste nas contas
	 * de receitas, despesas, ativos e passivos
	 * @param nomeContaRaiz nome da conta raiz
	 * @return hierarquia de conta
	 */
	private Conta createHierarquiaInicial(String nomeContaRaiz) {
		Conta rootConta = new Conta();
		rootConta.setNome(nomeContaRaiz);
		rootConta.persist();
		
		Conta receita = createContaReceita();
		Conta despesa = createContaDespesas();
		Conta ativo = createContaAtivo();
		Conta passivo = createContaPassivo();
		
		rootConta.addChild(receita);
		rootConta.addChild(despesa);
		rootConta.addChild(ativo);
		rootConta.addChild(passivo);
		
		
		return rootConta;
	}
	
	private Conta createContaReceita() {
		return createConta("br.com.bufunfa.finance.i18n.nomeContaReceita");
	}
	
	private Conta createContaDespesas() {
		return createConta("br.com.bufunfa.finance.i18n.nomeContaDespesa");
	}
	
	private Conta createContaAtivo() {
		return createConta("br.com.bufunfa.finance.i18n.nomeContaAtivo");
	}
	
	private Conta createContaPassivo() {
		return createConta("br.com.bufunfa.finance.i18n.nomeContaPassivo");
	}
	
	private Conta createConta(String nome) {
		Conta c = new Conta();
		c.setNome(nome);
		
		return c;
	}

}
