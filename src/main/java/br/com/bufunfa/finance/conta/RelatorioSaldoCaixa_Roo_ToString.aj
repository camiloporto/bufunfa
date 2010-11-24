package br.com.bufunfa.finance.conta;

import java.lang.String;

privileged aspect RelatorioSaldoCaixa_Roo_ToString {
    
    public String RelatorioSaldoCaixa.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SaldoPosterior: ").append(getSaldoPosterior()).append(", ");
        sb.append("InicioPeriodo: ").append(getInicioPeriodo()).append(", ");
        sb.append("FimPeriodo: ").append(getFimPeriodo()).append(", ");
        sb.append("SaldoAnterior: ").append(getSaldoAnterior()).append(", ");
        sb.append("SaldoOperacionalDoPeriodo: ").append(getSaldoOperacionalDoPeriodo());
        return sb.toString();
    }
    
}
