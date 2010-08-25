package br.com.bufunfa.finance.conta;

import org.springframework.beans.factory.annotation.Configurable;

privileged aspect ContaImpl_Roo_Configurable {
    
    declare @type: ContaImpl: @Configurable;
    
}
