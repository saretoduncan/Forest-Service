package modules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalsTest {
    Animals testAnimal;
    @BeforeEach
    public void setUp(){
        testAnimal= new Animals("antelope");
    }

    @Test
    void animal_instantiateCorrectly() {
        assertNotNull(testAnimal);
    }
}