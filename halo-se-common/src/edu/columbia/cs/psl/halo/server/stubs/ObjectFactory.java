
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

    private final static QName _DoTest_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "doTest");
    private final static QName _User_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "user");
    private final static QName _Class_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "class");
    private final static QName _DoTestResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "doTestResponse");
    private final static QName _Enrollment_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "enrollment");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.columbia.cs.psl.halo.server.stubs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Class }
     * 
     */
    public Class createClass() {
        return new Class();
    }

    /**
     * Create an instance of {@link DoTest }
     * 
     */
    public DoTest createDoTest() {
        return new DoTest();
    }

    /**
     * Create an instance of {@link Enrollment }
     * 
     */
    public Enrollment createEnrollment() {
        return new Enrollment();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link DoTestResponse }
     * 
     */
    public DoTestResponse createDoTestResponse() {
        return new DoTestResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoTest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "doTest")
    public JAXBElement<DoTest> createDoTest(DoTest value) {
        return new JAXBElement<DoTest>(_DoTest_QNAME, DoTest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Class }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "class")
    public JAXBElement<Class> createClass(Class value) {
        return new JAXBElement<Class>(_Class_QNAME, Class.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoTestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "doTestResponse")
    public JAXBElement<DoTestResponse> createDoTestResponse(DoTestResponse value) {
        return new JAXBElement<DoTestResponse>(_DoTestResponse_QNAME, DoTestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Enrollment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "enrollment")
    public JAXBElement<Enrollment> createEnrollment(Enrollment value) {
        return new JAXBElement<Enrollment>(_Enrollment_QNAME, Enrollment.class, null, value);
    }

}
