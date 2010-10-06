package br.com.bufunfa.finance.conta;

import java.lang.String;

privileged aspect ContaServiceParams_Roo_ToString {
    
    public String ContaServiceParams.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(getNome()).append(", ");
        sb.append("Descricao: ").append(getDescricao()).append(", ");
        sb.append("NomePai: ").append(getNomePai());
        return sb.toString();
    }
    
}
