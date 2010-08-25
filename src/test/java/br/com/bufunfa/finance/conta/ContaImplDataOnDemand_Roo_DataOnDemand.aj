package br.com.bufunfa.finance.conta;

import br.com.bufunfa.finance.conta.ContaImpl;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ContaImplDataOnDemand_Roo_DataOnDemand {
    
    declare @type: ContaImplDataOnDemand: @Component;
    
    private Random ContaImplDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<ContaImpl> ContaImplDataOnDemand.data;
    
    public ContaImpl ContaImplDataOnDemand.getNewTransientContaImpl(int index) {
        br.com.bufunfa.finance.conta.ContaImpl obj = new br.com.bufunfa.finance.conta.ContaImpl();
        obj.setDescricao("descricao_" + index);
        obj.setIdFather(new Integer(index).longValue());
        obj.setNome("nome_" + index);
        return obj;
    }
    
    public ContaImpl ContaImplDataOnDemand.getSpecificContaImpl(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        ContaImpl obj = data.get(index);
        return ContaImpl.findContaImpl(obj.getId());
    }
    
    public ContaImpl ContaImplDataOnDemand.getRandomContaImpl() {
        init();
        ContaImpl obj = data.get(rnd.nextInt(data.size()));
        return ContaImpl.findContaImpl(obj.getId());
    }
    
    public boolean ContaImplDataOnDemand.modifyContaImpl(ContaImpl obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void ContaImplDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = br.com.bufunfa.finance.conta.ContaImpl.findContaImplEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'ContaImpl' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<br.com.bufunfa.finance.conta.ContaImpl>();
        for (int i = 0; i < 10; i++) {
            br.com.bufunfa.finance.conta.ContaImpl obj = getNewTransientContaImpl(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
