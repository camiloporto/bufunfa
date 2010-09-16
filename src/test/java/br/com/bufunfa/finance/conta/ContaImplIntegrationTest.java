package br.com.bufunfa.finance.conta;

import java.util.Iterator;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = ContaImpl.class)
public class ContaImplIntegrationTest {
	
    @Test
    public void testBasicSave() {
    	ContaImpl c = new ContaImpl();
    	c.setId(1L);
    	c.setNome("Receitas");
    	c.persist();
    	
    	ContaImpl retrieved = ContaImpl.findContaImpl(1L);
    	Assert.assertNotNull(retrieved);
    	Assert.assertEquals("Receitas", retrieved.getNome());
    	
    }
    
    @Test
    public void testBasicAddChild() {
    	ContaImpl c = new ContaImpl();
    	c.setNome("Receitas");
    	c.persist();
    	
    	ContaImpl c2 = new ContaImpl();
    	c2.setNome("Salario");
    	
    	c.addChild(c2);
    	
    	ContaImpl retrieved = ContaImpl.findContaImpl(c2.getId());
    	Assert.assertNotNull(retrieved);
    	Assert.assertEquals("Salario", retrieved.getNome());
    	
    	ContaImpl father = ContaImpl.findContaImpl(retrieved.getIdFather());
    	Assert.assertNotNull(father);
    	Assert.assertEquals("Receitas", father.getNome());
    	
    }
    
    @Test
    public void testBasicGetChildren() {
    	ContaImpl c = new ContaImpl();
    	c.setNome("Receitas");
    	c.persist();
    	
    	ContaImpl c2 = new ContaImpl();
    	c2.setNome("Salario");
    	
    	c.addChild(c2);
    	
    	ContaImpl c3 = new ContaImpl();
    	c3.setNome("Investimentos");
    	
    	c.addChild(c3);
    	
    	Set<ContaImpl> children = c.getChildren();
    	Assert.assertNotNull(children);
    	Assert.assertTrue(children.size() == 2);
    	
    	Iterator<ContaImpl> childrenIt = children.iterator();
    	
    	ContaImpl child1 = childrenIt.next();
    	Assert.assertNotNull(child1.getId());
    	Assert.assertEquals(c.getId(), child1.getIdFather());
    	
    	ContaImpl child2 = childrenIt.next();
    	Assert.assertNotNull(child2.getId());
    	Assert.assertEquals(c.getId(), child2.getIdFather());
    }
}
