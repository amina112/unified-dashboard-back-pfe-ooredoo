package com.ooredoo.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DatastoreClusterEntityImplTest {
    private DatastoreCluster datastoreCluster;

    @BeforeEach
    public void setUp() {
        datastoreCluster = new DatastoreCluster("Test Cluster", 100.0, 200.0);
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters
        Assertions.assertEquals("Test Cluster", datastoreCluster.getName());
        Assertions.assertEquals(100.0, datastoreCluster.getFreeSpace());
        Assertions.assertEquals(200.0, datastoreCluster.getTotalCapacity());

        // Test setters
        datastoreCluster.setName("Updated Cluster");
        datastoreCluster.setFreeSpace(150.0);
        datastoreCluster.setTotalCapacity(250.0);

        Assertions.assertEquals("Updated Cluster", datastoreCluster.getName());
        Assertions.assertEquals(150.0, datastoreCluster.getFreeSpace());
        Assertions.assertEquals(250.0, datastoreCluster.getTotalCapacity());
    }

    @Test
    public void testDatastores() {
        // Test initial empty list
        Assertions.assertNotNull(datastoreCluster.getDatastores());
        Assertions.assertTrue(datastoreCluster.getDatastores().isEmpty());

        // Test adding datastores
        List<Datastore> datastores = new ArrayList<>();
        Datastore datastore1 = new Datastore();
        Datastore datastore2 = new Datastore();
        datastores.add(datastore1);
        datastores.add(datastore2);

        datastoreCluster.setDatastores(datastores);

        Assertions.assertEquals(2, datastoreCluster.getDatastores().size());
        Assertions.assertTrue(datastoreCluster.getDatastores().contains(datastore1));
        Assertions.assertTrue(datastoreCluster.getDatastores().contains(datastore2));
    }
}
