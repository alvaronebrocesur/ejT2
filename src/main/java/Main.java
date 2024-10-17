import dao.JdbcUtils;
import dao.PeliculaDao;
import models.Pelicula;

public class Main {
    public static void main(String[] args) {
        PeliculaDao pd = new PeliculaDao(JdbcUtils.getCon());
        pd.save(new Pelicula(0, "Tarzan", 2001, "accion"));
        System.out.println(pd.count());
        System.out.println(pd.findByGenero("Drama"));
    }
}
