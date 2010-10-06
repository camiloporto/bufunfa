package br.com.bufunfa.finance.conta.event;

import java.lang.String;
import java.util.List;

privileged aspect ContaEventCallback_Roo_JavaBean {
    
    public List<String> ContaEventCallback.getErrorMessages() {
        return this.errorMessages;
    }
    
    public void ContaEventCallback.setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
    
    public T ContaEventCallback.getSourceEvent() {
        return this.sourceEvent;
    }
    
    public void ContaEventCallback.setSourceEvent(T sourceEvent) {
        this.sourceEvent = sourceEvent;
    }
    
}
