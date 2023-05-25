package service;

import DAO.IDao;
import com.parcialBackend.model.Odontologo;

import java.util.List;

public class OdontologoService {

    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo registrar(Odontologo odontologo) {
        return odontologoIDao.registrar(odontologo);
    }

    public List<Odontologo> buscarTodos() {
        return odontologoIDao.listar();
    }
}

