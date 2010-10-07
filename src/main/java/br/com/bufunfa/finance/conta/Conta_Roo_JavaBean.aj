package br.com.bufunfa.finance.conta;

import java.lang.Long;
import java.lang.String;

privileged aspect Conta_Roo_JavaBean {
    
    public String Conta.getNome() {
        return this.nome;
    }
    
    public void Conta.setNome(String nome) {
        this.nome = nome;
    }
    
    public String Conta.getDescricao() {
        return this.descricao;
    }
    
    public void Conta.setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Long Conta.getIdFather() {
        return this.idFather;
    }
    
    public void Conta.setIdFather(Long idFather) {
        this.idFather = idFather;
    }
    
}
