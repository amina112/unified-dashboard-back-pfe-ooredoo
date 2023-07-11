package com.ooredoo.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class VMEntityImplTest {
    private VM vm;

    @BeforeEach
    public void setUp() {
        vm = new VM("Test VM", 4, "Running", 20.0, "Ubuntu", "192.168.1.100", "Resource Pool 1",
                new ArrayList<>(), 1000, 500, 8192L, "Active", 100, 40.0, 2000, 500, 50.0, 102400L);
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters
        Assertions.assertEquals("Test VM", vm.getName());
        Assertions.assertEquals(4, vm.getvCPUs());
        Assertions.assertEquals("Running", vm.getState());
        Assertions.assertEquals(20.0, vm.getCPU_Usage(), 0.01);
        Assertions.assertEquals("Ubuntu", vm.getGuest_OS());
        Assertions.assertEquals("192.168.1.100", vm.getIP());
        Assertions.assertEquals("Resource Pool 1", vm.getResource_Pool());
        Assertions.assertEquals(1000, vm.getThroughput());
        Assertions.assertEquals(500, vm.getUsed_Space());
        Assertions.assertEquals(8192L, vm.getMemory_Size());
        Assertions.assertEquals("Active", vm.getStatus());
        Assertions.assertEquals(100, vm.getVirtual_Disk_Bandwidth());
        Assertions.assertEquals(40.0, vm.getCPU_Utilization(), 0.01);
        Assertions.assertEquals(2000, vm.getRead_Throughput());
        Assertions.assertEquals(500, vm.getWrite_Throughput());
        Assertions.assertEquals(50.0, vm.getMemory_Utilization(), 0.01);
        Assertions.assertEquals(102400L, vm.getProvisioned_Space());

        // Test setters
        vm.setName("Updated VM");
        vm.setvCPUs(8);
        vm.setState("Stopped");
        vm.setCPU_Usage(10.0);
        vm.setGuest_OS("Windows");
        vm.setIP("192.168.1.200");
        vm.setResource_Pool("Resource Pool 2");
        vm.setThroughput(20L);
        vm.setUsed_Space(1000L);
        vm.setMemory_Size(16384L);
        vm.setStatus("Inactive");
        vm.setVirtual_Disk_Bandwidth(200L);
        vm.setCPU_Utilization(80.0);
        vm.setRead_Throughput(3000L);
        vm.setWrite_Throughput(1000L);
        vm.setMemory_Utilization(60.0);
        vm.setProvisioned_Space(204800L);

        Assertions.assertEquals("Updated VM", vm.getName());
        Assertions.assertEquals(8, vm.getvCPUs());
        Assertions.assertEquals("Stopped", vm.getState());
        Assertions.assertEquals(10.0, vm.getCPU_Usage(), 0.01);
        Assertions.assertEquals("Windows", vm.getGuest_OS());
        Assertions.assertEquals("192.168.1.200", vm.getIP());
        Assertions.assertEquals("Resource Pool 2", vm.getResource_Pool());
        Assertions.assertEquals(20, vm.getThroughput());
        Assertions.assertEquals(1000, vm.getUsed_Space());
        Assertions.assertEquals(16384L, vm.getMemory_Size());
        Assertions.assertEquals("Inactive", vm.getStatus());
        Assertions.assertEquals(200, vm.getVirtual_Disk_Bandwidth());
        Assertions.assertEquals(80.0, vm.getCPU_Utilization(), 0.01);
        Assertions.assertEquals(3000, vm.getRead_Throughput());
        Assertions.assertEquals(1000, vm.getWrite_Throughput());
        Assertions.assertEquals(60.0, vm.getMemory_Utilization(), 0.01);
        Assertions.assertEquals(204800L, vm.getProvisioned_Space());
    }

    @Test
    public void testGetDatastores() {
        // Test initial state
        List<Datastore> datastores = vm.getDatastores();
        Assertions.assertNotNull(datastores);
        Assertions.assertEquals(0, datastores.size());

        // Test setting datastores
        List<Datastore> newDatastores = new ArrayList<>();
        newDatastores.add(new Datastore("Datastore 1"));
        newDatastores.add(new Datastore("Datastore 2"));
        vm.setDatastores(newDatastores);

        datastores = vm.getDatastores();
        Assertions.assertEquals(2, datastores.size());
        Assertions.assertEquals("Datastore 1", datastores.get(0).getName());
        Assertions.assertEquals("Datastore 2", datastores.get(1).getName());

        // Test modifying datastores
        datastores.get(0).setName("Updated Datastore");

        datastores = vm.getDatastores();
        Assertions.assertEquals(2, datastores.size());
        Assertions.assertEquals("Updated Datastore", datastores.get(0).getName());
        Assertions.assertEquals("Datastore 2", datastores.get(1).getName());
    }
}
