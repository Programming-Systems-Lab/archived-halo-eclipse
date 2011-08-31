
package edu.columbia.cs.psl.halo.server.stubs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.columbia.cs.psl.halo.server.stubs package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Log_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "log");
    private final static QName _LogResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "logResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.columbia.cs.psl.halo.server.stubs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Log }
     * 
     */
    public Log createLog() {
        return new Log();
    }

    /**
     * Create an instance of {@link LogResponse }
     * 
     */
    public LogResponse createLogResponse() {
        return new LogResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Log }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "log")
    public JAXBElement<Log> createLog(Log value) {
        return new JAXBElement<Log>(_Log_QNAME, Log.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "logResponse")
    public JAXBElement<LogResponse> createLogResponse(LogResponse value) {
        return new JAXBElement<LogResponse>(_LogResponse_QNAME, LogResponse.class, null, value);
    }

}
