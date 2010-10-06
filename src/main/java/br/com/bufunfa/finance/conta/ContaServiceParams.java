/**
 * 
 */
package br.com.bufunfa.finance.conta;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

/**
 * Classe para armazenar os parametros
 * de entrada dos metodos de negocio
 * relacionado a {@link ContaImpl}
 * 
 * @author camilo
 *
 */
@RooJavaBean(settersByDefault=false)
@RooToString
@RooSerializable
public class ContaServiceParams {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5795045124261930342L;
	

	private String nome;
	
	private String descricao;
	
	private String nomePai;

	public ContaServiceParams(String nome, String descricao, String nomePai) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.nomePai = nomePai;
	}
	
	

}
