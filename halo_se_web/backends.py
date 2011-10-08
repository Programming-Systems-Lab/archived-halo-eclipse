from django.conf import settings
from django.contrib.auth.models import User, check_password
from login.models import HUser

class EmailAuthBackend(object):
    def authenticate(self,username=None, password=None):
        try:
            user = HUser.objects.get(email=username)
            if user.check_password(password):
                return user
        except User.DoesNotExist:
            return None

    def get_user(self, user_id):
        try:
            return HUser.objects.get(pk=user_id)
        except User.DoesNotExist:
            return None
