package br.com.bufunfa.finance.conta;

import java.math.BigDecimal;
import java.util.Date;

privileged aspect RelatorioSaldoCaixa_Roo_JavaBean {
    
    public Date RelatorioSaldoCaixa.getInicioPeriodo() {
        return this.inicioPeriodo;
    }
    
    public void RelatorioSaldoCaixa.setInicioPeriodo(Date inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }
    
    public Date RelatorioSaldoCaixa.getFimPeriodo() {
        return this.fimPeriodo;
    }
    
    public void RelatorioSaldoCaixa.setFimPeriodo(Date fimPeriodo) {
        this.fimPeriodo = fimPeriodo;
    }
    
    public BigDecimal RelatorioSaldoCaixa.getSaldoAnterior() {
        return this.saldoAnterior;
    }
    
    public void RelatorioSaldoCaixa.setSaldoAnterior(BigDecimal saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }
    
    public BigDecimal RelatorioSaldoCaixa.getSaldoOperacionalDoPeriodo() {
        return this.saldoOperacionalDoPeriodo;
    }
    
    public void RelatorioSaldoCaixa.setSaldoOperacionalDoPeriodo(BigDecimal saldoOperacionalDoPeriodo) {
        this.saldoOperacionalDoPeriodo = saldoOperacionalDoPeriodo;
    }
    
}
