package br.com.bufunfa.finance.conta;

import br.com.bufunfa.finance.conta.ContaImpl;
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

privileged aspect ContaImpl_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager ContaImpl.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ContaImpl.id;
    
    @Version
    @Column(name = "version")
    private Integer ContaImpl.version;
    
    public Long ContaImpl.getId() {
        return this.id;
    }
    
    public void ContaImpl.setId(Long id) {
        this.id = id;
    }
    
    public Integer ContaImpl.getVersion() {
        return this.version;
    }
    
    public void ContaImpl.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void ContaImpl.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void ContaImpl.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            ContaImpl attached = this.entityManager.find(this.getClass(), this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void ContaImpl.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public ContaImpl ContaImpl.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        ContaImpl merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager ContaImpl.entityManager() {
        EntityManager em = new ContaImpl().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long ContaImpl.countContaImpls() {
        return ((Number) entityManager().createQuery("select count(o) from ContaImpl o").getSingleResult()).longValue();
    }
    
    @SuppressWarnings("unchecked")
    public static List<ContaImpl> ContaImpl.findAllContaImpls() {
        return entityManager().createQuery("select o from ContaImpl o").getResultList();
    }
    
    public static ContaImpl ContaImpl.findContaImpl(Long id) {
        if (id == null) return null;
        return entityManager().find(ContaImpl.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public static List<ContaImpl> ContaImpl.findContaImplEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from ContaImpl o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
