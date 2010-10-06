package br.com.bufunfa.finance.conta;

import java.lang.Long;
import java.lang.String;
import javax.persistence.EntityManager;
import javax.persistence.Query;

privileged aspect ContaImpl_Roo_Finder {
    
    public static Query ContaImpl.findContaImplsByIdFather(Long idFather) {
        if (idFather == null) throw new IllegalArgumentException("The idFather argument is required");
        EntityManager em = ContaImpl.entityManager();
        Query q = em.createQuery("SELECT ContaImpl FROM ContaImpl AS contaimpl WHERE contaimpl.idFather = :idFather");
        q.setParameter("idFather", idFather);
        return q;
    }
    
    public static Query ContaImpl.findContaImplsByIdFatherIsNull() {
        EntityManager em = ContaImpl.entityManager();
        Query q = em.createQuery("SELECT ContaImpl FROM ContaImpl AS contaimpl WHERE contaimpl.idFather IS NULL");
        return q;
    }
    
    public static Query ContaImpl.findContaImplsByNome(String nome) {
        if (nome == null || nome.length() == 0) throw new IllegalArgumentException("The nome argument is required");
        EntityManager em = ContaImpl.entityManager();
        Query q = em.createQuery("SELECT ContaImpl FROM ContaImpl AS contaimpl WHERE contaimpl.nome = :nome");
        q.setParameter("nome", nome);
        return q;
    }
    
}
