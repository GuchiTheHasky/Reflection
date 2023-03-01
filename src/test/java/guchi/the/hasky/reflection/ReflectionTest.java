package guchi.the.hasky.reflection;

import guchi.the.hasky.reflection.victim.Victim;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionTest {
    private Reflection reflection;
    private Victim victim;

    @BeforeEach
    void init() {
        reflection = new Reflection();
        victim = new Victim();
    }

    @Test
    @DisplayName("Test, is new Victim created, and check default values. ")
    public void testCreateNewObject() throws Throwable {
        Victim testVictim = (Victim) reflection.createVictim(victim.getClass());

        assertNotNull(testVictim);
        assertNull(testVictim.getName());
        assertEquals(0, testVictim.getAge());
        assertFalse(testVictim.isHungry());
        assertEquals(0, testVictim.getWeight());
    }

    @Test
    @DisplayName("Test, set default parameters in constructor.")
    public void testSetDefaultParameters() throws IllegalAccessException {
        victim = new Victim("Lola", 21, true, 48);
        reflection.setNullValues(victim);

        assertNull(victim.getName());
        assertEquals(0, victim.getAge());
        assertFalse(victim.isHungry());
        assertEquals(0, victim.getWeight());
    }

    @Test
    @DisplayName("Test, invoke signature of methods with final modifier.")
    public void testPrintMethodsWithFinalSignatures() {
        String expectedString = "finalMethod : [java.lang.String arg0]";
        String actualString = reflection.printFinalMethodsSignaturesInfo(victim);
        System.out.println(expectedString);
        System.out.println(actualString);

        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test, call info about super class & interface.")
    public void testPrintSuperClassAndInterfacesInfo() {
        String expected = "class guchi.the.hasky.reflection.victim.Entity" +
                "\ninterface guchi.the.hasky.reflection.victim.VictimMethods";
        String actual = reflection.printSuperAndInterfaceInfo(victim.getClass());

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("Test, call private methods without parameters.")
    public void testCallPrivateMethodsWithoutParameters() {
        reflection.callPrivateMethods(victim);
        assertTrue(victim.isHungry());
    }

    @Test
    @DisplayName("Test, call private methods with parameter.")
    public void testCallPrivateMethodsWithParameters() {
        reflection.callPrivateMethodsAndSetParameters(victim, 32);
    }

    @Test
    @DisplayName("Test, call all methods without parameters.")
    public void testCallAllMethodsWithoutParameters() {
        reflection.callMethodsWithoutParameters(victim);
        assertTrue(victim.isHungry());
    }

    @Test
    @DisplayName("Test, call all private methods of victim parameters & change their parameters.")
    public void testCallAllPrivateMethodsOfVictim() {
        int argument = 32;
        String expected = "I am private, my age is: " + argument;
        String actual = reflection.callPrivateMethodsAndSetParameters(victim, argument);

        assertEquals(expected, actual);
    }


}
