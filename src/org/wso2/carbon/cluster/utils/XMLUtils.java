/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wso2.carbon.cluster.utils;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.wso2.carbon.cluster.bean.Axis2XMLBean;
import org.wso2.carbon.cluster.bean.CarbonXMLBean;
import org.wso2.carbon.cluster.bean.DatasourceBean;
import org.wso2.carbon.cluster.bean.RegistryXMLBean;

/**
 *
 * @author aruna
 */
public class XMLUtils {

    private File xmlFile;

    public XMLUtils(File xmlFile) {
        this.xmlFile = xmlFile;
    }

    public static void updateUserMgtXML(File userMgtXML, String jndiConfigNameUserDB) throws Exception {
        Document doc = initializeXML(userMgtXML);
        NodeList configNodeList = doc.getElementsByTagName("Configuration");
        Node configNode = configNodeList.item(0); //get the 0 index, since only one available

        NodeList nodeList = configNode.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equalsIgnoreCase("Property")) {
                    if (node.hasAttributes()) {
                        NamedNodeMap namedNodeMap = node.getAttributes();
                        Node attr = namedNodeMap.getNamedItem("name");
                        if (attr != null && attr.getNodeValue().equalsIgnoreCase("dataSource")) {
                            node.setTextContent(jndiConfigNameUserDB);
                        }
                    }
                }
            }
        }
        finalizeXML(userMgtXML, doc, 4);
    }

    public static void updateRegistryXML(File registryXML, RegistryXMLBean bean) throws Exception {

        Document doc = initializeXML(registryXML);
        NodeList registryNodeList = doc.getElementsByTagName("wso2registry");
        Node registryNode = registryNodeList.item(0); //get the 0 index, since only one available

        //adding shared registry
        Element dbConfig = doc.createElement("dbConfig");
        dbConfig.setAttribute("name", bean.getDbConfigName());
        Element dataSource = doc.createElement("dataSource");
        dataSource.appendChild(doc.createTextNode(bean.getDataSource()));
        dbConfig.appendChild(dataSource);
        registryNode.appendChild(dbConfig);

        // adding remote instance
        Element remoteInstance = doc.createElement("remoteInstance");
        remoteInstance.setAttribute("url", bean.getRemoteInstanceURL());
        Element remoteInstanceID = doc.createElement("id");
        remoteInstanceID.appendChild(doc.createTextNode(bean.getRemoteInstanceID()));
        remoteInstance.appendChild(remoteInstanceID);
        Element remoteInstanceDbConfig = doc.createElement("dbConfig");
        remoteInstanceDbConfig.appendChild(doc.createTextNode(bean.getRemoteInstanceDbConfig()));
        remoteInstance.appendChild(remoteInstanceDbConfig);
        Element remoteInstanceReadOnly = doc.createElement("readOnly");
        remoteInstanceReadOnly.appendChild(doc.createTextNode("" + bean.isRemoteInstanceReadOnly()));
        remoteInstance.appendChild(remoteInstanceReadOnly);
        Element remoteInstanceenableCache = doc.createElement("enableCache");
        remoteInstanceenableCache.appendChild(doc.createTextNode("" + bean.isRemoteInstanceEnableCache()));
        remoteInstance.appendChild(remoteInstanceenableCache);
        Element remoteInstanceRegistryRoot = doc.createElement("registryRoot");
        remoteInstanceRegistryRoot.appendChild(doc.createTextNode(bean.getRemoteInstanceRegistryRoot()));
        remoteInstance.appendChild(remoteInstanceRegistryRoot);
        Element remoteInstanceCacheId = doc.createElement("cacheId");
        remoteInstanceCacheId.appendChild(doc.createTextNode(bean.getRemoteInstanceCacheID()));
        remoteInstance.appendChild(remoteInstanceCacheId);
        registryNode.appendChild(remoteInstance);

        //adding system mount
        Element mountSystemConfig = doc.createElement("mount");
        mountSystemConfig.setAttribute("path", bean.getMountPathSystemConfig());
        mountSystemConfig.setAttribute("overwrite", "" + bean.isMountPathSystemOverwrite());
        Element mountSystemConfigInstanceID = doc.createElement("instanceId");
        mountSystemConfigInstanceID.appendChild(doc.createTextNode(bean.getMountSystemConfigInstanceID()));
        mountSystemConfig.appendChild(mountSystemConfigInstanceID);
        Element mountSystemConfigTargetPath = doc.createElement("targetPath");
        mountSystemConfigTargetPath.appendChild(doc.createTextNode(bean.getMountSystemConfigTargetPath()));
        mountSystemConfig.appendChild(mountSystemConfigTargetPath);
        registryNode.appendChild(mountSystemConfig);

        //adding gov mount
        Element mountSystemGovernance = doc.createElement("mount");
        mountSystemGovernance.setAttribute("path", "/_system/governance");
        mountSystemGovernance.setAttribute("overwrite", "true");
        Element mountSystemGovernanceInstanceID = doc.createElement("instanceId");
        mountSystemGovernanceInstanceID.appendChild(doc.createTextNode("instanceid"));
        mountSystemGovernance.appendChild(mountSystemGovernanceInstanceID);
        Element mountSystemGovernanceTargetPath = doc.createElement("targetPath");
        mountSystemGovernanceTargetPath.appendChild(doc.createTextNode("/_system/governance"));
        mountSystemGovernance.appendChild(mountSystemGovernanceTargetPath);
        registryNode.appendChild(mountSystemGovernance);

        finalizeXML(registryXML, doc, 4);

    }

    public static void updataMasterDatasourcesXML(File datasourceXMLFile, DatasourceBean bean) throws Exception {
        Document doc = initializeXML(datasourceXMLFile);
        NodeList dataSourcesList = doc.getElementsByTagName("datasources");
        Node dataSources = dataSourcesList.item(0); //get the 0 index, since only one available

//        Node dataSource = createDataSourceNode(datasourceBean);
        Element datasource = doc.createElement("datasource");
        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(bean.getName()));
        Element description = doc.createElement("description");
        description.appendChild(doc.createTextNode(bean.getDescription()));
        Element jndiConfig = doc.createElement("jndiConfig");
        Element jndiConfigName = doc.createElement("name");
        jndiConfig.appendChild(jndiConfigName);
        jndiConfigName.appendChild(doc.createTextNode(bean.getJndiConfigName()));

        Element definition = doc.createElement("definition");
        definition.setAttribute("type", bean.getDefinitionType());
        //adding configuration elements
        Element configuration = doc.createElement("configuration");
        definition.appendChild(configuration);
        Element url = doc.createElement("url");
        url.appendChild(doc.createTextNode(bean.getUrl()));
        Element username = doc.createElement("username");
        username.appendChild(doc.createTextNode(bean.getUsername()));
        Element password = doc.createElement("password");
        password.appendChild(doc.createTextNode(bean.getPassword()));
        Element driverClassName = doc.createElement("driverClassName");
        driverClassName.appendChild(doc.createTextNode(bean.getDriverClassName()));
        Element maxActive = doc.createElement("maxActive");
        maxActive.appendChild(doc.createTextNode("" + bean.getMaxActive()));
        Element maxWait = doc.createElement("maxWait");
        maxWait.appendChild(doc.createTextNode("" + bean.getMaxWait()));
        Element testOnBorrow = doc.createElement("testOnBorrow");
        testOnBorrow.appendChild(doc.createTextNode("" + bean.isTestOnBorrow()));
        Element validationQuery = doc.createElement("validationQuery");
        validationQuery.appendChild(doc.createTextNode(bean.getValidationQuery()));
        Element validationInterval = doc.createElement("validationInterval");
        validationInterval.appendChild(doc.createTextNode("" + bean.getValidationInterval()));
        configuration.appendChild(url);
        configuration.appendChild(username);
        configuration.appendChild(password);
        configuration.appendChild(driverClassName);
        configuration.appendChild(maxActive);
        configuration.appendChild(maxWait);
        configuration.appendChild(testOnBorrow);
        configuration.appendChild(validationQuery);
        configuration.appendChild(validationInterval);

        Node dataSourceNode = (Node) datasource;
        dataSourceNode.appendChild(name);
        dataSourceNode.appendChild(description);
        dataSourceNode.appendChild(jndiConfig);
        dataSourceNode.appendChild(definition);

        dataSources.appendChild(datasource);

        finalizeXML(datasourceXMLFile, doc, 4);

    }

    public static void updateCarbonXML(File carbonXMLFile, CarbonXMLBean carbonXMLBean) throws Exception {

        Document doc = initializeXML(carbonXMLFile);

        //TODO - The new nodes added to the end of document, find a way to format clean
        NodeList nList = doc.getElementsByTagName("Server");
        Node server = nList.item(0);
        //add MgtHostName element
        Element mgtHostnameElmnt = doc.createElement("MgtHostName");
        mgtHostnameElmnt.appendChild(doc.createTextNode(carbonXMLBean.getMgtHostName()));
        server.appendChild(mgtHostnameElmnt);
        //add HostName element
        Element hostnameElmnt = doc.createElement("HostName");
        hostnameElmnt.appendChild(doc.createTextNode(carbonXMLBean.getHostName()));
        server.appendChild(hostnameElmnt);

        Node portsNode = doc.getElementsByTagName("Ports").item(0);
        Element eElement = (Element) portsNode;

        eElement.getElementsByTagName("Offset").item(0).setTextContent("" + carbonXMLBean.getPortOffset());

        finalizeXML(carbonXMLFile, doc, 2);

    }

    public static void updateAxis2XML(File axis2XMLFile, Axis2XMLBean axis2XMLBean) throws Exception {

        Document doc = initializeXML(axis2XMLFile);
        //Get the clustering node list 
        NodeList clusterNodeList = doc.getElementsByTagName("clustering");
        NodeList memberList = doc.getElementsByTagName("members");
        //there is only one clustering node, so take the 0 index element
        Node clusterNode = clusterNodeList.item(0);
        //enable clustering
        NamedNodeMap clusterAttr = clusterNode.getAttributes();
        Node nodeAttr = clusterAttr.getNamedItem("enable");
        nodeAttr.setTextContent("true");
        //get the child nodes on the clustering node
        NodeList nodeList = clusterNode.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                NamedNodeMap attr = node.getAttributes();
                //need to check the attribute name of the parameter nodes
                Node attrNode = attr.item(0);
                if (attrNode != null) {
                    //need to check whether need to explicitly set the localMemberHost
                    //since in carbon 4.3.0 we need the ip address insted of localhost
                    if (attrNode.getTextContent().equalsIgnoreCase("membershipScheme")) {
                        node.setTextContent(axis2XMLBean.getMembershipScheme());
                    } else if (attrNode.getTextContent().equalsIgnoreCase("domain")) {
                        node.setTextContent(axis2XMLBean.getDomain());
                    } else if (attrNode.getTextContent().equalsIgnoreCase("localMemberHost")) {
                        node.setTextContent(axis2XMLBean.getLocalMemberHost());
                    } else if (attrNode.getTextContent().equalsIgnoreCase("localMemberPort")) {
                        node.setTextContent("" + axis2XMLBean.getLocalMemberPort());
                    }
                }
            }
        }
        //adding two member nodes, since we are creating a worker/manager setup
        int memberCount = 0;
        for (int i = 0; i < memberList.getLength(); i++) {
            NodeList members = memberList.item(i).getChildNodes();
            for (int j = 0; j < members.getLength(); j++) {
                Node member = members.item(j);
                //get the members available
                if (member != null && member.getNodeName().equalsIgnoreCase("member")) {
                    NodeList memberChilds = member.getChildNodes();
                    for (int k = 0; k < memberChilds.getLength(); k++) {
                        Node memberChild = memberChilds.item(k);

                        if (memberChild.getNodeType() == Node.ELEMENT_NODE) {
                            if (memberCount == 0) {
                                if (memberChild.getNodeName().equalsIgnoreCase("hostName")) {
                                    memberChild.setTextContent(axis2XMLBean.getLocalMemberHost());
                                } else if (memberChild.getNodeName().equalsIgnoreCase("port")) {
                                    memberChild.setTextContent("" + axis2XMLBean.getLocalMemberPort());
                                }
                            } else if (memberCount == 1) {
                                if (memberChild.getNodeName().equalsIgnoreCase("hostName")) {
                                    memberChild.setTextContent(axis2XMLBean.getOtherLocalMemberHost());
                                } else if (memberChild.getNodeName().equalsIgnoreCase("port")) {
                                    memberChild.setTextContent("" + axis2XMLBean.getOtherLocalMemberPort());
                                }
                            }
                        }
                    }
                    memberCount++;
                }
            }
        }

        if (memberCount < 2) {
            //we have to add new member programitacally,
            NodeList membersList = doc.getElementsByTagName("members");
            Node members = membersList.item(0); //get the 0 index, since only one available

//        Node dataSource = createDataSourceNode(datasourceBean);
            Element member = doc.createElement("member");
            Element name = doc.createElement("hostName");
            name.appendChild(doc.createTextNode(axis2XMLBean.getOtherLocalMemberHost()));
            Element port = doc.createElement("port");
            port.appendChild(doc.createTextNode("" + axis2XMLBean.getOtherLocalMemberPort()));
            member.appendChild(name);
            member.appendChild(port);
            members.appendChild(member);

        }
        finalizeXML(axis2XMLFile, doc, 4);

    }

    private static Document initializeXML(File xmlFile) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc;
    }

    private static void finalizeXML(File xmlFile, Document doc, int intendAmount) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "" + intendAmount);
        transformer.setOutputProperty("indent", "yes");
        transformer.transform(source, result);
    }

}
