# Generated by Django 3.2.4 on 2022-08-15 01:08

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('Peliculas', '0001_initial'),
    ]

    operations = [
        migrations.RenameField(
            model_name='pelicula',
            old_name='AñoEstreno',
            new_name='AnoEstreno',
        ),
    ]
