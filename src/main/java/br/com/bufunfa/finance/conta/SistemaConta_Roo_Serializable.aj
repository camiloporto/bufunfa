package br.com.bufunfa.finance.conta;

import java.io.Serializable;

privileged aspect SistemaConta_Roo_Serializable {
    
    declare parents: SistemaConta implements Serializable;
    
}
