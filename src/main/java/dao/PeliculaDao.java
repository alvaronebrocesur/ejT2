package dao;

import models.Pelicula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDao implements DAO<Pelicula>{
    Connection con;

    /**
     * Crea el DAO con la conexion
     * @param c conexion para inciarlo
     */
    public PeliculaDao(Connection c) {
        con=c;
    }

    @Override
    public List<Pelicula> findAll() {
        return List.of();
    }

    @Override
    public Pelicula findById(Integer id) {
        return null;
    }

    /**
     * Guarda una pelicula pasada por parametro en la base de datos
     * @param pelicula pelicula a guardar
     */
    @Override
    public void save(Pelicula pelicula) {
        try( PreparedStatement pst = con.prepareStatement("insert into pelicula(titulo, año, genero) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS)){
            pst.setString(1, pelicula.getTitulo());
            pst.setInt(2, pelicula.getAno());
            pst.setString(3, pelicula.getGenero());
            int result = pst.executeUpdate();
            if(result>0){
                ResultSet keys = pst.getGeneratedKeys();
                keys.next();
                Integer game_id = keys.getInt(1);
                pelicula.setId(game_id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Pelicula pelicula) {

    }

    @Override
    public void delete(Pelicula pelicula) {

    }

    /**
     * Devuelve el numero de peliculas que hay en la base de datos
     * @return numero de peliculas en la base de datos
     */
    public Integer count() {
        Integer ret = 0;
        try(PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM pelicula")){
            var result = ps.executeQuery();
            if(result.next()){
                ret = result.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    /**
     * Filtra las peliculas que pertenezcan al genero pasado por parametro
     * @param genero Genero por le cual filtrar
     * @return Lista con las peliculas pertenecientes al genero pasado por parametro
     */
    public List<Pelicula> findByGenero(String genero) {
        var ret = new ArrayList<Pelicula>(0);
        try(PreparedStatement ps = con.prepareStatement("SELECT * FROM pelicula WHERE genero = ?")){
            ps.setString(1, genero);
            var result = ps.executeQuery();
            while(result.next()){
                Pelicula p = new Pelicula(
                        result.getInt(1),
                        result.getString(2),
                        result.getInt(3),
                        result.getString(4)
                );
                ret.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }
}
