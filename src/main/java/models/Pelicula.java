package models;

import lombok.Data;

import java.io.Serializable;
@Data
public class Pelicula implements Serializable {
    private Integer id;
    private String titulo;
    private int ano;
    private String genero;

    public Pelicula(Integer id, String titulo, int ano, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", ano=" + ano +
                ", genero='" + genero + '\'' +
                '}';
    }
}
