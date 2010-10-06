package br.com.bufunfa.finance.wui.conta;

import br.com.bufunfa.finance.conta.IContaService;
import java.lang.String;
import org.primefaces.model.TreeNode;
import org.springframework.context.ApplicationContext;

privileged aspect UIConta_Roo_JavaBean {
    
    public IContaService UIConta.getContaService() {
        return this.contaService;
    }
    
    public void UIConta.setContaService(IContaService contaService) {
        this.contaService = contaService;
    }
    
    public ApplicationContext UIConta.getContext() {
        return this.context;
    }
    
    public void UIConta.setContext(ApplicationContext context) {
        this.context = context;
    }
    
    public TreeNode UIConta.getSelectedNode() {
        return this.selectedNode;
    }
    
    public void UIConta.setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
    
    public String UIConta.getNomeConta() {
        return this.nomeConta;
    }
    
    public void UIConta.setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }
    
    public String UIConta.getDescricaoConta() {
        return this.descricaoConta;
    }
    
    public void UIConta.setDescricaoConta(String descricaoConta) {
        this.descricaoConta = descricaoConta;
    }
    
    public String UIConta.getNomeContaPai() {
        return this.nomeContaPai;
    }
    
    public void UIConta.setNomeContaPai(String nomeContaPai) {
        this.nomeContaPai = nomeContaPai;
    }
    
    public TreeNode UIConta.getRoot() {
        return this.root;
    }
    
    public void UIConta.setRoot(TreeNode root) {
        this.root = root;
    }
    
}
