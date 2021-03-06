
package edu.columbia.cs.psl.halo.server.stubs;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "LogServiceService", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", wsdlLocation = "http://127.0.0.1:8080/LogServiceService/LogService?wsdl")
public class LogServiceService
    extends Service
{

    private final static URL LOGSERVICESERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(edu.columbia.cs.psl.halo.server.stubs.LogServiceService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = edu.columbia.cs.psl.halo.server.stubs.LogServiceService.class.getResource(".");
            url = new URL(baseUrl, "http://127.0.0.1:8080/LogServiceService/LogService?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://127.0.0.1:8080/LogServiceService/LogService?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        LOGSERVICESERVICE_WSDL_LOCATION = url;
    }

    public LogServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LogServiceService() {
        super(LOGSERVICESERVICE_WSDL_LOCATION, new QName("http://server.halo.psl.cs.columbia.edu/", "LogServiceService"));
    }

    /**
     * 
     * @return
     *     returns LogService
     */
    @WebEndpoint(name = "LogServicePort")
    public LogService getLogServicePort() {
        return super.getPort(new QName("http://server.halo.psl.cs.columbia.edu/", "LogServicePort"), LogService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LogService
     */
    @WebEndpoint(name = "LogServicePort")
    public LogService getLogServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.halo.psl.cs.columbia.edu/", "LogServicePort"), LogService.class, features);
    }

}
