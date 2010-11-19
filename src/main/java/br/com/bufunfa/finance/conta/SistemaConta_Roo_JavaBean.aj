package br.com.bufunfa.finance.conta;

import java.lang.Long;
import java.lang.String;

privileged aspect SistemaConta_Roo_JavaBean {
    
    public Long SistemaConta.getIdContaRoot() {
        return this.idContaRoot;
    }
    
    public void SistemaConta.setIdContaRoot(Long idContaRoot) {
        this.idContaRoot = idContaRoot;
    }
    
    public String SistemaConta.getNome() {
        return this.nome;
    }
    
    public void SistemaConta.setNome(String nome) {
        this.nome = nome;
    }
    
}
