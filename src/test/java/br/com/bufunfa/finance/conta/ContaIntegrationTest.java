package br.com.bufunfa.finance.conta;

import java.util.Iterator;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;
import org.springframework.test.context.ContextConfiguration;

@RooIntegrationTest(entity = Conta.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext-test.xml")
public class ContaIntegrationTest {
	
    @Test
    public void testBasicSave() {
    	Conta c = new Conta();
//    	c.setId(1L);
    	c.setNome("Receitas");
    	c.persist();
    	
    	Long idPersisted = c.getId();
    	Assert.assertNotNull(idPersisted);
    	
    	
    	Conta retrieved = Conta.findConta(idPersisted);
    	Assert.assertNotNull(retrieved);
    	Assert.assertEquals("Receitas", retrieved.getNome());
    	
    }
    
    @Test
    public void testBasicAddChild() {
    	Conta c = new Conta();
    	c.setNome("Receitas");
    	c.persist();
    	
    	Conta c2 = new Conta();
    	c2.setNome("Salario");
    	
    	c.addChild(c2);
    	
    	Conta retrieved = Conta.findConta(c2.getId());
    	Assert.assertNotNull(retrieved);
    	Assert.assertEquals("Salario", retrieved.getNome());
    	
    	Conta father = Conta.findConta(retrieved.getIdFather());
    	Assert.assertNotNull(father);
    	Assert.assertEquals("Receitas", father.getNome());
    	
    }
    
    @Test
    public void testBasicGetChildren() {
    	Conta c = new Conta();
    	c.setNome("Receitas");
    	c.persist();
    	
    	Conta c2 = new Conta();
    	c2.setNome("Salario");
    	
    	c.addChild(c2);
    	
    	Conta c3 = new Conta();
    	c3.setNome("Investimentos");
    	
    	c.addChild(c3);
    	
    	Set<Conta> children = c.getChildren();
    	Assert.assertNotNull(children);
    	Assert.assertTrue(children.size() == 2);
    	
    	Iterator<Conta> childrenIt = children.iterator();
    	
    	Conta child1 = childrenIt.next();
    	Assert.assertNotNull(child1.getId());
    	Assert.assertEquals(c.getId(), child1.getIdFather());
    	
    	Conta child2 = childrenIt.next();
    	Assert.assertNotNull(child2.getId());
    	Assert.assertEquals(c.getId(), child2.getIdFather());
    }
    
    @Test
    public void testGetContaByNome() {
    	Conta c = new Conta();
    	c.setNome("Receitas");
    	c.persist();
    	
    	Conta retrieved = (Conta) Conta.findContasByNome("Receitas").getResultList().get(0);
    	Assert.assertNotNull(retrieved);
    	Assert.assertEquals("Receitas", retrieved.getNome());
    }
}
