package br.com.bufunfa.finance.conta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import br.com.bufunfa.finance.utils.DateUtil;

/**
 * Essa classe representa um sistema de contas.
 * Um sistema de contas eh uma hierarquia de contas
 * 
 * Essa classe fornece dados basicos sobre
 * uma hierarquia de conta bem como um link
 * para a conta raiz da hierarquia de contas
 * 
 * @author camilo
 * @see Conta
 *
 */
@Entity
@RooJavaBean
@RooToString
@RooEntity
@RooSerializable
public class SistemaConta {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
    private Long idContaRoot;

    @NotNull
    private String nome;
    //FIXME Detalhes do sistema de contas deverah ficar em um Perfil?
    
    //FIXME refatorar calculos de saldo operacional e balanco.. fazer um metodo que faca operacoes basica entre quaisquer conta (somar, subtrai, etc..)
    
    /**
     * Retorna o saldo operacional das receitas e despesas
     * em determinado periodo
     * 
     */
    public BigDecimal getSaldoOperacional(Date inicio, Date fim) {
    	BigDecimal saldoReceita = getContaReceita().getSomaLancamentos(inicio, fim);
    	BigDecimal saldoDespesa = getContaDespesa().getSomaLancamentos(inicio, fim);
    	
    	return saldoReceita.add(saldoDespesa).setScale(2, RoundingMode.HALF_EVEN);
    }
    
    public BigDecimal getSaldoOperacional(Date dataReferencia) {
    	BigDecimal saldoReceita = getContaReceita().getSaldo(dataReferencia);
    	BigDecimal saldoDespesa = getContaDespesa().getSaldo(dataReferencia);
    	
    	return saldoReceita.add(saldoDespesa).setScale(2, RoundingMode.HALF_EVEN);
    }
    
    /**
     * Retorna a conta de passivos do Sistema
     * de contas
     * @return
     */
    Conta getContaPassivo() {
    	return getChildrenByNome(Conta.NOME_PASSIVO);
    }
    
    /**
     * Retorna a conta de ativos do Sistema
     * de contas
     * @return
     */
    Conta getContaAtivo() {
    	return getChildrenByNome(Conta.NOME_ATIVO);
    }
    
    /**
     * Retorna a conta de despesa do Sistema
     * de contas
     * @return conta de despesa
     */
    Conta getContaDespesa() {
    	return getChildrenByNome(Conta.NOME_DESPESA);
    }
    
    /**
     * Retorna a conta de receita do Sistema
     * de contas
     * @return conta de receita
     */
    Conta getContaReceita() {
    	
    	//Nunca poderah retornar null. Todo sistema de conta deve ter uma conta de receitas
    	return getChildrenByNome(Conta.NOME_RECEITA);
    	
    }
    
    private Conta getChildrenByNome(String nomeChild) {
    	Conta root = getRoot();
    	Set<Conta> children = root.getChildren();
    	for (Conta conta : children) {
			if(nomeChild.equals(conta.getNome()))
				return conta;
		}
    	
    	return null;
    }
    
    Conta getRoot() {
    	return Conta.findConta(idContaRoot);
    }

    /**
     * Retorna o saldo operacional de caixa de
     * um periodo
     * @param beginDate inicio do periodo
     * @param endDate final do periodo
     * @return relatorio de saldo operacional de caixa
     */
	public RelatorioSaldoCaixa getSaldoOperacionalDeCaixa(Date beginDate,
			Date endDate) {
		
		//FIXME validar entradas
		Date dataAnterior = DateUtil.getDiaAnterior(beginDate);
		
		dataAnterior = DateUtil.maximizeDate(dataAnterior);
		
		BigDecimal saldoAnterior = getSaldoOperacional(dataAnterior);
		
		BigDecimal saldoOperacionalDoPeriodo = getSaldoOperacional(beginDate, endDate);
		
		return new RelatorioSaldoCaixa(beginDate, endDate, saldoAnterior, saldoOperacionalDoPeriodo);
	}

	/**
	 * Retorna o balanco patrimonial do sistema 
	 * de contas em determinado periodo de tempo
	 * @param inicio inicio do periodo (opcional)
	 * @param fim final do periodo (obrigatorio)
	 * @return balanco patrimonial
	 */
	BalancoPatrimonial getBalancoPatrimonial(Date inicio,
			Date fim) {
		Date fakeInicio = null;
		BigDecimal saldoAtivo = null;
		BigDecimal saldoPassivo = null;
		if(inicio == null) {
			fakeInicio = DateUtil.MINIMUM_DATE;
			saldoAtivo = getContaAtivo().getSomaLancamentos(fakeInicio, fim);
			saldoPassivo = getContaPassivo().getSomaLancamentos(fakeInicio, fim);
			return new BalancoPatrimonial(saldoAtivo, saldoPassivo, inicio, fim);
		}
		
		saldoAtivo = getContaAtivo().getSomaLancamentos(inicio, fim);
    	saldoPassivo = getContaPassivo().getSomaLancamentos(inicio, fim);
    	
    	return new BalancoPatrimonial(saldoAtivo, saldoPassivo, inicio, fim);
	}
}
