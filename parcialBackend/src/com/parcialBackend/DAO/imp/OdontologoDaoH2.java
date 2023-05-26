
package com.parcialBackend.DAO.imp;

import com.parcialBackend.DAO.IDao;
import com.parcialBackend.model.Odontologo;
import com.parcialBackend.DAO.H2Connection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OdontologoDaoH2 implements IDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;

        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            logger.info("Conectando con la base de datos.");
            PreparedStatement ps  = connection.prepareStatement("INSERT INTO Odontologos(NUMEROMATRICULA,NOMBRE,APELLIDO) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,odontologo.getNumeroMatricula());
            ps.setString(2,odontologo.getNombre());
            ps.setString(3,odontologo.getApellido());

            ps.execute();

            ResultSet key = ps.getGeneratedKeys();

            logger.info("Datos Ingresados Correctamente");

            while (key.next()) {
                int id = key.getInt(1);
                odontologo.setId(id);
            }

            connection.commit();
            logger.info("Odontologo guardado en la base de datos: " + odontologo.toString());
        }  catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    logger.info("Tuvimos un problema");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    logger.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                logger.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }
        return odontologo;
    }

    @Override
    public List<Odontologo> listar() {

        Connection connection = null;

        List<Odontologo> odontologos = new ArrayList<>();
        Odontologo odontologo;

        try {
            connection = H2Connection.getConnection();;
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Odontologos");
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                odontologo = new Odontologo( result.getString("numeromatricula"), result.getString("nombre"), result.getString("apellido"),result.getInt("id"));
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
            }
        }
        return odontologos;
    }
}
