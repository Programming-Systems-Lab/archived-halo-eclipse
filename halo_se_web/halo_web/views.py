from django.shortcuts import render_to_response, get_object_or_404
from halo_web.models import Quest
from django.http import HttpResponse, Http404, HttpResponseRedirect

def index(request):
    return HttpResponse('Hello, world! You are at the halo_web index.')

def quests(request):
    quest_list = Quest.objects.all()
    return render_to_response('halo_web/quests.html', {'quest_list': quest_list})

def home(request):
    print request
    return HttpResponse("Hello world")