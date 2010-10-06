package br.com.bufunfa.finance.wui.conta;

import java.io.Serializable;

privileged aspect UIConta_Roo_Serializable {
    
    declare parents: UIConta implements Serializable;
    
}
