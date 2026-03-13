package app.db;

import app.model.Formatie;
import app.model.Membru;
import app.model.Contract;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBOperations {

    private Connection conn;

    // 🔴 MODIFICĂ dacă baza ta are alt nume
    private final String URL =
            "jdbc:mysql://localhost:3306/formatii_db?useSSL=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASS = "Nedelcu2005@";   // <-- pune parola ta aici

    public void connect() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASS);
    }

    public void disconnect() throws SQLException {
        if (conn != null) conn.close();
    }

    // =====================================================
    // ================== FORMATII =========================
    // =====================================================

    public List<Formatie> getFormatii() throws SQLException {

        String sql = "SELECT * FROM formatii";
        List<Formatie> list = new ArrayList<>();

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Formatie(
                        rs.getInt("idformatie"),
                        rs.getString("nume"),
                        rs.getString("gen_muzical"),
                        rs.getInt("an_infiintare"),
                        rs.getString("tara_origine")
                ));
            }
        }

        return list;
    }

    public void addFormatie(String nume, String gen, int an, String tara)
            throws SQLException {

        String sql = """
                INSERT INTO formatii(nume, gen_muzical, an_infiintare, tara_origine)
                VALUES(?,?,?,?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nume);
            ps.setString(2, gen);
            ps.setInt(3, an);
            ps.setString(4, tara);
            ps.executeUpdate();
        }
    }

    public void updateFormatie(int id, String nume, String gen, int an, String tara)
            throws SQLException {

        String sql = """
                UPDATE formatii
                SET nume=?, gen_muzical=?, an_infiintare=?, tara_origine=?
                WHERE idformatie=?
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nume);
            ps.setString(2, gen);
            ps.setInt(3, an);
            ps.setString(4, tara);
            ps.setInt(5, id);
            ps.executeUpdate();
        }
    }

    public void deleteFormatie(int id) throws SQLException {

        String sql = "DELETE FROM formatii WHERE idformatie=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // =====================================================
    // ================== MEMBRI ===========================
    // =====================================================

    public List<Membru> getMembri() throws SQLException {

        String sql = "SELECT * FROM membri";
        List<Membru> list = new ArrayList<>();

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Membru(
                        rs.getInt("idmembru"),
                        rs.getString("nume"),
                        rs.getString("prenume"),
                        rs.getInt("varsta"),
                        rs.getString("instrument"),
                        rs.getString("nationalitate")
                ));
            }
        }

        return list;
    }

    public void addMembru(String nume, String prenume, int varsta,
                          String instrument, String nationalitate)
            throws SQLException {

        String sql = """
                INSERT INTO membri(nume, prenume, varsta, instrument, nationalitate)
                VALUES(?,?,?,?,?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nume);
            ps.setString(2, prenume);
            ps.setInt(3, varsta);
            ps.setString(4, instrument);
            ps.setString(5, nationalitate);
            ps.executeUpdate();
        }
    }

    public void updateMembru(int id, String nume, String prenume,
                             int varsta, String instrument, String nationalitate)
            throws SQLException {

        String sql = """
                UPDATE membri
                SET nume=?, prenume=?, varsta=?, instrument=?, nationalitate=?
                WHERE idmembru=?
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nume);
            ps.setString(2, prenume);
            ps.setInt(3, varsta);
            ps.setString(4, instrument);
            ps.setString(5, nationalitate);
            ps.setInt(6, id);
            ps.executeUpdate();
        }
    }

    public void deleteMembru(int id) throws SQLException {

        String sql = "DELETE FROM membri WHERE idmembru=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // =====================================================
    // ================== CONTRACTE ========================
    // =====================================================

    public List<Contract> getContracts() throws SQLException {

        String sql = """
                SELECT c.idcontract,
                       f.idformatie, f.nume AS formatie,
                       m.idmembru, CONCAT(m.nume,' ',m.prenume) AS membru,
                       c.data_inceput,
                       c.data_sfarsit,
                       c.status_contract
                FROM contract c
                JOIN formatii f ON f.idformatie = c.idformatie
                JOIN membri m ON m.idmembru = c.idmembru
                ORDER BY c.idcontract
                """;

        List<Contract> list = new ArrayList<>();

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Contract(
                        rs.getInt("idcontract"),
                        rs.getInt("idformatie"),
                        rs.getInt("idmembru"),
                        rs.getDate("data_inceput"),
                        rs.getDate("data_sfarsit"),
                        rs.getString("status_contract"),
                        rs.getString("formatie"),
                        rs.getString("membru")
                ));
            }
        }

        return list;
    }

    public void addContract(int idFormatie, int idMembru,
                            Date dataStart, Date dataEnd,
                            String status)
            throws SQLException {

        String sql = """
                INSERT INTO contract(idformatie, idmembru,
                                     data_inceput, data_sfarsit, status_contract)
                VALUES(?,?,?,?,?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idFormatie);
            ps.setInt(2, idMembru);
            ps.setDate(3, dataStart);
            ps.setDate(4, dataEnd);
            ps.setString(5, status);
            ps.executeUpdate();
        }
    }

    public void updateContract(int idContract, int idFormatie,
                               int idMembru, Date dataStart,
                               Date dataEnd, String status)
            throws SQLException {

        String sql = """
                UPDATE contract
                SET idformatie=?, idmembru=?,
                    data_inceput=?, data_sfarsit=?, status_contract=?
                WHERE idcontract=?
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idFormatie);
            ps.setInt(2, idMembru);
            ps.setDate(3, dataStart);
            ps.setDate(4, dataEnd);
            ps.setString(5, status);
            ps.setInt(6, idContract);
            ps.executeUpdate();
        }
    }

    public void deleteContract(int idContract) throws SQLException {

        String sql = "DELETE FROM contract WHERE idcontract=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idContract);
            ps.executeUpdate();
        }
    }
}