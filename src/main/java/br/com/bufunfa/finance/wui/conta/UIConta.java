/**
 * 
 */
package br.com.bufunfa.finance.wui.conta;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;

import br.com.bufunfa.finance.conta.Conta;
import br.com.bufunfa.finance.conta.IContaService;
import br.com.bufunfa.finance.conta.event.ContaEvent;
import br.com.bufunfa.finance.conta.event.ContaEventCallback;
import br.com.bufunfa.finance.conta.event.ContaEventUILoaded;
import br.com.bufunfa.finance.conta.event.ContaEvent.EventType;

/**
 * @author camilo
 *
 */
@RooJavaBean
@RooSerializable
public class UIConta implements ApplicationContextAware, ApplicationListener<ContaEventCallback> {
	
	@Autowired
	private IContaService contaService;
	
	private ApplicationContext context;
	
	private TreeNode selectedNode;
	
	private String nomeConta;
	
	private String descricaoConta;
	
	private String nomeContaPai;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3637894640907136286L;
	/*
	private TreeNode receitaRoot = new DefaultTreeNode("ReceitasRoot", null);
	
	private TreeNode despesaRoot = new DefaultTreeNode("DespesasRoot", null);
	
	private TreeNode ativoRoot = new DefaultTreeNode("AtivosRoot", null);
	
	private TreeNode passivoRoot = new DefaultTreeNode("PassivosRoot", null);
	*/
	private TreeNode root = new DefaultTreeNode("root", null);
	
	
	public UIConta() {
		/**
		 * TODO Implementar funcionalidade de adicionar conta em determinado nivel 
		 * da hierarquia de contas
		 */
          
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
		
	}
	
	/**
	 * Handler para TODOS os eventos de callback relativos
	 * a Conta. Cada evento de callback eh uma
	 * subclasse de {@link ContaEventCallback}
	 * 
	 * @param event o evento de callback
	 */
	@Override
	public void onApplicationEvent(ContaEventCallback event) {
		ContaEvent sourceEvent = event.getSourceEvent();
		EventType sourceEventType = sourceEvent.getEventType();
		if(EventType.UILoaded.equals(sourceEventType)) {
			handleUILoaded(event);
		} else if(EventType.NewConta.equals(sourceEventType)) {
			handleNewContaAdded(event);
		}
		
	}
	
	/**
	 * Handle do evento de callback do evento
	 * {@link ContaEvent}
	 * @param evt
	 */
	private void handleNewContaAdded(ContaEventCallback evt) {
		ContaEvent sourceEvent = (ContaEvent) evt.getSourceEvent();
		String nomeContaPai = sourceEvent.getNomeContaPai();
		String nomeConta = sourceEvent.getNomeConta();
		
		System.out.println("UIConta.handleNewContaAdded() " + sourceEvent);
		
		//adiciona a conta recem criada a arvore de contas da WUI
		insertNodeIntoTree(nomeContaPai, nomeConta);
		
	}
	
	/**
	 * Handle do evento de callback do evento
	 * {@link ContaEventUILoaded}
	 * @param event o evento
	 */
	private void handleUILoaded(ContaEventCallback event) {
		ContaEventUILoaded sourceEvent = (ContaEventUILoaded) event.getSourceEvent();
		Set<Conta> contasTree = sourceEvent.getRootContas();
		
		for (Conta contaImpl : contasTree) {
			mountTree(root, contaImpl);
			
		}
	}

	/**
	 * Handler do evento de clique na opcao New do menu
	 * de contexto da hierarquia de contas
	 * @param evt o evento
	 */
	public void contextMenuNewEvent(ActionEvent evt) {
		
		ContaEvent event = new ContaEvent(
				this,
				EventType.NewConta,
				(String) getSelectedNode().getData(),
				getNomeConta(), 
				getDescricaoConta());
		publishEvent(event);
	}
	
	/**
	 * Dispara um evento para o contexto do Spring
	 * @param event o evento
	 */
	private void publishEvent(ApplicationEvent event) {
		if(context != null)
			context.publishEvent(event);
		
	}

	public void init() {
		
		//FIXME Adiciona conta na arvore, porem o menu de contexto deixa de funcionar!! corrigir
		
		System.out.println("UIConta.init()");
		ContaEvent event = new ContaEvent(
				this,
				EventType.NewConta,				
				null, 
				"Receitas",
				null);
		publishEvent(event);
		
		event = new ContaEvent(
				this,
				EventType.NewConta,				
				null, 
				"Despesas",
				null);
		publishEvent(event);
		
		event = new ContaEvent(
				this,
				EventType.NewConta,				
				null, 
				"Ativos",
				null);
		publishEvent(event);
		
		event = new ContaEvent(
				this,
				EventType.NewConta,				
				null, 
				"Passivos",
				null);
		publishEvent(event);
		
		/*
		ContaEventUILoaded uiLoadedEvent = new ContaEventUILoaded(
				this, 
				null, 
				null, 
				null, 
				new HashSet<ContaImpl>());
		publishEvent(uiLoadedEvent);
		*/
		
	}
	
	/**
	 * Monta uma hierarquia de contas na WUI Tree de contas
	 * com um nivel de children. OU seja, adiciona a conta atual
	 * e suas contas de nivel imediato
	 * @param node WUI TreeNode a encangar a conta
	 * @param conta a conta e seus filhos a montar na WUI TreeNode
	 */
	private void mountTree(TreeNode fatherNode, Conta conta) {
		TreeNode contaNode = new DefaultTreeNode(conta.getNome(), null);
		fatherNode.addChild(contaNode);
		
		Set<Conta> childrenLevel1 = conta.getChildren();
		for (Conta contaImpl : childrenLevel1) {
			contaNode.addChild(new DefaultTreeNode(contaImpl.getNome(), null));
		}
		
	}
	
	/**
	 * Insere uma conta abaixo do pai informado
	 * na Arvore de contas da WUI
	 * @param nomeContaPai nome da conta pai na arvore de contas da WUI
	 * @param nomeConta nome da conta a adicionar
	 */
	public void insertNodeIntoTree(String nomeContaPai, String nomeConta) {
		//FIXME tambem nao esta atualizando a Tree na WUI. dizendo que nao acha o id da tree.
		System.out.println("UIConta.insertNodeIntoTree() adicionando " + nomeConta + " a " + nomeContaPai);
		TreeNode contaNode = new DefaultTreeNode(nomeConta, null);
		if(nomeContaPai == null) {//conta adicionada eh raiz
			this.root.addChild(contaNode);
		} else {
			TreeNode noPai = getNo(nomeContaPai, this.root);
			if(noPai != null) {
				noPai.addChild(contaNode);
			}
		}
	}
	
	
	public TreeNode getNo(String nome, TreeNode root) {
		if(root.isLeaf()) {
			if(nome.equalsIgnoreCase((String) root.getData()))
				return root;
			return null;
		} else {
			List<TreeNode> children = root.getChildren();
			for (TreeNode nextChild : children) {
				TreeNode found = getNo(nome, nextChild);
				if(found != null)
					return found;
			}
		}
		
		return null;
	}
	
	
	
}
