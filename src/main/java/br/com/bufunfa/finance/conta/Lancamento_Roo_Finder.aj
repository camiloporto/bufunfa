package br.com.bufunfa.finance.conta;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;

privileged aspect Lancamento_Roo_Finder {
    
    public static Query Lancamento.findLancamentoesByDataEfetivacaoBetween(Date minDataEfetivacao, Date maxDataEfetivacao) {
        if (minDataEfetivacao == null) throw new IllegalArgumentException("The minDataEfetivacao argument is required");
        if (maxDataEfetivacao == null) throw new IllegalArgumentException("The maxDataEfetivacao argument is required");
        EntityManager em = Lancamento.entityManager();
        Query q = em.createQuery("SELECT Lancamento FROM Lancamento AS lancamento WHERE lancamento.dataEfetivacao BETWEEN :minDataEfetivacao AND :maxDataEfetivacao");
        q.setParameter("minDataEfetivacao", minDataEfetivacao);
        q.setParameter("maxDataEfetivacao", maxDataEfetivacao);
        return q;
    }
    
}
