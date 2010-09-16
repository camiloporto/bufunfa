/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.util.Set;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Testes da implementacao do servico de contas
 * 
 * @author camilo
 * @see ContaServiceImpl
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext-test.xml")
public class ContaServiceIntegrationTest {
	
	@Resource(name="contaService")
	private IContaService contaService;
	
//	@Before
	public void prepare() {
		ContaImpl c = new ContaImpl();
    	c.setId(1L);
    	c.setNome("Receitas");
    	c.persist();
    	
    	ContaImpl c2 = new ContaImpl();
    	c2.setId(2L);
    	c2.setNome("Despesas");
    	c2.persist();
    	
    	ContaImpl c3 = new ContaImpl();
    	c3.setId(3L);
    	c3.setNome("Ativo");
    	c3.persist();
    	
    	ContaImpl c4 = new ContaImpl();
    	c4.setId(4L);
    	c4.setNome("Passivo");
    	c4.persist();
	}
	
	@Test
	@Rollback
	public void testGetRootContas() {
		prepare();
		Assert.assertNotNull(contaService);
		Set<ContaImpl> roots = contaService.getRootContas();
		Assert.assertNotNull(roots);
		for (ContaImpl contaImpl : roots) {
			Assert.assertNull(contaImpl.getIdFather());
			System.out
					.println("ContaServiceIntegrationTest.testGetRootContas() " + contaImpl);
		}
	}

}
