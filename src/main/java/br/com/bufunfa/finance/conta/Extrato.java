/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.roo.addon.serializable.RooSerializable;

/**
 * Extrato de uma conta. contem os
 * lancamentos de uma conta em determinado
 * periodo
 * 
 * @author camilo
 * @see Conta
 * @see Lancamento
 *
 */
@RooSerializable
public class Extrato {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7784305380193327756L;

	private Conta conta;
	
	private SortedSet<Lancamento> lancamentos;

	public Extrato(Conta conta, Collection<Lancamento> lancamentos) {
		super();
		if(conta == null) throw new IllegalArgumentException("br.com.bufunfa.finance.conta.contaRequired");
		if(lancamentos == null) throw new IllegalArgumentException("br.com.bufunfa.finance.conta.lancamentosRequired");
		
		this.conta = conta;
		this.lancamentos = new TreeSet<Lancamento>();
		this.lancamentos.addAll(lancamentos);
	}
	
	public BigDecimal getSaldo() {
		BigDecimal soma = new BigDecimal(0.0);
		for (Lancamento l : this.lancamentos) {
			soma = soma.add(l.getQuantidade());
		}
		
		return soma;
	}
	
	public Set<Lancamento> getLancamentos() {
		return Collections.unmodifiableSet(this.lancamentos);
	}
	
	

}
