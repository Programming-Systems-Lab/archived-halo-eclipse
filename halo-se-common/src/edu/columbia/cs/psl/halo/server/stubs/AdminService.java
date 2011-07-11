
package edu.columbia.cs.psl.halo.server.stubs;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
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
@WebService(name = "AdminService", targetNamespace = "http://server.halo.psl.cs.columbia.edu/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AdminService {


    /**
     * 
     * @param arg0
     * @return
     *     returns edu.columbia.cs.psl.halo.server.stubs.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUser", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetUser")
    @ResponseWrapper(localName = "getUserResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetUserResponse")
    public User getUser(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns edu.columbia.cs.psl.halo.server.stubs.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "updateUser", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.UpdateUser")
    @ResponseWrapper(localName = "updateUserResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.UpdateUserResponse")
    public User updateUser(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0);

    /**
     * 
     * @return
     *     returns java.util.List<edu.columbia.cs.psl.halo.server.stubs.Course>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getCourses", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetCourses")
    @ResponseWrapper(localName = "getCoursesResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetCoursesResponse")
    public List<Course> getCourses();

    /**
     * 
     * @return
     *     returns java.util.List<edu.columbia.cs.psl.halo.server.stubs.User>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUsers", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetUsers")
    @ResponseWrapper(localName = "getUsersResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetUsersResponse")
    public List<User> getUsers();

    /**
     * 
     * @param arg0
     * @return
     *     returns edu.columbia.cs.psl.halo.server.stubs.Course
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createCourse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.CreateCourse")
    @ResponseWrapper(localName = "createCourseResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.CreateCourseResponse")
    public Course createCourse(
        @WebParam(name = "arg0", targetNamespace = "")
        Course arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns edu.columbia.cs.psl.halo.server.stubs.Course
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "updateCourse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.UpdateCourse")
    @ResponseWrapper(localName = "updateCourseResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.UpdateCourseResponse")
    public Course updateCourse(
        @WebParam(name = "arg0", targetNamespace = "")
        Course arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteCourse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.DeleteCourse")
    @ResponseWrapper(localName = "deleteCourseResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.DeleteCourseResponse")
    public boolean deleteCourse(
        @WebParam(name = "arg0", targetNamespace = "")
        Course arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns edu.columbia.cs.psl.halo.server.stubs.Enrollment
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createEnrollment", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.CreateEnrollment")
    @ResponseWrapper(localName = "createEnrollmentResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.CreateEnrollmentResponse")
    public Enrollment createEnrollment(
        @WebParam(name = "arg0", targetNamespace = "")
        Enrollment arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns edu.columbia.cs.psl.halo.server.stubs.Enrollment
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "updateEnrollment", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.UpdateEnrollment")
    @ResponseWrapper(localName = "updateEnrollmentResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.UpdateEnrollmentResponse")
    public Enrollment updateEnrollment(
        @WebParam(name = "arg0", targetNamespace = "")
        Enrollment arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteEnrollment", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.DeleteEnrollment")
    @ResponseWrapper(localName = "deleteEnrollmentResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.DeleteEnrollmentResponse")
    public boolean deleteEnrollment(
        @WebParam(name = "arg0", targetNamespace = "")
        Enrollment arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns edu.columbia.cs.psl.halo.server.stubs.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createUser", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.CreateUser")
    @ResponseWrapper(localName = "createUserResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.CreateUserResponse")
    public User createUser(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteUser", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.DeleteUser")
    @ResponseWrapper(localName = "deleteUserResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.DeleteUserResponse")
    public boolean deleteUser(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<edu.columbia.cs.psl.halo.server.stubs.Enrollment>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getEnrollmentsFor", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetEnrollmentsFor")
    @ResponseWrapper(localName = "getEnrollmentsForResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetEnrollmentsForResponse")
    public List<Enrollment> getEnrollmentsFor(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns edu.columbia.cs.psl.halo.server.stubs.Course
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getCourse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetCourse")
    @ResponseWrapper(localName = "getCourseResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetCourseResponse")
    public Course getCourse(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

}
