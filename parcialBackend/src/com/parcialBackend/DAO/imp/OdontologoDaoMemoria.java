package com.parcialBackend.DAO.imp;

import com.parcialBackend.model.Odontologo;
import com.parcialBackend.DAO.IDao;
import org.apache.log4j.Logger;

import java.util.List;

public class OdontologoDaoMemoria implements IDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDaoMemoria.class);

    private List<Odontologo> odontologoRepo;

    public OdontologoDaoMemoria(List<Odontologo> odontologoRepo) {
        this.odontologoRepo = odontologoRepo;
    }

    @Override
    public Odontologo registrar( Odontologo odontologo ) {
        odontologoRepo.add(odontologo);
        logger.info("Odontologo guardado: " + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listar() {
        logger.info("Listado de todos los odontologos: \n" + odontologoRepo);
        return odontologoRepo;
    }

}
