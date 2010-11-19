package br.com.bufunfa.finance.conta;

import java.lang.String;

privileged aspect SistemaConta_Roo_ToString {
    
    public String SistemaConta.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("IdContaRoot: ").append(getIdContaRoot()).append(", ");
        sb.append("Nome: ").append(getNome());
        return sb.toString();
    }
    
}
