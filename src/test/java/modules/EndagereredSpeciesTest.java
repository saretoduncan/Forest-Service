package modules;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class EndagereredSpeciesTest {
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
            connection.createQuery(deleteAnimals)
                    .executeUpdate();
            connection.createQuery(deleteSpecies).executeUpdate();

        }

    }
    @Test
    void endangeredSpecies_instantiateCorrectly() {
        testAnimal.save();
        testEndangered = new EndagereredSpecies("healthy","young", testAnimal.getId());
      assertTrue(testEndangered instanceof EndagereredSpecies);
    }
    @Test
    void endangeredSpecies_instantiate_getters(){
        testAnimal.save();
        testEndangered = new EndagereredSpecies("healthy","young", testAnimal.getId());
        assertEquals(testAnimal.getId(),testEndangered.getANIMAL_ID());
        assertEquals("healthy",testEndangered.getHEALTH());
        assertEquals("young",testEndangered.getAGE());

    }
    @Test
    public void endangered_instantiateWithDelete_true(){
        testAnimal.save();
        testEndangered = new EndagereredSpecies("healthy","young", testAnimal.getId());
        testEndangered.save();
        EndagereredSpecies.delete(testEndangered.getId());
        assertEquals(0,EndagereredSpecies.getAll().size());
    }
    @Test
    public void endangered_instantiateFindById_true(){
        testAnimal.save();
        testEndangered = new EndagereredSpecies("healthy","young", testAnimal.getId());
        testEndangered.save();
        System.out.println(testEndangered.getId());
        System.out.println(EndagereredSpecies.findById(testEndangered.getId()));
        assertEquals( EndagereredSpecies.findById(testEndangered.getId()),testEndangered);
    }
    @Test
    public void Endangered_instantiatesWithSave_true() {
        testAnimal.save();
        Animals otherAnimal= new Animals("lion");
        otherAnimal.save();
        testEndangered = new EndagereredSpecies("healthy","young", testAnimal.getId());
        testEndangered.save();
        EndagereredSpecies testEndangered2 = new EndagereredSpecies("healthy", "old", otherAnimal.getId());
        testEndangered2.save();
        assertEquals(EndagereredSpecies.getAll().get(0),testEndangered);
        assertEquals(EndagereredSpecies.getAll().get(1),testEndangered2);
    }
    @Test
    public void Endegered_InstantiatesWithClearAll_true(){
        testAnimal.save();
        Animals otherAnimal= new Animals("lion");
        otherAnimal.save();
        testEndangered = new EndagereredSpecies("healthy","young", testAnimal.getId());
        testEndangered.save();
        EndagereredSpecies.clearAll();
        assertEquals(0,EndagereredSpecies.getAll().size());
    }



}