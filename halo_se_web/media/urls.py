from django.conf.urls.defaults import patterns, include, url

urlpatterns = patterns('',

    url(r'^(?P<poll_id>\d+)/vote/$', 'polls.views.vote'),

)