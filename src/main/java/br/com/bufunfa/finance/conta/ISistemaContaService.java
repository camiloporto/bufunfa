/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Interface do servico de sistema de contas
 * 
 * @author camilo
 *
 */
public interface ISistemaContaService {
	
	/**
	 * Adiciona um novo sistema de contas
	 * @param nomeSistema nome do sistema de contas
	 * @TODO Acrescentar/alterar os parametros necessarios para se criar um sistema de contas?
	 */
	public void addSistemaConta(String nomeSistema);
	
	/**
	 * Calcula o saldo operacional de um sistema de contas
	 * em determinado periodo
	 * @param idSistemaConta identificador do sistema de contas
	 * @param inicio inicio do periodo (opcional)
	 * @param fim final do periodo
	 * @return saldo operacional do periodo
	 */
	public BigDecimal getSaldoOperacional(Long idSistemaConta, Date inicio, Date fim);

}
