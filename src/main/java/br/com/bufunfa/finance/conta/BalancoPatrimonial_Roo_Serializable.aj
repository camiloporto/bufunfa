package br.com.bufunfa.finance.conta;

import java.io.Serializable;

privileged aspect BalancoPatrimonial_Roo_Serializable {
    
    declare parents: BalancoPatrimonial implements Serializable;
    
}
