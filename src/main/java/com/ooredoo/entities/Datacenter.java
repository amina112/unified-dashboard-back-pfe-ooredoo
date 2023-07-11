package com.ooredoo.entities;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Datacenter {

    //variables
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int DatastoreClusters;
    private int Datastores;
    private int Hypervisors;
    private int VirtualMachines;


    @Relationship(type = "contains", direction = Relationship.OUTGOING)
    private List<HypervisorCluster> HypervisorClustersList = new ArrayList<>();
    @Relationship(type = "contains", direction = Relationship.OUTGOING)
    private List<Datastore> DatastoresList= new ArrayList<>();


    //Getters and stters
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getDatastoreClusters() {return DatastoreClusters;}
    public void setDatastoreClusters(int datastoreClusters) {DatastoreClusters = datastoreClusters;}

    public int getDatastores() {return Datastores;}
    public void setDatastores(int datastores) {Datastores = datastores;}

    public int getHypervisors() {return Hypervisors;}
    public void setHypervisors(int hypervisors) {Hypervisors = hypervisors;}

    public int getVirtualMachines() {return VirtualMachines;}
    public void setVirtualMachines(int virtualMachines) {VirtualMachines = virtualMachines;}

    public List<HypervisorCluster> getHypervisorClustersList() {return HypervisorClustersList;}
    public void setHypervisorClustersList(List<HypervisorCluster> hypervisorClustersList) {HypervisorClustersList = hypervisorClustersList;}

    public List<Datastore> getDatastoresList() {return DatastoresList;}
    public void setDatastoresList(List<Datastore> datastoresList) {DatastoresList = datastoresList;}

    //Constructor
    public Datacenter() {    }

    //constructor : name
    public Datacenter(String name) { this.name = name; }

    // parameterized constructor
    public Datacenter(String name, int datastoreClusters, int datastores, int hypervisors, int virtualMachines) {
        this.name = name;
        DatastoreClusters = datastoreClusters;
        Datastores = datastores;
        Hypervisors = hypervisors;
        VirtualMachines = virtualMachines;
    }
}
