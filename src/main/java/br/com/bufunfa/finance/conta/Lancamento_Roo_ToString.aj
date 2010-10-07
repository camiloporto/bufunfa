package br.com.bufunfa.finance.conta;

import java.lang.String;

privileged aspect Lancamento_Roo_ToString {
    
    public String Lancamento.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("IdConta: ").append(getIdConta()).append(", ");
        sb.append("Quantidade: ").append(getQuantidade()).append(", ");
        sb.append("DataRegistro: ").append(getDataRegistro()).append(", ");
        sb.append("DataEfetivacao: ").append(getDataEfetivacao()).append(", ");
        sb.append("Descricao: ").append(getDescricao());
        return sb.toString();
    }
    
}
