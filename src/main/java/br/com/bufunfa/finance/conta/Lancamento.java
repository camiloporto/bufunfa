package br.com.bufunfa.finance.conta;

import javax.persistence.Entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@RooJavaBean
@RooToString
@RooSerializable
@RooEntity(finders = { "findLancamentoesByDataEfetivacaoBetween" })
public class Lancamento {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2396515845940328300L;

	@NotNull
    private Long idConta;

    @NotNull
    private BigDecimal quantidade;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date dataRegistro;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date dataEfetivacao;

    private String descricao;
}
