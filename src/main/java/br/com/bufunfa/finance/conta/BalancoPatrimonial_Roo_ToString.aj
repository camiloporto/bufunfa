package br.com.bufunfa.finance.conta;

import java.lang.String;

privileged aspect BalancoPatrimonial_Roo_ToString {
    
    public String BalancoPatrimonial.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PatrimonioLiquido: ").append(getPatrimonioLiquido()).append(", ");
        sb.append("Ativo: ").append(getAtivo()).append(", ");
        sb.append("Passivo: ").append(getPassivo()).append(", ");
        sb.append("InicioPeriodo: ").append(getInicioPeriodo()).append(", ");
        sb.append("FinalPeriodo: ").append(getFinalPeriodo());
        return sb.toString();
    }
    
}
