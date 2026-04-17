import java.sql.*;
import java.util.Scanner;

public class MenuArtista {
    public static void menuArtista(Scanner sc,String url,String usuario,String contrasenia) throws SQLException {
        Connection conexion = null;
        PreparedStatement ps = null;
        boolean terminado = false;
        while (!terminado) {
            System.out.println("MENU DE ARTISTAS");
            System.out.println("1. LISTAR ARTISTAS.");
            System.out.println("2. AÑADIR ARTISTAS.");
            System.out.println("3. ELIMINAR ARTISTAS.");
            System.out.println("4. SALIR");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    try {
                        conexion = DriverManager.getConnection(url, usuario, contrasenia);
                        Statement st = conexion.createStatement();
                        String query = "SELECT * FROM ARTISTA";
                        ResultSet rs = st.executeQuery(query);
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String nombre = rs.getString("nombre");
                            String genero = rs.getString("generoMusical");
                            String pais = rs.getString("paisOrigen");
                            System.out.println("ID: " + id);
                            System.out.println("Nombre: " + nombre);
                            System.out.println("Genero Musical: " + genero);
                            System.out.println("Pais de origen: " + pais);
                        }
                    }catch (SQLException e) {
                        System.out.println("Error al mostrar los datos: "  + e.getMessage());
                    }
                    break;
                case 2:
                    int id = 0;
                    conexion = DriverManager.getConnection(url, usuario, contrasenia);
                    conexion.setAutoCommit(false);
                    String query_id = "SELECT MAX(id) AS id FROM ARTISTA";
                    Statement st = conexion.createStatement();
                    ResultSet rs = st.executeQuery(query_id);
                    while (rs.next()) {
                        id = rs.getInt("id");
                    }


                    System.out.println("Inserte nombre del artista: ");
                    String nombre = sc.nextLine();
                    System.out.println("Cual genero musical toca? ");
                    String genero = sc.nextLine();
                    System.out.println("Inserte su pais de origen: ");
                    String paisOrigen = sc.nextLine();
                    Artista artista = new Artista(id, nombre, genero, paisOrigen);
                    try {

                        String query = "INSERT INTO ARTISTA (id, nombre, generoMusical, paisOrigen) VALUES "
                                + "(?,?,?,?)";
                        ps = conexion.prepareStatement(query);
                        if (id != 0) {
                            artista.setId(id+1);
                            ps.setInt(1, artista.getId());
                        }else{
                            artista.setId(id+1);
                            ps.setInt(1, artista.getId());
                        }
                        ps.setString(2, artista.getNombre());
                        ps.setString(3, artista.getGeneroMusical());
                        ps.setString(4, artista.getPaisOrigen());
                        ps.execute();
                        conexion.commit();
                        System.out.println("ARTISTA INSERTADO CORRECTAMENTE");
                    }catch (SQLException e) {
                        System.out.println("ERROR AL INSERTAR, NO SE HA INSERTADO. " +  e.getMessage());
                        conexion.rollback();
                    }finally {
                        if(ps != null) {
                            try {
                                ps.close();
                            } catch (SQLException e) {
                                System.out.println("Error al cerrar la Preparestatement: "  + e.getMessage());
                            }
                        }
                        if(conexion != null) {
                            try {
                                conexion.close();
                            } catch (SQLException e) {
                                System.out.println("Error al cerrar la Conexion: "  + e.getMessage());
                            }
                        }
                    }
                    break;
                case 3:
                    try {
                        System.out.println("SELECCIONA A ARTISTA A ELIMINAR (Por ID):");
                        int id_artista = sc.nextInt();
                        sc.nextLine();
                        conexion = DriverManager.getConnection(url, usuario, contrasenia);
                        String sql = "DELETE FROM ARTISTA WHERE id = ?";

                        ps = conexion.prepareStatement(sql);
                        ps.setInt(1, id_artista);
                        ps.executeUpdate();
                        System.out.println("ARTISTA ELIMINADO CORRECTAMENTE");
                    }catch (SQLException e) {
                        System.out.println("Error al eliminar el artista: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del menu artistas...");
                    terminado = true;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
}
