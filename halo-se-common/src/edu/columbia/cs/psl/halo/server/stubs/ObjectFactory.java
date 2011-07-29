
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

    private final static QName _GetQuestsFor_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getQuestsFor");
    private final static QName _GetMyAchievements_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getMyAchievements");
    private final static QName _GetProfileImageResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getProfileImageResponse");
    private final static QName _SetProfileImageResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "setProfileImageResponse");
    private final static QName _GetLevel_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getLevel");
    private final static QName _User_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "user");
    private final static QName _GetMyProgressResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getMyProgressResponse");
    private final static QName _GetRememberMeToken_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getRememberMeToken");
    private final static QName _SetDefaultTitleResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "setDefaultTitleResponse");
    private final static QName _SetProfileImage_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "setProfileImage");
    private final static QName _GetMeResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getMeResponse");
    private final static QName _GetEnrollments_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getEnrollments");
    private final static QName _MarkTaskCompleted_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "markTaskCompleted");
    private final static QName _GetAllAchievements_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getAllAchievements");
    private final static QName _GetMyProfileImage_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getMyProfileImage");
    private final static QName _GetMe_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getMe");
    private final static QName _SetDefaultTitle_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "setDefaultTitle");
    private final static QName _GetMyTitlesResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getMyTitlesResponse");
    private final static QName _GetAssignmentsForResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getAssignmentsForResponse");
    private final static QName _GetAllQuests_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getAllQuests");
    private final static QName _GetMyProgress_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getMyProgress");
    private final static QName _GetEnrollmentsResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getEnrollmentsResponse");
    private final static QName _GetMyAchievementsResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getMyAchievementsResponse");
    private final static QName _GetAssignmentsFor_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getAssignmentsFor");
    private final static QName _GetProfileImage_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getProfileImage");
    private final static QName _GetMyProgressForResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getMyProgressForResponse");
    private final static QName _Enrollment_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "enrollment");
    private final static QName _MarkTaskCompletedResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "markTaskCompletedResponse");
    private final static QName _GetMyProgressFor_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getMyProgressFor");
    private final static QName _GetAllAchievementsResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getAllAchievementsResponse");
    private final static QName _GetMyTitles_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getMyTitles");
    private final static QName _GetQuestsForResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getQuestsForResponse");
    private final static QName _GetLevelResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getLevelResponse");
    private final static QName _Course_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "course");
    private final static QName _GetAllQuestsResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getAllQuestsResponse");
    private final static QName _GetMyProfileImageResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getMyProfileImageResponse");
    private final static QName _GetRememberMeTokenResponse_QNAME = new QName("http://server.halo.psl.cs.columbia.edu/", "getRememberMeTokenResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.columbia.cs.psl.halo.server.stubs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllQuests }
     * 
     */
    public GetAllQuests createGetAllQuests() {
        return new GetAllQuests();
    }

    /**
     * Create an instance of {@link GetMyProgress }
     * 
     */
    public GetMyProgress createGetMyProgress() {
        return new GetMyProgress();
    }

    /**
     * Create an instance of {@link GetAssignmentsForResponse }
     * 
     */
    public GetAssignmentsForResponse createGetAssignmentsForResponse() {
        return new GetAssignmentsForResponse();
    }

    /**
     * Create an instance of {@link GetMyTitlesResponse }
     * 
     */
    public GetMyTitlesResponse createGetMyTitlesResponse() {
        return new GetMyTitlesResponse();
    }

    /**
     * Create an instance of {@link GetMe }
     * 
     */
    public GetMe createGetMe() {
        return new GetMe();
    }

    /**
     * Create an instance of {@link SetDefaultTitle }
     * 
     */
    public SetDefaultTitle createSetDefaultTitle() {
        return new SetDefaultTitle();
    }

    /**
     * Create an instance of {@link MarkTaskCompleted }
     * 
     */
    public MarkTaskCompleted createMarkTaskCompleted() {
        return new MarkTaskCompleted();
    }

    /**
     * Create an instance of {@link GetAllAchievements }
     * 
     */
    public GetAllAchievements createGetAllAchievements() {
        return new GetAllAchievements();
    }

    /**
     * Create an instance of {@link GetMyProfileImage }
     * 
     */
    public GetMyProfileImage createGetMyProfileImage() {
        return new GetMyProfileImage();
    }

    /**
     * Create an instance of {@link GetAllQuestsResponse }
     * 
     */
    public GetAllQuestsResponse createGetAllQuestsResponse() {
        return new GetAllQuestsResponse();
    }

    /**
     * Create an instance of {@link Course }
     * 
     */
    public Course createCourse() {
        return new Course();
    }

    /**
     * Create an instance of {@link GetMyProfileImageResponse }
     * 
     */
    public GetMyProfileImageResponse createGetMyProfileImageResponse() {
        return new GetMyProfileImageResponse();
    }

    /**
     * Create an instance of {@link GetRememberMeTokenResponse }
     * 
     */
    public GetRememberMeTokenResponse createGetRememberMeTokenResponse() {
        return new GetRememberMeTokenResponse();
    }

    /**
     * Create an instance of {@link GetAllAchievementsResponse }
     * 
     */
    public GetAllAchievementsResponse createGetAllAchievementsResponse() {
        return new GetAllAchievementsResponse();
    }

    /**
     * Create an instance of {@link GetMyTitles }
     * 
     */
    public GetMyTitles createGetMyTitles() {
        return new GetMyTitles();
    }

    /**
     * Create an instance of {@link GetQuestsForResponse }
     * 
     */
    public GetQuestsForResponse createGetQuestsForResponse() {
        return new GetQuestsForResponse();
    }

    /**
     * Create an instance of {@link GetLevelResponse }
     * 
     */
    public GetLevelResponse createGetLevelResponse() {
        return new GetLevelResponse();
    }

    /**
     * Create an instance of {@link GetMyProgressForResponse }
     * 
     */
    public GetMyProgressForResponse createGetMyProgressForResponse() {
        return new GetMyProgressForResponse();
    }

    /**
     * Create an instance of {@link Enrollment }
     * 
     */
    public Enrollment createEnrollment() {
        return new Enrollment();
    }

    /**
     * Create an instance of {@link MarkTaskCompletedResponse }
     * 
     */
    public MarkTaskCompletedResponse createMarkTaskCompletedResponse() {
        return new MarkTaskCompletedResponse();
    }

    /**
     * Create an instance of {@link GetMyProgressFor }
     * 
     */
    public GetMyProgressFor createGetMyProgressFor() {
        return new GetMyProgressFor();
    }

    /**
     * Create an instance of {@link GetEnrollmentsResponse }
     * 
     */
    public GetEnrollmentsResponse createGetEnrollmentsResponse() {
        return new GetEnrollmentsResponse();
    }

    /**
     * Create an instance of {@link GetMyAchievementsResponse }
     * 
     */
    public GetMyAchievementsResponse createGetMyAchievementsResponse() {
        return new GetMyAchievementsResponse();
    }

    /**
     * Create an instance of {@link GetAssignmentsFor }
     * 
     */
    public GetAssignmentsFor createGetAssignmentsFor() {
        return new GetAssignmentsFor();
    }

    /**
     * Create an instance of {@link GetProfileImage }
     * 
     */
    public GetProfileImage createGetProfileImage() {
        return new GetProfileImage();
    }

    /**
     * Create an instance of {@link GetProfileImageResponse }
     * 
     */
    public GetProfileImageResponse createGetProfileImageResponse() {
        return new GetProfileImageResponse();
    }

    /**
     * Create an instance of {@link SetProfileImageResponse }
     * 
     */
    public SetProfileImageResponse createSetProfileImageResponse() {
        return new SetProfileImageResponse();
    }

    /**
     * Create an instance of {@link GetQuestsFor }
     * 
     */
    public GetQuestsFor createGetQuestsFor() {
        return new GetQuestsFor();
    }

    /**
     * Create an instance of {@link GetMyAchievements }
     * 
     */
    public GetMyAchievements createGetMyAchievements() {
        return new GetMyAchievements();
    }

    /**
     * Create an instance of {@link GetMeResponse }
     * 
     */
    public GetMeResponse createGetMeResponse() {
        return new GetMeResponse();
    }

    /**
     * Create an instance of {@link GetEnrollments }
     * 
     */
    public GetEnrollments createGetEnrollments() {
        return new GetEnrollments();
    }

    /**
     * Create an instance of {@link SetDefaultTitleResponse }
     * 
     */
    public SetDefaultTitleResponse createSetDefaultTitleResponse() {
        return new SetDefaultTitleResponse();
    }

    /**
     * Create an instance of {@link GetRememberMeToken }
     * 
     */
    public GetRememberMeToken createGetRememberMeToken() {
        return new GetRememberMeToken();
    }

    /**
     * Create an instance of {@link SetProfileImage }
     * 
     */
    public SetProfileImage createSetProfileImage() {
        return new SetProfileImage();
    }

    /**
     * Create an instance of {@link GetLevel }
     * 
     */
    public GetLevel createGetLevel() {
        return new GetLevel();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link GetMyProgressResponse }
     * 
     */
    public GetMyProgressResponse createGetMyProgressResponse() {
        return new GetMyProgressResponse();
    }

    /**
     * Create an instance of {@link Level }
     * 
     */
    public Level createLevel() {
        return new Level();
    }

    /**
     * Create an instance of {@link Achievement }
     * 
     */
    public Achievement createAchievement() {
        return new Achievement();
    }

    /**
     * Create an instance of {@link AchievementRecord }
     * 
     */
    public AchievementRecord createAchievementRecord() {
        return new AchievementRecord();
    }

    /**
     * Create an instance of {@link Title }
     * 
     */
    public Title createTitle() {
        return new Title();
    }

    /**
     * Create an instance of {@link QuestProgress }
     * 
     */
    public QuestProgress createQuestProgress() {
        return new QuestProgress();
    }

    /**
     * Create an instance of {@link Task }
     * 
     */
    public Task createTask() {
        return new Task();
    }

    /**
     * Create an instance of {@link Quest }
     * 
     */
    public Quest createQuest() {
        return new Quest();
    }

    /**
     * Create an instance of {@link Assignment }
     * 
     */
    public Assignment createAssignment() {
        return new Assignment();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestsFor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getQuestsFor")
    public JAXBElement<GetQuestsFor> createGetQuestsFor(GetQuestsFor value) {
        return new JAXBElement<GetQuestsFor>(_GetQuestsFor_QNAME, GetQuestsFor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMyAchievements }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getMyAchievements")
    public JAXBElement<GetMyAchievements> createGetMyAchievements(GetMyAchievements value) {
        return new JAXBElement<GetMyAchievements>(_GetMyAchievements_QNAME, GetMyAchievements.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProfileImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getProfileImageResponse")
    public JAXBElement<GetProfileImageResponse> createGetProfileImageResponse(GetProfileImageResponse value) {
        return new JAXBElement<GetProfileImageResponse>(_GetProfileImageResponse_QNAME, GetProfileImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetProfileImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "setProfileImageResponse")
    public JAXBElement<SetProfileImageResponse> createSetProfileImageResponse(SetProfileImageResponse value) {
        return new JAXBElement<SetProfileImageResponse>(_SetProfileImageResponse_QNAME, SetProfileImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLevel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getLevel")
    public JAXBElement<GetLevel> createGetLevel(GetLevel value) {
        return new JAXBElement<GetLevel>(_GetLevel_QNAME, GetLevel.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMyProgressResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getMyProgressResponse")
    public JAXBElement<GetMyProgressResponse> createGetMyProgressResponse(GetMyProgressResponse value) {
        return new JAXBElement<GetMyProgressResponse>(_GetMyProgressResponse_QNAME, GetMyProgressResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRememberMeToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getRememberMeToken")
    public JAXBElement<GetRememberMeToken> createGetRememberMeToken(GetRememberMeToken value) {
        return new JAXBElement<GetRememberMeToken>(_GetRememberMeToken_QNAME, GetRememberMeToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetDefaultTitleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "setDefaultTitleResponse")
    public JAXBElement<SetDefaultTitleResponse> createSetDefaultTitleResponse(SetDefaultTitleResponse value) {
        return new JAXBElement<SetDefaultTitleResponse>(_SetDefaultTitleResponse_QNAME, SetDefaultTitleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetProfileImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "setProfileImage")
    public JAXBElement<SetProfileImage> createSetProfileImage(SetProfileImage value) {
        return new JAXBElement<SetProfileImage>(_SetProfileImage_QNAME, SetProfileImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getMeResponse")
    public JAXBElement<GetMeResponse> createGetMeResponse(GetMeResponse value) {
        return new JAXBElement<GetMeResponse>(_GetMeResponse_QNAME, GetMeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEnrollments }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getEnrollments")
    public JAXBElement<GetEnrollments> createGetEnrollments(GetEnrollments value) {
        return new JAXBElement<GetEnrollments>(_GetEnrollments_QNAME, GetEnrollments.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MarkTaskCompleted }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "markTaskCompleted")
    public JAXBElement<MarkTaskCompleted> createMarkTaskCompleted(MarkTaskCompleted value) {
        return new JAXBElement<MarkTaskCompleted>(_MarkTaskCompleted_QNAME, MarkTaskCompleted.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllAchievements }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getAllAchievements")
    public JAXBElement<GetAllAchievements> createGetAllAchievements(GetAllAchievements value) {
        return new JAXBElement<GetAllAchievements>(_GetAllAchievements_QNAME, GetAllAchievements.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMyProfileImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getMyProfileImage")
    public JAXBElement<GetMyProfileImage> createGetMyProfileImage(GetMyProfileImage value) {
        return new JAXBElement<GetMyProfileImage>(_GetMyProfileImage_QNAME, GetMyProfileImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getMe")
    public JAXBElement<GetMe> createGetMe(GetMe value) {
        return new JAXBElement<GetMe>(_GetMe_QNAME, GetMe.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetDefaultTitle }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "setDefaultTitle")
    public JAXBElement<SetDefaultTitle> createSetDefaultTitle(SetDefaultTitle value) {
        return new JAXBElement<SetDefaultTitle>(_SetDefaultTitle_QNAME, SetDefaultTitle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMyTitlesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getMyTitlesResponse")
    public JAXBElement<GetMyTitlesResponse> createGetMyTitlesResponse(GetMyTitlesResponse value) {
        return new JAXBElement<GetMyTitlesResponse>(_GetMyTitlesResponse_QNAME, GetMyTitlesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAssignmentsForResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getAssignmentsForResponse")
    public JAXBElement<GetAssignmentsForResponse> createGetAssignmentsForResponse(GetAssignmentsForResponse value) {
        return new JAXBElement<GetAssignmentsForResponse>(_GetAssignmentsForResponse_QNAME, GetAssignmentsForResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllQuests }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getAllQuests")
    public JAXBElement<GetAllQuests> createGetAllQuests(GetAllQuests value) {
        return new JAXBElement<GetAllQuests>(_GetAllQuests_QNAME, GetAllQuests.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMyProgress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getMyProgress")
    public JAXBElement<GetMyProgress> createGetMyProgress(GetMyProgress value) {
        return new JAXBElement<GetMyProgress>(_GetMyProgress_QNAME, GetMyProgress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEnrollmentsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getEnrollmentsResponse")
    public JAXBElement<GetEnrollmentsResponse> createGetEnrollmentsResponse(GetEnrollmentsResponse value) {
        return new JAXBElement<GetEnrollmentsResponse>(_GetEnrollmentsResponse_QNAME, GetEnrollmentsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMyAchievementsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getMyAchievementsResponse")
    public JAXBElement<GetMyAchievementsResponse> createGetMyAchievementsResponse(GetMyAchievementsResponse value) {
        return new JAXBElement<GetMyAchievementsResponse>(_GetMyAchievementsResponse_QNAME, GetMyAchievementsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAssignmentsFor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getAssignmentsFor")
    public JAXBElement<GetAssignmentsFor> createGetAssignmentsFor(GetAssignmentsFor value) {
        return new JAXBElement<GetAssignmentsFor>(_GetAssignmentsFor_QNAME, GetAssignmentsFor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProfileImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getProfileImage")
    public JAXBElement<GetProfileImage> createGetProfileImage(GetProfileImage value) {
        return new JAXBElement<GetProfileImage>(_GetProfileImage_QNAME, GetProfileImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMyProgressForResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getMyProgressForResponse")
    public JAXBElement<GetMyProgressForResponse> createGetMyProgressForResponse(GetMyProgressForResponse value) {
        return new JAXBElement<GetMyProgressForResponse>(_GetMyProgressForResponse_QNAME, GetMyProgressForResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Enrollment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "enrollment")
    public JAXBElement<Enrollment> createEnrollment(Enrollment value) {
        return new JAXBElement<Enrollment>(_Enrollment_QNAME, Enrollment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MarkTaskCompletedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "markTaskCompletedResponse")
    public JAXBElement<MarkTaskCompletedResponse> createMarkTaskCompletedResponse(MarkTaskCompletedResponse value) {
        return new JAXBElement<MarkTaskCompletedResponse>(_MarkTaskCompletedResponse_QNAME, MarkTaskCompletedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMyProgressFor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getMyProgressFor")
    public JAXBElement<GetMyProgressFor> createGetMyProgressFor(GetMyProgressFor value) {
        return new JAXBElement<GetMyProgressFor>(_GetMyProgressFor_QNAME, GetMyProgressFor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllAchievementsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getAllAchievementsResponse")
    public JAXBElement<GetAllAchievementsResponse> createGetAllAchievementsResponse(GetAllAchievementsResponse value) {
        return new JAXBElement<GetAllAchievementsResponse>(_GetAllAchievementsResponse_QNAME, GetAllAchievementsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMyTitles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getMyTitles")
    public JAXBElement<GetMyTitles> createGetMyTitles(GetMyTitles value) {
        return new JAXBElement<GetMyTitles>(_GetMyTitles_QNAME, GetMyTitles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestsForResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getQuestsForResponse")
    public JAXBElement<GetQuestsForResponse> createGetQuestsForResponse(GetQuestsForResponse value) {
        return new JAXBElement<GetQuestsForResponse>(_GetQuestsForResponse_QNAME, GetQuestsForResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLevelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getLevelResponse")
    public JAXBElement<GetLevelResponse> createGetLevelResponse(GetLevelResponse value) {
        return new JAXBElement<GetLevelResponse>(_GetLevelResponse_QNAME, GetLevelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Course }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "course")
    public JAXBElement<Course> createCourse(Course value) {
        return new JAXBElement<Course>(_Course_QNAME, Course.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllQuestsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getAllQuestsResponse")
    public JAXBElement<GetAllQuestsResponse> createGetAllQuestsResponse(GetAllQuestsResponse value) {
        return new JAXBElement<GetAllQuestsResponse>(_GetAllQuestsResponse_QNAME, GetAllQuestsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMyProfileImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getMyProfileImageResponse")
    public JAXBElement<GetMyProfileImageResponse> createGetMyProfileImageResponse(GetMyProfileImageResponse value) {
        return new JAXBElement<GetMyProfileImageResponse>(_GetMyProfileImageResponse_QNAME, GetMyProfileImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRememberMeTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.halo.psl.cs.columbia.edu/", name = "getRememberMeTokenResponse")
    public JAXBElement<GetRememberMeTokenResponse> createGetRememberMeTokenResponse(GetRememberMeTokenResponse value) {
        return new JAXBElement<GetRememberMeTokenResponse>(_GetRememberMeTokenResponse_QNAME, GetRememberMeTokenResponse.class, null, value);
    }

}
