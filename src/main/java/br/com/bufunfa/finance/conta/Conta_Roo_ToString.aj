package br.com.bufunfa.finance.conta;

import java.lang.String;

privileged aspect Conta_Roo_ToString {
    
    public String Conta.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Children: ").append(getChildren() == null ? "null" : getChildren().size()).append(", ");
        sb.append("Roots: ").append(getRoots() == null ? "null" : getRoots().size()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Nome: ").append(getNome()).append(", ");
        sb.append("Descricao: ").append(getDescricao()).append(", ");
        sb.append("IdFather: ").append(getIdFather());
        return sb.toString();
    }
    
}
