/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.util.ArrayList;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import br.com.bufunfa.finance.conta.event.ContaEvent;
import br.com.bufunfa.finance.conta.event.ContaEventCallback;
import br.com.bufunfa.finance.conta.event.ContaEventUILoaded;
import br.com.bufunfa.finance.conta.event.ContaEvent.EventType;

/**
 * @author camilo
 *
 */
@Component("contaPresenter")
public class ContaPresenter implements ApplicationListener<ContaEvent>, ApplicationContextAware {
	
	
	@Resource(name="contaService")
	private IContaService contaService;
	
	private ApplicationContext context;
	
	public ContaPresenter() {
		
	}
	

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
		
	}

	@Override
	public void onApplicationEvent(ContaEvent event) {
		EventType type = event.getEventType();
		if(EventType.NewConta.equals(type)){
			handleNewConta(event);
		} else if(EventType.UILoaded.equals(type)) {
			handleUILoaded(event);
		}
		
		
		
	}
	
	/**
	 * 
	 * @param event
	 */
	private void handleUILoaded(ContaEvent event) {
		ContaServiceParams params = new ContaServiceParams(
				event.getNomeConta(), 
				event.getDescricaoConta(),
				event.getNomeContaPai());
		
		
		System.out.println("ContaPresenter.handleUILoaded()");
		//TODO Receber erros gerados trtando alguma excecao
		Set<Conta> rootContas = contaService.getRootContas();
		ContaEventUILoaded evt = (ContaEventUILoaded) event;
		
		//TODO Fazer cast de acordo com o tipo de evento recebido (EventType)
		evt.getRootContas().addAll(rootContas);
		ContaEventCallback<ContaEventUILoaded> callbackEvt = new ContaEventCallback<ContaEventUILoaded>(
				this, 
				new ArrayList<String>(), //TODO Colocar mensagens de erro aqui. 
				evt);
		
		context.publishEvent(callbackEvt);
		
	}


	private void handleNewConta(ContaEvent event) {
		ContaServiceParams params = new ContaServiceParams(
				event.getNomeConta(), 
				event.getDescricaoConta(),
				event.getNomeContaPai());
		
		
		//TODO Receber erros gerados trtando alguma excecao
		contaService.addConta(params);
		
		ContaEventCallback<ContaEvent> callbackEvt = new ContaEventCallback<ContaEvent>(
				this, 
				new ArrayList<String>(), //TODO Colocar mensagens de erro aqui. 
				event);
		
		context.publishEvent(callbackEvt);
	}



}
