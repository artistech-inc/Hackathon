/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.utils;

/**
 * Interface for build info.
 *
 * @author matta
 */
public interface IBuildInfo {

    String getBuild();

    String getBuilder();

    String getBuildtime();

    String getSvn();

    String getVersion();

    void setBuild(String value);

    void setBuilder(String value);

    void setBuildtime(String value);

    void setSvn(String value);

    void setVersion(String value);
    
    String getApplicationName();
    
    void setApplicationName(String value);
    
    String getCompany();
    
    void setCompany(String value);
}
