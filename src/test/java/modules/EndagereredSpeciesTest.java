package modules;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class EndagereredSpeciesTest {
    EndagereredSpecies testAnimal;


    @BeforeAll
    public static void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "softwaredev", "k1pk0sg31");

    }

    @BeforeEach
    public void setUp() {
        testAnimal = new EndagereredSpecies("antelope", "healthy", "young");

    }

    @AfterEach
    public void after() {
        try (Connection connection = DB.sql2o.open()) {
            String deleteAnimals = "DELETE FROM animals *;";
            String deleteSighten= "DELETE FROM sighting *";
            connection.createQuery(deleteAnimals)
                    .executeUpdate();
            connection.createQuery(deleteSighten).executeUpdate();


        }

    }

    @Test
    void endangeredSpecies_instantiateCorrectly() {

        assertTrue(testAnimal instanceof EndagereredSpecies);
    }
    @Test
    void endangeredSpecies_instantiate_getters(){


        assertEquals("antelope", testAnimal.getName());
        assertEquals("healthy",testAnimal.getHEALTH());
        assertEquals("young",testAnimal.getAGE());

    }
    @Test
    public void endangered_instantiateWithDelete_true(){
        testAnimal.save();


        testAnimal.delete(testAnimal.getId());
        assertEquals(0, EndagereredSpecies.getAll().size());
    }
//
    @Test
    public void endangered_instantiateFindById_true(){
        testAnimal.save();
        EndagereredSpecies testEndangered = new EndagereredSpecies("Elephant","weak", "old");
        testEndangered.save();
        System.out.println(testEndangered.getId());
        System.out.println(EndagereredSpecies.findById(testEndangered.getId()).getName());
        assertEquals( EndagereredSpecies.findById(testEndangered.getId()),testEndangered);
    }

    @Test
    public void Endegered_InstantiatesWithClearAll_true(){
        testAnimal.save();

        EndagereredSpecies testEndangered = new EndagereredSpecies("Elephant","weak", "old");
        testEndangered.save();
        EndagereredSpecies.clearAll();
        assertEquals(0, EndagereredSpecies.getAll().size());
    }

}