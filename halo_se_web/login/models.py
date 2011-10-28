from django.db import models
import hashlib

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
    def __unicode__(self):
        return self.firstname + " " + self.lastname
    def getName(self):
        return self.firstname + " " + self.lastname
    def getPassword(self):
        return self.password
    def getEmail(self):
        return self.email
    def check_password(self,typedPassword):
        if (hashlib.sha1(typedPassword).hexdigest()==self.getPassword()):
            return True
        return False
    def getXP(self):
        return self.xp
    def getUserId(self):
        return self.id
    def is_active(self):
        return True     #we don't have a way to denote inactive users
    def is_authenticated(self):
        return True

class Enrollment(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    type = models.CharField(max_length=765, db_column='TYPE', blank=True) # Field name made lowercase.
    course_id = models.IntegerField(null=True, db_column='COURSE_ID', blank=True) # Field name made lowercase.
    user_id = models.IntegerField(null=True, db_column='USER_ID', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'enrollment'
    def __unicode__(self):
        return str(self.user_id)+" has "+str(self.type)+" access to "+str(self.course_id)
    def getType(self):
        return self.type
    def getCourseId(self):
        return self.course_id
    def getUserId(self):
        return self.user_id
    
class Course(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    name = models.CharField(max_length=765, db_column='NAME', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'course'
    def __unicode__(self):
        return self.name
    def getId(self):
        return self.id
    def getName(self):
        return self.name
    
    
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

class Studentassignment(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    assignedon = models.DateTimeField(null=True, db_column='ASSIGNEDON', blank=True) # Field name made lowercase.
    description = models.CharField(max_length=765, db_column='DESCRIPTION', blank=True) # Field name made lowercase.
    dueon = models.DateTimeField(null=True, db_column='DUEON', blank=True) # Field name made lowercase.
    title = models.CharField(max_length=765, db_column='TITLE', blank=True) # Field name made lowercase.
    course_id = models.IntegerField(null=True, db_column='COURSE_ID', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'studentassignment'
    def __unicode__(self):
        return self.title

class Task(models.Model):
    id = models.IntegerField(primary_key=True, db_column='ID') # Field name made lowercase.
    description = models.CharField(max_length=765, db_column='DESCRIPTION', blank=True) # Field name made lowercase.
    name = models.CharField(max_length=765, db_column='NAME', blank=True) # Field name made lowercase.
    type = models.CharField(max_length=765, db_column='TYPE', blank=True) # Field name made lowercase.
    parent_id = models.IntegerField(null=True, db_column='PARENT_ID', blank=True) # Field name made lowercase.
    quest_id = models.IntegerField(null=True, db_column='QUEST_ID', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'task'