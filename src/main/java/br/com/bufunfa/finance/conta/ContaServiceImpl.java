/**
 * 
 */
package br.com.bufunfa.finance.conta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
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
	public Set<Conta> getRootContas() {
		return Conta.getRoots();
	}
	/**
	 * Adiciona uma conta na hierarquia de conta
	 */
	public void addConta(ContaServiceParams params) {
		//FIXME Efetuar validacao
		
		if(params.getNomePai() == null) {//Inserir conta Raiz
			Conta newConta = new Conta();
			newConta.setNome(params.getNome());
			newConta.setDescricao(params.getDescricao());
			newConta.persist();
			return;
		} else {//inserir conta abaixo de outra conta (pai)
			//FIXME fazer com que nome do Pai e da conta seja unico
			List<Conta> result = Conta.findContasByNome(params.getNomePai()).getResultList();
			if(!result.isEmpty()) {
				Conta father = (Conta) result.get(0);
				Conta newConta = new Conta();
				newConta.setNome(params.getNome());
				newConta.setDescricao(params.getDescricao());
				
				father.addChild(newConta);
			}
			
		}
	}
	
	/**
	 * Adiciona uma transacao a uma conta
	 * @param origem conta de origem (para retirar a quantidade)
	 * @param destino conta de destino (a adicionar a quantidade)
	 * @param quantidade quantidade a ser lancada nas contas
	 * @param descricao descricao da transacao
	 * @param dataEfetivacao data para a transacao ser efetivada (pode ser uma data futura
	 */
	public void addTransacao(Conta origem, Conta destino, BigDecimal quantidade, String descricao, Date dataEfetivacao) {
		
		Date dataRegistro = Calendar.getInstance().getTime(); 
		
		Lancamento l = new Lancamento();
		l.setDataEfetivacao(dataEfetivacao);
		l.setDataRegistro(dataRegistro);//data do registro eh a data atual
		l.setDescricao(descricao);
		l.setQuantidade(quantidade.negate());
		
		origem.addLancamento(l);
		
		Lancamento l2 = new Lancamento();
		l2.setDataEfetivacao(dataEfetivacao);
		l2.setDataRegistro(dataRegistro);//data do registro eh a data atual
		l2.setDescricao(descricao);
		l2.setQuantidade(quantidade);
		
		destino.addLancamento(l2);
		
	}
	
	/**
	 * @see IContaService#getExtrato(Long, Date, Date)
	 */
	public Extrato getExtrato(Long idConta, Date inicio, Date fim) {
		Conta c = Conta.findConta(idConta);
		List<Lancamento> lancamentos = c.getLancamentos(inicio, fim);
		Extrato e = new Extrato(c, lancamentos);
		
		return e;
	}
	
}
