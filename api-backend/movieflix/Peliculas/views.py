from django.shortcuts import render
from  django.http import HttpResponse,JsonResponse
from .models import Pelicula
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework.permissions import AllowAny
from rest_framework.decorators import api_view
from rest_framework.exceptions import UnsupportedMediaType
from rest_framework import generics, status, permissions, viewsets, request, filters
from .serializer import CustomMovieSerializer

def login(request):
   return render(request,'login.html')




def ViewAddMovie(request):
    return render(request,'AddMovie.html')



def crearPelicula(request):
           
    if request.method == 'POST': 
        
        TituloPeli = request.POST.get('titulo') 
        AnoEstrenoPeli = request.POST.get('AnoEstreno') 
        DescripcionPeli = request.POST.get('descripcion')
        GeneroPeli = request.POST.get('genero')
        LinkPeli= request.POST.get('LinkPelicula')
        TipoPeli=request.POST.get('tipoPelicula')
        ImagenPosterPeli=request.POST.get('ImagenPoster')
        ImagenBannerPeli=request.POST.get('ImagenBanner')
        ImagenLogoPeli=request.POST.get('ImageLogo')
        
        peli=Pelicula.objects.create(
            Titulo=TituloPeli,
            Descripcion=DescripcionPeli,
            ImagenBanner=ImagenBannerPeli,
            ImagenPoster=ImagenPosterPeli,
            LinkPelicula=LinkPeli,
            AnoEstreno=AnoEstrenoPeli,
            genero=GeneroPeli,
            ImagenLogo=ImagenLogoPeli,
            tipo_pelicula=TipoPeli
        )
        peli.save()

        
        

    else:
       return JsonResponse({"status": 'error'})     
    
    return JsonResponse({"status": "correcto"}) 


@api_view(['GET'])
def MovieList(request):
    queryset=Pelicula.objects.all()
    serializer = CustomMovieSerializer(queryset, many=True)
    return Response(serializer.data)