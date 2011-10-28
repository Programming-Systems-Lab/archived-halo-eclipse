from django.http import HttpResponse, Http404, HttpResponseRedirect
from django.shortcuts import render_to_response#, context_instance
from django import forms
from django.template.context import RequestContext
from login.models import HUser, Enrollment, Course, Studentassignment, Quest, Task
from halo_admin.util import contains_course
from halo_web.models import Questprogress
from datetime import date, timedelta

def dashboard(request):
    user = request.user
    if (user.is_authenticated()):
        u_id = user.getUserId()
        enrollment = Enrollment.objects.filter(user_id=u_id)
        admin_enrollment = enrollment.filter(type="ADMIN")
        admin_courses = []
        assignment_list = []
        quest_list = []
        task_list = []
        for enr in admin_enrollment:
            course = Course.objects.get(id=enr.getCourseId())
            admin_courses.append(course)
            for course in admin_courses:
                
                asmtset = Studentassignment.objects.filter(course_id = course.id)
                assignment_list.append([course.id, asmtset])

                for asmt in asmtset:
                    questset = Quest.objects.filter(assignment_id = asmt.id)
                    quest_list.append([asmt.id, questset])

                    for quest in questset:
                        taskset = Task.objects.filter(quest_id = quest.id)
                        task_list.append([quest.id, taskset])
     
                   
     #   print admin_courses
     #   print assignment_list
     #   print quest_list
     #   print task_list
  #  course_select = AdminSelectForm(admin_courses)
    else:
        enrollment = None
        admin_courses = None
        assignment_list = None
        quest_list = None
        task_list = None
    
    data1 = (1,2,3)
    
    return render_to_response('halo_admin/dashboard.html', {'user': user, 'enrollment': enrollment, 'admin_courses': admin_courses, 'assignment_list': assignment_list, 'quest_list': quest_list, 'task_list': task_list, 'data1': data1}, context_instance = RequestContext(request))

def course_admin(request, course_id):
    user = request.user
    this_course = Course.objects.get(id=course_id)

    if (user.is_authenticated()):
        u_id = user.getUserId()
        my_enrollment = Enrollment.objects.filter(user_id=u_id)
        admin_enrollment = my_enrollment.filter(type="ADMIN")
        admin_courses = []
        for enr in admin_enrollment:
            course = Course.objects.get(id=enr.getCourseId())
            admin_courses.append(course)
        admin_this_course = contains_course(admin_courses, course_id)

        assignments = Studentassignment.objects.filter(course_id=course_id)
        quests = []
        for asmt in assignments:
            asmt_quests = Quest.objects.filter(assignment_id = asmt.id)
            for q in asmt_quests:
                quests.append(q)
        course_enrollment = Enrollment.objects.filter(course_id = course_id).filter(type="STUDENT")
        tasks = []
        for q in quests:
            q_tasks = Task.objects.filter(quest_id = q.id)
            for qt in q_tasks:
                tasks.append(qt)
        
        data = []
        students = []
        for enr in course_enrollment:
            stu_id = enr.user_id
            enddate = date.today()
            startdate = enddate - timedelta(days=7)
            stu_progress = Questprogress.objects.filter(user_id=stu_id).filter(completed=1).filter(updated__range=[startdate, enddate])
            data.append(len(stu_progress))
            students.append(HUser.objects.get(id=stu_id).lastname)
        data_max = max(data)
    else:
        my_enrollment = None
        admin_courses = None
        admin_this_course = False
        data_max = None
        tasks = None
        quests = None
        students = None
        data = None
        course_enrollment = None
        
    return render_to_response('halo_admin/course_admin.html', {'user': user, 'enrollment': my_enrollment, 'data_max': data_max, 'assignments':assignments, 'tasks': tasks, 'quests': quests,'data': data, 'students': students, 'course_enrollment': course_enrollment, 'this_course': this_course, 'admin_courses': admin_courses, 'admin_this_course': admin_this_course}, context_instance=RequestContext(request))

#class AdminSelectForm(forms.Form):
#    course = forms.CharField(max_length=100, choices = admin_courses)

