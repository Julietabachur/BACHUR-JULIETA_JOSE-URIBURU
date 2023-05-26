
package com.parcialBackend.DAO;

import com.parcialBackend.model.Odontologo;


import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import com.parcialBackend.model.Odontologo;



public class OdontologoDaoH2 implements IDao<Odontologo> {


    private static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";


    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Conectando con la base de datos.");
            preparedStatement = connection.prepareStatement("INSERT INTO Odontologos(numeroMatricula,nombre,apellido) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,odontologo.getNumeroMatricula());
            preparedStatement.setString(2,odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());

            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();

            logger.info("Datos Ingresados Correctamente");

            if(keys.next())
                odontologo.setId(keys.getInt(1));

            preparedStatement.close();
            logger.info("Cerrando la base de datos.");}
        catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

            logger.error("Se encontró un error");

        }
        return odontologo;


    }

    @Override
    public List<Odontologo> listar() {

        Connection connection = null;

        List<Odontologo> odontologos = new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Odontologos");
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String numeroMatricula = result.getString("numeroMatricula");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");

                Odontologo odontologo = new Odontologo(numeroMatricula, nombre, apellido, id);
                odontologos.add(odontologo);
            }
            logger.info("Listado de odontologos " + odontologos);

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                logger.error("No se pudo cerrar la base de datos " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return odontologos;
    }
}
