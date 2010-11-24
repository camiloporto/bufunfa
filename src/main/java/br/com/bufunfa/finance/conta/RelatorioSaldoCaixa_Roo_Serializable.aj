package br.com.bufunfa.finance.conta;

import java.io.Serializable;

privileged aspect RelatorioSaldoCaixa_Roo_Serializable {
    
    declare parents: RelatorioSaldoCaixa implements Serializable;
    
    private static final long RelatorioSaldoCaixa.serialVersionUID = 6419514958581749741L;
    
}
