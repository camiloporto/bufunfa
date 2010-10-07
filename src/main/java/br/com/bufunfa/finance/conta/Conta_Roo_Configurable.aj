package br.com.bufunfa.finance.conta;

import org.springframework.beans.factory.annotation.Configurable;

privileged aspect Conta_Roo_Configurable {
    
    declare @type: Conta: @Configurable;
    
}
