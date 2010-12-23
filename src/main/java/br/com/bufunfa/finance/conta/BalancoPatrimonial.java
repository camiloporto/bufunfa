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
 * Classe que armazena informacoes resumidas
 * de um balanco patrimonial
 * 
 * @author camilo
 *
 */

@RooJavaBean(settersByDefault=false)
@RooToString
@RooSerializable
public class BalancoPatrimonial {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6787770165502233495L;

	private BigDecimal ativo;
	
	private BigDecimal passivo;
	
	private Date inicioPeriodo;
	
	private Date finalPeriodo;

	public BalancoPatrimonial(BigDecimal ativo, BigDecimal passivo,
			Date inicioPeriodo, Date finalPeriodo) {
		super();
		this.ativo = ativo;
		this.passivo = passivo;
		this.inicioPeriodo = inicioPeriodo;
		this.finalPeriodo = finalPeriodo;
	}
	
	public BigDecimal getPatrimonioLiquido() {
		return ativo.add(passivo).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	

}
