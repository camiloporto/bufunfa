package br.com.bufunfa.finance.conta;

import java.lang.Long;
import java.lang.String;

privileged aspect ContaImpl_Roo_JavaBean {
    
    public String ContaImpl.getNome() {
        return this.nome;
    }
    
    public void ContaImpl.setNome(String nome) {
        this.nome = nome;
    }
    
    public String ContaImpl.getDescricao() {
        return this.descricao;
    }
    
    public void ContaImpl.setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Long ContaImpl.getIdFather() {
        return this.idFather;
    }
    
    public void ContaImpl.setIdFather(Long idFather) {
        this.idFather = idFather;
    }
    
}
