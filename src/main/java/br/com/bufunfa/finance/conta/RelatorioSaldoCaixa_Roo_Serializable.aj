package br.com.bufunfa.finance.conta;

import java.io.Serializable;

privileged aspect RelatorioSaldoCaixa_Roo_Serializable {
    
    declare parents: RelatorioSaldoCaixa implements Serializable;
    
    private static final long RelatorioSaldoCaixa.serialVersionUID = -7653484492832216575L;
    
}
