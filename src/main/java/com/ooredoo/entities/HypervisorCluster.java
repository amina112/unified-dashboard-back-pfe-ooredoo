package com.ooredoo.entities;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class HypervisorCluster {

    //variables
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int esxsInHighMemoryUsage;
    private int numberOfESX;
    private int totalCPU;
    private long totalMemory;


    @Relationship(type = "contains", direction = Relationship.OUTGOING)
    private List<Hypervisor> Hypervisors= new ArrayList<>();


    //Getters and stters

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getEsxsInHighMemoryUsage() {return esxsInHighMemoryUsage;}
    public void setEsxsInHighMemoryUsage(int esxsInHighMemoryUsage) {this.esxsInHighMemoryUsage = esxsInHighMemoryUsage;}

    public int getNumberOfESX() {return numberOfESX;}
    public void setNumberOfESX(int numberOfESX) {this.numberOfESX = numberOfESX;}

    public int getTotalCPU() {return totalCPU;}
    public void setTotalCPU(int totalCPU) {this.totalCPU = totalCPU;}

    public long getTotalMemory() {return totalMemory;}
    public void setTotalMemory(long totalMemory) {this.totalMemory = totalMemory;}

    public void setHypervisors(List<Hypervisor> hypervisors) {Hypervisors = hypervisors;}
    public List<Hypervisor> getHypervisors() { return Hypervisors;}

    //Constructor
    public HypervisorCluster() {    }

    //constructor : name
    public HypervisorCluster(String name) { this.name = name; }

    // parameterized constructor
    public HypervisorCluster(String name, int esxsInHighMemoryUsage, int numberOfESX, int totalCPU, long totalMemory) {
        this.name = name;
        this.esxsInHighMemoryUsage = esxsInHighMemoryUsage;
        this.numberOfESX = numberOfESX;
        this.totalCPU = totalCPU;
        this.totalMemory = totalMemory;
    }
}
