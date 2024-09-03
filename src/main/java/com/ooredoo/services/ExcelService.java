package com.ooredoo.services;

import java.io.FileInputStream;
import com.ooredoo.repositories.VMRepository;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.ooredoo.entities.VM;
import com.ooredoo.entities.Datastore;
import com.ooredoo.entities.VM;
import com.ooredoo.repositories.VMRepository;

@Service
	public class ExcelService {

	  /*  public List<List<String>> readExcelFile(String filePath) throws IOException {
	        List<List<String>> data = new ArrayList<>();

	        try (FileInputStream file = new FileInputStream(filePath);
	             Workbook workbook = new XSSFWorkbook(file)) {

	            Sheet sheet = workbook.getSheetAt(0);  // Reading the first sheet
	            for (Row row : sheet) {
	                List<String> rowData = new ArrayList<>();
	                for (Cell cell : row) {
	                    rowData.add(getCellValueAsString(cell));
	                }
	                data.add(rowData);
	            }
	        }
	        return data;
	    }

	    private String getCellValueAsString(Cell cell) {
	        switch (cell.getCellType()) {
	            case STRING:
	                return cell.getStringCellValue();
	            case NUMERIC:
	                if (DateUtil.isCellDateFormatted(cell)) {
	                    return cell.getDateCellValue().toString();
	                } else {
	                    return String.valueOf(cell.getNumericCellValue());
	                }
	            case BOOLEAN:
	                return String.valueOf(cell.getBooleanCellValue());
	            case FORMULA:
	                return cell.getCellFormula();
	            default:
	                return "";
	        }
	    }
	 @Autowired
	    private Repository clientRepository;
	    private VMRepository vmRepository;

	 
	   public void importClientsFromExcel(MultipartFile file) {
	        List<Client> clients = new ArrayList<>();
	        try (InputStream inputStream = file.getInputStream();
	             Workbook workbook = WorkbookFactory.create(inputStream)) {

	            Sheet sheet = workbook.getSheetAt(0); // Lire la première feuille
	            for (Row row : sheet) {
	                if (row.getRowNum() == 0) {
	                    continue;
	                }

	                Long id = null;
	                if (row.getCell(0) != null) {
	                    id = (long) row.getCell(0).getNumericCellValue();
	                }

	                String name = null;
	                if (row.getCell(1) != null) {
	                    name = row.getCell(1).getStringCellValue();
	                }

	                int age = 0;
	                if (row.getCell(2) != null) {
	                    age = (int) row.getCell(2).getNumericCellValue();
	                }

	                String department = null;
	                if (row.getCell(3) != null) {
	                    department = row.getCell(3).getStringCellValue();
	                }

	                Client client = new Client(id, name, age, department);
	                clients.add(client);
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (NullPointerException e) {
	            System.err.println("NullPointerException: Assurez-vous que toutes les cellules nécessaires sont présentes et correctement formatées.");
	            e.printStackTrace();
	        }


	        // Sauvegarder les clients dans la base de données Neo4j
	        clientRepository.saveAll(clients);
	    }
	   
	   public List<Client> getAllClients() {
	        return clientRepository.findAll();
	    } 
		
	    public void importVMSFromExcel(MultipartFile file) {
	   
	   List<VM> vms = new ArrayList<>();
		 try (InputStream inputStream = file.getInputStream();
	             Workbook workbook = WorkbookFactory.create(inputStream)) {
	       Sheet sheet = workbook.getSheetAt(0);

	       for (Row row : sheet) {
	           Long id = null;
	           if (row.getCell(0) != null) {
	               id = (long) row.getCell(0).getNumericCellValue();
	           }

	           double CPU_Usage = 0.0;
	           if (row.getCell(1) != null) {
	               CPU_Usage = row.getCell(1).getNumericCellValue();
	           }

	           double CPU_Utilization = 0.0;
	           if (row.getCell(2) != null) {
	               CPU_Utilization = row.getCell(2).getNumericCellValue();
	           }

	           String Guest_OS = null;
	           if (row.getCell(3) != null) {
	               Guest_OS = row.getCell(3).getStringCellValue();
	           }

	           String IP = null;
	           if (row.getCell(4) != null) {
	               IP = row.getCell(4).getStringCellValue();
	           }

	           long Memory_Size = 0;
	           if (row.getCell(5) != null) {
	               Memory_Size = (long) row.getCell(5).getNumericCellValue();
	           }

	           double Memory_Utilization = 0.0;
	           if (row.getCell(6) != null) {
	               Memory_Utilization = row.getCell(6).getNumericCellValue();
	           }

	           long Provisioned_Space = 0;
	           if (row.getCell(7) != null) {
	               Provisioned_Space = (long) row.getCell(7).getNumericCellValue();
	           }

	           long Read_Throughput = 0;
	           if (row.getCell(8) != null) {
	               Read_Throughput = (long) row.getCell(8).getNumericCellValue();
	           }

	           String Resource_Pool = null;
	           if (row.getCell(9) != null) {
	               Resource_Pool = row.getCell(9).getStringCellValue();
	           }

	           String State = null;
	           if (row.getCell(10) != null) {
	               State = row.getCell(10).getStringCellValue();
	           }

	           String Status = null;
	           if (row.getCell(11) != null) {
	               Status = row.getCell(11).getStringCellValue();
	           }

	           long Throughput = 0;
	           if (row.getCell(12) != null) {
	               Throughput = (long) row.getCell(12).getNumericCellValue();
	           }

	           long Used_Space = 0;
	           if (row.getCell(13) != null) {
	               Used_Space = (long) row.getCell(13).getNumericCellValue();
	           }

	           long Virtual_Disk_Bandwidth = 0;
	           if (row.getCell(14) != null) {
	               Virtual_Disk_Bandwidth = (long) row.getCell(14).getNumericCellValue();
	           }

	           long Write_Throughput = 0;
	           if (row.getCell(15) != null) {
	               Write_Throughput = (long) row.getCell(15).getNumericCellValue();
	           }

	           String name = null;
	           if (row.getCell(16) != null) {
	               name = row.getCell(16).getStringCellValue();
	           }

	           int vCPUs = 0;
	           if (row.getCell(17) != null) {
	               vCPUs = (int) row.getCell(17).getNumericCellValue();
	           }

	           VM vm = new VM(id, CPU_Usage, CPU_Utilization, Guest_OS, IP, Memory_Size, Memory_Utilization,
	                           Provisioned_Space, Read_Throughput, Resource_Pool, State, Status, Throughput,
	                           Used_Space, Virtual_Disk_Bandwidth, Write_Throughput, name, vCPUs);
	           public VM(String name, int vCPUs, String state, double CPU_Usage, String guest_OS, String IP, String resource_Pool, List<Datastore> datastores, long throughput, long used_Space, long memory_Size, String status, long virtual_Disk_Bandwidth, double CPU_Utilization, long read_Throughput, long write_Throughput, double memory_Utilization, long provisioned_Space) {

	           vms.add(vm);
	       }

	   } catch (IOException e) {
	       e.printStackTrace();
	   } catch (NullPointerException e) {
	       System.err.println("NullPointerException: Assurez-vous que toutes les cellules nécessaires sont présentes et correctement formatées.");
	       e.printStackTrace();
	   }

	   // Sauvegarder les VMs dans la base de données Neo4j
	   vmRepository.saveAll(vms);

	   
	   
	   
	   
	   */
	   
	}


	






