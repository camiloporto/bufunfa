/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.io.Serializable;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 * @author camilo
 *
 */
@Service("contaService")
public class ContaServiceImpl implements Serializable, IContaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3053392618339886088L;

	/* (non-Javadoc)
	 * @see br.com.bufunfa.finance.conta.IContaService#getRootContas()
	 */
	@Override
	public Set<ContaImpl> getRootContas() {
		return ContaImpl.getRoots();
	}
	
	public void addConta(Long id, String nome) {
		
		ContaImpl c = new ContaImpl();
		c.setId(id);
		c.setNome(nome);
		System.out.println("ContaServiceImpl.addConta() adding " + c);
		c.persist();
	}

}
