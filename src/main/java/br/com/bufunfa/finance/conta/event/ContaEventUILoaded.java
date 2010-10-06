/**
 * 
 */
package br.com.bufunfa.finance.conta.event;

import java.util.HashSet;
import java.util.Set;

import org.springframework.roo.addon.javabean.RooJavaBean;

import br.com.bufunfa.finance.conta.ContaImpl;

/**
 * Evento gerado quando a tela de conta
 * termina de ser carregada.
 * 
 * Nesse caso algumas informacoes basicas,
 * precisam ser apresentadas, tais como
 * a hierarquia de contas cadastradas
 * 
 * @author camilo
 *
 */
@RooJavaBean(settersByDefault=false)
public class ContaEventUILoaded extends ContaEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1614736758429250821L;
	
	private Set<ContaImpl> rootContas = new HashSet<ContaImpl>();

	public ContaEventUILoaded(Object source, String nomeContaPai,
			String nomeConta, String descricaoConta, Set<ContaImpl> rootContas) {
		super(source, EventType.UILoaded, nomeConta, descricaoConta, nomeContaPai);
		if(rootContas != null) {
			this.rootContas = rootContas;
		}
		
	}

}
