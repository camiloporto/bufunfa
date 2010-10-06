/**
 * 
 */
package br.com.bufunfa.finance.wui;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.bufunfa.finance.wui.conta.UIConta;

/**
 * @author camilo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext-test.xml")
public class UIContaTest {
	
	@Resource(name="UiConta")
	private UIConta uiConta;
	
	public UIContaTest() {
		
	}
	
	@Test
	public void testDefault() {
		
	}
	
	@Test
	public void testFindNoByNome() {
		TreeNode root = new DefaultTreeNode("root", null);
		uiConta.setRoot(root);
		
		//testa caso base do algoritmo de busca
		TreeNode found = uiConta.getNo("root", root);
		Assert.assertNotNull(found);
		Assert.assertEquals("root", found.getData().toString());
		
		//adiciona dois filhos diretamente na raiz
		TreeNode c1 = new DefaultTreeNode("c1", root);
		TreeNode c2 = new DefaultTreeNode("c2", root);
		uiConta.setRoot(root);
		
		found = uiConta.getNo("c2", root);
		Assert.assertNotNull(found);
		Assert.assertEquals("c2", found.getData().toString());
		
		//adiciona um neto
		TreeNode c3 = new DefaultTreeNode("c1.filho", c1);
		uiConta.setRoot(root);
		found = uiConta.getNo("c1.filho", root);
		Assert.assertNotNull(found);
		Assert.assertEquals("c1.filho", found.getData().toString());

		//procura por no nao existente
		found = uiConta.getNo("NoNaoExistente", root);
		Assert.assertNull(found);
		
		
	}
	
	@Test
	public void testInsertNodeIntoTree() {
		TreeNode root = new DefaultTreeNode("root", null);
		
		//adiciona dois filhos diretamente na raiz
		TreeNode c1 = new DefaultTreeNode("c1", root);
		TreeNode c2 = new DefaultTreeNode("c2", root);
		uiConta.setRoot(root);
		
		//insere um no abaixo de c1
		uiConta.insertNodeIntoTree("c1", "c1.filho");
		
		TreeNode found = uiConta.getNo("c1.filho", uiConta.getRoot());
		Assert.assertNotNull(found);
		Assert.assertEquals("c1.filho", found.getData().toString());
		Assert.assertEquals("c1", found.getParent().getData().toString());
		
	}
	
//	@Test
	public void testInitialState() {
		Assert.assertNotNull(uiConta);
		Assert.assertNotNull(uiConta.getRoot());
	}

}
