package com.ooredoo.repositories;

import com.ooredoo.entities.DatastoreCluster;
import com.ooredoo.entities.HypervisorCluster;
import org.neo4j.driver.types.Relationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface DatastoreClusterRepository extends Neo4jRepository<DatastoreCluster, Long> {

    //get DatastoreClusters and their Datastores
    @Query("MATCH (n:DatastoreCluster) RETURN n")
    Collection<DatastoreCluster> getAll();
    //get DatastoreClusters only
    @Query("MATCH (n:DatastoreCluster) RETURN n")
    Collection<DatastoreCluster> getAllDatastoreClusters();
    //get DatastoreClusters based on Datacenter
    @Query("MATCH (h:Datacenter {name: $name})-[:contains]->(DatastoreCluster:DatastoreCluster) RETURN DatastoreCluster")
    List<DatastoreCluster> findDatastoreClustersByDatacenterName(@Param("name") String DatastoreClusterName);

    //add DatastoreCluster
    DatastoreCluster save(DatastoreCluster DatastoreCluster);

    //search
    DatastoreCluster findByName(String name);

    //delete
    void deleteByName(String name);
    void deleteAllByNameIn(List<String> names);

    //create DatastoreCluster Datastore relationship
    @Query("MATCH (DatastoreCluster:DatastoreCluster {name: $DatastoreClusterName}), (Datastore:Datastore {name: $DatastoreName}) CREATE (DatastoreCluster)-[r:contains]->(Datastore)")
    Relationship createRelationshipBetweenDatastoreClusterAndDatastore(String DatastoreClusterName, String DatastoreName);

    //delete DatastoreCluster Datastore relationship
    @Query("MATCH (DatastoreCluster:DatastoreCluster {name: $DatastoreClusterName})-[r:contains]->(Datastore:Datastore {name: $DatastoreName}) DELETE r")
    void deleteRelationshipBetweenDatastoreClusterAndDatastore(@Param("DatastoreClusterName") String DatastoreClusterName, @Param("DatastoreName") String DatastoreName);



}
