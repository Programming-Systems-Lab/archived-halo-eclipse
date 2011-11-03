halo_se_web / HALO SE Web
@author Miriam Melnick mrm2198@columbia.edu 
Please email me with any questions.

Table of Contents
0. Overview
1. Install Dependencies
2. Modify Settings
3. Choose Debug or Production Mode


(0)
	a. Make sure all dependencies (see (1)) are installed.
	b. Check out git@ase.cs.columbia.edu/halo.git .
	c. Modify settings as explained in (2).
	d. Set debug or production mode as explained in (3).
	e. cd halo_se_web
	f. python manage.py syncdb (synchronize your data tables with those of HALO SE Web)
	g. python manage.py runserver (start the server)

(1)
	a. Python ( http://www.python.org/ ). Tested with Python 2.7.1. Python provides the underlying codebase for django, our web framework.
	b. Django ( https://www.djangoproject.com/ ). Tested with Django 1.3.1. Django is the web framework on which HALO SE Web runs.
	c. MySQLdb ( http://sourceforge.net/projects/mysql-python/ ). Tested with MySQLdb 1.2.3. MySQLdb provides an interface between python/django and MySQL.
	d. django-googlecharts ( https://github.com/jacobian/django-googlecharts ). Tested with django-googlecharts commit c542996cf9a7a097b447e1ce5a1a6b66bdad254f. django-googlecharts provides an interface between django and Google Charts. HALO SE Web uses Google Charts to provide graphical representations of usage data.

(2)
	In this section {{ HALO_ROOT }} will represent the directory to which you checked out halo.git. Replace it with the absolute path appropriate to your situation.
	a. open {{ HALO_ROOT }}/halo_se_web/settings.py.
	b. In settings.py, change MEDIA_ROOT to '{{ HALO_ROOT }}/halo_se_web/templates/media/user-uploaded/' .
	c. In settings.py, change STATIC_ROOT to '{{ HALO_ROOT }}/halo_se_web/static/' .
	d. In settings.py, change TEMPLATE_DIRS to ("{{ HALO_ROOT }}/halo_se_web/templates") .

(3)	
	In this section {{ HALO_ROOT }} will represent the directory to which you checked out halo.git. Replace it with the absolute path appropriate to your situation.
	a. open {{ HALO_ROOT }}/halo_se_web/settings.py.
	b. If you want to see all possible error messages, go to c. Else go to d.
	c. To see all error messages:
		i. In settings.py, change DEBUG to True.
		ii. In settings.py, change TEMPLATE_DEBUG to DEBUG.
	d. To hide error messages (as in a production environment):
		i. In settings.py, change DEBUG to False.
		ii. In settings.py, change TEMPLATE_DEBUG to False.