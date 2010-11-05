package br.com.bufunfa.finance.conta;

import java.io.Serializable;

privileged aspect Extrato_Roo_Serializable {
    
    declare parents: Extrato implements Serializable;
    
}
