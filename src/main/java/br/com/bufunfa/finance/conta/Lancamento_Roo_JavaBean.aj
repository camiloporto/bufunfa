package br.com.bufunfa.finance.conta;

import java.lang.Double;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

privileged aspect Lancamento_Roo_JavaBean {
    
    public Long Lancamento.getIdConta() {
        return this.idConta;
    }
    
    public void Lancamento.setIdConta(Long idConta) {
        this.idConta = idConta;
    }
    
    public Double Lancamento.getQuantidade() {
        return this.quantidade;
    }
    
    public void Lancamento.setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
    
    public Date Lancamento.getDataRegistro() {
        return this.dataRegistro;
    }
    
    public void Lancamento.setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
    
    public Date Lancamento.getDataEfetivacao() {
        return this.dataEfetivacao;
    }
    
    public void Lancamento.setDataEfetivacao(Date dataEfetivacao) {
        this.dataEfetivacao = dataEfetivacao;
    }
    
    public String Lancamento.getDescricao() {
        return this.descricao;
    }
    
    public void Lancamento.setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
