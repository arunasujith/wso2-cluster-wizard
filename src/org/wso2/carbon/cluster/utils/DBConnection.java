/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wso2.carbon.cluster.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.DatabaseMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.ibatis.jdbc.ScriptRunner;

/**
 *
 * @author aruna
 */
public class DBConnection {

    private static Connection connection;

    private DBConnection() {
    }

    public static Connection connect(String host, String port, String username,
            String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/",
                username, password);
        return connection;
    }

    public static Connection connect(String host, String port, String db, String username,
            String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Connecting to database...");
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db,
                username, password);
        return connection;
    }

    public static void createDatabase(Connection conn, String dbName) throws Exception {
        Statement stmt = conn.createStatement();
        String sql = "CREATE DATABASE " + dbName;
        stmt.executeUpdate(sql);
    }
    
    public static void dropDatabase(Connection conn, String dbName) throws Exception {
        Statement stmt = conn.createStatement();
        String sql = "DROP DATABASE " + dbName;
        stmt.executeUpdate(sql);
    }
    
    public static void useDatabase(Connection con ,String dbName )throws Exception{
        Statement stmt = con.createStatement();
        String sql = "USE " + dbName;
        stmt.executeUpdate(sql);
    }

    public static boolean dbAlreadyExists(Connection conn, String dbName) throws Exception {
        boolean isExist = false;
        ResultSet resultSet = conn.getMetaData().getCatalogs();
        while (resultSet.next()) {
            // Get the database name, which is at position 1
            if (resultSet.getString(1).equalsIgnoreCase(dbName)) {
                isExist = true;
            }
        }
        resultSet.close();
        return isExist;
    }

    public static void runScript(Connection con, File sqlFile) throws Exception {
        ScriptRunner runner = new ScriptRunner(con);
        runner.setAutoCommit(true);
        runner.runScript(new BufferedReader(new FileReader(sqlFile)));
    }
    //grant all on WSO2_USER_DB.* TO regadmin@"carbondb.mysql-wso2.com" identified by "regadmin";
    public static void grantPermission(Connection con , String dbName, String user, String host)throws Exception{
        
        String sql = "grant all on "+dbName+".* TO "+user+"@\""+host+"\" identified by \""+user+"\"";
        System.out.println(sql);
        //Statement stmt = con.createStatement();
        //stmt.executeUpdate(sql);
    }

    public static void main(String[] args) throws Exception {
        Connection con = DBConnection.connect("localhost", "3306", "root", "123");
//        if (!dbAlreadyExists(con, "DDD")) {
//            createDatabase(con, "DDD");
//        }
        useDatabase(con , "REGISTRY_DB");
//        //Connection con1 = DBConnection.connect("localhost", "3306", "BBB", "root", "123");
//        runScript(con, new File("/home/aruna/Desktop/cluster/manager_wso2esb-4.6.0/dbscripts/mysql.sql"));
        grantPermission(con, "REGISTRY_DB", "root", "localhost");
    }
}
