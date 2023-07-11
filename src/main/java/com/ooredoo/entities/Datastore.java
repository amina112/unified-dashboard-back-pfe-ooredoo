package com.ooredoo.entities;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Datastore {

    //variables
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private double Bandwidth;
    private double Capacity;
    private double FreeSpace;
    private int Hypervisors;
    private double Latency;
    private double Provisioned;
    private double Throughput;
    private String Type;
    private double UsedSpace;

    @Relationship(type = "Associated", direction = Relationship.OUTGOING)
    private List<VM> VMS= new ArrayList<>();



    //Getters and stters
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}


    public double getBandwidth() {return Bandwidth;}
    public void setBandwidth(double bandwidth) {Bandwidth = bandwidth;}

    public double getCapacity() {return Capacity;}
    public void setCapacity(double capacity) {Capacity = capacity;}

    public double getFreeSpace() {return FreeSpace;}
    public void setFreeSpace(double freeSpace) {FreeSpace = freeSpace;}

    public int getHypervisors() {return Hypervisors;}
    public void setHypervisors(int hypervisors) {Hypervisors = hypervisors;}

    public double getLatency() {return Latency;}
    public void setLatency(double latency) {Latency = latency;}

    public double getProvisioned() {return Provisioned;}
    public void setProvisioned(double provisioned) {Provisioned = provisioned;}

    public double getThroughput() {return Throughput;}
    public void setThroughput(double throughput) {Throughput = throughput;}

    public String getType() {return Type;}
    public void setType(String type) {Type = type;}

    public double getUsedSpace() {return UsedSpace;}
    public void setUsedSpace(double usedSpace) {UsedSpace = usedSpace;}

    public List<VM> getVMS() {return VMS;}
    public void setVMS(List<VM> VMS) {this.VMS = VMS;}

    //Constructor
    public Datastore() {    }

    //constructor : name
    public Datastore(String name) { this.name = name; }

    // parameterized constructor
    public Datastore(String name, String type,double freeSpace,double provisioned, double throughput, double usedSpace, double latency,double bandwidth,  int hypervisors, double capacity  ) {
        this.name = name;
        Bandwidth = bandwidth;
        Capacity = capacity;
        FreeSpace = freeSpace;
        Hypervisors = hypervisors;
        Latency = latency;
        Provisioned = provisioned;
        Throughput = throughput;
        Type = type;
        UsedSpace = usedSpace;
    }
}
