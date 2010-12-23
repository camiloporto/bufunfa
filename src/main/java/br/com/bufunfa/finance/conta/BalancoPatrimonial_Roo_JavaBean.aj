package br.com.bufunfa.finance.conta;

import java.math.BigDecimal;
import java.util.Date;

privileged aspect BalancoPatrimonial_Roo_JavaBean {
    
    public BigDecimal BalancoPatrimonial.getAtivo() {
        return this.ativo;
    }
    
    public BigDecimal BalancoPatrimonial.getPassivo() {
        return this.passivo;
    }
    
    public Date BalancoPatrimonial.getInicioPeriodo() {
        return this.inicioPeriodo;
    }
    
    public Date BalancoPatrimonial.getFinalPeriodo() {
        return this.finalPeriodo;
    }
    
}
