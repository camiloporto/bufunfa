package br.com.bufunfa.finance.conta;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;
import org.springframework.test.context.ContextConfiguration;

import br.com.bufunfa.finance.utils.TestUtils;

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
    
    @Test
	public void testGetSomaLancamentosDeContaNaoFolha() {
		Conta contaTestSoma = new Conta();
		contaTestSoma.setNome("Conta Corrente");
		contaTestSoma.persist();
		
		Conta contaTestSomaFilha1 = new Conta();
		contaTestSomaFilha1.setNome("Conta Filha 1");
		
		Conta contaTestSomaFilha2 = new Conta();
		contaTestSomaFilha2.setNome("Conta Filha 2");
		
		contaTestSoma.addChild(contaTestSomaFilha1);
		contaTestSoma.addChild(contaTestSomaFilha2);
		
		Lancamento l1 = TestUtils.createLancamento(
				20.0, 
				TestUtils.createDate(2010, 1, 5),
				TestUtils.createDate(2010, 1, 5), 
				"Deposito efetuado");
		
		Lancamento l2 = TestUtils.createLancamento(
				-25.0, 
				TestUtils.createDate(2010, 1, 10),
				TestUtils.createDate(2010, 1, 10), 
				"Almoco no debito");
		
		Lancamento l3 = TestUtils.createLancamento(
				150.0, 
				TestUtils.createDate(2010, 1, 15),
				TestUtils.createDate(2010, 1, 15), 
				"Deposito do aluguel");
		
		contaTestSomaFilha1.addLancamento(l1);
		contaTestSomaFilha1.addLancamento(l2);
		contaTestSomaFilha2.addLancamento(l3);
		
		Date initDate = TestUtils.createDate(2010, 1, 5);
		Date endDate = TestUtils.createDate(2010, 1, 15);
		
		Assert.assertEquals(new BigDecimal(-5.0), contaTestSomaFilha1.getSomaLancamentos(initDate, endDate));
		Assert.assertEquals(new BigDecimal(150.0), contaTestSomaFilha2.getSomaLancamentos(initDate, endDate));
		
		
		BigDecimal saldo = contaTestSoma.getSomaLancamentos(initDate, endDate);
		
		Assert.assertEquals(new BigDecimal(145.0), saldo);
	}
    
    @Test
    public void testBasicGetSomaLancamentos() {
    	
    	Conta contaTest = new Conta();
		contaTest.setNome("Conta Corrente");
		contaTest.persist();
		
		
		Lancamento l1 = TestUtils.createLancamento(
				20.0, 
				TestUtils.createDate(2010, 1, 5),
				TestUtils.createDate(2010, 1, 5), 
				"Deposito efetuado");
		
		Lancamento l2 = TestUtils.createLancamento(
				-25.0, 
				TestUtils.createDate(2010, 1, 10),
				TestUtils.createDate(2010, 1, 10), 
				"Almoco no debito");
		
		Lancamento l3 = TestUtils.createLancamento(
				150.0, 
				TestUtils.createDate(2010, 1, 15),
				TestUtils.createDate(2010, 1, 15), 
				"Deposito do aluguel");
		contaTest.addLancamento(l1);
		contaTest.addLancamento(l2);
		contaTest.addLancamento(l3);
		
		
		BigDecimal soma = contaTest.getSomaLancamentos(TestUtils.createDate(2010, 1, 5), TestUtils.createDate(2010, 1, 15));
		
		Assert.assertEquals(new BigDecimal(145.0), soma);
    	
    }
    
    @Test
    public void testBasicGetSaldo() {
    	
    	Conta contaTest = new Conta();
		contaTest.setNome("Conta Corrente");
		contaTest.persist();
		
		Lancamento l1 = TestUtils.createLancamento(
				20.0, 
				TestUtils.createDate(2010, 1, 5),
				TestUtils.createDate(2010, 1, 5), 
				"Deposito efetuado");
		
		Lancamento l2 = TestUtils.createLancamento(
				-25.0, 
				TestUtils.createDate(2010, 1, 10),
				TestUtils.createDate(2010, 1, 10), 
				"Almoco no debito");
		
		Lancamento l3 = TestUtils.createLancamento(
				150.0, 
				TestUtils.createDate(2010, 1, 15),
				TestUtils.createDate(2010, 1, 15), 
				"Deposito do aluguel");
		contaTest.addLancamento(l1);
		contaTest.addLancamento(l2);
		contaTest.addLancamento(l3);
		
		BigDecimal saldo = contaTest.getSaldo( 
				TestUtils.createDate(2010, 1, 15));
		
		Assert.assertEquals(new BigDecimal(145.0), saldo);
    	
    }
    
    @Test
	public void testGetSaldoDeContaNaoFolha() {
		Conta contaTestSaldo = new Conta();
		contaTestSaldo.setNome("Conta Corrente");
		contaTestSaldo.persist();
		
		Conta contaTestSaldoFilha1 = new Conta();
		contaTestSaldoFilha1.setNome("Conta Filha 1");
		
		Conta contaTestSaldoFilha2 = new Conta();
		contaTestSaldoFilha2.setNome("Conta Filha 2");
		
		contaTestSaldo.addChild(contaTestSaldoFilha1);
		contaTestSaldo.addChild(contaTestSaldoFilha2);
		
		Lancamento l1 = TestUtils.createLancamento(
				20.0, 
				TestUtils.createDate(2010, 1, 5),
				TestUtils.createDate(2010, 1, 5), 
				"Deposito efetuado");
		
		Lancamento l2 = TestUtils.createLancamento(
				-25.0, 
				TestUtils.createDate(2010, 1, 10),
				TestUtils.createDate(2010, 1, 10), 
				"Almoco no debito");
		
		Lancamento l3 = TestUtils.createLancamento(
				150.0, 
				TestUtils.createDate(2010, 1, 15),
				TestUtils.createDate(2010, 1, 15), 
				"Deposito do aluguel");
		
		contaTestSaldoFilha1.addLancamento(l1);
		contaTestSaldoFilha1.addLancamento(l2);
		contaTestSaldoFilha2.addLancamento(l3);
		
		Assert.assertEquals(new BigDecimal(-5.0), contaTestSaldoFilha1.getSaldo(TestUtils.createDate(2010, 1, 15)));
		Assert.assertEquals(new BigDecimal(150.0), contaTestSaldoFilha2.getSaldo(TestUtils.createDate(2010, 1, 15)));
		
		BigDecimal saldo = contaTestSaldo.getSaldo(
				TestUtils.createDate(2010, 1, 15));
		
		Assert.assertEquals(new BigDecimal(145.0), saldo);
		//FIXME Implementar saldo de conta nao folha!
	}
}
