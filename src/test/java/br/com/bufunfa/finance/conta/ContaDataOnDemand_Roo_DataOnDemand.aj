package br.com.bufunfa.finance.conta;

import br.com.bufunfa.finance.conta.Conta;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ContaDataOnDemand_Roo_DataOnDemand {
    
    declare @type: ContaDataOnDemand: @Component;
    
    private Random ContaDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<Conta> ContaDataOnDemand.data;
    
    public Conta ContaDataOnDemand.getNewTransientConta(int index) {
        br.com.bufunfa.finance.conta.Conta obj = new br.com.bufunfa.finance.conta.Conta();
        obj.setDescricao("descricao_" + index);
        obj.setIdFather(new Integer(index).longValue());
        obj.setNome("nome_" + index);
        return obj;
    }
    
    public Conta ContaDataOnDemand.getSpecificConta(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        Conta obj = data.get(index);
        return Conta.findConta(obj.getId());
    }
    
    public Conta ContaDataOnDemand.getRandomConta() {
        init();
        Conta obj = data.get(rnd.nextInt(data.size()));
        return Conta.findConta(obj.getId());
    }
    
    public boolean ContaDataOnDemand.modifyConta(Conta obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void ContaDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = br.com.bufunfa.finance.conta.Conta.findContaEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Conta' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<br.com.bufunfa.finance.conta.Conta>();
        for (int i = 0; i < 10; i++) {
            br.com.bufunfa.finance.conta.Conta obj = getNewTransientConta(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