def assignment_admin(request, course_id):
    user = request.user
    this_course = Course.objects.get(id=course_id)
    
    if (user.is_authenticated()):
        u_id = user.getUserId()
        enrollment = Enrollment.objects.filter(user_id=u_id)
        admin_enrollment = enrollment.filter(type="ADMIN")
        admin_courses = []
        for enr in admin_enrollment:
            course = Course.objects.get(id=enr.getCourseId())
            admin_courses.append(course)
        admin_this_course = contains_course(admin_courses, course_id)
        
        assignment_list = Studentassignment.objects.filter(course_id=course_id)
        print assignment_list
    else:
        enrollment = None
        admin_courses = None
        admin_this_course = False
        assignment_list = None
    return render_to_response('halo_admin/assignment_admin.html', {'user': user, 'enrollment': enrollment, 'this_course': this_course, 'admin_courses': admin_courses, 'admin_this_course': admin_this_course, 'assignment_list': assignment_list}, context_instance=RequestContext(request))

def quest_admin(request, course_id, assignment_id):
    user = request.user
    this_course = Course.objects.get(id=course_id)
    
    if (user.is_authenticated()):
        u_id = user.getUserId()
        enrollment = Enrollment.objects.filter(user_id=u_id)
        admin_enrollment = enrollment.filter(type="ADMIN")
        admin_courses = []
        for enr in admin_enrollment:
            course = Course.objects.get(id=enr.getCourseId())
            admin_courses.append(course)
        admin_this_course = contains_course(admin_courses, course_id)
        this_assignment = Studentassignment.objects.get(id=assignment_id)
        quest_list = Quest.objects.filter(assignment_id=assignment_id)
        print quest_list
    else:
        enrollment = None
        admin_courses = None
        admin_this_course = False
        quest_list = None
    return render_to_response('halo_admin/quest_admin.html', {'user': user, 'enrollment': enrollment, 'this_course': this_course, 'this_assignment': this_assignment, 'admin_courses': admin_courses, 'admin_this_course': admin_this_course, 'quest_list': quest_list}, context_instance=RequestContext(request))

def task_admin(request, course_id, assignment_id, quest_id):
    user = request.user
    this_course = Course.objects.get(id=course_id)
    
    if (user.is_authenticated()):
        u_id = user.getUserId()
        enrollment = Enrollment.objects.filter(user_id=u_id)
        admin_enrollment = enrollment.filter(type="ADMIN")
        admin_courses = []
        for enr in admin_enrollment:
            course = Course.objects.get(id=enr.getCourseId())
            admin_courses.append(course)
        admin_this_course = contains_course(admin_courses, course_id)
        this_assignment = Studentassignment.objects.get(id=assignment_id)
        this_quest = Quest.objects.get(id=quest_id)
        task_list = Task.objects.filter(quest_id=quest_id)
        task_list_range = range(len(task_list))
    else:
        enrollment = None
        admin_courses = None
        admin_this_course = False
        task_list = None
    return render_to_response('halo_admin/task_admin.html', {'user': user, 'enrollment': enrollment, 'this_course': this_course, 'this_assignment': this_assignment, 'this_quest': this_quest, 'admin_courses': admin_courses, 'admin_this_course': admin_this_course, 'task_list': task_list, 'task_list_range': task_list_range}, context_instance=RequestContext(request))

#def add_quest2(request, course_id=None, assignment_id=None, parent_quest_id=None):
#    user = request.user
#    this_course = Course.objects.get(id=course_id)
#    
#    if (user.is_authenticated()):
#        u_id = user.getUserId()
#        enrollment = Enrollment.objects.filter(user_id=u_id)
#        admin_enrollment = enrollment.filter(type="ADMIN")
#        admin_courses = []
#        for enr in admin_enrollment:
#            course = Course.objects.get(id=enr.getCourseId())
#            admin_courses.append(course)
#        admin_this_course = contains_course(admin_courses, course_id)
#        
#    else:
#        admin_this_course = False
#    if request.method == 'POST':
#        form = AddQuestForm2(request.user, course_id, assignment_id, parent_quest_id, request.POST)
#        if form.is_valid():
#            new_quest = Quest(name=form.cleaned_data['name'], description=form.cleaned_data['description'], assignment_id = assignment_id, parent_id = parent_quest_id, experiencepoints = form.cleaned_data['experiencePoints'])
#            new_quest.save()
#            print new_quest.id
#            return HttpResponseRedirect('/admin/add_quest')
#    else:
#        form = AddQuestForm2(request.user, courses, assignments, quests)
#    return render_to_response('halo_admin/add_quest.html', {'user': user, 'admin_this_course': admin_this_course, 'form': form}, context_instance = RequestContext(request))


