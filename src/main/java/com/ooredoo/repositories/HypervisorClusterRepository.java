package com.ooredoo.repositories;

import com.ooredoo.entities.Hypervisor;
import com.ooredoo.entities.HypervisorCluster;
import org.neo4j.driver.types.Relationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface HypervisorClusterRepository extends Neo4jRepository<HypervisorCluster, Long> {

    //get HypervisorClusters Hypervisors and their vms
    @Query("MATCH (n:HypervisorCluster) RETURN n")
    Collection<HypervisorCluster> getAll();
    //get HypervisorClusters only
    @Query("MATCH (n:HypervisorCluster) RETURN n")
    Collection<HypervisorCluster> getAllHypervisorClusters();
    //get HypervisorClusters based on Datacenter
    @Query("MATCH (h:Datacenter {name: $name})-[:contains]->(HypervisorCluster:HypervisorCluster) RETURN HypervisorCluster")
    List<HypervisorCluster> findHypervisorClustersByDatacenterName(@Param("name") String hypervisorClusterName);

    //add HypervisorCluster
    HypervisorCluster save(HypervisorCluster HypervisorCluster);

    //search
    HypervisorCluster findByName(String name);

    //delete
    void deleteByName(String name);
    void deleteAllByNameIn(List<String> names);

    //create HypervisorCluster Hypervisor relationship
    @Query("MATCH (HypervisorCluster:HypervisorCluster {name: $HypervisorClusterName}), (Hypervisor:Hypervisor {name: $HypervisorName}) CREATE (HypervisorCluster)-[r:contains]->(Hypervisor)")
    Relationship createRelationshipBetweenHypervisorClusterAndHypervisor(String HypervisorClusterName, String HypervisorName);

    //delete HypervisorCluster Hypervisor relationship
    @Query("MATCH (HypervisorCluster:HypervisorCluster {name: $HypervisorClusterName})-[r:contains]->(Hypervisor:Hypervisor {name: $HypervisorName}) DELETE r")
    void deleteRelationshipBetweenHypervisorClusterAndHypervisor(@Param("HypervisorClusterName") String HypervisorClusterName, @Param("HypervisorName") String HypervisorName);


}
