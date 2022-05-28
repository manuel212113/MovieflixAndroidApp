package com.spicode.movieflix.modelo;

public class ModeloDatosPelicula {

    private String PTitulo;
    private String PImagen;
    private String PVideo;

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

    public ModeloDatosPelicula(){

    }
    public ModeloDatosPelicula(String pTitulo, String pImagen, String pVideo){

        PTitulo = pTitulo;
        PImagen = pImagen;
        PVideo = pVideo;
    }


}
