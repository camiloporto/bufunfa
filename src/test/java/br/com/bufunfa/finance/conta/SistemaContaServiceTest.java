/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.bufunfa.finance.utils.DateUtil;
import br.com.bufunfa.finance.utils.TestUtils;

/**
 * @author camilo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext-test.xml")
public class SistemaContaServiceTest {
	
	@Resource(name="sistemaContaService")
	private ISistemaContaService sistemaContaService;
	
	public SistemaContaServiceTest() {
		
	}
	
	@Test
    public void testGetSaldoOperacionalDeCaixa() {
    	sistemaContaService.addSistemaConta("SistemaConta");
    	List<SistemaConta> lista = SistemaConta.findAllSistemaContas();
		Assert.assertNotNull(lista);
		Assert.assertTrue(lista.size() > 0);
		
		SistemaConta novo = null;
		for (SistemaConta sistemaConta : lista) {
			if("SistemaConta".equals(sistemaConta.getNome())) {
				novo = sistemaConta;
			}
		}
		
		Assert.assertNotNull(novo);
		Assert.assertNotNull(novo.getId());
		Assert.assertNotNull(novo.getIdContaRoot());
		
		Conta despesa = novo.getContaDespesa();
		Conta receita = novo.getContaReceita();
		
		
		SistemaContaIntegrationTest.adicionaAlgumasDespesas(despesa);
		SistemaContaIntegrationTest.adicionaAlgumasReceitas(receita);
		
		
		/*
		 * retorna o saldo operacional de caixa entre 10/1/2010 e 30/1/2010
		 */
		RelatorioSaldoCaixa relatorioSaldoCaixa = sistemaContaService.getSaldoOperacionalCaixa(
				novo.getId(),
				TestUtils.createDate(2010, 1, 10), 
				TestUtils.createDate(2010, 1, 15));
		
		RelatorioSaldoCaixa relatorioEsperado = new RelatorioSaldoCaixa(
				TestUtils.createDate(2010, 1, 10), 
				TestUtils.createDate(2010, 1, 15), 
				new BigDecimal("-35.00"), 
				new BigDecimal("593.00"));
		
		Assert.assertTrue(DateUtil.isDayMonthYearEqual(relatorioEsperado.getFimPeriodo(), relatorioSaldoCaixa.getFimPeriodo()));
		Assert.assertTrue(DateUtil.isDayMonthYearEqual(relatorioEsperado.getInicioPeriodo(), relatorioSaldoCaixa.getInicioPeriodo()));
		Assert.assertEquals(relatorioEsperado.getSaldoAnterior(), relatorioSaldoCaixa.getSaldoAnterior());
		Assert.assertEquals(relatorioEsperado.getSaldoOperacionalDoPeriodo(), relatorioSaldoCaixa.getSaldoOperacionalDoPeriodo());
		Assert.assertEquals(relatorioEsperado.getSaldoPosterior(), relatorioSaldoCaixa.getSaldoPosterior());
    }
	
	@Test
    public void testGetSaldoOperacional() {
    	sistemaContaService.addSistemaConta("SistemaConta");
    	List<SistemaConta> lista = SistemaConta.findAllSistemaContas();
		Assert.assertNotNull(lista);
		Assert.assertTrue(lista.size() > 0);
		
		SistemaConta novo = null;
		for (SistemaConta sistemaConta : lista) {
			if("SistemaConta".equals(sistemaConta.getNome())) {
				novo = sistemaConta;
			}
		}
		
		Assert.assertNotNull(novo);
		Assert.assertNotNull(novo.getId());
		Assert.assertNotNull(novo.getIdContaRoot());
		
		Conta despesa = novo.getContaDespesa();
		Conta receita = novo.getContaReceita();
		
		SistemaContaIntegrationTest.adicionaAlgumasDespesas(despesa);
		SistemaContaIntegrationTest.adicionaAlgumasReceitas(receita);
		
		BigDecimal saldoOperacional = sistemaContaService.getSaldoOperacional(
				novo.getId(),
				TestUtils.createDate(2010, 1, 5), 
				TestUtils.createDate(2010, 1, 30));
		BigDecimal saldoOperacionalEsperado = new BigDecimal("558.00");
		
		Assert.assertEquals(saldoOperacionalEsperado, saldoOperacional);
		
		/*
		 * retorna o saldo operacional em 30/1/2010
		 */
		saldoOperacional = sistemaContaService.getSaldoOperacional(
				novo.getId(), 
				TestUtils.createDate(2010, 1, 30));
		saldoOperacionalEsperado = new BigDecimal("558.00");
		
		Assert.assertEquals(saldoOperacionalEsperado, saldoOperacional);
    }
	
	@Test
	public void testAddNovoSistemaConta() {
		String NOME_SISTEMA = "NovoSistema";
		sistemaContaService.addSistemaConta(NOME_SISTEMA);
		
		List<SistemaConta> lista = SistemaConta.findAllSistemaContas();
		Assert.assertNotNull(lista);
		Assert.assertTrue(lista.size() > 0);
		
		SistemaConta novo = null;
		for (SistemaConta sistemaConta : lista) {
			if(NOME_SISTEMA.equals(sistemaConta.getNome())) {
				novo = sistemaConta;
			}
		}
		
		Assert.assertNotNull(novo);
		Assert.assertNotNull(novo.getId());
		Assert.assertNotNull(novo.getIdContaRoot());
		
		Conta rootConta = Conta.findConta(novo.getIdContaRoot());
		Assert.assertNotNull(rootConta);
		
		Assert.assertEquals(NOME_SISTEMA, rootConta.getNome());
		
		Set<Conta> children = rootConta.getChildren();
		Assert.assertNotNull(children);
		
		//verifica que as 4 contas basicas (receita, despeas, ativo e passivo)
		//foram criadas
		//FIXME Internacionalizar os nomes das contas basicas
		Assert.assertEquals(4, children.size());
		String NOME_RECEITA = "br.com.bufunfa.finance.i18n.nomeContaReceita";
		String NOME_DESPESA = "br.com.bufunfa.finance.i18n.nomeContaDespesa";
		String NOME_ATIVO = "br.com.bufunfa.finance.i18n.nomeContaAtivo";
		String NOME_PASSIVO = "br.com.bufunfa.finance.i18n.nomeContaPassivo";
		Conta receita = null;
		Conta despesa = null;
		Conta ativo = null;
		Conta passivo = null;
		
		for (Conta conta : children) {
			if(NOME_RECEITA.equals(conta.getNome()))
				receita = conta;
			if(NOME_DESPESA.equals(conta.getNome()))
				despesa = conta;
			if(NOME_ATIVO.equals(conta.getNome()))
				ativo = conta;
			if(NOME_PASSIVO.equals(conta.getNome()))
				passivo = conta;
		}
		
		Assert.assertNotNull(receita);
		Assert.assertNotNull(despesa);
		Assert.assertNotNull(ativo);
		Assert.assertNotNull(passivo);
		
		Assert.assertEquals(NOME_RECEITA, receita.getNome());
		Assert.assertEquals(NOME_DESPESA, despesa.getNome());
		Assert.assertEquals(NOME_ATIVO, ativo.getNome());
		Assert.assertEquals(NOME_PASSIVO, passivo.getNome());
		
		Assert.assertEquals(rootConta.getId(), receita.getIdFather());
		Assert.assertEquals(rootConta.getId(), despesa.getIdFather());
		Assert.assertEquals(rootConta.getId(), ativo.getIdFather());
		Assert.assertEquals(rootConta.getId(), passivo.getIdFather());
	}

}
