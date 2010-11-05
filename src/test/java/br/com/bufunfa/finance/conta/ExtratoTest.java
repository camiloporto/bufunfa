package br.com.bufunfa.finance.conta;

import java.math.BigDecimal;
import java.util.SortedSet;
import java.util.TreeSet;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.bufunfa.finance.utils.TestUtils;

public class ExtratoTest {
	
	private static Conta contaSample;
	
	private static SortedSet<Lancamento> lancamentos;
	
	public ExtratoTest() {
		
	}
	
	@BeforeClass
	public static void prepareClass() {
		contaSample = new Conta();
		contaSample.setNome("Conta Corrente");
		
		lancamentos = new TreeSet<Lancamento>();
		
		Lancamento l1 = TestUtils.createLancamento(
				20.0, 
				TestUtils.createDate(2010, 1, 1),
				TestUtils.createDate(2010, 1, 1), 
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
		
		lancamentos.add(l1);
		lancamentos.add(l2);
		lancamentos.add(l3);
		
	}
	
	@Test
	public void testGetSaldoExtrato() {
		Extrato e = new Extrato(contaSample, lancamentos);
		BigDecimal saldo = e.getSaldo();
		
		Assert.assertNotNull(saldo);
		Assert.assertEquals(new BigDecimal(145.0), saldo);
	}

}
