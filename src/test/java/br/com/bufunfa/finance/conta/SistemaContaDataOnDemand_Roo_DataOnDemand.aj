package br.com.bufunfa.finance.conta;

import br.com.bufunfa.finance.conta.SistemaConta;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SistemaContaDataOnDemand_Roo_DataOnDemand {
    
    declare @type: SistemaContaDataOnDemand: @Component;
    
    private Random SistemaContaDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<SistemaConta> SistemaContaDataOnDemand.data;
    
    public SistemaConta SistemaContaDataOnDemand.getNewTransientSistemaConta(int index) {
        br.com.bufunfa.finance.conta.SistemaConta obj = new br.com.bufunfa.finance.conta.SistemaConta();
        obj.setIdContaRoot(new Integer(index).longValue());
        obj.setNome("nome_" + index);
        return obj;
    }
    
    public SistemaConta SistemaContaDataOnDemand.getSpecificSistemaConta(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        SistemaConta obj = data.get(index);
        return SistemaConta.findSistemaConta(obj.getId());
    }
    
    public SistemaConta SistemaContaDataOnDemand.getRandomSistemaConta() {
        init();
        SistemaConta obj = data.get(rnd.nextInt(data.size()));
        return SistemaConta.findSistemaConta(obj.getId());
    }
    
    public boolean SistemaContaDataOnDemand.modifySistemaConta(SistemaConta obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void SistemaContaDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = br.com.bufunfa.finance.conta.SistemaConta.findSistemaContaEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'SistemaConta' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<br.com.bufunfa.finance.conta.SistemaConta>();
        for (int i = 0; i < 10; i++) {
            br.com.bufunfa.finance.conta.SistemaConta obj = getNewTransientSistemaConta(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
