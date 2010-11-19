package br.com.bufunfa.finance.conta;

import br.com.bufunfa.finance.conta.SistemaContaDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SistemaContaIntegrationTest_Roo_IntegrationTest {
    
    declare @type: SistemaContaIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: SistemaContaIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private SistemaContaDataOnDemand SistemaContaIntegrationTest.dod;
    
    @Test
    public void SistemaContaIntegrationTest.testCountSistemaContas() {
        org.junit.Assert.assertNotNull("Data on demand for 'SistemaConta' failed to initialize correctly", dod.getRandomSistemaConta());
        long count = br.com.bufunfa.finance.conta.SistemaConta.countSistemaContas();
        org.junit.Assert.assertTrue("Counter for 'SistemaConta' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void SistemaContaIntegrationTest.testFindSistemaConta() {
        org.junit.Assert.assertNotNull("Data on demand for 'SistemaConta' failed to initialize correctly", dod.getRandomSistemaConta());
        java.lang.Long id = dod.getRandomSistemaConta().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SistemaConta' failed to provide an identifier", id);
        br.com.bufunfa.finance.conta.SistemaConta obj = br.com.bufunfa.finance.conta.SistemaConta.findSistemaConta(id);
        org.junit.Assert.assertNotNull("Find method for 'SistemaConta' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'SistemaConta' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void SistemaContaIntegrationTest.testFindAllSistemaContas() {
        org.junit.Assert.assertNotNull("Data on demand for 'SistemaConta' failed to initialize correctly", dod.getRandomSistemaConta());
        long count = br.com.bufunfa.finance.conta.SistemaConta.countSistemaContas();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'SistemaConta', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<br.com.bufunfa.finance.conta.SistemaConta> result = br.com.bufunfa.finance.conta.SistemaConta.findAllSistemaContas();
        org.junit.Assert.assertNotNull("Find all method for 'SistemaConta' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'SistemaConta' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void SistemaContaIntegrationTest.testFindSistemaContaEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'SistemaConta' failed to initialize correctly", dod.getRandomSistemaConta());
        long count = br.com.bufunfa.finance.conta.SistemaConta.countSistemaContas();
        if (count > 20) count = 20;
        java.util.List<br.com.bufunfa.finance.conta.SistemaConta> result = br.com.bufunfa.finance.conta.SistemaConta.findSistemaContaEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'SistemaConta' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'SistemaConta' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void SistemaContaIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'SistemaConta' failed to initialize correctly", dod.getRandomSistemaConta());
        java.lang.Long id = dod.getRandomSistemaConta().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SistemaConta' failed to provide an identifier", id);
        br.com.bufunfa.finance.conta.SistemaConta obj = br.com.bufunfa.finance.conta.SistemaConta.findSistemaConta(id);
        org.junit.Assert.assertNotNull("Find method for 'SistemaConta' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySistemaConta(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'SistemaConta' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void SistemaContaIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'SistemaConta' failed to initialize correctly", dod.getRandomSistemaConta());
        java.lang.Long id = dod.getRandomSistemaConta().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SistemaConta' failed to provide an identifier", id);
        br.com.bufunfa.finance.conta.SistemaConta obj = br.com.bufunfa.finance.conta.SistemaConta.findSistemaConta(id);
        org.junit.Assert.assertNotNull("Find method for 'SistemaConta' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySistemaConta(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'SistemaConta' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void SistemaContaIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'SistemaConta' failed to initialize correctly", dod.getRandomSistemaConta());
        br.com.bufunfa.finance.conta.SistemaConta obj = dod.getNewTransientSistemaConta(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'SistemaConta' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'SistemaConta' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'SistemaConta' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void SistemaContaIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'SistemaConta' failed to initialize correctly", dod.getRandomSistemaConta());
        java.lang.Long id = dod.getRandomSistemaConta().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SistemaConta' failed to provide an identifier", id);
        br.com.bufunfa.finance.conta.SistemaConta obj = br.com.bufunfa.finance.conta.SistemaConta.findSistemaConta(id);
        org.junit.Assert.assertNotNull("Find method for 'SistemaConta' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'SistemaConta' with identifier '" + id + "'", br.com.bufunfa.finance.conta.SistemaConta.findSistemaConta(id));
    }
    
}
