/**
 * 
 */
package br.com.bufunfa.finance.wui;

import java.io.Serializable;

import javax.annotation.Resource;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.roo.addon.javabean.RooJavaBean;

/**
 * @author camilo
 *
 */
@Resource(name="UIConta")
@RooJavaBean
public class UIConta implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3637894640907136286L;
	
	private TreeNode receitaRoot;
	
	private TreeNode despesaRoot;
	
	private TreeNode ativoRoot;
	
	private TreeNode passivoRoot;
	
	
	public UIConta() {
		
		/**
		 * TODO Incrementar essa classe para desenhar todas as contas de 
		 * receitas/despesas/ativo/passivo. Organizar layout da tela tambem
		 */
		receitaRoot = new DefaultTreeNode("Receitas", null);  
        TreeNode node0 = new DefaultTreeNode("Salario", receitaRoot);
        TreeNode node1 = new DefaultTreeNode("Acoes", receitaRoot);  
        TreeNode node2 = new DefaultTreeNode("Tesouro Direto", receitaRoot);  
          
        TreeNode node10 = new DefaultTreeNode("PETR4", node1);  
        TreeNode node11 = new DefaultTreeNode("VALE5", node1);  
          
	}
	
	
	
}
