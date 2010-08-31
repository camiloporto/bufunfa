/**
 * 
 */
package br.com.bufunfa.finance.wui;

import java.io.Serializable;

import javax.annotation.Resource;

/**
 * @author camilo
 *
 */
@Resource(name="UIConta")
public class UIConta implements Serializable {
	
	private boolean contaActive = false;
	
	/**
	 * TODO Refatorar .xhtmls para alterar conteudo principal da pagina atraves do atributo "rendered"
	 * e nao com navegacao JSF. Nos templates, inserir tags "include" com atributo rendered de acordo com
	 * a tela que deve ser apresentada		cd
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 3637894640907136286L;
	
	
	public UIConta() {
		
	}
	
	public String goTransacao() {
		contaActive = false;
		System.out.println("UIConta.go to transacao");
		return null;
	}
	
	public String goConta() {
		contaActive = true;
		System.out.println("UIConta.go to conta");
		return null;
	}
	
	public boolean isContaActive() {
		return contaActive;
	}

}
