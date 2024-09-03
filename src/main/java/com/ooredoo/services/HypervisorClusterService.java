package com.ooredoo.services;

import com.ooredoo.entities.Hypervisor;
import com.ooredoo.entities.HypervisorCluster;
import com.ooredoo.entities.VM;
import com.ooredoo.repositories.HypervisorClusterRepository;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;


@Service
public class HypervisorClusterService {

    final HypervisorClusterRepository HypervisorClusterRepository;
    final HypervisorService HypervisorService;
    final VMService vmService;

    public HypervisorClusterService(HypervisorClusterRepository HypervisorClusterRepository, VMService vmService, HypervisorService HypervisorService) {
        this.HypervisorClusterRepository = HypervisorClusterRepository;
        this.vmService = vmService;
        this.HypervisorService = HypervisorService;
    }


    //------------------- display --------------------------
    //display HypervisorClusters and their Hypervisors list and vms
    public Collection<HypervisorCluster> getAll() {
        Collection<HypervisorCluster> HypervisorClusters = HypervisorClusterRepository.getAll();

        for (HypervisorCluster HypervisorCluster : HypervisorClusters) {
            String HypervisorClusterName = HypervisorCluster.getName();
            List<Hypervisor> Hypervisors = HypervisorService.getHypervisorsByHypervisorClusterName(HypervisorClusterName);
            for (Hypervisor hypervisor : Hypervisors) {
                String hypervisorName = hypervisor.getName();
                List<VM> vms = vmService.getVMsByHypervisorName(hypervisorName);
                hypervisor.setVMS(vms);
            }
            HypervisorCluster.setHypervisors(Hypervisors);
        }

        return HypervisorClusters;
    }
    //display HypervisorClusters and their Hypervisors list
  public Collection<HypervisorCluster> getAllHypervisorClustersandHypervisors() {
            Collection<HypervisorCluster> HypervisorClusters = HypervisorClusterRepository.getAll();

            for (HypervisorCluster HypervisorCluster : HypervisorClusters) {
                String HypervisorClusterName = HypervisorCluster.getName();
                List<Hypervisor> Hypervisors = HypervisorService.getHypervisorsByHypervisorClusterName(HypervisorClusterName);
                HypervisorCluster.setHypervisors(Hypervisors);
            }

            return HypervisorClusters;
              }
    //display only HypervisorClusters
    public Collection<HypervisorCluster> getAllHypervisorClusters() {
        return HypervisorClusterRepository.getAllHypervisorClusters();
    }
    //display hypervisorCluster list of a Datacenter
    public List<HypervisorCluster> getHypervisorClustersByDatacenterName(String DatacenterName) { return HypervisorClusterRepository.findHypervisorClustersByDatacenterName(DatacenterName);}

    //-------------------- add ---------------------------------
    //add single
    public HypervisorCluster addSingleHypervisorCluster(HypervisorCluster HypervisorCluster) { return HypervisorClusterRepository.save(HypervisorCluster);}
    //add multiple
    public List<HypervisorCluster> addMultipleHypervisorClusters(List<HypervisorCluster> HypervisorClusters) {return HypervisorClusterRepository.saveAll(HypervisorClusters);}

    //------------------- update --------------------------------
    public HypervisorCluster updateHypervisorClusterByName(String name, HypervisorCluster updatedHypervisorCluster) {
        HypervisorCluster existingHypervisorCluster = HypervisorClusterRepository.findByName(name);


        // Update the specific fields with the provided values
        if (updatedHypervisorCluster.getName() != null) { existingHypervisorCluster.setName(updatedHypervisorCluster.getName());}
        if (updatedHypervisorCluster.getEsxsInHighMemoryUsage() != 0) {existingHypervisorCluster.setEsxsInHighMemoryUsage(updatedHypervisorCluster.getEsxsInHighMemoryUsage());}
        if (updatedHypervisorCluster.getNumberOfESX() != 0) {existingHypervisorCluster.setNumberOfESX(updatedHypervisorCluster.getNumberOfESX());}
        if (updatedHypervisorCluster.getTotalCPU() != 0) {existingHypervisorCluster.setTotalCPU(updatedHypervisorCluster.getTotalCPU());}
        if (updatedHypervisorCluster.getTotalMemory() != 0) {existingHypervisorCluster.setTotalMemory(updatedHypervisorCluster.getTotalMemory());}


        // Save the updated HypervisorCluster node
        return HypervisorClusterRepository.save(existingHypervisorCluster);
    }

    //---------------------- delete -------------------------------
    //delete single
    public void deleteSingleHypervisorClusterByName(String name) { HypervisorClusterRepository.deleteByName(name);}
    //delete multipe
    public void deleteMultipleHypervisorClustersByName(List<String> names) { HypervisorClusterRepository.deleteAllByNameIn(names);}
    public void importHypervisorClustersFromExcel(MultipartFile file) {
        List<HypervisorCluster> clusters = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // Lire la première feuille
            for (Row row : sheet) {

                if (row.getRowNum() == 0) {
                    continue; // Sauter la ligne d'en-tête
                }

                // Lire et traiter chaque cellule avec gestion des types et espaces
                String name = getStringValue(row.getCell(0));
                int esxsInHighMemoryUsage = (int) getNumericValue(row.getCell(1));
                int numberOfESX = (int) getNumericValue(row.getCell(2));
                int totalCPU = (int) getNumericValue(row.getCell(3));
                long totalMemory = (long) getNumericValue(row.getCell(4));

                HypervisorCluster cluster = new HypervisorCluster(name, esxsInHighMemoryUsage, numberOfESX, totalCPU, totalMemory);
                clusters.add(cluster);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sauvegarder les HypervisorClusters dans la base de données Neo4j
        HypervisorClusterRepository.saveAll(clusters);
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

    public List<HypervisorCluster> getHypervisorClusters() {
        return HypervisorClusterRepository.findAll();
    }



}
