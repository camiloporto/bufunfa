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
	
	/**
	 * Calcula o saldo operacional de um sistema de contas
	 * ateh determinada data
	 * @param idSistemaConta identificador do sistema de contas
	 * @param referenceDate final do periodo
	 * @return saldo operacional ateh a data informada
	 */
	public BigDecimal getSaldoOperacional(Long idSistemaConta, Date referenceDate);
	
	/**
	 * Retorna um relatorio de saldo operacional de caixa
	 * de um determinado periodo
	 * @param idSistemaConta id do sistema de contas
	 * @param inicio inicio do periodo
	 * @param fim final do periodo
	 * @return saldo operacional de caixa do periodo
	 */
	public RelatorioSaldoCaixa getSaldoOperacionalCaixa(Long idSistemaConta, Date inicio, Date fim);
	
	
	/**
	 * Retorna um relatorio de balanco patrimonial
	 * @param idSistemaConta id do sistema de contas
	 * @param inicio inicio do periodo do balanco
	 * @param fim final do periodo do balanco
	 * @return balanco patrimonial do periodo
	 */
	public BalancoPatrimonial getBalancoPatrimonial(Long idSistemaConta,
			Date inicio, Date fim);

}
