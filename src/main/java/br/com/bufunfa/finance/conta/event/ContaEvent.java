/**
 * 
 */
package br.com.bufunfa.finance.conta.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

/**
 * Evento base de conta
 * contem informacoes base para todos
 * os eventos relacionados a conta
 * 
 * @author camilo
 *
 */
@RooJavaBean(settersByDefault=false)
@RooToString
public class ContaEvent extends ApplicationEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2015286599195436519L;
	
	public enum EventType {
		UILoaded,
		NewConta
	}
	
	private EventType eventType;

	private String nomeContaPai;
	
	private String nomeConta;
	
	private String descricaoConta;

	public ContaEvent(Object source, EventType eventType, String nomeContaPai,
			String nomeConta, String descricaoConta) {
		super(source);
		this.eventType = eventType;
		this.nomeContaPai = nomeContaPai;
		this.nomeConta = nomeConta;
		this.descricaoConta = descricaoConta;
	}

	
	
	

}
