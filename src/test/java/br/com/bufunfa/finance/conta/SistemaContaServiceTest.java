/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
