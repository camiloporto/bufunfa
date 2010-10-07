package br.com.bufunfa.finance.conta;

import br.com.bufunfa.finance.conta.Conta;
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

privileged aspect Conta_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager Conta.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Conta.id;
    
    @Version
    @Column(name = "version")
    private Integer Conta.version;
    
    public Long Conta.getId() {
        return this.id;
    }
    
    public void Conta.setId(Long id) {
        this.id = id;
    }
    
    public Integer Conta.getVersion() {
        return this.version;
    }
    
    public void Conta.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void Conta.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Conta.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Conta attached = this.entityManager.find(this.getClass(), this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Conta.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public Conta Conta.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Conta merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Conta.entityManager() {
        EntityManager em = new Conta().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Conta.countContas() {
        return ((Number) entityManager().createQuery("select count(o) from Conta o").getSingleResult()).longValue();
    }
    
    @SuppressWarnings("unchecked")
    public static List<Conta> Conta.findAllContas() {
        return entityManager().createQuery("select o from Conta o").getResultList();
    }
    
    public static Conta Conta.findConta(Long id) {
        if (id == null) return null;
        return entityManager().find(Conta.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public static List<Conta> Conta.findContaEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from Conta o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
