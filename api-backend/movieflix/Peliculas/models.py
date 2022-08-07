from django.db import models

# Create your models here.




class Pelicula(models.Model):
    pelicula_id = models.AutoField(primary_key=True)
    Titulo=models.CharField('Titulo Pelicula', max_length=250 )
    Descripcion=models.CharField('Descripcion Pelicula', max_length=250)
    ImagenBanner=models.CharField('imagen tipo banner (Larga)',max_length=300)
    ImagenPoster=models.CharField('Imagen tipo Poster',max_length=300)
    LinkPelicula=models.CharField('Link de la pelicula (.mp4 o mkv)',max_length=300, default="Sin Celular")
    AñoEstreno=models.CharField('Año Estreno',max_length=300)
    genero=models.CharField('Genero Pelicula',max_length=300)
    tipo_pelicula=models.CharField('Tipo pelicula , ultima pelicula',max_length=300)
