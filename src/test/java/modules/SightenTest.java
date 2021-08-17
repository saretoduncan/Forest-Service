package modules;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.security.Timestamp;
import java.util.Date;

import static java.time.LocalTime.now;
import static org.junit.jupiter.api.Assertions.*;

class SightenTest {
    Animals testAnimal;
    EndagereredSpecies testEndangered;

    @BeforeAll
    public static void before(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "softwaredev", "k1pk0sg31");

    }
    @BeforeEach
    public void setUp(){
        testAnimal= new Animals("antelope");

    }
    @AfterEach
    public void after(){
        try(Connection connection= DB.sql2o.open()){
            String deleteAnimals= "DELETE FROM animals *;";
            String deleteSpecies= "DELETE FROM endegered *";
            String deleteSighten= "DELETE FROM sighten *";
            connection.createQuery(deleteAnimals).executeUpdate();
            connection.createQuery(deleteSpecies).executeUpdate();
            connection.createQuery(deleteSighten).executeUpdate();

        }

    }
    @Test
    void endangeredSpecies_instantiateCorrectly_true(){
        testAnimal.save();
        Sighten testSighten= new Sighten( "zone A", testAnimal.getId());
        assertTrue(testSighten instanceof Sighten);



    }
    @Test
    void sighten_instantiate_getters(){
        testAnimal.save();
        Sighten testSighten= new Sighten( "zone A", testAnimal.getId());
        assertEquals( testAnimal.getId(),testSighten.getANIMALID());
        assertEquals("zone A",testSighten.getLOCATION());



    }
    @Test
    void sighted_instantiateSave_true(){
        testAnimal.save();
        Sighten testSighten= new Sighten( "zone A", testAnimal.getId());
        testSighten.save();

        assertEquals(Sighten.getAll().get(0).getId(),testSighten.getId());
    }
    @Test
    void sighted_instantiateClearAll_true(){
        testAnimal.save();
        Sighten testSighten= new Sighten( "zone A", testAnimal.getId());
        testSighten.save();
        Sighten.clearAll();
        assertEquals(0,Sighten.getAll().size());
    }  @Test
    void sighted_instantiateDelete_true(){
        testAnimal.save();
        Sighten testSighten= new Sighten( "zone A", testAnimal.getId());
        testSighten.save();
        Sighten.delete(testSighten.getId());
        assertEquals(0,Sighten.getAll().size());
    }


}