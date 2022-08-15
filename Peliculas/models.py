from django.db import models

# Create your models here.




class Pelicula(models.Model):
    pelicula_id = models.AutoField(primary_key=True)
    Titulo=models.TextField('Titulo Pelicula' )
    Descripcion=models.TextField('Descripcion Pelicula')
    ImagenBanner=models.TextField('imagen tipo banner (Larga)')
    ImagenPoster=models.TextField('Imagen tipo Poster')
    ImagenLogo=models.TextField('Imagen tipo Poster',max_length=300)
    LinkPelicula=models.TextField('Link de la pelicula (.mp4 o mkv)', default="")
    AnoEstreno=models.TextField('Año Estreno')
    genero=models.TextField('Genero Pelicula')
    tipo_pelicula=models.TextField('Tipo pelicula , ultima pelicula')
