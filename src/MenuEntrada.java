import java.sql.*;
import java.util.Scanner;

public class MenuEntrada {
    public static void menuEntradas(Scanner sc, String url, String usuario, String contrasenia) throws SQLException {
        Connection conexion = null;
        PreparedStatement ps = null;
        boolean terminado = false;
        while (!terminado) {
            System.out.println("MENU DE ENTRADAS");
            System.out.println("1. LISTAR ENTRADAS.");
            System.out.println("2. REGISTRAR ENTRADA.");
            System.out.println("3. SALIR");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    try {
                        conexion = DriverManager.getConnection(url, usuario, contrasenia);
                        Statement st = conexion.createStatement();
                        String query = "SELECT * FROM ENTRADA";
                        ResultSet rs = st.executeQuery(query);
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            int id_concierto = rs.getInt("concierto_id");
                            String comprador = rs.getString("comprador");
                            int cantidad = rs.getInt("cantidad");
                            String fecha_compra = rs.getString("fechaCompra");
                            System.out.println("ID: " + id);
                            System.out.println("ID concierto: " + id_concierto);
                            System.out.println("Comprador: " + comprador);
                            System.out.println("Cantidad: " + cantidad);
                            System.out.println("Fecha de la compra: " + fecha_compra);
                        }
                    }catch (SQLException e) {
                        System.out.println("Error al mostrar los datos: "  + e.getMessage());
                    }
                    break;
                case 2:
                    int id = 0;
                    conexion = DriverManager.getConnection(url, usuario, contrasenia);
                    conexion.setAutoCommit(false);
                    String query_id = "SELECT MAX(id) AS id FROM ENTRADA";
                    Statement st = conexion.createStatement();
                    ResultSet rs = st.executeQuery(query_id);
                    while (rs.next()) {
                        id = rs.getInt("id");
                    }
                    System.out.println("Ingrese el id del concierto: ");
                    int id_concierto = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Inserte nombre del comprador: ");
                    String comprador = sc.nextLine();
                    System.out.println("Cantidad de entradas: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Inserte fecha de la compra");
                    String fecha_compra = sc.nextLine();
                    Entrada entrada = new Entrada(id,id_concierto,comprador,cantidad,fecha_compra);
                    try {

                        String query = "INSERT INTO ENTRADA (id, concierto_id, comprador, cantidad, fechaCompra) VALUES "
                                + "(?,?,?,?,?)";
                        ps = conexion.prepareStatement(query);
                        if (id != 0) {
                            entrada.setId(id+1);
                            ps.setInt(1, entrada.getId());
                        }else{
                            entrada.setId(id+1);
                            ps.setInt(1, entrada.getId());
                        }
                        ps.setInt(2, entrada.getConcierto());
                        ps.setString(3, entrada.getComprador());
                        ps.setInt(4, entrada.getCantidad());
                        ps.setString(5, entrada.getFechaCompra());
                        ps.execute();
                        conexion.commit();
                        System.out.println("ENTRADA(S) REGISTRADAS CORRECTAMENTE");
                    }catch (SQLException e) {
                        System.out.println("ERROR AL REGISTRAR, NO SE HA REGISTRADO. " +  e.getMessage());
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
                    System.out.println("Saliendo del menu entradas...");
                    terminado = true;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
}
