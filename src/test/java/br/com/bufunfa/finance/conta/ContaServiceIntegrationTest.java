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
		Conta c = new Conta();
    	c.setId(1L);
    	c.setNome("Receitas");
    	c.persist();
    	
    	Conta c2 = new Conta();
    	c2.setId(2L);
    	c2.setNome("Despesas");
    	c2.persist();
    	
    	Conta c3 = new Conta();
    	c3.setId(3L);
    	c3.setNome("Ativo");
    	c3.persist();
    	
    	Conta c4 = new Conta();
    	c4.setId(4L);
    	c4.setNome("Passivo");
    	c4.persist();
	}
	
	@Test
	@Rollback
	public void testGetRootContas() {
		prepare();
		Assert.assertNotNull(contaService);
		Set<Conta> roots = contaService.getRootContas();
		Assert.assertNotNull(roots);
		for (Conta contaImpl : roots) {
			Assert.assertNull(contaImpl.getIdFather());
			System.out
					.println("ContaServiceIntegrationTest.testGetRootContas() " + contaImpl);
		}
	}

}
