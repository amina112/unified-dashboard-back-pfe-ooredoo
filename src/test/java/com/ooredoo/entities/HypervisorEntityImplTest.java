package com.ooredoo.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class HypervisorEntityImplTest {
    private Hypervisor hypervisor;

    @BeforeEach
    public void setUp() {
        hypervisor = new Hypervisor("Test Hypervisor", 0.8, 100.0, 0.6, "Model 1", "Active", 4, 8192, "1.0");
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters
        Assertions.assertEquals("Test Hypervisor", hypervisor.getName());
        Assertions.assertEquals(0.8, hypervisor.getCPU_Utilization());
        Assertions.assertEquals(100.0, hypervisor.getDisk_Bandwidth());
        Assertions.assertEquals(0.6, hypervisor.getMemory_Utilization());
        Assertions.assertEquals("Model 1", hypervisor.getModel());
        Assertions.assertEquals("Active", hypervisor.getStatus());
        Assertions.assertEquals(4, hypervisor.getTotal_CPU());
        Assertions.assertEquals(8192, hypervisor.getTotal_Memory());
        Assertions.assertEquals("1.0", hypervisor.getVersion());

        // Test setters
        hypervisor.setName("Updated Hypervisor");
        hypervisor.setCPU_Utilization(0.7);
        hypervisor.setDisk_Bandwidth(200.0);
        hypervisor.setMemory_Utilization(0.5);
        hypervisor.setModel("Model 2");
        hypervisor.setStatus("Inactive");
        hypervisor.setTotal_CPU(8);
        hypervisor.setTotal_Memory(16384);
        hypervisor.setVersion("2.0");

        Assertions.assertEquals("Updated Hypervisor", hypervisor.getName());
        Assertions.assertEquals(0.7, hypervisor.getCPU_Utilization());
        Assertions.assertEquals(200.0, hypervisor.getDisk_Bandwidth());
        Assertions.assertEquals(0.5, hypervisor.getMemory_Utilization());
        Assertions.assertEquals("Model 2", hypervisor.getModel());
        Assertions.assertEquals("Inactive", hypervisor.getStatus());
        Assertions.assertEquals(8, hypervisor.getTotal_CPU());
        Assertions.assertEquals(16384, hypervisor.getTotal_Memory());
        Assertions.assertEquals("2.0", hypervisor.getVersion());
    }

    @Test
    public void testVMSList() {
        // Test initial empty list
        Assertions.assertNotNull(hypervisor.getVMS());
        Assertions.assertTrue(hypervisor.getVMS().isEmpty());

        // Test adding VMs
        List<VM> vms = new ArrayList<>();
        VM vm1 = new VM();
        VM vm2 = new VM();
        vms.add(vm1);
        vms.add(vm2);

        hypervisor.setVMS(vms);

        Assertions.assertEquals(2, hypervisor.getVMS().size());
        Assertions.assertTrue(hypervisor.getVMS().contains(vm1));
        Assertions.assertTrue(hypervisor.getVMS().contains(vm2));
    }
}
