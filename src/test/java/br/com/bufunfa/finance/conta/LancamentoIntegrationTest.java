/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author camilo
 *
 */
@RooIntegrationTest(entity = Lancamento.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext-test.xml")
public class LancamentoIntegrationTest {
	
	private Conta contaSample;
	
	@Before
	public void beforeTest() {
		contaSample = new Conta();
		contaSample.setId(1L);
		contaSample.setNome("Receitas");
		
		contaSample.persist();
		
	}
	
	@After
	public void afterTest() {
		contaSample.remove();
	}
	
	//FIXME Ajustar configuracao de ids das entidades! colocar automatico
	@Test
	public void testBasicAddLancamento() throws ParseException {
		Lancamento l = new Lancamento();
		l.setDataEfetivacao(parseDate("30/10/2009"));
		l.setDataRegistro(parseDate("30/10/2009"));
		l.setDescricao("lancamento de teste");
		l.setId(123L);
		l.setQuantidade(new BigDecimal(2.0));
		
		contaSample.addLancamento(l);
		
		Lancamento retrieved = Lancamento.findLancamento(123L);
		Assert.assertNotNull(retrieved);
		Assert.assertEquals(new Long(123), retrieved.getId());
		Assert.assertEquals(contaSample.getId(), retrieved.getIdConta());
		
	}
	
	@Test
	public void testBasicGetLancamentosEfetivados() throws ParseException {
		Lancamento l = new Lancamento();
		l.setDataEfetivacao(parseDate("30/10/2010"));
		l.setDataRegistro(parseDate("30/10/2010"));
		l.setDescricao("lancamento de teste");
		l.setId(1L);
		l.setQuantidade(new BigDecimal(20.0));
		
		Lancamento l2 = new Lancamento();
		l2.setDataEfetivacao(parseDate("20/10/2010"));
		l2.setDataRegistro(parseDate("20/10/2010"));
		l2.setDescricao("lancamento de teste 2");
		l2.setId(2L);
		l2.setQuantidade(new BigDecimal(25.0));
		
		contaSample.addLancamento(l);
		contaSample.addLancamento(l2);
		
		List<Lancamento> lancamentos = contaSample.getLancamentos(
				parseDate("1/10/2010"), 
				parseDate("31/10/2010"));
		
		Assert.assertNotNull(lancamentos);
		Assert.assertTrue(2 == lancamentos.size());
	}
	
	/**
	 * Testa a pesquisa de lancamentos passando um intervalo de datas
	 * coincidentes com as datas dos lancamentos. A busca deve
	 * incluir os extremos dos intervalos
	 * @throws ParseException
	 */
	@Test
	public void testGetLancamentosEfetivadosEmIntervaloJusto() throws ParseException {
		Lancamento l = new Lancamento();
		l.setDataEfetivacao(parseDate("30/11/2010"));
		l.setDataRegistro(parseDate("30/11/2010"));
		l.setDescricao("lancamento de teste");
		l.setId(1L);
		l.setQuantidade(new BigDecimal(20.0));
		
		Lancamento l2 = new Lancamento();
		l2.setDataEfetivacao(parseDate("20/11/2010"));
		l2.setDataRegistro(parseDate("20/11/2010"));
		l2.setDescricao("lancamento de teste 2");
		l2.setId(2L);
		l2.setQuantidade(new BigDecimal(25.0));
		
		contaSample.addLancamento(l);
		contaSample.addLancamento(l2);
		
		List<Lancamento> lancamentos = contaSample.getLancamentos(
				parseDate("20/11/2010"), 
				parseDate("30/11/2010"));
		
		Assert.assertNotNull(lancamentos);
		Assert.assertEquals(new Integer(2), new Integer(lancamentos.size()));
	}
	
	Date parseDate(String dateStr) throws ParseException {
		DateFormat df = DateFormat.getDateInstance();
		return df.parse(dateStr);
	}

}
