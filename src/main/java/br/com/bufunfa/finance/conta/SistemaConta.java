package br.com.bufunfa.finance.conta;

import javax.persistence.Entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import javax.validation.constraints.NotNull;

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
}
