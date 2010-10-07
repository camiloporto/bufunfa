package br.com.bufunfa.finance.conta;

import br.com.bufunfa.finance.conta.ContaDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ContaIntegrationTest_Roo_IntegrationTest {
    
    declare @type: ContaIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    @Autowired
    private ContaDataOnDemand ContaIntegrationTest.dod;
    
    @Test
    public void ContaIntegrationTest.testCountContas() {
        org.junit.Assert.assertNotNull("Data on demand for 'Conta' failed to initialize correctly", dod.getRandomConta());
        long count = br.com.bufunfa.finance.conta.Conta.countContas();
        org.junit.Assert.assertTrue("Counter for 'Conta' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void ContaIntegrationTest.testFindConta() {
        org.junit.Assert.assertNotNull("Data on demand for 'Conta' failed to initialize correctly", dod.getRandomConta());
        java.lang.Long id = dod.getRandomConta().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Conta' failed to provide an identifier", id);
        br.com.bufunfa.finance.conta.Conta obj = br.com.bufunfa.finance.conta.Conta.findConta(id);
        org.junit.Assert.assertNotNull("Find method for 'Conta' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Conta' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void ContaIntegrationTest.testFindAllContas() {
        org.junit.Assert.assertNotNull("Data on demand for 'Conta' failed to initialize correctly", dod.getRandomConta());
        long count = br.com.bufunfa.finance.conta.Conta.countContas();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Conta', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<br.com.bufunfa.finance.conta.Conta> result = br.com.bufunfa.finance.conta.Conta.findAllContas();
        org.junit.Assert.assertNotNull("Find all method for 'Conta' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Conta' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void ContaIntegrationTest.testFindContaEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Conta' failed to initialize correctly", dod.getRandomConta());
        long count = br.com.bufunfa.finance.conta.Conta.countContas();
        if (count > 20) count = 20;
        java.util.List<br.com.bufunfa.finance.conta.Conta> result = br.com.bufunfa.finance.conta.Conta.findContaEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'Conta' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Conta' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void ContaIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'Conta' failed to initialize correctly", dod.getRandomConta());
        java.lang.Long id = dod.getRandomConta().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Conta' failed to provide an identifier", id);
        br.com.bufunfa.finance.conta.Conta obj = br.com.bufunfa.finance.conta.Conta.findConta(id);
        org.junit.Assert.assertNotNull("Find method for 'Conta' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyConta(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Conta' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void ContaIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'Conta' failed to initialize correctly", dod.getRandomConta());
        java.lang.Long id = dod.getRandomConta().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Conta' failed to provide an identifier", id);
        br.com.bufunfa.finance.conta.Conta obj = br.com.bufunfa.finance.conta.Conta.findConta(id);
        org.junit.Assert.assertNotNull("Find method for 'Conta' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyConta(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Conta' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void ContaIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Conta' failed to initialize correctly", dod.getRandomConta());
        br.com.bufunfa.finance.conta.Conta obj = dod.getNewTransientConta(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Conta' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Conta' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Conta' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void ContaIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'Conta' failed to initialize correctly", dod.getRandomConta());
        java.lang.Long id = dod.getRandomConta().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Conta' failed to provide an identifier", id);
        br.com.bufunfa.finance.conta.Conta obj = br.com.bufunfa.finance.conta.Conta.findConta(id);
        org.junit.Assert.assertNotNull("Find method for 'Conta' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Conta' with identifier '" + id + "'", br.com.bufunfa.finance.conta.Conta.findConta(id));
    }
    
}
