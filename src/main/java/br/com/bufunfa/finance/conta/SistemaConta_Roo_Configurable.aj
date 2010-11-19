package br.com.bufunfa.finance.conta;

import org.springframework.beans.factory.annotation.Configurable;

privileged aspect SistemaConta_Roo_Configurable {
    
    declare @type: SistemaConta: @Configurable;
    
}
