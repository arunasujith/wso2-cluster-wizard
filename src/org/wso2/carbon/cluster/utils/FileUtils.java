/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wso2.carbon.cluster.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JOptionPane;

import javax.swing.JTextArea;

/**
 *
 * @author aruna
 */
public class FileUtils {

    public static JTextArea textArea = null;

    public static boolean unzipFile(String zipFilePath, String destDirectory) {
        boolean isExtracted = true;
        ZipInputStream zipIn = null;
        try {
            File destDir = new File(destDirectory);
            if (!destDir.exists()) {
                destDir.mkdir();
            }
            zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                textArea.append("INFO ::  Extracting :: " + entry.getName() + "\n");
                String filePath = destDirectory + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    extractFile(zipIn, filePath);
                } else {
                    File dir = new File(filePath);
                    dir.mkdir();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
            textArea.append("========================================================================" + "\n");
            textArea.append("INFO :: Successfully Extracted :: " + "\n");
            textArea.append("========================================================================" + "\n");
        } catch (IOException e) {
            isExtracted = false;
            textArea.append("========================================================================" + "\n");
            textArea.append("ERROR :: " + e.toString() + "\n");
            textArea.append("========================================================================" + "\n");
        } finally {
            if (zipIn != null) {
                try {
                    zipIn.close();
                } catch (IOException ex) {
                    
                }
            }
        }
        return isExtracted;
    }

    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[4096];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

    public static void renameFolder(String destinationFolderPath, String zipFileName, String prefix) {
        File folder = new File(destinationFolderPath + File.separator + zipFileName);
        File managerFolder = new File(destinationFolderPath + File.separator + prefix + zipFileName);
        if (folder.exists()) {
            folder.renameTo(managerFolder);
        } else {
            System.out.println(zipFileName + " Folder not found..!!");
        }
    }

    public static void makeCopy(File sourceFile, File desFile) {
        try {
            org.apache.commons.io.FileUtils.copyDirectory(sourceFile, desFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteProducts(String absPath, String productName) {
        boolean success = true;
        File managerFolder = new File(absPath + File.separator + ClusterConstants.PREFIX_MANAGER + productName);
        File workerFolder = new File(absPath + File.separator + ClusterConstants.PREFIX_WORKER + productName);

        try {
            if (managerFolder.exists()) {

                int option = JOptionPane.showConfirmDialog(null, ClusterConstants.PREFIX_MANAGER + productName
                        + " folder already exists and it will be replaced...!!! ", "Warning", JOptionPane.WARNING_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    textArea.append("INFO :: Manager folder already Exists...!!! " + "\n");
                    textArea.append("========================================================================" + "\n");
                    org.apache.commons.io.FileUtils.deleteDirectory(managerFolder);
                    textArea.append("INFO :: Deleted manager folder " + "\n");
                    textArea.append("========================================================================" + "\n");
                } else {
                    success = false;
                }

            }
            if (workerFolder.exists()) {
                int option = JOptionPane.showConfirmDialog(null, ClusterConstants.PREFIX_WORKER + productName
                        + " folder already exists and it will be replaced...!!! ", "Warning", JOptionPane.WARNING_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    textArea.append("INFO :: Worker folder already Exists...!!! " + "\n");
                    textArea.append("========================================================================" + "\n");
                    org.apache.commons.io.FileUtils.deleteDirectory(workerFolder);
                    textArea.append("INFO :: Deleted worker folder " + "\n");
                    textArea.append("========================================================================" + "\n");
                } else {
                    success = false;
                }
            }
        } catch (Exception e) {
            textArea.append("ERROR :: Unable to delete folder " + e.toString() + "\n");
            textArea.append("========================================================================" + "\n");
        }
        return success;
    }

    public static void main(String[] arg) throws Exception {
        String zipFilePath = "/home/aruna/Desktop/wso2as-6.0.0-SNAPSHOT.zip";
        String destDirectory = "/home/aruna/Desktop";

//    	unzipFile(zipFilePath ,destDirectory, null);
//    	renameFolder(destDirectory, "wso2as-6.0.0-SNAPSHOT");
//    	makeCopy(new File(destDirectory + File.separator + "manager_" +"wso2as-6.0.0-SNAPSHOT"), new File(destDirectory + File.separator + "worker_" +"wso2as-6.0.0-SNAPSHOT"));
    }
}