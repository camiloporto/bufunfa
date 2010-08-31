package br.com.bufunfa.finance.conta;

import java.util.Set;
import javax.persistence.Entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@RooJavaBean
@RooToString
@RooSerializable
@RooEntity(finders = { "findContaImplsByIdFather" })
public class ContaImpl {

    /**
	 * 
	 */
    private static final long serialVersionUID = -4675413456786824207L;

    @NotNull
    private String nome;

    private String descricao;

    private Long idFather;

    /**
     * Adiciona uma conta filha a conta atual
     * @param child conta filha
     * @return true se adicionada
     */
    public boolean addChild(@Valid ContaImpl child) {
        if (!isPersisted()) throw new IllegalStateException(getNome() + "nao foi salva");
        child.setIdFather(getId());
        child.persist();
        return true;
    }

    public Set<ContaImpl> getChildren() {
    	//TODO Verificar como usar o finder para recuupear as contas
    	return findContaImplsByIdFather(getId()).getResultList();
//        return null;
    }

    /**
     * Verifica se a conta atual ja foi persistida
     * @return true, se sim
     */
    private boolean isPersisted() {
        return null != getId();
    }
}
