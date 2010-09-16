package br.com.bufunfa.finance.wui;

import br.com.bufunfa.finance.conta.IContaService;
import org.primefaces.model.TreeNode;

privileged aspect UIConta_Roo_JavaBean {
    
    public IContaService UIConta.getContaService() {
        return this.contaService;
    }
    
    public void UIConta.setContaService(IContaService contaService) {
        this.contaService = contaService;
    }
    
    public TreeNode UIConta.getRoot() {
        return this.root;
    }
    
    public void UIConta.setRoot(TreeNode root) {
        this.root = root;
    }
    
}
