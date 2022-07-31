package com.spicode.movieflix.modelo;

public class UltimasPeliculas {


    private String PTitulo;
    private String PImagen;
    private String PVideo;
    private String PDescripcion;
    private  String PImagenBanner;
    private  String PLogo;



    public UltimasPeliculas(){

    }
    public UltimasPeliculas(String PTitulo, String PImagen) {
        this.PTitulo = PTitulo;
        this.PImagen = PImagen;
    }





    public String getPTitulo() {
        return PTitulo;
    }

    public void setPTitulo(String PTitulo) {
        this.PTitulo = PTitulo;
    }

    public String getPImagen() {
        return PImagen;
    }

    public void setPImagen(String PImagen) {
        this.PImagen = PImagen;
    }

    public String getPVideo() {
        return PVideo;
    }

    public void setPVideo(String PVideo) {
        this.PVideo = PVideo;
    }

    public String getPDescripcion() {
        return PDescripcion;
    }

    public void setPDescripcion(String PDescripcion) {
        this.PDescripcion = PDescripcion;
    }

    public String getPImagenBanner() {
        return PImagenBanner;
    }

    public void setPImagenBanner(String PImagenBanner) {
        this.PImagenBanner = PImagenBanner;
    }

    public String getPLogo() {
        return PLogo;
    }

    public void setPLogo(String PLogo) {
        this.PLogo = PLogo;
    }




}
