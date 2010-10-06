package br.com.bufunfa.finance.conta.event;

import java.lang.String;

privileged aspect ContaEvent_Roo_ToString {
    
    public String ContaEvent.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EventType: ").append(getEventType()).append(", ");
        sb.append("NomeContaPai: ").append(getNomeContaPai()).append(", ");
        sb.append("NomeConta: ").append(getNomeConta()).append(", ");
        sb.append("DescricaoConta: ").append(getDescricaoConta());
        return sb.toString();
    }
    
}
