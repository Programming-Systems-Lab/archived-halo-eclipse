
package edu.columbia.cs.psl.halo.server.stubs;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "LogService", targetNamespace = "http://server.halo.psl.cs.columbia.edu/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface LogService {


    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "log", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.Log")
    @ResponseWrapper(localName = "logResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.LogResponse")
    public void log(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

}
