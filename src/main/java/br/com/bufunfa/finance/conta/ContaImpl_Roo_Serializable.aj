package br.com.bufunfa.finance.conta;

import java.io.Serializable;

privileged aspect ContaImpl_Roo_Serializable {
    
    declare parents: ContaImpl implements Serializable;
    
}
