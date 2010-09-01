package br.com.bufunfa.finance.wui;

import org.primefaces.model.TreeNode;

privileged aspect UIConta_Roo_JavaBean {
    
    public TreeNode UIConta.getReceitaRoot() {
        return this.receitaRoot;
    }
    
    public void UIConta.setReceitaRoot(TreeNode receitaRoot) {
        this.receitaRoot = receitaRoot;
    }
    
    public TreeNode UIConta.getDespesaRoot() {
        return this.despesaRoot;
    }
    
    public void UIConta.setDespesaRoot(TreeNode despesaRoot) {
        this.despesaRoot = despesaRoot;
    }
    
    public TreeNode UIConta.getAtivoRoot() {
        return this.ativoRoot;
    }
    
    public void UIConta.setAtivoRoot(TreeNode ativoRoot) {
        this.ativoRoot = ativoRoot;
    }
    
    public TreeNode UIConta.getPassivoRoot() {
        return this.passivoRoot;
    }
    
    public void UIConta.setPassivoRoot(TreeNode passivoRoot) {
        this.passivoRoot = passivoRoot;
    }
    
}
