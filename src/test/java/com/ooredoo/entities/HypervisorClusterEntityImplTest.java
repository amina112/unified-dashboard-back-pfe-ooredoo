package com.ooredoo.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class HypervisorClusterEntityImplTest {
    private HypervisorCluster hypervisorCluster;

    @BeforeEach
    public void setUp() {
        hypervisorCluster = new HypervisorCluster("Test Cluster", 2, 5, 8, 16384L);
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters
        Assertions.assertEquals("Test Cluster", hypervisorCluster.getName());
        Assertions.assertEquals(2, hypervisorCluster.getEsxsInHighMemoryUsage());
        Assertions.assertEquals(5, hypervisorCluster.getNumberOfESX());
        Assertions.assertEquals(8, hypervisorCluster.getTotalCPU());
        Assertions.assertEquals(16384L, hypervisorCluster.getTotalMemory());

        // Test setters
        hypervisorCluster.setName("Updated Cluster");
        hypervisorCluster.setEsxsInHighMemoryUsage(3);
        hypervisorCluster.setNumberOfESX(6);
        hypervisorCluster.setTotalCPU(12);
        hypervisorCluster.setTotalMemory(32768L);

        Assertions.assertEquals("Updated Cluster", hypervisorCluster.getName());
        Assertions.assertEquals(3, hypervisorCluster.getEsxsInHighMemoryUsage());
        Assertions.assertEquals(6, hypervisorCluster.getNumberOfESX());
        Assertions.assertEquals(12, hypervisorCluster.getTotalCPU());
        Assertions.assertEquals(32768L, hypervisorCluster.getTotalMemory());
    }

    @Test
    public void testHypervisorsList() {
        // Test initial empty list
        Assertions.assertNotNull(hypervisorCluster.getHypervisors());
        Assertions.assertTrue(hypervisorCluster.getHypervisors().isEmpty());

        // Test adding hypervisors
        List<Hypervisor> hypervisors = new ArrayList<>();
        Hypervisor hypervisor1 = new Hypervisor();
        Hypervisor hypervisor2 = new Hypervisor();
        hypervisors.add(hypervisor1);
        hypervisors.add(hypervisor2);

        hypervisorCluster.setHypervisors(hypervisors);

        Assertions.assertEquals(2, hypervisorCluster.getHypervisors().size());
        Assertions.assertTrue(hypervisorCluster.getHypervisors().contains(hypervisor1));
        Assertions.assertTrue(hypervisorCluster.getHypervisors().contains(hypervisor2));
    }
}
