# Generated by Django 3.2.4 on 2022-08-15 00:39

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Pelicula',
            fields=[
                ('pelicula_id', models.AutoField(primary_key=True, serialize=False)),
                ('Titulo', models.CharField(max_length=250, verbose_name='Titulo Pelicula')),
                ('Descripcion', models.CharField(max_length=250, verbose_name='Descripcion Pelicula')),
                ('ImagenBanner', models.CharField(max_length=300, verbose_name='imagen tipo banner (Larga)')),
                ('ImagenPoster', models.CharField(max_length=300, verbose_name='Imagen tipo Poster')),
                ('ImagenLogo', models.CharField(max_length=300, verbose_name='Imagen tipo Poster')),
                ('LinkPelicula', models.CharField(default='Sin Celular', max_length=300, verbose_name='Link de la pelicula (.mp4 o mkv)')),
                ('AñoEstreno', models.CharField(max_length=300, verbose_name='Año Estreno')),
                ('genero', models.CharField(max_length=300, verbose_name='Genero Pelicula')),
                ('tipo_pelicula', models.CharField(max_length=300, verbose_name='Tipo pelicula , ultima pelicula')),
            ],
        ),
    ]
