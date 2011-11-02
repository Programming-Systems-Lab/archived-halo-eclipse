# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#     * Rearrange models' order
#     * Make sure each model has one field with primary_key=True
# Feel free to rename the models, but don't rename db_table values or field names.
#
# Also note: You'll have to insert the output of 'django-admin.py sqlcustom [appname]'
# into your database.

from django.db import models

class Achievement(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    name = models.CharField(max_length=765, db_column='NAME', blank=True) # Field name made lowercase.
    points = models.IntegerField(null=True, db_column='POINTS', blank=True) # Field name made lowercase.
    resulttitle_id = models.IntegerField(null=True, db_column='RESULTTITLE_ID', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'achievement'

class Achievementrecord(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    completiontime = models.DateTimeField(null=True, db_column='COMPLETIONTIME', blank=True) # Field name made lowercase.
    achievement_id = models.IntegerField(null=True, db_column='ACHIEVEMENT_ID', blank=True) # Field name made lowercase.
    user_id = models.IntegerField(null=True, db_column='USER_ID', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'achievementrecord'

class AuthGroup(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(unique=True, max_length=240)
    class Meta:
        db_table = u'auth_group'

class DjangoContentType(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=300)
    app_label = models.CharField(unique=True, max_length=255)
    model = models.CharField(unique=True, max_length=255)
    class Meta:
        db_table = u'django_content_type'

class AuthPermission(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=150)
    content_type = models.ForeignKey(DjangoContentType)
    codename = models.CharField(unique=True, max_length=255)
    class Meta:
        db_table = u'auth_permission'

class AuthGroupPermissions(models.Model):
    id = models.IntegerField(primary_key=True)
    group = models.ForeignKey(AuthGroup)
    permission = models.ForeignKey(AuthPermission)
    class Meta:
        db_table = u'auth_group_permissions'

class AuthUser(models.Model):
    id = models.IntegerField(primary_key=True)
    username = models.CharField(unique=True, max_length=90)
    first_name = models.CharField(max_length=90)
    last_name = models.CharField(max_length=90)
    email = models.CharField(max_length=225)
    password = models.CharField(max_length=384)
    is_staff = models.IntegerField()
    is_active = models.IntegerField()
    is_superuser = models.IntegerField()
    last_login = models.DateTimeField()
    date_joined = models.DateTimeField()
    class Meta:
        db_table = u'auth_user'

class AuthMessage(models.Model):
    id = models.IntegerField(primary_key=True)
    user = models.ForeignKey(AuthUser)
    message = models.TextField()
    class Meta:
        db_table = u'auth_message'

class AuthUserGroups(models.Model):
    id = models.IntegerField(primary_key=True)
    user = models.ForeignKey(AuthUser)
    group = models.ForeignKey(AuthGroup)
    class Meta:
        db_table = u'auth_user_groups'

class AuthUserUserPermissions(models.Model):
    id = models.IntegerField(primary_key=True)
    user = models.ForeignKey(AuthUser)
    permission = models.ForeignKey(AuthPermission)
    class Meta:
        db_table = u'auth_user_user_permissions'

class Causualrelation(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    achievementcause_id = models.IntegerField(null=True, db_column='ACHIEVEMENTCAUSE_ID', blank=True) # Field name made lowercase.
    achievementresult_id = models.IntegerField(null=True, db_column='ACHIEVEMENTRESULT_ID', blank=True) # Field name made lowercase.
    taskcause_id = models.IntegerField(null=True, db_column='TASKCAUSE_ID', blank=True) # Field name made lowercase.
    taskresult_id = models.IntegerField(null=True, db_column='TASKRESULT_ID', blank=True) # Field name made lowercase.
    cardinality = models.IntegerField(null=True, db_column='CARDINALITY', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'causualrelation'

class Course(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    name = models.CharField(max_length=765, db_column='NAME', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'course'

class DjangoAdminLog(models.Model):
    id = models.IntegerField(primary_key=True)
    action_time = models.DateTimeField()
    user = models.ForeignKey(AuthUser)
    content_type = models.ForeignKey(DjangoContentType, null=True, blank=True)
    object_id = models.TextField(blank=True)
    object_repr = models.CharField(max_length=600)
    action_flag = models.IntegerField()
    change_message = models.TextField()
    class Meta:
        db_table = u'django_admin_log'

class DjangoSession(models.Model):
    session_key = models.CharField(max_length=120, primary_key=True)
    session_data = models.TextField()
    expire_date = models.DateTimeField()
    class Meta:
        db_table = u'django_session'

class DjangoSite(models.Model):
    id = models.IntegerField(primary_key=True)
    domain = models.CharField(max_length=300)
    name = models.CharField(max_length=150)
    class Meta:
        db_table = u'django_site'

class Enrollment(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    type = models.CharField(max_length=765, db_column='TYPE', blank=True) # Field name made lowercase.
    course_id = models.IntegerField(null=True, db_column='COURSE_ID', blank=True) # Field name made lowercase.
    user_id = models.IntegerField(null=True, db_column='USER_ID', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'enrollment'

class HUser(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    achievementpoints = models.IntegerField(null=True, db_column='ACHIEVEMENTPOINTS', blank=True) # Field name made lowercase.
    email = models.CharField(max_length=765, db_column='EMAIL', blank=True) # Field name made lowercase.
    firstname = models.CharField(max_length=765, db_column='FIRSTNAME', blank=True) # Field name made lowercase.
    lastname = models.CharField(max_length=765, db_column='LASTNAME', blank=True) # Field name made lowercase.
    password = models.CharField(max_length=765, db_column='PASSWORD', blank=True) # Field name made lowercase.
    status = models.CharField(max_length=765, db_column='STATUS', blank=True) # Field name made lowercase.
    thumbnail = models.TextField(db_column='THUMBNAIL', blank=True) # Field name made lowercase.
    xp = models.IntegerField(null=True, db_column='XP', blank=True) # Field name made lowercase.
    activetitle_id = models.IntegerField(null=True, db_column='ACTIVETITLE_ID', blank=True) # Field name made lowercase.
    level_id = models.IntegerField(null=True, db_column='LEVEL_ID', blank=True) # Field name made lowercase.
    rememberme = models.CharField(max_length=765, db_column='REMEMBERME', blank=True) # Field name made lowercase.
    remembermeexp = models.DateTimeField(null=True, blank=True)
    facebooksessionkey = models.CharField(max_length=765, db_column='FACEBOOKSESSIONKEY', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'h_user'

class HUserTitle(models.Model):
    user_id = models.IntegerField(primary_key=True, db_column='User_ID') # Field name made lowercase.
    titles_id = models.IntegerField(db_column='titles_ID') # Field name made lowercase.
    class Meta:
        db_table = u'h_user_title'

class Level(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    level = models.IntegerField(null=True, db_column='LEVEL', blank=True) # Field name made lowercase.
    xprequired = models.IntegerField(null=True, db_column='XPREQUIRED', blank=True) # Field name made lowercase.
    xpmax = models.IntegerField(null=True, db_column='XPMAX', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'level'

class Logaction(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    log_key = models.CharField(max_length=765, blank=True)
    class Meta:
        db_table = u'logaction'

class Logentry(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    createdat = models.DateTimeField(null=True, db_column='CREATEDAT', blank=True) # Field name made lowercase.
    ip = models.BigIntegerField(null=True, db_column='IP', blank=True) # Field name made lowercase.
    params = models.CharField(max_length=765, db_column='PARAMS', blank=True) # Field name made lowercase.
    action_id = models.IntegerField(null=True, db_column='ACTION_ID', blank=True) # Field name made lowercase.
    createdby_id = models.IntegerField(null=True, db_column='CREATEDBY_ID', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'logentry'

class PollsPoll(models.Model):
    id = models.IntegerField(primary_key=True)
    question = models.CharField(max_length=600)
    pub_date = models.DateTimeField()
    class Meta:
        db_table = u'polls_poll'
        
class PollsChoice(models.Model):
    id = models.IntegerField(primary_key=True)
    poll = models.ForeignKey(PollsPoll)
    choice = models.CharField(max_length=600)
    votes = models.IntegerField()
    class Meta:
        db_table = u'polls_choice'


class Quest(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    description = models.TextField(db_column='DESCRIPTION', blank=True) # Field name made lowercase.
    name = models.CharField(max_length=765, db_column='NAME', blank=True) # Field name made lowercase.
    assignment_id = models.IntegerField(null=True, db_column='ASSIGNMENT_ID', blank=True) # Field name made lowercase.
    parent_id = models.IntegerField(null=True, db_column='PARENT_ID', blank=True) # Field name made lowercase.
    experiencepoints = models.IntegerField(null=True, db_column='experiencePoints', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'quest'
    def __unicode__(self):
        return self.name

class Questprogress(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    completed = models.IntegerField(null=True, db_column='COMPLETED', blank=True) # Field name made lowercase.
    updated = models.DateTimeField(null=True, db_column='UPDATED', blank=True) # Field name made lowercase.
    quest_id = models.IntegerField(null=True, db_column='QUEST_ID', blank=True) # Field name made lowercase.
    task_id = models.IntegerField(null=True, db_column='TASK_ID', blank=True) # Field name made lowercase.
    user_id = models.IntegerField(null=True, db_column='USER_ID', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'questprogress'

class Studentasisgnment(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    assignedon = models.DateTimeField(null=True, db_column='ASSIGNEDON', blank=True) # Field name made lowercase.
    description = models.CharField(max_length=765, db_column='DESCRIPTION', blank=True) # Field name made lowercase.
    dueon = models.DateTimeField(null=True, db_column='DUEON', blank=True) # Field name made lowercase.
    title = models.CharField(max_length=765, db_column='TITLE', blank=True) # Field name made lowercase.
    course_id = models.IntegerField(null=True, db_column='COURSE_ID', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'studentasisgnment'

class Task(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    description = models.CharField(max_length=765, db_column='DESCRIPTION', blank=True) # Field name made lowercase.
    name = models.CharField(max_length=765, db_column='NAME', blank=True) # Field name made lowercase.
    type = models.CharField(max_length=765, db_column='TYPE', blank=True) # Field name made lowercase.
    parent_id = models.IntegerField(null=True, db_column='PARENT_ID', blank=True) # Field name made lowercase.
    quest_id = models.IntegerField(null=True, db_column='QUEST_ID', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'task'

class Title(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    position = models.CharField(max_length=765, db_column='POSITION', blank=True) # Field name made lowercase.
    title = models.CharField(max_length=765, db_column='TITLE', blank=True) # Field name made lowercase.
    comesfrom_id = models.IntegerField(null=True, db_column='COMESFROM_ID', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'title'