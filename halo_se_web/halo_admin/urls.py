from django.conf.urls.defaults import patterns, include, url

urlpatterns = patterns('halo_admin.views',
    (r'^$', 'dashboard'),
    (r'^(?P<course_id>\d+)/(?P<assignment_id>\d+)/(?P<quest_id>\d+)/add_task/(?P<parent_task_id>\d+)$', 'add_task'),
    (r'^(?P<course_id>\d+)/(?P<assignment_id>\d+)/(?P<quest_id>\d+)/add_task/$', 'add_task'),
    (r'^(?P<course_id>\d+)/(?P<assignment_id>\d+)/(?P<quest_id>\d+)/tasks/$', 'task_admin'),
    (r'^(?P<course_id>\d+)/(?P<assignment_id>\d+)/quests/$', 'quest_admin'),
    (r'^(?P<course_id>\d+)/(?P<assignment_id>\d+)/add_quest/(?P<parent_quest_id>\d+)/$', 'add_quest'),
    (r'^(?P<course_id>\d+)/(?P<assignment_id>\d+)/add_quest/$', 'add_quest'),
    (r'^(?P<course_id>\d+)/add_assignment/$', 'add_assignment'),
    (r'^(?P<course_id>\d+)/assignments/$', 'assignment_admin'),
    (r'^(?P<course_id>\d+)/student_list/(?P<focus_id>\d+)/$', 'student_list'),
    (r'^(?P<course_id>\d+)/student_list/$', 'student_list'),
    (r'^(?P<course_id>\d+)/$', 'course_admin'),
)