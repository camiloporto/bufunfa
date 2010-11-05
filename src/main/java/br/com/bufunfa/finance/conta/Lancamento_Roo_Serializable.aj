package br.com.bufunfa.finance.conta;

import java.io.Serializable;

privileged aspect Lancamento_Roo_Serializable {
    
    declare parents: Lancamento implements Serializable;
    
}
