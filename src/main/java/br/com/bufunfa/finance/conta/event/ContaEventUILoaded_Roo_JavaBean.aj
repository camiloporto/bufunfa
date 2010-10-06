package br.com.bufunfa.finance.conta.event;

import br.com.bufunfa.finance.conta.ContaImpl;
import java.util.Set;

privileged aspect ContaEventUILoaded_Roo_JavaBean {
    
    public Set<ContaImpl> ContaEventUILoaded.getRootContas() {
        return this.rootContas;
    }
    
}
