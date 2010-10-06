/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.util.Set;

/**
 * Interface do servico de conta
 * 
 * @author camilo
 *
 */
public interface IContaService {
	
	/**
	 * Recupera as contas raizes da hieraquia das contas.
	 * As contas raizes sao: Receita, Despesa, Ativo, Passivo 
	 * 
	 * @return contas reizes da hierarquia de contas
	 */
	public Set<ContaImpl> getRootContas();
	
	/**
	 * Adiciona uma nova conta na hierarquia
	 * de contas
	 * @param params
	 */
	public void addConta(ContaServiceParams params);

}
