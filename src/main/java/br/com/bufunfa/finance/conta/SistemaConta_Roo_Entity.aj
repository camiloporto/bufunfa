package br.com.bufunfa.finance.conta;

import br.com.bufunfa.finance.conta.SistemaConta;
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

privileged aspect SistemaConta_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager SistemaConta.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long SistemaConta.id;
    
    @Version
    @Column(name = "version")
    private Integer SistemaConta.version;
    
    public Long SistemaConta.getId() {
        return this.id;
    }
    
    public void SistemaConta.setId(Long id) {
        this.id = id;
    }
    
    public Integer SistemaConta.getVersion() {
        return this.version;
    }
    
    public void SistemaConta.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void SistemaConta.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void SistemaConta.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            SistemaConta attached = this.entityManager.find(this.getClass(), this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void SistemaConta.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public SistemaConta SistemaConta.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SistemaConta merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager SistemaConta.entityManager() {
        EntityManager em = new SistemaConta().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long SistemaConta.countSistemaContas() {
        return ((Number) entityManager().createQuery("select count(o) from SistemaConta o").getSingleResult()).longValue();
    }
    
    @SuppressWarnings("unchecked")
    public static List<SistemaConta> SistemaConta.findAllSistemaContas() {
        return entityManager().createQuery("select o from SistemaConta o").getResultList();
    }
    
    public static SistemaConta SistemaConta.findSistemaConta(Long id) {
        if (id == null) return null;
        return entityManager().find(SistemaConta.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public static List<SistemaConta> SistemaConta.findSistemaContaEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from SistemaConta o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
