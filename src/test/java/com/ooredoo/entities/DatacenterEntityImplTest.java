package com.ooredoo.entities;

import com.ooredoo.repositories.DatacenterRepository;
import com.ooredoo.services.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DatacenterEntityImplTest {
    private Datacenter datacenter;

    @BeforeEach
    public void setUp() {
        datacenter = new Datacenter("Test Datacenter", 2, 5, 3, 10);
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters
        Assertions.assertEquals("Test Datacenter", datacenter.getName());
        Assertions.assertEquals(2, datacenter.getDatastoreClusters());
        Assertions.assertEquals(5, datacenter.getDatastores());
        Assertions.assertEquals(3, datacenter.getHypervisors());
        Assertions.assertEquals(10, datacenter.getVirtualMachines());

        // Test setters
        datacenter.setName("Updated Datacenter");
        datacenter.setDatastoreClusters(4);
        datacenter.setDatastores(8);
        datacenter.setHypervisors(6);
        datacenter.setVirtualMachines(20);

        Assertions.assertEquals("Updated Datacenter", datacenter.getName());
        Assertions.assertEquals(4, datacenter.getDatastoreClusters());
        Assertions.assertEquals(8, datacenter.getDatastores());
        Assertions.assertEquals(6, datacenter.getHypervisors());
        Assertions.assertEquals(20, datacenter.getVirtualMachines());
    }

    @Test
    public void testHypervisorClustersList() {
        // Test initial empty list
        Assertions.assertNotNull(datacenter.getHypervisorClustersList());
        Assertions.assertTrue(datacenter.getHypervisorClustersList().isEmpty());

        // Test adding hypervisor clusters
        List<HypervisorCluster> hypervisorClusters = new ArrayList<>();
        HypervisorCluster cluster1 = new HypervisorCluster();
        HypervisorCluster cluster2 = new HypervisorCluster();
        hypervisorClusters.add(cluster1);
        hypervisorClusters.add(cluster2);

        datacenter.setHypervisorClustersList(hypervisorClusters);

        Assertions.assertEquals(2, datacenter.getHypervisorClustersList().size());
        Assertions.assertTrue(datacenter.getHypervisorClustersList().contains(cluster1));
        Assertions.assertTrue(datacenter.getHypervisorClustersList().contains(cluster2));
    }

    @Test
    public void testDatastoresList() {
        // Test initial empty list
        Assertions.assertNotNull(datacenter.getDatastoresList());
        Assertions.assertTrue(datacenter.getDatastoresList().isEmpty());

        // Test adding datastores
        List<Datastore> datastores = new ArrayList<>();
        Datastore datastore1 = new Datastore();
        Datastore datastore2 = new Datastore();
        datastores.add(datastore1);
        datastores.add(datastore2);

        datacenter.setDatastoresList(datastores);

        Assertions.assertEquals(2, datacenter.getDatastoresList().size());
        Assertions.assertTrue(datacenter.getDatastoresList().contains(datastore1));
        Assertions.assertTrue(datacenter.getDatastoresList().contains(datastore2));
    }

}
