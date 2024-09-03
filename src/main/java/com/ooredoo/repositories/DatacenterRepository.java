package com.ooredoo.repositories;

import com.ooredoo.entities.Datacenter;
import org.neo4j.driver.types.Relationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface DatacenterRepository extends Neo4jRepository<Datacenter, Long> {

    //get Datacenters and their vms
    // @Query("MATCH (n:Datacenter) RETURN n")
    //Collection<Datacenter> getAll();
    //get Datacenters only
    @Query("MATCH (n:Datacenter) RETURN n")
    Collection<Datacenter> getAllDatacenters();

    //add Datacenter
    Datacenter save(Datacenter Datacenter);

    //search
    Datacenter findByName(String name);

    //delete
    void deleteByName(String name);
    void deleteAllByNameIn(List<String> names);

    //create Datacenter DatastoreCluster relationship
    @Query("MATCH (Datacenter:Datacenter {name: $DatacenterName}), (DatastoreCluster:DatastoreCluster {name: $DatastoreClusterName}) CREATE (Datacenter)-[r:contains]->(DatastoreCluster)")
    Relationship createRelationshipBetweenDatacenterAndDatastoreCluster(String DatacenterName, String DatastoreClusterName);
    //delete Datacenter DatastoreCluster relationship
    @Query("MATCH (Datacenter:Datacenter {name: $DatacenterName})-[r:contains]->(DatastoreCluster:DatastoreCluster {name: $DatastoreClusterName}) DELETE r")
    void deleteRelationshipBetweenDatacenterAndDatastoreCluster(@Param("DatacenterName") String DatacenterName, @Param("DatastoreClusterName") String DatastoreClusterName);

    //create Datacenter Datastore relationship
    @Query("MATCH (Datacenter:Datacenter {name: $DatacenterName}), (Datastore:Datastore {name: $DatastoreName}) CREATE (Datacenter)-[r:contains]->(Datastore)")
    Relationship createRelationshipBetweenDatacenterAndDatastore(String DatacenterName, String DatastoreName);
    //delete Datacenter Datastore relationship
    @Query("MATCH (Datacenter:Datacenter {name: $DatacenterName})-[r:contains]->(Datastore:Datastore {name: $DatastoreName}) DELETE r")
    void deleteRelationshipBetweenDatacenterAndDatastore(@Param("DatacenterName") String DatacenterName, @Param("DatastoreName") String DatastoreName);


    //create Datacenter HypervisorCluster relationship
    @Query("MATCH (Datacenter:Datacenter {name: $DatacenterName}), (HypervisorCluster:HypervisorCluster {name: $HypervisorClusterName}) CREATE (Datacenter)-[r:contains]->(HypervisorCluster)")
    Relationship createRelationshipBetweenDatacenterAndHypervisorCluster(String DatacenterName, String HypervisorClusterName);
    //delete Datacenter HypervisorCluster relationship
    @Query("MATCH (Datacenter:Datacenter {name: $DatacenterName})-[r:contains]->(HypervisorCluster:HypervisorCluster {name: $HypervisorClusterName}) DELETE r")
    void deleteRelationshipBetweenDatacenterAndHypervisorCluster(@Param("DatacenterName") String DatacenterName, @Param("HypervisorClusterName") String HypervisorClusterName);


}