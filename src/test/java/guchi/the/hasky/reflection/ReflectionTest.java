package guchi.the.hasky.reflection;


import guchi.the.hasky.reflection.victim.Victim;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;


public class ReflectionTest {
    Reflection reflection = new Reflection();
    Victim victim = new Victim();
    Victim newVictim = (Victim) reflection.createVictim(victim.getClass());

    public ReflectionTest() throws Throwable {
    }

    @DisplayName("Test, is new Victim created")
    @Test
    public void testCreateNewVictim() {
        assertEquals(victim.getAge(), newVictim.getAge());
        assertEquals(victim.getWeight(), newVictim.getWeight());
        assertEquals(victim.isAngry(), newVictim.isAngry());

        assertNotNull(newVictim);
    }

    @DisplayName("Test, set default parameters in constructor.")
    @Test
    public void testSetDefaultParameters() throws IllegalAccessException {
        victim = new Victim("Lola", 21, true, 48);
        reflection.setNullValues(victim);

        assertEquals(victim.getName(), newVictim.getName());
        assertEquals(victim.getAge(), newVictim.getAge());
        assertEquals(victim.isHungry(), newVictim.isHungry());
        assertEquals(victim.getWeight(), newVictim.getWeight());
    }

    @DisplayName("Test, invoke methods with final signature.")
    @Test
    public void testPrintMethodsWithFinalSignatures() {
        String result = reflection.printFinalMethodsSignaturesInfo(victim);
        String newResult = reflection.printFinalMethodsSignaturesInfo(newVictim);

        assertEquals(result, newResult);
    }

    @DisplayName("Test, call info about super class & interface.")
    @Test
    public void testPrintSuperClassAndInterfacesInfo() {
        String result = reflection.printSuperAndInterfaceInfo(victim.getClass());
        String newResult = reflection.printSuperAndInterfaceInfo(newVictim.getClass());

        assertEquals(result, newResult);
    }

    @DisplayName("Test, call methods without parameters.")
    @Test
    public void testCallMethodsWithoutParameters() throws InvocationTargetException, IllegalAccessException {
        Reflection reflection1 = new Reflection();
        Victim anotherVictim = new Victim("Ivan", 35, false, 90);

        reflection1.callMethodsWithoutParameters(anotherVictim);
        assertTrue(anotherVictim.isHungry());
    }
    @DisplayName("Test, call all private methods of victim parameters & change their parameters.")
    @Test
    public void testCallAllPrivateMethodsOfVictim() throws InvocationTargetException, IllegalAccessException {
        reflection.callPrivateMethods(victim, 10);
        assertTrue(victim.isHungry());
    }


}
