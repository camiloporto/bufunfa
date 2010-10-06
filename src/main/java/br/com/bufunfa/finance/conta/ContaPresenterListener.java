/**
 * 
 */
package br.com.bufunfa.finance.conta;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import br.com.bufunfa.finance.wui.conta.UIConta;
import br.com.bufunfa.finance.wui.conta.UiContaListener;

/**
 * Interface do Handler dos eventos e callback
 * gerados pelo {@link ContaPresenter} ao terminar
 * o processamento dos eventos de casos de uso
 * de {@link UiContaListener}
 * 
 * @author camilo
 * 
 * @see UIConta
 * @deprecated
 * @see ApplicationContext#publishEvent(org.springframework.context.ApplicationEvent)
 * @see ApplicationEvent
 * @see ApplicationListener
 *
 */
public interface ContaPresenterListener {
	
	//TODO Inserir evento com informacoes de retorno
	/**
	 * Handler de callback do evento
	 * {@link UiContaListener#WUILoaded()}
	 */
	public void WUILoadedProcessed();
	
	//TODO Inserir evento com informacoes de retorno
	/**
	 * Handler de callback do evento
	 * {@link UiContaListener#newContaAdded()}
	 */
	public void newContaAddedProcessed();
	

}
