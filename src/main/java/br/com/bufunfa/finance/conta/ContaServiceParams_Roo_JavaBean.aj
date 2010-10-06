package br.com.bufunfa.finance.conta;

import java.lang.String;

privileged aspect ContaServiceParams_Roo_JavaBean {
    
    public String ContaServiceParams.getNome() {
        return this.nome;
    }
    
    public String ContaServiceParams.getDescricao() {
        return this.descricao;
    }
    
    public String ContaServiceParams.getNomePai() {
        return this.nomePai;
    }
    
}
