/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wso2.carbon.cluster.main;

import org.wso2.carbon.cluster.ui.ClusterWizard;

/**
 *
 * @author aruna
 */
public class Main {
    
    public static void main(String args []){
        ClusterWizard clusterWizard = new ClusterWizard();
        clusterWizard.setLocationRelativeTo(null);
        clusterWizard.setResizable(false);
        clusterWizard.setVisible(true);
    }
          
}

//TODO
//check all are filled
//