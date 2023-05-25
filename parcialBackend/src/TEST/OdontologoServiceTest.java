package TEST;

import DAO.OdontologoDaoH2;
import com.parcialBackend.model.Odontologo;
import org.junit.Assert;
import org.junit.BeforeClass;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import service.OdontologoService;

import java.util.List;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4.class)

public class OdontologoServiceTest {


    private static OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

        @BeforeClass
        public static void agregarOdontologo() {

            Odontologo Odontologo1 =odontologoService.registrar(new Odontologo("23905798","JOSE","ARIAS"));
            Odontologo Odontologo2 =odontologoService.registrar(new Odontologo("123125","JULIETA","BACHUR"));
            Odontologo Odontologo3 =odontologoService.registrar(new Odontologo("D4325","HOMERO","PETTINATO"));

        }

        @Test

        public void listar(){

            List<Odontologo> odontologos =odontologoService.buscarTodos();
            Assert.assertTrue(odontologos.size() > 0);
            System.out.println(odontologos);


        }



}
