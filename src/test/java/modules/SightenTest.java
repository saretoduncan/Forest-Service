package modules;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.security.Timestamp;
import java.text.DateFormat;
import java.util.Date;

import static java.time.LocalTime.now;
import static org.junit.jupiter.api.Assertions.*;

class SightenTest {
    EndagereredSpecies testAnimal;
    Sighten testSighting;

    @BeforeAll
    public static void before(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "softwaredev", "k1pk0sg31");

    }
    @BeforeEach
    public void setUp(){
        testAnimal = new EndagereredSpecies("antelope", "healthy", "young");
        testAnimal.save();
        testSighting= new Sighten("duncan","zone A", testAnimal.getId());

    }
    @AfterEach
    public void after(){
        try(Connection connection= DB.sql2o.open()){
            String deleteAnimals= "DELETE FROM animals *;";

            String deleteSighten= "DELETE FROM sighting *";
            connection.createQuery(deleteAnimals).executeUpdate();

            connection.createQuery(deleteSighten).executeUpdate();

        }

    }
    @Test
    void endangeredSpecies_instantiateCorrectly_true(){


        assertTrue(testSighting instanceof Sighten);



    }
    @Test
    void sighten_instantiate_getters(){

        assertEquals( testAnimal.getId(),testSighting.getANIMALID());
        assertEquals("zone A",testSighting.getLOCATION());



    }
    @Test
    void sighted_instantiateSave_true(){

        testSighting.save();

        assertEquals(Sighten.getAll().get(0).getId(),testSighting.getId());
    }
    @Test
    void sighted_instantiateClearAll_true(){

        testSighting.save();
        Sighten.clearAll();
        assertEquals(0,Sighten.getAll().size());
    }
    @Test
    void sighted_instantiateDelete_true(){


        testSighting.save();
        Sighten.delete(testSighting.getId());
        assertEquals(0,Sighten.getAll().size());
    }
    @Test
    void save_instanceOfLastSeen_true(){
        testSighting.save();
        Timestamp timeSighting = Sighten.findById(testSighting.getId()).getLastSeen();
        long rightNow = new Date().getTime();
        assertEquals(DateFormat.getDateInstance().format(rightNow), DateFormat.getDateInstance().format(timeSighting));
    }


}