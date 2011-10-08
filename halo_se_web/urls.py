from django.conf.urls.defaults import patterns, include, url

# Uncomment the next two lines to enable the admin:
#from django.contrib import admin
#admin.autodiscover()

urlpatterns = patterns('',
                       
    # Examples:
    # url(r'^$', 'halo_se_web.views.home', name='home'),
    # url(r'^halo_se_web/', include('halo_se_web.foo.urls')),

    # Uncomment the admin/doc line below to enable admin documentation:
    # url(r'^admin/doc/', include('django.contrib.admindocs.urls')),

    url(r'^$', 'halo_web.views.home'),
    url(r'^login/$', 'login.views.doLogin'),
    url(r'^logout/$', 'login.views.doLogout'),
    url(r'^login/success/$', 'login.views.success'),
    url(r'^login/credentials/$', 'login.views.showCredentials'), #remove this before production
        
    url(r'^admin/', include('halo_admin.urls')),

    url(r'^halo_web/$', 'halo_web.views.index'),
    url(r'^halo_web/quests/$', 'halo_web.views.quests'),
    # Uncomment the next line to enable the admin:
#    url(r'^admin/', include(admin.site.urls)),
    
 #   url(r'^media/', include('media.urls')),
)
