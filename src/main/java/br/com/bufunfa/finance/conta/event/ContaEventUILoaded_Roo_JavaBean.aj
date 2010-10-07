package br.com.bufunfa.finance.conta.event;

import br.com.bufunfa.finance.conta.Conta;
import java.util.Set;

privileged aspect ContaEventUILoaded_Roo_JavaBean {
    
    public Set<Conta> ContaEventUILoaded.getRootContas() {
        return this.rootContas;
    }
    
}
