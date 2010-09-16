/**
 * 
 */
package br.com.bufunfa.finance.wui;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.Resource;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.javabean.RooJavaBean;

import br.com.bufunfa.finance.conta.ContaImpl;
import br.com.bufunfa.finance.conta.IContaService;

/**
 * @author camilo
 *
 */
@Resource(name="UIConta")
@RooJavaBean
public class UIConta implements Serializable {
	
	@Autowired
	private IContaService contaService;
	
	
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
	
	public void init() {
		
		System.err.println("UIConta.init() initing conta");
		
		contaService.addConta(1L, "Receitas");
		contaService.addConta(2L, "Despesas");
		contaService.addConta(3L, "Ativos");
		contaService.addConta(4L, "Passivos");
		
		ContaImpl receitasConta = ContaImpl.findContaImpl(1L);
		ContaImpl child = new ContaImpl();
		child.setId(5L);
		child.setNome("PETR4");
		receitasConta.addChild(child);
		
		
		Set<ContaImpl> rootContas = contaService.getRootContas();
		System.out.println("UIConta.init() root.size: " + rootContas.size());
		
		for (ContaImpl contaImpl : rootContas) {
			mountTree(root, contaImpl);
			
		}
		
	}
	
	/**
	 * Monta uma hierarquia de contas na WUI Tree de contas
	 * com um nivel de children. OU seja, adiciona a conta atual
	 * e suas contas de nivel imediato
	 * @param node WUI TreeNode a encangar a conta
	 * @param conta a conta e seus filhos a montar na WUI TreeNode
	 */
	private void mountTree(TreeNode fatherNode, ContaImpl conta) {
		TreeNode contaNode = new DefaultTreeNode(conta.getNome(), null);
		fatherNode.addChild(contaNode);
		
		Set<ContaImpl> childrenLevel1 = conta.getChildren();
		for (ContaImpl contaImpl : childrenLevel1) {
			contaNode.addChild(new DefaultTreeNode(contaImpl.getNome(), null));
		}
		
	}
	
}
