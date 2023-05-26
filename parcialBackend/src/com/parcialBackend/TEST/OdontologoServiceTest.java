package com.parcialBackend.TEST;

import com.parcialBackend.DAO.OdontologoDaoH2;
import com.parcialBackend.model.Odontologo;
import org.junit.Test;
import com.parcialBackend.service.OdontologoService;

import java.util.List;

import static org.junit.Assert.*;

public class OdontologoServiceTest {


    private static OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());


    @Test
    public void deberiaAgregarOdontologo(){
        Odontologo odontologo = new Odontologo("142536", "Homero", "Simpson" );
        Odontologo odontologoResult = odontologoService.registrar(odontologo);
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
