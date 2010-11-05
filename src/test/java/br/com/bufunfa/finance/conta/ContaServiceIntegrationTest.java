/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.BeforeClass;
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
	
	private static Conta origem;
	
	private static Conta destino;
	
	@BeforeClass
	public static void prepare() {
		System.out.println("ContaServiceIntegrationTest.prepare()");
		
		origem = new Conta();
		origem.setId(1L);
		origem.setNome("Receitas");
		origem.persist();
    	
		destino = new Conta();
		destino.setId(2L);
		destino.setNome("Despesas");
		destino.persist();
    	
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
//		prepare();
		Assert.assertNotNull(contaService);
		Set<Conta> roots = contaService.getRootContas();
		Assert.assertNotNull(roots);
		for (Conta contaImpl : roots) {
			Assert.assertNull(contaImpl.getIdFather());
			
		}
	}
	
	@Test
	public void testBasicAddTransacao() {
		Calendar cal = Calendar.getInstance();
		cal.set(2010, 01, 01);
		contaService.addTransacao(origem, 
				destino, 
				new BigDecimal(20.0), 
				"almoco", 
				cal.getTime());
		
		Lancamento lancamentosOrigem = origem.getLancamentos(cal.getTime(), cal.getTime()).get(0);
		Lancamento lancamentosDestino = destino.getLancamentos(cal.getTime(), cal.getTime()).get(0);
		
		System.out
		.println("ContaServiceIntegrationTest.testBasicAddTransacao() " + lancamentosOrigem);

System.out
.println("ContaServiceIntegrationTest.testBasicAddTransacao() " + lancamentosDestino);
		
		Assert.assertNotNull(lancamentosOrigem);
		Assert.assertNotNull(lancamentosDestino);
		
		Assert.assertEquals(lancamentosOrigem.getDescricao(), lancamentosDestino.getDescricao());
		Assert.assertEquals(lancamentosOrigem.getDataEfetivacao(), lancamentosDestino.getDataEfetivacao());
		Assert.assertEquals(lancamentosOrigem.getDataRegistro(), lancamentosDestino.getDataRegistro());
		
		
		
		//assegura que a soma dos lancamentos sao zero
		Assert.assertEquals(new BigDecimal(-20.0), lancamentosOrigem.getQuantidade());
		Assert.assertEquals(new BigDecimal(20.0), lancamentosDestino.getQuantidade());
//		Assert.assertTrue(lancamentosOrigem.getQuantidade().add(lancamentosDestino.getQuantidade()).equals(new BigDecimal(0.0)));
	}

}
