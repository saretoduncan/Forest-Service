package modules;

import org.junit.jupiter.api.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class AnimalsTest {
    Animals testAnimal;
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
            connection.createQuery(deleteAnimals)
                    .executeUpdate();

        }

    }

    @Test
    void animal_instantiateCorrectly() {
        assertNotNull(testAnimal);
    }
    @Test
    public void animals_instantiatesWithName_String() {

        assertEquals("antelope", testAnimal.getNAME());
    }
    @Test
    public void Animals_instantiatesWithSave_true() {
         testAnimal.save();
         Animals otherAnimal= new Animals("lion");
         otherAnimal.save();
        assertEquals(Animals.getAll().get(0), testAnimal);
        assertEquals(Animals.getAll().get(1),otherAnimal);
    }
    @Test
    public void Animals_instantiateWithDelete_true(){
        testAnimal.save();
        testAnimal.delete(testAnimal.getId());
        assertEquals(0,Animals.getAll().size());
    }
    @Test
    public void Animals_instateWithDbClearALl(){
        testAnimal.save();
        Animals otherAnimal= new Animals("elephant");
        otherAnimal.save();
        Animals.clearAll();
        assertEquals(0,Animals.getAll().size());
    }
}