/**
 * 
 */
package br.com.bufunfa.finance.conta.event;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationEvent;
import org.springframework.roo.addon.javabean.RooJavaBean;

import br.com.bufunfa.finance.conta.ContaPresenter;

/**
 * Evento base de callback 
 * para da feedback sobre os eventos 
 * relacionados a Conta
 * 
 * @author camilo
 * 
 * @see ContaEvent
 *
 */
@RooJavaBean
public class ContaEventCallback<T extends ContaEvent> extends ApplicationEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6563211133067887810L;
	/**
	 * TODO Colocar aqui mensagens de erros ocorridas e outras
	 * informacoes uteis
	 */
	
	private List<String> errorMessages = new ArrayList<String>();
	
	private T sourceEvent;

	public ContaEventCallback(Object source, List<String> errorMessages, T srcEvent) {
		super(source);
		if(errorMessages != null)
			this.errorMessages = errorMessages;
		this.sourceEvent = srcEvent;
	}
	
	

}
