package br.com.bufunfa.finance.conta;

import br.com.bufunfa.finance.conta.Lancamento;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect LancamentoDataOnDemand_Roo_DataOnDemand {
    
    declare @type: LancamentoDataOnDemand: @Component;
    
    private Random LancamentoDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<Lancamento> LancamentoDataOnDemand.data;
    
    public Lancamento LancamentoDataOnDemand.getNewTransientLancamento(int index) {
        br.com.bufunfa.finance.conta.Lancamento obj = new br.com.bufunfa.finance.conta.Lancamento();
        obj.setDataEfetivacao(new java.util.Date());
        obj.setDataRegistro(new java.util.Date());
        obj.setDescricao("descricao_" + index);
        obj.setIdConta(new Integer(index).longValue());
        obj.setQuantidade(null);
        return obj;
    }
    
    public Lancamento LancamentoDataOnDemand.getSpecificLancamento(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        Lancamento obj = data.get(index);
        return Lancamento.findLancamento(obj.getId());
    }
    
    public Lancamento LancamentoDataOnDemand.getRandomLancamento() {
        init();
        Lancamento obj = data.get(rnd.nextInt(data.size()));
        return Lancamento.findLancamento(obj.getId());
    }
    
    public boolean LancamentoDataOnDemand.modifyLancamento(Lancamento obj) {
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void LancamentoDataOnDemand.init() {
        if (data != null) {
            return;
        }
        
        data = br.com.bufunfa.finance.conta.Lancamento.findLancamentoEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Lancamento' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<br.com.bufunfa.finance.conta.Lancamento>();
        for (int i = 0; i < 10; i++) {
            br.com.bufunfa.finance.conta.Lancamento obj = getNewTransientLancamento(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
