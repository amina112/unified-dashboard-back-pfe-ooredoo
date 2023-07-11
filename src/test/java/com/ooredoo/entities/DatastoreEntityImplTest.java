package com.ooredoo.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DatastoreEntityImplTest {
    private Datastore datastore;

    @BeforeEach
    public void setUp() {
        datastore = new Datastore("Test Datastore", "Type", 100.0, 50.0, 10.0, 30.0, 5.0, 200.0, 3, 500.0);
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters
        Assertions.assertEquals("Test Datastore", datastore.getName());
        Assertions.assertEquals("Type", datastore.getType());
        Assertions.assertEquals(100.0, datastore.getFreeSpace());
        Assertions.assertEquals(50.0, datastore.getProvisioned());
        Assertions.assertEquals(10.0, datastore.getThroughput());
        Assertions.assertEquals(30.0, datastore.getUsedSpace());
        Assertions.assertEquals(5.0, datastore.getLatency());
        Assertions.assertEquals(200.0, datastore.getBandwidth());
        Assertions.assertEquals(3, datastore.getHypervisors());
        Assertions.assertEquals(500.0, datastore.getCapacity());

        // Test setters
        datastore.setName("Updated Datastore");
        datastore.setType("Updated Type");
        datastore.setFreeSpace(150.0);
        datastore.setProvisioned(75.0);
        datastore.setThroughput(20.0);
        datastore.setUsedSpace(40.0);
        datastore.setLatency(8.0);
        datastore.setBandwidth(250.0);
        datastore.setHypervisors(4);
        datastore.setCapacity(600.0);

        Assertions.assertEquals("Updated Datastore", datastore.getName());
        Assertions.assertEquals("Updated Type", datastore.getType());
        Assertions.assertEquals(150.0, datastore.getFreeSpace());
        Assertions.assertEquals(75.0, datastore.getProvisioned());
        Assertions.assertEquals(20.0, datastore.getThroughput());
        Assertions.assertEquals(40.0, datastore.getUsedSpace());
        Assertions.assertEquals(8.0, datastore.getLatency());
        Assertions.assertEquals(250.0, datastore.getBandwidth());
        Assertions.assertEquals(4, datastore.getHypervisors());
        Assertions.assertEquals(600.0, datastore.getCapacity());
    }

    @Test
    public void testVMSList() {
        // Test initial empty list
        Assertions.assertNotNull(datastore.getVMS());
        Assertions.assertTrue(datastore.getVMS().isEmpty());

        // Test adding VMs
        List<VM> vms = new ArrayList<>();
        VM vm1 = new VM();
        VM vm2 = new VM();
        vms.add(vm1);
        vms.add(vm2);

        datastore.setVMS(vms);

        Assertions.assertEquals(2, datastore.getVMS().size());
        Assertions.assertTrue(datastore.getVMS().contains(vm1));
        Assertions.assertTrue(datastore.getVMS().contains(vm2));
    }
}
