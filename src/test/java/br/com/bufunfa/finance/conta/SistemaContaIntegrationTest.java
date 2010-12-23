package br.com.bufunfa.finance.conta;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;

import br.com.bufunfa.finance.utils.DateUtil;
import br.com.bufunfa.finance.utils.TestUtils;

@RooIntegrationTest(entity = SistemaConta.class)
public class SistemaContaIntegrationTest {
	
	@Resource(name="sistemaContaService")
	private ISistemaContaService sistemaContaService;
	
	public SistemaContaIntegrationTest() {
		
	}
	
	@Test
	public void testGetBalancoPatrimonial() {
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
		
		Conta ativo = novo.getContaAtivo();
		Conta passivo = novo.getContaPassivo();
		
		
		adicionaAlgunsAtivos(ativo);
		adicionaAlgunsPassivos(passivo);
		
		/*
		 * retorna o balanco patrimonial em 30/1/2010
		 */
		
		BalancoPatrimonial balanco = novo.getBalancoPatrimonial(
				TestUtils.createDate(2010, 1, 10), 
				TestUtils.createDate(2010, 1, 15));
		
		Assert.assertNotNull(balanco);
		
		BalancoPatrimonial balancoEsperado = new BalancoPatrimonial(
				new BigDecimal("50000"),
				new BigDecimal("-10500"),
				TestUtils.createDate(2010, 1, 10), 
				TestUtils.createDate(2010, 1, 15));
		BigDecimal patrimonioLiquidoEsperado = new BigDecimal("39500.00");
		
		Assert.assertEquals(balancoEsperado.getAtivo(), balanco.getAtivo());
		Assert.assertEquals(balancoEsperado.getPassivo(), balanco.getPassivo());
		Assert.assertTrue(DateUtil.isDayMonthYearEqual(balancoEsperado.getInicioPeriodo(), balanco.getInicioPeriodo()));
		Assert.assertTrue(DateUtil.isDayMonthYearEqual(balancoEsperado.getFinalPeriodo(), balanco.getFinalPeriodo()));
		Assert.assertEquals(patrimonioLiquidoEsperado, balanco.getPatrimonioLiquido());
		
		//verifica o balanco, sem passar o inicio do periodo
		//nesse caso, deve vir o balanco desde o 1o lancamento
		balanco = novo.getBalancoPatrimonial(
				null, 
				TestUtils.createDate(2010, 1, 30));
		
		Assert.assertNotNull(balanco);
		balancoEsperado = new BalancoPatrimonial(
				new BigDecimal("55000"),
				new BigDecimal("-30500"),
				null, 
				TestUtils.createDate(2010, 1, 30));
		patrimonioLiquidoEsperado = new BigDecimal("24500.00");
		
		Assert.assertEquals(balancoEsperado.getAtivo(), balanco.getAtivo());
		Assert.assertEquals(balancoEsperado.getPassivo(), balanco.getPassivo());
		Assert.assertTrue(DateUtil.isDayMonthYearEqual(balancoEsperado.getFinalPeriodo(), balanco.getFinalPeriodo()));
		Assert.assertNull(balanco.getInicioPeriodo());
		Assert.assertEquals(patrimonioLiquidoEsperado, balanco.getPatrimonioLiquido());
		
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
		
		adicionaAlgumasDespesas(despesa);
		adicionaAlgumasReceitas(receita);
		
		
		/*
		 * retorna o saldo operacional de caixa entre 10/1/2010 e 30/1/2010
		 */
		RelatorioSaldoCaixa relatorioSaldoCaixa = novo.getSaldoOperacionalDeCaixa(
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
		
		adicionaAlgumasDespesas(despesa);
		adicionaAlgumasReceitas(receita);
		
		BigDecimal saldoOperacional = novo.getSaldoOperacional(TestUtils.createDate(2010, 1, 30));
		BigDecimal saldoOperacionalEsperado = new BigDecimal("558.00");
		
		Assert.assertEquals(saldoOperacionalEsperado, saldoOperacional);
		
		/*
		 * retorna o saldo operacional entre 10/1/2010 e 30/1/2010
		 */
		saldoOperacional = novo.getSaldoOperacional(
				TestUtils.createDate(2010, 1, 10), 
				TestUtils.createDate(2010, 1, 30));
		saldoOperacionalEsperado = new BigDecimal("593.00");
		
		Assert.assertEquals(saldoOperacionalEsperado, saldoOperacional);
    }
    
    static void adicionaAlgumasReceitas(Conta receita) {
    	Lancamento l1 = TestUtils.createLancamento(
				20.0, 
				TestUtils.createDate(2010, 1, 5),
				TestUtils.createDate(2010, 1, 5), 
				"Deposito efetuado");
		
		Lancamento l2 = TestUtils.createLancamento(
				1500.00, 
				TestUtils.createDate(2010, 1, 10),
				TestUtils.createDate(2010, 1, 10), 
				"Salario recebido");
		
		Lancamento l3 = TestUtils.createLancamento(
				150.0, 
				TestUtils.createDate(2010, 1, 15),
				TestUtils.createDate(2010, 1, 15), 
				"Reembolso");
		
		receita.addLancamento(l1);
		receita.addLancamento(l2);
		receita.addLancamento(l3);
		
	}
    
    /**
     * Adiciona 3 passivos (total de -30.500,00)
     * @param passivo conta de pssivos
     */
	static void adicionaAlgunsPassivos(Conta passivo) {
		Lancamento l1 = TestUtils.createLancamento(
				-20000.0, 
				TestUtils.createDate(2010, 1, 5),
				TestUtils.createDate(2010, 1, 5), 
				"Financimaneto da Compra de carro");
		
		Lancamento l2 = TestUtils.createLancamento(
				-10000.0, 
				TestUtils.createDate(2010, 1, 10),
				TestUtils.createDate(2010, 1, 10), 
				"financiamento de Compra de apartamento");
		
		Lancamento l3 = TestUtils.createLancamento(
				-500.0, 
				TestUtils.createDate(2010, 1, 15),
				TestUtils.createDate(2010, 1, 15), 
				"divida de cartao de credito");
		
		passivo.addLancamento(l1);
		passivo.addLancamento(l2);
		passivo.addLancamento(l3);
		
	}
    
    /**
     * Adiciona 3 ativos (total de 55.000,00)
     * @param ativo conta de ativos
     */
	static void adicionaAlgunsAtivos(Conta ativo) {
		Lancamento l1 = TestUtils.createLancamento(
				5000.0, 
				TestUtils.createDate(2010, 1, 5),
				TestUtils.createDate(2010, 1, 5), 
				"Compra de carro");
		
		Lancamento l2 = TestUtils.createLancamento(
				35000.0, 
				TestUtils.createDate(2010, 1, 10),
				TestUtils.createDate(2010, 1, 10), 
				"Compra de apartamento");
		
		Lancamento l3 = TestUtils.createLancamento(
				15000.0, 
				TestUtils.createDate(2010, 1, 15),
				TestUtils.createDate(2010, 1, 15), 
				"Compra de acoes");
		
		ativo.addLancamento(l1);
		ativo.addLancamento(l2);
		ativo.addLancamento(l3);
		
	}
    
	/**
     * Adiciona 3 despesas
     * @param despesa conta de despesas
     */
	static void adicionaAlgumasDespesas(Conta despesa) {
		Lancamento l1 = TestUtils.createLancamento(
				-55.0, 
				TestUtils.createDate(2010, 1, 5),
				TestUtils.createDate(2010, 1, 5), 
				"Deposito efetuado");
		
		Lancamento l2 = TestUtils.createLancamento(
				-850, 
				TestUtils.createDate(2010, 1, 10),
				TestUtils.createDate(2010, 1, 10), 
				"Auguel");
		
		Lancamento l3 = TestUtils.createLancamento(
				-207.0, 
				TestUtils.createDate(2010, 1, 15),
				TestUtils.createDate(2010, 1, 15), 
				"Condominio");
		
		despesa.addLancamento(l1);
		despesa.addLancamento(l2);
		despesa.addLancamento(l3);
		
	}
}
