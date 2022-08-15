from django.db import models

# Create your models here.




class Pelicula(models.Model):
    pelicula_id = models.AutoField(primary_key=True)
    Titulo=models.CharField('Titulo Pelicula' )
    Descripcion=models.CharField('Descripcion Pelicula')
    ImagenBanner=models.CharField('imagen tipo banner (Larga)')
    ImagenPoster=models.CharField('Imagen tipo Poster')
    ImagenLogo=models.CharField('Imagen tipo Poster',max_length=300)
    LinkPelicula=models.CharField('Link de la pelicula (.mp4 o mkv)', default="")
    AnoEstreno=models.CharField('Año Estreno')
    genero=models.CharField('Genero Pelicula')
    tipo_pelicula=models.CharField('Tipo pelicula , ultima pelicula')
