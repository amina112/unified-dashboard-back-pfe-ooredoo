package com.ooredoo.entities;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class VM {

    @Id
    @GeneratedValue
    private Long id;
    private double CPU_Usage;
    private double CPU_Utilization;
    private String Guest_OS;
    private String IP;
    private long Memory_Size;
    private double Memory_Utilization;
    private long Provisioned_Space;
    private long Read_Throughput;
    private String Resource_Pool;
    private String State;
    private String Status;
    private long Throughput;
    private long Used_Space;
    private long Virtual_Disk_Bandwidth;
    private long Write_Throughput;
    private String name;
    private int vCPUs;

    @Relationship(type = "Associated", direction = Relationship.INCOMING)
    private List<Datastore> Datastores= new ArrayList<>();

    //Getters and Setters
    public double getCPU_Usage() {return CPU_Usage;}
    public void setCPU_Usage(double CPU_Usage) {this.CPU_Usage = CPU_Usage;}

    public double getCPU_Utilization() {return CPU_Utilization;}
    public void setCPU_Utilization(double CPU_Utilization) {this.CPU_Utilization = CPU_Utilization;}

    public String getGuest_OS() {return Guest_OS;}
    public void setGuest_OS(String guest_OS) {Guest_OS = guest_OS;}

    public String getIP() {return IP;}
    public void setIP(String IP) {this.IP = IP;}

    public Long getMemory_Size() {return Memory_Size;}
    public void setMemory_Size(Long memory_Size) {Memory_Size = memory_Size;}

    public double getMemory_Utilization() {return Memory_Utilization;}
    public void setMemory_Utilization(double memory_Utilization) {Memory_Utilization = memory_Utilization;}

    public Long getProvisioned_Space() {return Provisioned_Space;}
    public void setProvisioned_Space(Long provisioned_Space) {Provisioned_Space = provisioned_Space;}

    public Long getRead_Throughput() {return Read_Throughput;}
    public void setRead_Throughput(Long read_Throughput) {Read_Throughput = read_Throughput;}

    public String getResource_Pool() {return Resource_Pool;}
    public void setResource_Pool(String resource_Pool) {Resource_Pool = resource_Pool;}

    public String getState() {return State;}
    public void setState(String state) {State = state;}

    public String getStatus() {return Status;}
    public void setStatus(String status) {Status = status;}

    public Long getThroughput() {return Throughput;}
    public void setThroughput(Long throughput) {Throughput = throughput;}

    public Long getUsed_Space() {return Used_Space;}
    public void setUsed_Space(Long used_Space) {Used_Space = used_Space;}

    public Long getVirtual_Disk_Bandwidth() {return Virtual_Disk_Bandwidth;}
    public void setVirtual_Disk_Bandwidth(Long virtual_Disk_Bandwidth) {Virtual_Disk_Bandwidth = virtual_Disk_Bandwidth;}

    public Long getWrite_Throughput() {return Write_Throughput;}
    public void setWrite_Throughput(Long write_Throughput) {Write_Throughput = write_Throughput;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getvCPUs() {return vCPUs;}
    public void setvCPUs(int vCPUs) {this.vCPUs = vCPUs;}

    public List<Datastore> getDatastores() {return Datastores;}
    public void setDatastores(List<Datastore> datastores) {Datastores = datastores;}

    //constructor
    public VM() { }

    //constructor : name
    public VM(String name) { this.name = name; }


    // parameterized constructor : all
    public VM(String name, int vCPUs, String state, double CPU_Usage, String guest_OS, String IP, String resource_Pool, List<Datastore> datastores, long throughput, long used_Space, long memory_Size, String status, long virtual_Disk_Bandwidth, double CPU_Utilization, long read_Throughput, long write_Throughput, double memory_Utilization, long provisioned_Space) {
        this.CPU_Usage = CPU_Usage;
        this.CPU_Utilization = CPU_Utilization;
        Guest_OS = guest_OS;
        this.IP = IP;
        Memory_Size = memory_Size;
        Memory_Utilization = memory_Utilization;
        Provisioned_Space = provisioned_Space;
        Read_Throughput = read_Throughput;
        Resource_Pool = resource_Pool;
        State = state;
        Status = status;
        Throughput = throughput;
        Used_Space = used_Space;
        Virtual_Disk_Bandwidth = virtual_Disk_Bandwidth;
        Write_Throughput = write_Throughput;
        this.name = name;
        this.vCPUs = vCPUs;
    }

}
