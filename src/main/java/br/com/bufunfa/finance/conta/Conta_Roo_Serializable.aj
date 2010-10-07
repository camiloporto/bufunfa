package br.com.bufunfa.finance.conta;

import java.io.Serializable;

privileged aspect Conta_Roo_Serializable {
    
    declare parents: Conta implements Serializable;
    
}
