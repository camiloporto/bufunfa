/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

/**
 * Relatorio de saldo operacional de caixa
 * 
 * Consiste em calcular, o saldo do sistema de conta
 * na data anterior ao periodo, o saldo operacional
 * (receitas - despesas) do periodo e verificar
 * o que foi acrescentado (ou subtraido) do saldo
 * anterior.
 * 
 * @author camilo
 *
 */
@RooSerializable
@RooJavaBean
@RooToString
public class RelatorioSaldoCaixa {
	
	private Date inicioPeriodo;
	
	private Date fimPeriodo;
	
	private BigDecimal saldoAnterior;
	
	private BigDecimal saldoOperacionalDoPeriodo;
	
	public RelatorioSaldoCaixa() {
		
	}

	public RelatorioSaldoCaixa(Date inicioPeriodo, Date fimPeriodo,
			BigDecimal saldoAnterior, BigDecimal saldoOperacionalDoPeriodo) {
		super();
		this.inicioPeriodo = inicioPeriodo;
		this.fimPeriodo = fimPeriodo;
		this.saldoAnterior = saldoAnterior;
		this.saldoOperacionalDoPeriodo = saldoOperacionalDoPeriodo;
	}

	public BigDecimal getSaldoPosterior() {
		return getSaldoAnterior().setScale(2, RoundingMode.HALF_EVEN).add(getSaldoOperacionalDoPeriodo());
	}
	
	

}
