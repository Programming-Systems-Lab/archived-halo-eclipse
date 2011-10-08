from django.http import HttpResponse, Http404, HttpResponseRedirect
from django.shortcuts import render_to_response#, context_instance
from django import forms
from django.template.context import RequestContext
from login.models import HUser, Enrollment, Course, Studentassignment, Quest, Task
from halo_admin.util import contains_course

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
            print admin_courses
            for course in admin_courses:
                asmtset = Studentassignment.objects.filter(course_id = course.id)
                assignment_list.append([course.id, asmtset])

                for asmt in asmtset:
                    questset = Quest.objects.filter(assignment_id = asmt.id)
                    quest_list.append([asmt.id, questset])

                    for quest in questset:
                        taskset = Task.objects.filter(quest_id = quest.id)
                        task_list.append([quest.id, taskset])
                   
        print admin_courses
        print assignment_list
        print quest_list
        print task_list
  #  course_select = AdminSelectForm(admin_courses)
    else:
        enrollment = None
        admin_courses = None
        assignment_list = None
        quest_list = None
        task_list = None
    
    return render_to_response('halo_admin/dashboard.html', {'user': user, 'enrollment': enrollment, 'admin_courses': admin_courses, 'assignment_list': assignment_list, 'quest_list': quest_list, 'task_list': task_list}, context_instance = RequestContext(request))

def course_admin(request, course_id):
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
    else:
        enrollment = None
        admin_courses = None
        admin_this_course = False
    return render_to_response('halo_admin/course_admin.html', {'user': user, 'enrollment': enrollment, 'this_course': this_course, 'admin_courses': admin_courses, 'admin_this_course': admin_this_course}, context_instance=RequestContext(request))

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
        
        quest_list = Quest.objects.filter(assignment_id=assignment_id)
    else:
        enrollment = None
        admin_courses = None
        admin_this_course = False
        quest_list = None
    return render_to_response('halo_admin/quest_admin.html', {'user': user, 'enrollment': enrollment, 'this_course': this_course, 'admin_courses': admin_courses, 'admin_this_course': admin_this_course, 'quest_list': quest_list}, context_instance=RequestContext(request))

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
        
        task_list = Task.objects.filter(quest_id=quest_id)
    else:
        enrollment = None
        admin_courses = None
        admin_this_course = False
        task_list = None
    return render_to_response('halo_admin/quest_admin.html', {'user': user, 'enrollment': enrollment, 'this_course': this_course, 'admin_courses': admin_courses, 'admin_this_course': admin_this_course, 'task_list': task_list}, context_instance=RequestContext(request))

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
            return HttpResponseRedirect('/admin/'+str(course_id)+'/assignments')
    else:
        form = AddQuestForm(request.user, course_id, assignment_id, parent_quest_id)
    return render_to_response('halo_admin/add_quest.html', {'user': user, 'admin_this_course': admin_this_course, 'form': form}, context_instance = RequestContext(request))

#class AddQuestForm(forms.Form):
#    def __init__(self, user, *args, **kwargs):
#        super(AddQuestForm, self).__init__(*args, **kwargs)
#        if (user.is_authenticated()):
#            u_id = user.getUserId()
#            enrollment = Enrollment.objects.filter(user_id=u_id)
#            admin_enrollment = enrollment.filter(type="ADMIN")
#            admin_courses = []
#            for enr in admin_enrollment:
#                course = Course.objects.get(id=enr.getCourseId())
#                admin_courses.append(course)
#            self.fields['course'].choices = [('','-- choose a course --'),] + [(c.id, c.name) for c in admin_courses]
#            self.fields['assignment'].choices = [('','-- choose an assignment --'),] + [(a.id, a.title) for a in Studentassignment.objects.filter(course_id = self.fields['course'].value)]
#    course = forms.ChoiceField(widget=forms.Select, choices=())
#    assignment = forms.ChoiceField(widget=forms.Select, choices=())
#    parent_quest = forms.ChoiceField(widget=forms.Select, choices=())

class AddQuestForm(forms.Form):
    def __init__(self, user, course_id, assignment_id, parent_quest_id, *args, **kwargs):
        super(AddQuestForm, self).__init__(*args, **kwargs)
    
    name = forms.CharField(max_length=150)
    description = forms.CharField(widget=forms.Textarea)
    experiencePoints = forms.IntegerField()
    
    