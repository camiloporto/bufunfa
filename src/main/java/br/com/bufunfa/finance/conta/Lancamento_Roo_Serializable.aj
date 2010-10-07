package br.com.bufunfa.finance.conta;

import java.io.Serializable;

privileged aspect Lancamento_Roo_Serializable {
    
    declare parents: Lancamento implements Serializable;
    
    private static final long Lancamento.serialVersionUID = 8345492188083983789L;
    
}
