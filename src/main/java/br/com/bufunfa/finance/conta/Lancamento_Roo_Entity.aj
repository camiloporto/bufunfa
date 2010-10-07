package br.com.bufunfa.finance.conta;

import br.com.bufunfa.finance.conta.Lancamento;
import java.lang.Integer;
import java.lang.Long;
import java.lang.SuppressWarnings;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Lancamento_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager Lancamento.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Lancamento.id;
    
    @Version
    @Column(name = "version")
    private Integer Lancamento.version;
    
    public Long Lancamento.getId() {
        return this.id;
    }
    
    public void Lancamento.setId(Long id) {
        this.id = id;
    }
    
    public Integer Lancamento.getVersion() {
        return this.version;
    }
    
    public void Lancamento.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void Lancamento.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Lancamento.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Lancamento attached = this.entityManager.find(this.getClass(), this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Lancamento.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public Lancamento Lancamento.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Lancamento merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Lancamento.entityManager() {
        EntityManager em = new Lancamento().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Lancamento.countLancamentoes() {
        return ((Number) entityManager().createQuery("select count(o) from Lancamento o").getSingleResult()).longValue();
    }
    
    @SuppressWarnings("unchecked")
    public static List<Lancamento> Lancamento.findAllLancamentoes() {
        return entityManager().createQuery("select o from Lancamento o").getResultList();
    }
    
    public static Lancamento Lancamento.findLancamento(Long id) {
        if (id == null) return null;
        return entityManager().find(Lancamento.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public static List<Lancamento> Lancamento.findLancamentoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from Lancamento o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
