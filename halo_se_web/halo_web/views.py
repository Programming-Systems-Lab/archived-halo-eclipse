from django.shortcuts import render_to_response, get_object_or_404
from login.models import Quest, Enrollment, Course, HUser
from django.http import HttpResponse, Http404, HttpResponseRedirect
from django.template.context import RequestContext

def index(request):
    user = request.user
    enrollment = Enrollment.objects.filter(user_id = user.id)
    courses = []
    for enr in enrollment:
        courses.append(Course.objects.get(id = enr.course_id))
    return render_to_response('halo_web/index.html', {'courses': courses}, context_instance = RequestContext(request))

def quests(request):
    quest_list = Quest.objects.all()
    return render_to_response('halo_web/quests.html', {'quest_list': quest_list}, context_instance = RequestContext(request))

def home(request):
    user = request.user
    admin_courses = []

    if (user.is_authenticated()):
        u_id = user.getUserId()
        my_enrollment = Enrollment.objects.filter(user_id=u_id)
        admin_enrollment = my_enrollment.filter(type="ADMIN")
        for enr in admin_enrollment:
            course = Course.objects.get(id=enr.getCourseId())
            admin_courses.append(course)
        if (len(admin_courses)>0):
            return HttpResponseRedirect('/admin/')
        else:
            return HttpResponseRedirect('/halo_web/')
    return HttpResponseRedirect('/login/')

def leaderboard(request, course_id):
    this_course = Course.objects.get(id=course_id)
    course_enrollment = Enrollment.objects.filter(course_id=course_id)
    enr_this_course = course_enrollment.get(user_id = request.user.id)
    students = []
    leaders = []
    for enrstud in course_enrollment:
        student = HUser.objects.get(id = enrstud.user_id)
        students.append(student)
    students.sort(key=lambda student: student.getXP(), reverse = True)
    for i in range(5):
        leaders.append(students[i])
    return render_to_response('halo_web/leaderboard.html', {'enr_this_course': enr_this_course, 'leaders': leaders}, context_instance = RequestContext(request))