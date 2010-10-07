package br.com.bufunfa.finance.conta;

import org.springframework.beans.factory.annotation.Configurable;

privileged aspect ContaDataOnDemand_Roo_Configurable {
    
    declare @type: ContaDataOnDemand: @Configurable;
    
}
