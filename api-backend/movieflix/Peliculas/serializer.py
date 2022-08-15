from csv import field_size_limit
from dataclasses import field
from pyexpat import model
from rest_framework import serializers
from rest_framework.authtoken.models import Token
from django.contrib.auth import authenticate
from django.utils.translation import gettext_lazy as _
from .models import Pelicula
from rest_framework.decorators import api_view



class CustomMovieSerializer(serializers.ModelSerializer):
    class Meta:
        model = Pelicula
        fields = '__all__'