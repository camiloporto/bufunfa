/**
 * 
 */
package br.com.bufunfa.finance.wui;

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
public class UIContaTest {
	
	@Resource(name="Conta")
	private UIConta uiConta;
	
	public UIContaTest() {
		
	}
	
	@Test
	public void testDefault() {
		
	}
	
//	@Test
	public void testInitialState() {
		Assert.assertNotNull(uiConta);
		Assert.assertNotNull(uiConta.getRoot());
	}

}
