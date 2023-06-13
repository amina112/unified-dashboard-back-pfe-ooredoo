package com.ooredoo.entities;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.util.List;

@NodeEntity
public class DatastoreCluster {

    //variables
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private double freeSpace;
    private double totalCapacity;

    @Relationship(type = "contains", direction = Relationship.OUTGOING)
    private List<Datastore> Datastores;


    //Getters and setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public double getFreeSpace() {return freeSpace;}
    public void setFreeSpace(double freeSpace) {this.freeSpace = freeSpace;}

    public double getTotalCapacity() {return totalCapacity;}
    public void setTotalCapacity(double totalCapacity) {this.totalCapacity = totalCapacity;}

    public List<Datastore> getDatastores() {return Datastores;}
    public void setDatastores(List<Datastore> datastores) {Datastores = datastores;}

    //Constructor
    public DatastoreCluster() {    }

    // parameterized constructor

    public DatastoreCluster(String name, double freeSpace, double totalCapacity) {
        this.name = name;
        this.freeSpace = freeSpace;
        this.totalCapacity = totalCapacity;
    }
}
