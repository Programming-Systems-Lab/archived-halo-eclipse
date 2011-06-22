/**
 * UserServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package edu.columbia.cs.psl.halo.server;

public interface UserServiceService extends javax.xml.rpc.Service {
    public java.lang.String getUserServicePortAddress();

    public edu.columbia.cs.psl.halo.server.UserService getUserServicePort() throws javax.xml.rpc.ServiceException;

    public edu.columbia.cs.psl.halo.server.UserService getUserServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
