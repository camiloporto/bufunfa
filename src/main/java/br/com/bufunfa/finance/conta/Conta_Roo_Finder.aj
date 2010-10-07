package br.com.bufunfa.finance.conta;

import java.lang.Long;
import java.lang.String;
import javax.persistence.EntityManager;
import javax.persistence.Query;

privileged aspect Conta_Roo_Finder {
    
    public static Query Conta.findContasByIdFather(Long idFather) {
        if (idFather == null) throw new IllegalArgumentException("The idFather argument is required");
        EntityManager em = Conta.entityManager();
        Query q = em.createQuery("SELECT Conta FROM Conta AS conta WHERE conta.idFather = :idFather");
        q.setParameter("idFather", idFather);
        return q;
    }
    
    public static Query Conta.findContasByIdFatherIsNull() {
        EntityManager em = Conta.entityManager();
        Query q = em.createQuery("SELECT Conta FROM Conta AS conta WHERE conta.idFather IS NULL");
        return q;
    }
    
    public static Query Conta.findContasByNome(String nome) {
        if (nome == null || nome.length() == 0) throw new IllegalArgumentException("The nome argument is required");
        EntityManager em = Conta.entityManager();
        Query q = em.createQuery("SELECT Conta FROM Conta AS conta WHERE conta.nome = :nome");
        q.setParameter("nome", nome);
        return q;
    }
    
}
