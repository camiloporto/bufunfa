package br.com.bufunfa.finance.conta;

import br.com.bufunfa.finance.conta.LancamentoDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect LancamentoIntegrationTest_Roo_IntegrationTest {
    
    declare @type: LancamentoIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    @Autowired
    private LancamentoDataOnDemand LancamentoIntegrationTest.dod;
    
    @Test
    public void LancamentoIntegrationTest.testCountLancamentoes() {
        org.junit.Assert.assertNotNull("Data on demand for 'Lancamento' failed to initialize correctly", dod.getRandomLancamento());
        long count = br.com.bufunfa.finance.conta.Lancamento.countLancamentoes();
        org.junit.Assert.assertTrue("Counter for 'Lancamento' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void LancamentoIntegrationTest.testFindLancamento() {
        org.junit.Assert.assertNotNull("Data on demand for 'Lancamento' failed to initialize correctly", dod.getRandomLancamento());
        java.lang.Long id = dod.getRandomLancamento().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Lancamento' failed to provide an identifier", id);
        br.com.bufunfa.finance.conta.Lancamento obj = br.com.bufunfa.finance.conta.Lancamento.findLancamento(id);
        org.junit.Assert.assertNotNull("Find method for 'Lancamento' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Lancamento' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void LancamentoIntegrationTest.testFindAllLancamentoes() {
        org.junit.Assert.assertNotNull("Data on demand for 'Lancamento' failed to initialize correctly", dod.getRandomLancamento());
        long count = br.com.bufunfa.finance.conta.Lancamento.countLancamentoes();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Lancamento', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<br.com.bufunfa.finance.conta.Lancamento> result = br.com.bufunfa.finance.conta.Lancamento.findAllLancamentoes();
        org.junit.Assert.assertNotNull("Find all method for 'Lancamento' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Lancamento' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void LancamentoIntegrationTest.testFindLancamentoEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Lancamento' failed to initialize correctly", dod.getRandomLancamento());
        long count = br.com.bufunfa.finance.conta.Lancamento.countLancamentoes();
        if (count > 20) count = 20;
        java.util.List<br.com.bufunfa.finance.conta.Lancamento> result = br.com.bufunfa.finance.conta.Lancamento.findLancamentoEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'Lancamento' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Lancamento' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void LancamentoIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'Lancamento' failed to initialize correctly", dod.getRandomLancamento());
        java.lang.Long id = dod.getRandomLancamento().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Lancamento' failed to provide an identifier", id);
        br.com.bufunfa.finance.conta.Lancamento obj = br.com.bufunfa.finance.conta.Lancamento.findLancamento(id);
        org.junit.Assert.assertNotNull("Find method for 'Lancamento' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyLancamento(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Lancamento' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void LancamentoIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'Lancamento' failed to initialize correctly", dod.getRandomLancamento());
        java.lang.Long id = dod.getRandomLancamento().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Lancamento' failed to provide an identifier", id);
        br.com.bufunfa.finance.conta.Lancamento obj = br.com.bufunfa.finance.conta.Lancamento.findLancamento(id);
        org.junit.Assert.assertNotNull("Find method for 'Lancamento' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyLancamento(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Lancamento' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void LancamentoIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Lancamento' failed to initialize correctly", dod.getRandomLancamento());
        br.com.bufunfa.finance.conta.Lancamento obj = dod.getNewTransientLancamento(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Lancamento' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Lancamento' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Lancamento' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void LancamentoIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'Lancamento' failed to initialize correctly", dod.getRandomLancamento());
        java.lang.Long id = dod.getRandomLancamento().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Lancamento' failed to provide an identifier", id);
        br.com.bufunfa.finance.conta.Lancamento obj = br.com.bufunfa.finance.conta.Lancamento.findLancamento(id);
        org.junit.Assert.assertNotNull("Find method for 'Lancamento' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Lancamento' with identifier '" + id + "'", br.com.bufunfa.finance.conta.Lancamento.findLancamento(id));
    }
    
}