def add_quest(request,course_id, assignment_id, parent_quest_id=None):
    user = request.user
    this_course = Course.objects.get(id=course_id)
    
    if (user.is_authenticated()):
        u_id = user.getUserId()
        enrollment = Enrollment.objects.filter(user_id=u_id)
        admin_enrollment = enrollment.filter(type="ADMIN")
        admin_courses = []
        for enr in admin_enrollment:
            course = Course.objects.get(id=enr.getCourseId())
            admin_courses.append(course)
        admin_this_course = contains_course(admin_courses, course_id)
        
        quest_list = Quest.objects.filter(assignment_id=assignment_id)

    else:
        admin_this_course = False
    if request.method == 'POST':
        form = AddQuestForm(request.user, course_id, assignment_id, parent_quest_id, request.POST)
        if form.is_valid():
            new_quest = Quest(name=form.cleaned_data['name'], description=form.cleaned_data['description'], assignment_id = assignment_id, parent_id = parent_quest_id, experiencepoints = form.cleaned_data['experiencePoints'])
            new_quest.save()
            print new_quest.id
            return HttpResponseRedirect('/admin/'+str(course_id)+'/'+str(assignment_id)+'/quests')
    else:
        form = AddQuestForm(request.user, course_id, assignment_id, parent_quest_id)
    return render_to_response('halo_admin/add_quest.html', {'user': user, 'admin_this_course': admin_this_course, 'form': form}, context_instance = RequestContext(request))

class AddQuestForm(forms.Form):
    def __init__(self, user, course_id, assignment_id, parent_quest_id, *args, **kwargs):
        super(AddQuestForm, self).__init__(*args, **kwargs)
    
    name = forms.CharField(max_length=150)
    description = forms.CharField(widget=forms.Textarea)
    experiencePoints = forms.IntegerField()
    
#    
#class AddQuestForm2(forms.Form):
#    def __init__(self, user, courses, assignments, quests, *args, **kwargs):
#        super(AddQuestForm2, self).__init__(*args, **kwargs)
#    courses = forms.Select(choices=courses)
#    assignment = forms.Select(choices = assignments)
#    name = forms.CharField(max_length=150)
#    description = forms.CharField(widget=forms.Textarea)
#    experiencePoints = forms.IntegerField()
#        
class AddAssignmentForm(forms.Form):
    def __init__(self, user, course_id, *args, **kwargs):
        super(AddAssignmentForm, self).__init__(*args, **kwargs)
    assignedon = forms.DateTimeField(widget=forms.DateTimeInput)
    description = forms.CharField(widget=forms.Textarea)
    dueon = forms.DateTimeField(widget=forms.DateTimeInput)
    title = forms.CharField()
        
def add_assignment(request, course_id):
    user = request.user
    this_course = Course.objects.get(id=course_id)
    
    if (user.is_authenticated()):
        u_id = user.getUserId()
        enrollment = Enrollment.objects.filter(user_id=u_id)
        admin_enrollment = enrollment.filter(type="ADMIN")
        admin_courses = []
        for enr in admin_enrollment:
            course = Course.objects.get(id=enr.getCourseId())
            admin_courses.append(course)
        admin_this_course = contains_course(admin_courses, course_id)
        
        asmt_list = Studentassignment.objects.filter(course_id=course_id)

    else:
        admin_this_course = False
    if request.method == 'POST':
        form = AddAssignmentForm(request.user, course_id, request.POST)
        if form.is_valid():
            new_asmt = Studentassignment(assignedon=form.cleaned_data['assignedon'], description=form.cleaned_data['description'], dueon = form.cleaned_data['dueon'], title = form.cleaned_data['title'], course_id = course_id)
            new_asmt.save()
            print new_asmt.id
            return HttpResponseRedirect('/admin/'+str(course_id))
    else:
        form = AddAssignmentForm(request.user, course_id)
    return render_to_response('halo_admin/add_assignment.html', {'user': user, 'admin_this_course': admin_this_course, 'form': form}, context_instance = RequestContext(request))

