package br.com.bufunfa.finance.conta;

import org.springframework.beans.factory.annotation.Configurable;

privileged aspect Lancamento_Roo_Configurable {
    
    declare @type: Lancamento: @Configurable;
    
}
