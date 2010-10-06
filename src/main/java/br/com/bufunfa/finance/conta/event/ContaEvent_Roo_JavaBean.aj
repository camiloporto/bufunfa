package br.com.bufunfa.finance.conta.event;

import br.com.bufunfa.finance.conta.event.ContaEvent.EventType;
import java.lang.String;

privileged aspect ContaEvent_Roo_JavaBean {
    
    public EventType ContaEvent.getEventType() {
        return this.eventType;
    }
    
    public String ContaEvent.getNomeContaPai() {
        return this.nomeContaPai;
    }
    
    public String ContaEvent.getNomeConta() {
        return this.nomeConta;
    }
    
    public String ContaEvent.getDescricaoConta() {
        return this.descricaoConta;
    }
    
}
