/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.io.Serializable;
import java.util.List;
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
	/**
	 * Adiciona uma conta na hierarquia de conta
	 */
	public void addConta(ContaServiceParams params) {
		//FIXME Efetuar validacao
		
		if(params.getNomePai() == null) {//Inserir conta Raiz
			ContaImpl newConta = new ContaImpl();
			newConta.setNome(params.getNome());
			newConta.setDescricao(params.getDescricao());
			newConta.persist();
			return;
		} else {//inserir conta abaixo de outra conta (pai)
			//FIXME fazer com que nome do Pai e da conta seja unico
			List<ContaImpl> result = ContaImpl.findContaImplsByNome(params.getNomePai()).getResultList();
			if(!result.isEmpty()) {
				ContaImpl father = (ContaImpl) result.get(0);
				ContaImpl newConta = new ContaImpl();
				newConta.setNome(params.getNome());
				newConta.setDescricao(params.getDescricao());
				
				father.addChild(newConta);
			}
			
		}
	}

}
