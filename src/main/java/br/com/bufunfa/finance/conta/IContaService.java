/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.math.BigDecimal;
import java.util.Date;
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
	public Set<Conta> getRootContas();
	
	/**
	 * Adiciona uma nova conta na hierarquia
	 * de contas
	 * @param params
	 */
	public void addConta(ContaServiceParams params);
	
	/**
	 * Adiciona uma transacao a uma conta
	 * @param origem conta de origem (para retirar a quantidade)
	 * @param destino conta de destino (a adicionar a quantidade)
	 * @param quantidade quantidade a ser lancada nas contas
	 * @param descricao descricao da transacao
	 * @param dataEfetivacao data para a transacao ser efetivada (pode ser uma data futura
	 */
	public void addTransacao(Conta origem, Conta destino, BigDecimal quantidade, String descricao, Date dataEfetivacao);
	
	/**
	 * Retorna o extrato de uma conta em determinado periodo
	 * @param idConta id da conta
	 * @param inicio inicio do periodo
	 * @param fim final do periodo
	 * @return extrato da conta no periodo informado
	 */
	public Extrato getExtrato(Long idConta, Date inicio, Date fim);

}
