package guchi.the.hasky.reflection;


import guchi.the.hasky.reflection.victim.Victim;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.InvocationTargetException;

import static junit.framework.TestCase.assertEquals;

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
    }

    @Test
    public void testSetDefaultParameters() throws IllegalAccessException {
        victim = new Victim("Lola", 21, true, 48);
        reflection.setNullValues(victim);

        assertEquals(victim.getName(), newVictim.getName());
        assertEquals(victim.getAge(), newVictim.getAge());
        assertEquals(victim.isHungry(), newVictim.isHungry());
        assertEquals(victim.getWeight(), newVictim.getWeight());
    }

    @Test
    public void testPrintMethodsWithFinalSignatures(){
        String result = reflection.printFinalMethodsSignaturesInfo(victim);
        String newResult = reflection.printFinalMethodsSignaturesInfo(newVictim);

        assertEquals(result, newResult);
    }

    @Test
    public void testSuper() throws InvocationTargetException, IllegalAccessException {
        String result = reflection.printSuperAndInterfaceInfo(victim.getClass());
        String newResult = reflection.printSuperAndInterfaceInfo(newVictim.getClass());

        assertEquals(result, newResult);
    }




}
