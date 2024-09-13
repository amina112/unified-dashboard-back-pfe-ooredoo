package com.ooredoo.services;

import com.ooredoo.entities.Hypervisor;
import com.ooredoo.entities.VM;
import com.ooredoo.repositories.VMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.io.FileInputStream;
import java.io.IOException;

import com.ooredoo.repositories.VMRepository;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

@Service
public class VMService {

    final VMRepository VMRepository;

    public VMService(VMRepository VMRepository) {
        this.VMRepository = VMRepository;
    }

    //------------------- display --------------------------
    //display vm list
    public Collection<VM> getAllVMs() {
        return VMRepository.getAllVMs();
    }
    //display vm list of a Hypervisor
    public List<VM> getVMsByHypervisorName(String hypervisorName) {
        return VMRepository.findVMsByHypervisorName(hypervisorName);
    }
    //display vm list of a Datastore
    public List<VM> getVMsByDatastoreName(String DatastoreName) {
        return VMRepository.findVMsByDatastoreName(DatastoreName);
    }

    //-------------------- add ---------------------------------
    //add single
    public VM addSingleVM(VM VM) { return VMRepository.save(VM);}
    //add multiple
    public List<VM> addMultipleVMs(List<VM> VMs) {return VMRepository.saveAll(VMs);}

    //------------------- update --------------------------------
    public VM updateVMByName(String name, VM updatedVM) {
        VM existingVM = VMRepository.findByName(name);


        // Update the specific fields with the provided values
        if (updatedVM.getName() != null) { existingVM.setName(updatedVM.getName());}
        if (updatedVM.getCPU_Usage() != 0.0) {existingVM.setCPU_Usage(updatedVM.getCPU_Usage());}
        if (updatedVM.getCPU_Utilization() != 0.0) {existingVM.setCPU_Utilization(updatedVM.getCPU_Utilization());}
        if (updatedVM.getGuest_OS() != null) {existingVM.setGuest_OS(updatedVM.getGuest_OS());}
        if (updatedVM.getIP() != null) {existingVM.setIP(updatedVM.getIP());}
        if (updatedVM.getCPU_Utilization() != 0.0) {existingVM.setCPU_Utilization(updatedVM.getCPU_Utilization());}
        if (updatedVM.getStatus() != null) {existingVM.setStatus(updatedVM.getStatus());}
        if (updatedVM.getState() != null) {existingVM.setState(updatedVM.getState());}
        if (updatedVM.getResource_Pool() != null) {existingVM.setResource_Pool(updatedVM.getResource_Pool());}
        if (updatedVM.getMemory_Utilization() != 0.0) {existingVM.setMemory_Utilization(updatedVM.getMemory_Utilization());}
        if (updatedVM.getvCPUs() != 0) {existingVM.setvCPUs(updatedVM.getvCPUs());}
        if (updatedVM.getMemory_Size() != 0) {existingVM.setMemory_Size(updatedVM.getMemory_Size());}
        if (updatedVM.getVirtual_Disk_Bandwidth() != 0) {existingVM.setVirtual_Disk_Bandwidth(updatedVM.getVirtual_Disk_Bandwidth());}
        if (updatedVM.getWrite_Throughput() != 0) {existingVM.setWrite_Throughput(updatedVM.getWrite_Throughput());}
        if (updatedVM.getUsed_Space() != 0) {existingVM.setUsed_Space(updatedVM.getUsed_Space());}
        if (updatedVM.getThroughput() != 0) {existingVM.setThroughput(updatedVM.getThroughput());}
        if (updatedVM.getRead_Throughput() != 0) {existingVM.setRead_Throughput(updatedVM.getRead_Throughput());}
        if (updatedVM.getProvisioned_Space() != 0) {existingVM.setProvisioned_Space(updatedVM.getProvisioned_Space());}



        // Save the updated Hypervisor node
        return VMRepository.save(existingVM);
    }

    //---------------------- delete -------------------------------
    //delete single
    public void deleteSingleVMByName(String name) { VMRepository.deleteByName(name);}
    //delete multipe
    public void deleteMultipleVMsByName(List<String> names) { VMRepository.deleteAllByNameIn(names);
    }

    public void importClientsFromExcel(MultipartFile file) {
        List<VM> vms = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // Lire la première feuille
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip header row
                }

                // Lire et traiter chaque cellule avec gestion des types et espaces
                double CPU_Usage = getNumericValue(row.getCell(0));
                double CPU_Utilization = getNumericValue(row.getCell(1));
                String IP = getStringValue(row.getCell(2));
                long Memory_Size = (long) getNumericValue(row.getCell(3));
                double Memory_Utilization = getNumericValue(row.getCell(4));
                long Provisioned_Space = (long) getNumericValue(row.getCell(5));
                long Read_Throughput = (long) getNumericValue(row.getCell(6));
                String Resource_Pool = getStringValue(row.getCell(7));
                String State = getStringValue(row.getCell(8));
                String Status = getStringValue(row.getCell(9));
                long Throughput = (long) getNumericValue(row.getCell(10));
                long Used_Space = (long) getNumericValue(row.getCell(11));
                long Virtual_Disk_Bandwidth = (long) getNumericValue(row.getCell(12));
                long Write_Throughput = (long) getNumericValue(row.getCell(13));
                String name = getStringValue(row.getCell(14));
                int vCPUs = (int) getNumericValue(row.getCell(15));
                String Guest_OS = getStringValue(row.getCell(16));

VM vm = new VM(CPU_Usage, CPU_Utilization, IP, Memory_Size, Memory_Utilization, Provisioned_Space,
        Read_Throughput, Resource_Pool, null, State, Status, Throughput, Used_Space, 
        Virtual_Disk_Bandwidth, Write_Throughput, name, vCPUs, Guest_OS);
                vms.add(vm);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sauvegarder les VM dans la base de données Neo4j
        VMRepository.saveAll(vms);
    }

    // Méthodes utilitaires pour gérer les types de cellules
    private double getNumericValue(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell != null && cell.getCellType() == CellType.STRING) {
            // Supprimer les espaces dans les nombres formatés avec des séparateurs
            String cellValue = cell.getStringCellValue().replace(" ", "").replace(",", "");
            try {
                return Double.parseDouble(cellValue);
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }
        return 0.0;
    }

    private String getStringValue(Cell cell) {
        return cell != null && cell.getCellType() == CellType.STRING ? cell.getStringCellValue() : "";
    }
   
   public List<VM> getAllClients() {
        return VMRepository.findAll();
    }


}
