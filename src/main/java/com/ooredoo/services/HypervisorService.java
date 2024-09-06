package com.ooredoo.services;

import com.ooredoo.entities.Hypervisor;
import com.ooredoo.entities.VM;
import com.ooredoo.repositories.HypervisorRepository;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;


@Service
public class HypervisorService {

    final HypervisorRepository hypervisorRepository;
    final VMService vmService;

    public HypervisorService(HypervisorRepository hypervisorRepository, VMService vmService) {
        this.hypervisorRepository = hypervisorRepository;
        this.vmService = vmService;
    }


    //------------------- display --------------------------
    //display hypervisors and their vm list
    public Collection<Hypervisor> getAll() {
            Collection<Hypervisor> hypervisors = hypervisorRepository.getAll();

            for (Hypervisor hypervisor : hypervisors) {
                String hypervisorName = hypervisor.getName();
                List<VM> vms = vmService.getVMsByHypervisorName(hypervisorName);
                hypervisor.setVMS(vms);
            }

            return hypervisors;
              }
    //display only hypervisors
    public Collection<Hypervisor> getAllHypervisors() {
        return hypervisorRepository.getAllHypervisors();
    }
    //display hypervisor list of a HypervisorCluster
    public List<Hypervisor> getHypervisorsByHypervisorClusterName(String hypervisorClusterName) { return hypervisorRepository.findHypervisorsByHypervisorClusterName(hypervisorClusterName);}

    //-------------------- add ---------------------------------
    //add single
    public Hypervisor addSingleHypervisor(Hypervisor hypervisor) { return hypervisorRepository.save(hypervisor);}
    //add multiple
    public List<Hypervisor> addMultipleHypervisors(List<Hypervisor> hypervisors) {return hypervisorRepository.saveAll(hypervisors);}

    //------------------- update --------------------------------
    public Hypervisor updateHypervisorByName(String name, Hypervisor updatedHypervisor) {
        Hypervisor existingHypervisor = hypervisorRepository.findByName(name);


        // Update the specific fields with the provided values
        if (updatedHypervisor.getName() != null) { existingHypervisor.setName(updatedHypervisor.getName());}
        if (updatedHypervisor.getCPU_Utilization() != 0.0) {existingHypervisor.setCPU_Utilization(updatedHypervisor.getCPU_Utilization());}
        if (updatedHypervisor.getDisk_Bandwidth() != 0.0) {existingHypervisor.setDisk_Bandwidth(updatedHypervisor.getDisk_Bandwidth());}
        if (updatedHypervisor.getMemory_Utilization() != 0.0) {existingHypervisor.setMemory_Utilization(updatedHypervisor.getMemory_Utilization());}
        if (updatedHypervisor.getModel() != null) {existingHypervisor.setModel(updatedHypervisor.getModel());}
        if (updatedHypervisor.getStatus() != null) {existingHypervisor.setStatus(updatedHypervisor.getStatus());}
        if (updatedHypervisor.getTotal_CPU() != 0) {existingHypervisor.setTotal_CPU(updatedHypervisor.getTotal_CPU());}
        if (updatedHypervisor.getTotal_Memory() != 0) {existingHypervisor.setTotal_Memory(updatedHypervisor.getTotal_Memory());}
        if (updatedHypervisor.getVersion() != null) {existingHypervisor.setVersion(updatedHypervisor.getVersion());}

        // Save the updated Hypervisor node
        return hypervisorRepository.save(existingHypervisor);
    }

    //---------------------- delete -------------------------------
    //delete single
    public void deleteSingleHypervisorByName(String name) { hypervisorRepository.deleteByName(name);}
    //delete multiple
    public void deleteMultipleHypervisorsByName(List<String> names) { hypervisorRepository.deleteAllByNameIn(names);}
    
    
    
    public void importHypervisorsFromExcel(MultipartFile file) {
        List<Hypervisor> hypervisors = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            // Utiliser le bon type de Sheet

            Sheet sheet = workbook.getSheetAt(0); // Lire la première feuille
            for (Row row : sheet) {

            // Utiliser une boucle for standard pour itérer sur les lignes
            	 if (row.getRowNum() == 0) {
                     continue; // Skip header row
                 }

                // Lire et traiter chaque cellule avec gestion des types et espaces
                String name = getStringValue(row.getCell(0));
                double CPU_Utilization = getNumericValue(row.getCell(1));
                double Disk_Bandwidth = getNumericValue(row.getCell(2));
                double Memory_Utilization = getNumericValue(row.getCell(3));
                String Model = getStringValue(row.getCell(4));
                String Status = getStringValue(row.getCell(5));
                int Total_CPU = (int) getNumericValue(row.getCell(6));
                int Total_Memory = (int) getNumericValue(row.getCell(7));
                String Version = getStringValue(row.getCell(8));

                Hypervisor hypervisor = new Hypervisor(name, CPU_Utilization, Disk_Bandwidth, Memory_Utilization, Model, Status, Total_CPU, Total_Memory, Version);
                hypervisors.add(hypervisor);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sauvegarder les Hypervisors dans la base de données Neo4j
        hypervisorRepository.saveAll(hypervisors);
    }

    // Méthodes utilitaires pour gérer les types de cellules
    private double getNumericValue(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell != null && cell.getCellType() == CellType.STRING) {
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

    public List<Hypervisor> getHypervisors() {
        return hypervisorRepository.findAll();
    }
}
