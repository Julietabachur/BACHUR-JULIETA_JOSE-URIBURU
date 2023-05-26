package com.parcialBackend.service;

import com.parcialBackend.DAO.imp.OdontologoDaoH2;
import com.parcialBackend.model.Odontologo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OdontologoServiceTest {
    private static OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());


    @Test
    public void deberiaAgregarOdontologo(){
        Odontologo odontologo = new Odontologo("142536", "Homero", "Simpson" );
        Odontologo odontologoResult = odontologoService.registrarOdontologo(odontologo);
        assertNotNull(odontologoResult);
        assertEquals(123456, odontologoResult.getNumeroMatricula());

    }

    @Test
        public void deberialistar(){

            List<Odontologo> odontologosTest = odontologoService.buscarTodos();
            assertTrue(odontologosTest.size() > 0);
            assertTrue(odontologosTest.size() > 2);
    }

}
