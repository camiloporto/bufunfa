/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.io.Serializable;

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
