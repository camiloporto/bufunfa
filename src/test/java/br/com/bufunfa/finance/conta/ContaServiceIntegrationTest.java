/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.bufunfa.finance.utils.TestUtils;

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
	
	public ContaServiceIntegrationTest() {
		
	}
	
	@Resource(name="contaService")
	private IContaService contaService;
	
	private static Conta origem;
	
	private static Conta destino;
	
	@BeforeClass
	public static void prepare() {
		
		origem = new Conta();
		origem.setNome("Receitas");
		origem.persist();
    	
		destino = new Conta();
		destino.setNome("Despesas");
		destino.persist();
		
    	
    	Conta c3 = new Conta();
    	c3.setNome("Ativo");
    	c3.persist();
    	
    	Conta c4 = new Conta();
    	c4.setNome("Passivo");
    	c4.persist();
	}
	
	@Test
	@Rollback
	public void testGetRootContas() {
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
		
		Assert.assertNotNull(lancamentosOrigem);
		Assert.assertNotNull(lancamentosDestino);
		
		Assert.assertEquals(lancamentosOrigem.getDescricao(), lancamentosDestino.getDescricao());
		Assert.assertEquals(lancamentosOrigem.getDataEfetivacao(), lancamentosDestino.getDataEfetivacao());
		Assert.assertEquals(lancamentosOrigem.getDataRegistro(), lancamentosDestino.getDataRegistro());
		
		
		
		//assegura que a soma dos lancamentos sao zero
		Assert.assertEquals(new BigDecimal(-20.0), lancamentosOrigem.getQuantidade());
		Assert.assertEquals(new BigDecimal(20.0), lancamentosDestino.getQuantidade());
		Assert.assertTrue(lancamentosOrigem.getQuantidade().add(lancamentosDestino.getQuantidade()).equals(new BigDecimal(0.0)));
	}
	
	@Test
	public void testGetBasicExtrato() {
		Conta contaTestExtrato = new Conta();
		contaTestExtrato.setNome("Conta Corrente");
		contaTestExtrato.persist();
		
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
		contaTestExtrato.addLancamento(l1);
		contaTestExtrato.addLancamento(l2);
		contaTestExtrato.addLancamento(l3);
		
		//recupera o extrato entre 1/1/2010 a 10/1/2010
		Extrato e = contaService.getExtrato(
				contaTestExtrato.getId(), 
				TestUtils.createDate(2010, 1, 4),
				TestUtils.createDate(2010, 1, 10));
		
		//verifica que o saldo calculado eh igual ao esperado: -5.0
		Assert.assertNotNull(e);
		Assert.assertEquals(new BigDecimal(-5.0), e.getSaldo());
		Assert.assertEquals(2, e.getLancamentos().size());
		 
	}
	
	/**
	 * teste com data de pesquisa coincidindo com as 
	 * datas do primeiro e ultimo lancamento. deve incluir os ilmites
	 */
	@Test
	public void testGetExtratoComDatasLimites() {
		Conta contaTestExtrato = new Conta();
		contaTestExtrato.setNome("Conta Corrente");
		contaTestExtrato.persist();
		
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
		contaTestExtrato.addLancamento(l1);
		contaTestExtrato.addLancamento(l2);
		contaTestExtrato.addLancamento(l3);
		
		//recupera o extrato entre 5/1/2010 a 10/1/2010
		Extrato e = contaService.getExtrato(
				contaTestExtrato.getId(), 
				TestUtils.createDate(2010, 1, 5),
				TestUtils.createDate(2010, 1, 10));
		
		//verifica que o saldo calculado eh igual ao esperado: -5.0
		Assert.assertNotNull(e);
		Assert.assertEquals(new BigDecimal(-5.0), e.getSaldo());
		Assert.assertEquals(2, e.getLancamentos().size());
		
	}
	
	@Test
	public void testBasicGetSaldo() {
		Conta contaTestExtrato = new Conta();
		contaTestExtrato.setNome("Conta Corrente");
		contaTestExtrato.persist();
		
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
		contaTestExtrato.addLancamento(l1);
		contaTestExtrato.addLancamento(l2);
		contaTestExtrato.addLancamento(l3);
		
		BigDecimal saldo = contaService.getSaldo(
				contaTestExtrato.getId(), 
				TestUtils.createDate(2010, 1, 15));
		
		Assert.assertEquals(new BigDecimal(145.0), saldo);
	}
	/*
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
		
		
		BigDecimal saldo = contaService.getSaldo(
				contaTestSaldo.getId(), 
				TestUtils.createDate(2010, 1, 15));
		
		Assert.assertEquals(new BigDecimal(145.0), saldo);
		
	}
	*/
	//FIXME efetuar mais testes com valores invalidos para entradas dos metodos de servico
}