def add_task(request,course_id, assignment_id, quest_id, parent_task_id=None):
    user = request.user
    this_course = Course.objects.get(id=course_id)
    
    if (user.is_authenticated()):
        u_id = user.getUserId()
        enrollment = Enrollment.objects.filter(user_id=u_id)
        admin_enrollment = enrollment.filter(type="ADMIN")
        admin_courses = []
        for enr in admin_enrollment:
            course = Course.objects.get(id=enr.getCourseId())
            admin_courses.append(course)
        admin_this_course = contains_course(admin_courses, course_id)
        this_assignment = Studentassignment.objects.get(id=assignment_id)
        this_quest = Quest.objects.get(id=quest_id)


    else:
        admin_this_course = False
    if request.method == 'POST':
        form = AddTaskForm(request.user, course_id, assignment_id, quest_id, parent_task_id, request.POST)
        if form.is_valid():
            new_task = Task(name=form.cleaned_data['name'],description=form.cleaned_data['description'], quest_id = quest_id, parent_id = parent_task_id)
            new_task.save()
            print new_task.id
            return HttpResponseRedirect('/admin/'+str(course_id)+'/'+str(assignment_id)+'/'+str(quest_id)+'/tasks')
    else:
        form = AddTaskForm(request.user, course_id, assignment_id, quest_id, parent_task_id)
    return render_to_response('halo_admin/add_task.html', {'user': user, 'admin_this_course': admin_this_course, 'form': form}, context_instance = RequestContext(request))

class AddTaskForm(forms.Form):
    def __init__(self, user, course_id, assignment_id, quest_id, parent_task_id, *args, **kwargs):
        super(AddTaskForm, self).__init__(*args, **kwargs)
    
    name = forms.CharField(max_length=150)
    description = forms.CharField(widget=forms.Textarea)
    
def student_list(request, course_id, focus_id = None):
    user = request.user
    this_course = Course.objects.get(id=course_id)
    if (focus_id):
        focus_user = HUser.objects.get(id=focus_id)
        focus = {'name': focus_user.getName(), 'email': focus_user.getEmail(), 'id': focus_user.getUserId()}
        focus_data = []
        focus_labels = []
        focus_max = 0
        for i in range(30):
            startdate = date.today() - timedelta(days=(31-i))
            enddate = date.today() - timedelta(days=(30-i))
            stu_progress = Questprogress.objects.filter(user_id=focus['id']).filter(completed=1).filter(updated__range=[startdate, enddate])
            focus_max = max(focus_max, len(stu_progress))
            focus_data.append(len(stu_progress))
            if (i % 7 == 0):
                focus_labels.append(enddate)
        
        print focus_data
        print focus_max
            
    else: 
        focus = None
        focus_data = None
        focus_max = None
        focus_labels = None
    if (user.is_authenticated()):
        u_id = user.getUserId()
        my_enrollment = Enrollment.objects.filter(user_id=u_id)
        admin_enrollment = my_enrollment.filter(type="ADMIN")
        admin_courses = []
        for enr in admin_enrollment:
            course = Course.objects.get(id=enr.getCourseId())
            admin_courses.append(course)
        admin_this_course = contains_course(admin_courses, course_id)
        students = []
        course_enrollment = Enrollment.objects.filter(course_id = course_id)
        for enr in course_enrollment:
            stud = HUser.objects.get(id=enr.user_id)
            stud_data = {'name': stud.getName(), 'email': stud.getEmail(), 'id': stud.getUserId()}
            students.append(stud_data)
    else:
        my_enrollment = None
        admin_courses = None
        admin_this_course = False
        data_max = None
        tasks = None
        quests = None
        students = None
        data = None
        course_enrollment = None
        
    return render_to_response('halo_admin/student_list.html', {'course_id': course_id, 'user': user, 'focus': focus, 'focus_data': focus_data, 'focus_range': range(focus_max+1), 'focus_labels': focus_labels, 'admin_this_course': admin_this_course,'students': students}, context_instance=RequestContext(request))

    