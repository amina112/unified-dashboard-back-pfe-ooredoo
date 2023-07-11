package com.ooredoo.repositories;

import com.ooredoo.entities.Hypervisor;
import com.ooredoo.entities.VM;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface VMRepository extends Neo4jRepository<VM, Long> {

    //get vm based on hypervisor
    @Query("MATCH (h:Hypervisor {name: $name})-[:run]->(vm:VM) RETURN vm")
    List<VM> findVMsByHypervisorName(@Param("name") String hypervisorName);
    //get vm based on Datastore
    @Query("MATCH (h:Datastore {name: $name})-[:Associated]->(vm:VM) RETURN vm")
    List<VM> findVMsByDatastoreName(@Param("name") String DatastoreName);
    //get all vm
    @Query("MATCH (n:VM) RETURN n")
    Collection<VM> getAllVMs();

    //add vm
    VM save(Hypervisor hypervisor);

    //search
    VM findByName(String name);

    //delete
    void deleteByName(String name);
    void deleteAllByNameIn(List<String> names);
}
