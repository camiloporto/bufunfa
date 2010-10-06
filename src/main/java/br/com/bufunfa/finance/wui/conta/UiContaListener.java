/**
 * 
 */
package br.com.bufunfa.finance.wui.conta;

import br.com.bufunfa.finance.conta.ContaPresenterListener;


/**
 * Interface do Listener de {@link UIConta}
 * Define os Handlers dos eventos de Casos de Uso 
 * gerados pelo Usuario ao interagir com a
 * WUI de Conta
 * 
 * @author camilo
 *
 */
public interface UiContaListener {
	
	//FIXME Inserir Evento gerado com informacoes do caso de uso
	/**
	 * Tratador do evento gerado quando a WUI Conta for iniciada
	 * ou carregada. Geralmente necessita
	 * preencher algumas informacoes iniciais
	 * para apresentar na tela. Esse evento
	 * pode ser gerado para essa ocasiao
	 */
	public void WUILoaded();
	
	//FIXME Inserir evento com informacoes do caso de uso 
	/**
	 * Handler do evento gerado ao inserir
	 * uma nova conta na hierarquia de contas
	 */
	public void newContaAdded();
	
	public void setContaPresenterListener(
			ContaPresenterListener contaPresenterListener);

}
