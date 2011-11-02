from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.models import AnonymousUser
from django.http import HttpResponse, Http404, HttpResponseRedirect
from django import forms
from django.shortcuts import render_to_response
from django.template.context import RequestContext
from login.models import HUser
import hashlib

class LoginForm(forms.Form):
    uni = forms.CharField(max_length=100)
    password = forms.CharField(widget=forms.PasswordInput(render_value=False),max_length=100)

def doLogin(request):
    if (request.user.is_authenticated()):
        return HttpResponse("You are already logged in. Please <a href='/logout'>logout</a> if you wish to change users.")
    def errorHandle(error):
        form = LoginForm()
        return render_to_response('login/login.html', {
                'error' : error,
                'form' : form,
        }, context_instance = RequestContext(request))
    if request.method == 'POST': # If the form has been submitted...
        form = LoginForm(request.POST) # A form bound to the POST data
        if form.is_valid(): # All validation rules pass
            email = request.POST['uni']
            password = request.POST['password']
            user = authenticate(username=email, password=password)
            if user is not None:
                if user.is_active:
                    # Redirect to a success page.
                    login(request, user)
                    return HttpResponseRedirect('/')
                else:
                    # Return a 'disabled account' error message
                    error = u'account disabled'
                    return errorHandle(error)
            else:
                 # Return an 'invalid login' error message.
                error = u'invalid login'
                return errorHandle(error)
        else:
            error = u'form is invalid'
            return errorHandle(error)
    else:
        form = LoginForm() # An unbound form
        return render_to_response('login/login.html', {
            'request': request,
            'form': form
        }, context_instance = RequestContext(request))

def doLogout(request):
    logout(request)
    return HttpResponseRedirect('/')

def showCredentials(request):
    user = request.user
    if not user.is_authenticated():
        return render_to_response('login/notloggedin.html', {'dump': user}, context_instance = RequestContext(request))
    else:
        password = user.getPassword()
        email = user.getEmail()
        return render_to_response('login/credentials.html', {'dump': user, 'password': password, 'email': email}, context_instance = RequestContext(request))


