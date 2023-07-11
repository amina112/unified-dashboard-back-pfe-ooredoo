package com.ooredoo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Hypervisor {

    //variables
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private double CPU_Utilization;
    private double Disk_Bandwidth;
    private double Memory_Utilization;
    private String Model;
    private String Status;
    private int Total_CPU;
    private int Total_Memory;
    private String Version;

    @Relationship(type = "run", direction = Relationship.OUTGOING)
    private List<VM> VMS= new ArrayList<>();


    //Getters and stters

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public double getCPU_Utilization() {return CPU_Utilization;}
    public void setCPU_Utilization(double CPU_Utilization) {this.CPU_Utilization = CPU_Utilization;}

    public double getDisk_Bandwidth() {return Disk_Bandwidth;}
    public void setDisk_Bandwidth(double disk_Bandwidth) {Disk_Bandwidth = disk_Bandwidth;}

    public double getMemory_Utilization() {return Memory_Utilization;}
    public void setMemory_Utilization(double memory_Utilization) {Memory_Utilization = memory_Utilization;}

    public String getModel() {return Model;}
    public void setModel(String model) {Model = model;}

    public String getStatus() {return Status;}
    public void setStatus(String status) {Status = status;}

    public int getTotal_CPU() {return Total_CPU;}
    public void setTotal_CPU(int total_CPU) {Total_CPU = total_CPU;}

    public int getTotal_Memory() {return Total_Memory;}
    public void setTotal_Memory(int total_Memory) {Total_Memory = total_Memory;}

    public String getVersion() {return Version;}
    public void setVersion(String version) {Version = version;}

    public List<VM> getVMS() { return VMS;}
    public void setVMS(List<VM> VMS) {this.VMS = VMS;}

    //Constructor
    public Hypervisor() {    }
    //constructor : name
    public Hypervisor(String name) { this.name = name; }

    //parameterized constructor
    public Hypervisor(String name, double CPU_Utilization, double disk_Bandwidth, double memory_Utilization, String model, String status, int total_CPU, int total_Memory, String version) {
        this.name = name;
        this.CPU_Utilization = CPU_Utilization;
        Disk_Bandwidth = disk_Bandwidth;
        Memory_Utilization = memory_Utilization;
        Model = model;
        Status = status;
        Total_CPU = total_CPU;
        Total_Memory = total_Memory;
        Version = version;
    }
}
