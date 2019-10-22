package com.java.arquillian.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class VersionUtil {
    public VersionUtil() {
     // no-arg constructor for json library to construct current object
    }

    /**
     * This menthod return a JsonObject with informations like project name and version number
     *
     * @param groupId and artifactId
     * @return String {projectName:projectVersion}
     */
    public Map<String, String> getVersionInJson(String groupId, String artifactId) {
	Map<String, String> version = new HashMap<>();
	version.put(getProjectName(groupId, artifactId), getVersion(groupId, artifactId));

	return version;
    }

    /**
     * This menthod return a version number of specific project
     *
     * @param groupId and artifactId
     * @return String projectVersion
     */
    public String getVersion(String groupId, String artifactId) {
	Properties properties = getPropertiesInfo(groupId, artifactId);
	String version = properties.getProperty("version");

	if (version == null) {
	    Package aPackage = getClass().getPackage();
	    if (aPackage != null) {
		version = aPackage.getImplementationVersion();
		if (version == null) {
		    version = aPackage.getSpecificationVersion();
		}
	    }
	}
	if (version == null) {
	    version = "";
	}
	return version;
    }

    private String getProjectName(String groupId, String artifactId) {
	Properties properties = getPropertiesInfo(groupId, artifactId);
	String projectName = properties.getProperty("m2e.projectName");

	if (projectName == null) {
	    projectName = "";
	}
	return projectName;
    }

    private Properties getPropertiesInfo(String groupId, String artifactId) {
	Properties property = new Properties();
	InputStream pomFile = getClass()
		.getResourceAsStream("/META-INF/maven/" + groupId + "/" + artifactId + "/pom.properties");
	if (pomFile != null) {
	    try {
		property.load(pomFile);
	    } catch (IOException e) {
		// Can not read file from path
	    }
	}
	return property;
    }
}
