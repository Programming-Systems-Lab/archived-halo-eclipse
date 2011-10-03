
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
@WebService(name = "UserService", targetNamespace = "http://server.halo.psl.cs.columbia.edu/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserService {


    /**
     * 
     * @param arg0
     * @return
     *     returns edu.columbia.cs.psl.halo.server.stubs.Level
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getLevel", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetLevel")
    @ResponseWrapper(localName = "getLevelResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetLevelResponse")
    public Level getLevel(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "setPassword", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.SetPassword")
    @ResponseWrapper(localName = "setPasswordResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.SetPasswordResponse")
    public void setPassword(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @return
     *     returns java.util.List<edu.columbia.cs.psl.halo.server.stubs.Enrollment>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getEnrollments", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetEnrollments")
    @ResponseWrapper(localName = "getEnrollmentsResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetEnrollmentsResponse")
    public List<Enrollment> getEnrollments();

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "markTaskCompleted", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.MarkTaskCompleted")
    @ResponseWrapper(localName = "markTaskCompletedResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.MarkTaskCompletedResponse")
    public void markTaskCompleted(
        @WebParam(name = "arg0", targetNamespace = "")
        Task arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<edu.columbia.cs.psl.halo.server.stubs.QuestProgress>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMyProgressFor", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetMyProgressFor")
    @ResponseWrapper(localName = "getMyProgressForResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetMyProgressForResponse")
    public List<QuestProgress> getMyProgressFor(
        @WebParam(name = "arg0", targetNamespace = "")
        Quest arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "setDefaultTitle", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.SetDefaultTitle")
    @ResponseWrapper(localName = "setDefaultTitleResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.SetDefaultTitleResponse")
    public boolean setDefaultTitle(
        @WebParam(name = "arg0", targetNamespace = "")
        Title arg0);

    /**
     * 
     * @return
     *     returns java.util.List<java.lang.Byte>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMyProfileImage", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetMyProfileImage")
    @ResponseWrapper(localName = "getMyProfileImageResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetMyProfileImageResponse")
    public List<Byte> getMyProfileImage();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.Byte>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getProfileImage", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetProfileImage")
    @ResponseWrapper(localName = "getProfileImageResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetProfileImageResponse")
    public List<Byte> getProfileImage(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "setProfileImage", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.SetProfileImage")
    @ResponseWrapper(localName = "setProfileImageResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.SetProfileImageResponse")
    public void setProfileImage(
        @WebParam(name = "arg0", targetNamespace = "")
        List<Byte> arg0);

    /**
     * 
     * @return
     *     returns java.util.List<edu.columbia.cs.psl.halo.server.stubs.Achievement>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllAchievements", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetAllAchievements")
    @ResponseWrapper(localName = "getAllAchievementsResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetAllAchievementsResponse")
    public List<Achievement> getAllAchievements();

    /**
     * 
     * @return
     *     returns java.util.List<edu.columbia.cs.psl.halo.server.stubs.Quest>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllQuests", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetAllQuests")
    @ResponseWrapper(localName = "getAllQuestsResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetAllQuestsResponse")
    public List<Quest> getAllQuests();

    /**
     * 
     * @return
     *     returns java.util.List<edu.columbia.cs.psl.halo.server.stubs.Title>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMyTitles", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetMyTitles")
    @ResponseWrapper(localName = "getMyTitlesResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetMyTitlesResponse")
    public List<Title> getMyTitles();

    /**
     * 
     * @return
     *     returns java.util.List<edu.columbia.cs.psl.halo.server.stubs.AchievementRecord>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMyAchievements", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetMyAchievements")
    @ResponseWrapper(localName = "getMyAchievementsResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetMyAchievementsResponse")
    public List<AchievementRecord> getMyAchievements();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<edu.columbia.cs.psl.halo.server.stubs.Assignment>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAssignmentsFor", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetAssignmentsFor")
    @ResponseWrapper(localName = "getAssignmentsForResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetAssignmentsForResponse")
    public List<Assignment> getAssignmentsFor(
        @WebParam(name = "arg0", targetNamespace = "")
        Course arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<edu.columbia.cs.psl.halo.server.stubs.Quest>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getQuestsFor", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetQuestsFor")
    @ResponseWrapper(localName = "getQuestsForResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetQuestsForResponse")
    public List<Quest> getQuestsFor(
        @WebParam(name = "arg0", targetNamespace = "")
        Assignment arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<edu.columbia.cs.psl.halo.server.stubs.Quest>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllQuestsFor", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetAllQuestsFor")
    @ResponseWrapper(localName = "getAllQuestsForResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetAllQuestsForResponse")
    public List<Quest> getAllQuestsFor(
        @WebParam(name = "arg0", targetNamespace = "")
        Assignment arg0);

    /**
     * 
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMaxAchievementPts", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetMaxAchievementPts")
    @ResponseWrapper(localName = "getMaxAchievementPtsResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetMaxAchievementPtsResponse")
    public int getMaxAchievementPts();

    /**
     * 
     * @return
     *     returns java.util.List<edu.columbia.cs.psl.halo.server.stubs.QuestProgress>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMyProgress", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetMyProgress")
    @ResponseWrapper(localName = "getMyProgressResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetMyProgressResponse")
    public List<QuestProgress> getMyProgress();

    /**
     * 
     * @return
     *     returns edu.columbia.cs.psl.halo.server.stubs.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMe", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetMe")
    @ResponseWrapper(localName = "getMeResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetMeResponse")
    public User getMe();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getRememberMeToken", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetRememberMeToken")
    @ResponseWrapper(localName = "getRememberMeTokenResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.GetRememberMeTokenResponse")
    public String getRememberMeToken();

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "postQuestCompletionToFacebook", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.PostQuestCompletionToFacebook")
    @ResponseWrapper(localName = "postQuestCompletionToFacebookResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.PostQuestCompletionToFacebookResponse")
    public boolean postQuestCompletionToFacebook(
        @WebParam(name = "arg0", targetNamespace = "")
        Quest arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "postTaskCompletionToFacebook", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.PostTaskCompletionToFacebook")
    @ResponseWrapper(localName = "postTaskCompletionToFacebookResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.PostTaskCompletionToFacebookResponse")
    public boolean postTaskCompletionToFacebook(
        @WebParam(name = "arg0", targetNamespace = "")
        Task arg0);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "setFBToken", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.SetFBToken")
    @ResponseWrapper(localName = "setFBTokenResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.SetFBTokenResponse")
    public void setFBToken(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "logoutOfFacebook", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.LogoutOfFacebook")
    @ResponseWrapper(localName = "logoutOfFacebookResponse", targetNamespace = "http://server.halo.psl.cs.columbia.edu/", className = "edu.columbia.cs.psl.halo.server.stubs.LogoutOfFacebookResponse")
    public void logoutOfFacebook();

}
