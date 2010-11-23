package br.com.bufunfa.finance.conta;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;

import br.com.bufunfa.finance.utils.TestUtils;

@RooIntegrationTest(entity = SistemaConta.class)
public class SistemaContaIntegrationTest {
	
	@Resource(name="sistemaContaService")
	private ISistemaContaService sistemaContaService;
	
	public SistemaContaIntegrationTest() {
		
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
		
		//FIXME Corrigir teste: implementar saldo operacional por periodo: efetuar consuta semelhante ao extrato somando os lancamentos de cada conta e etc..
		Assert.assertEquals(saldoOperacionalEsperado, saldoOperacional);
    }
    
    private void adicionaAlgumasReceitas(Conta receita) {
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
     * Adiciona 3 despesas
     * @param despesa conta de despesas
     */
	private void adicionaAlgumasDespesas(Conta despesa) {
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
