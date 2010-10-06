package br.com.bufunfa.finance.conta;

import br.com.bufunfa.finance.conta.ContaImplDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ContaImplIntegrationTest_Roo_IntegrationTest {
    
    declare @type: ContaImplIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    @Autowired
    private ContaImplDataOnDemand ContaImplIntegrationTest.dod;
    
    @Test
    public void ContaImplIntegrationTest.testCountContaImpls() {
        org.junit.Assert.assertNotNull("Data on demand for 'ContaImpl' failed to initialize correctly", dod.getRandomContaImpl());
        long count = br.com.bufunfa.finance.conta.ContaImpl.countContaImpls();
        org.junit.Assert.assertTrue("Counter for 'ContaImpl' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void ContaImplIntegrationTest.testFindContaImpl() {
        org.junit.Assert.assertNotNull("Data on demand for 'ContaImpl' failed to initialize correctly", dod.getRandomContaImpl());
        java.lang.Long id = dod.getRandomContaImpl().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'ContaImpl' failed to provide an identifier", id);
        br.com.bufunfa.finance.conta.ContaImpl obj = br.com.bufunfa.finance.conta.ContaImpl.findContaImpl(id);
        org.junit.Assert.assertNotNull("Find method for 'ContaImpl' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'ContaImpl' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void ContaImplIntegrationTest.testFindAllContaImpls() {
        org.junit.Assert.assertNotNull("Data on demand for 'ContaImpl' failed to initialize correctly", dod.getRandomContaImpl());
        long count = br.com.bufunfa.finance.conta.ContaImpl.countContaImpls();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'ContaImpl', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<br.com.bufunfa.finance.conta.ContaImpl> result = br.com.bufunfa.finance.conta.ContaImpl.findAllContaImpls();
        org.junit.Assert.assertNotNull("Find all method for 'ContaImpl' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'ContaImpl' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void ContaImplIntegrationTest.testFindContaImplEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'ContaImpl' failed to initialize correctly", dod.getRandomContaImpl());
        long count = br.com.bufunfa.finance.conta.ContaImpl.countContaImpls();
        if (count > 20) count = 20;
        java.util.List<br.com.bufunfa.finance.conta.ContaImpl> result = br.com.bufunfa.finance.conta.ContaImpl.findContaImplEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'ContaImpl' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'ContaImpl' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void ContaImplIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'ContaImpl' failed to initialize correctly", dod.getRandomContaImpl());
        java.lang.Long id = dod.getRandomContaImpl().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'ContaImpl' failed to provide an identifier", id);
        br.com.bufunfa.finance.conta.ContaImpl obj = br.com.bufunfa.finance.conta.ContaImpl.findContaImpl(id);
        org.junit.Assert.assertNotNull("Find method for 'ContaImpl' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyContaImpl(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'ContaImpl' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void ContaImplIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'ContaImpl' failed to initialize correctly", dod.getRandomContaImpl());
        java.lang.Long id = dod.getRandomContaImpl().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'ContaImpl' failed to provide an identifier", id);
        br.com.bufunfa.finance.conta.ContaImpl obj = br.com.bufunfa.finance.conta.ContaImpl.findContaImpl(id);
        org.junit.Assert.assertNotNull("Find method for 'ContaImpl' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyContaImpl(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'ContaImpl' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void ContaImplIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'ContaImpl' failed to initialize correctly", dod.getRandomContaImpl());
        br.com.bufunfa.finance.conta.ContaImpl obj = dod.getNewTransientContaImpl(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'ContaImpl' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'ContaImpl' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'ContaImpl' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void ContaImplIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'ContaImpl' failed to initialize correctly", dod.getRandomContaImpl());
        java.lang.Long id = dod.getRandomContaImpl().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'ContaImpl' failed to provide an identifier", id);
        br.com.bufunfa.finance.conta.ContaImpl obj = br.com.bufunfa.finance.conta.ContaImpl.findContaImpl(id);
        org.junit.Assert.assertNotNull("Find method for 'ContaImpl' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'ContaImpl' with identifier '" + id + "'", br.com.bufunfa.finance.conta.ContaImpl.findContaImpl(id));
    }
    
}
