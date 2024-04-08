/*
 * Nama     : Leviana Nanda Ramadhanti
 * NPM      : 2019.435.017.07
 * Kelas    : RU
 * Judul TA : Analisis dan Implementasi Metode Scrum Pada Sistem Penjualan
              Toko MargasSari Roti
 * Universitas Indraprasta PGRI Jakarta 2023.
 */
package konek;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jual";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection connect() throws SQLException {
        Connection conn = null;
        try {
            // Memuat driver JDBC untuk MySQL
            Class.forName("com.mysql.jdbc.Driver");

            // Membuat koneksi ke basis data
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Koneksi ke basis data berhasil!");
        } catch (ClassNotFoundException e) {
            // Jika driver JDBC tidak ditemukan
            System.err.println("Driver JDBC tidak ditemukan!");
            e.printStackTrace();
        } catch (SQLException e) {
            // Jika gagal membuat koneksi
            System.err.println("Gagal terhubung ke basis data!");
            e.printStackTrace();
        }

        return conn;
    }

    public static void main(String[] args) {
        try {
            Connection connection = connect();
            if (connection != null) {
                connection.close();
                System.out.println("Koneksi ditutup.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
