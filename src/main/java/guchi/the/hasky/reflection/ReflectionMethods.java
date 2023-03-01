package guchi.the.hasky.reflection;


public interface ReflectionMethods {
    /**
     * Method accepts class and returns its object.
     */
    Object createVictim(Class<?> clazz) throws Throwable;

    /**
     * Method accepts Object and invoke all his methods(without parameters);
     * if this, hasn't acces to invoke methods, throw new InvocationTargetException;
     * if this, invokes method with some exception, throw new InvocationTargetException.
     */
    void callMethodsWithoutParameters(Object object);

    /**
     * Method accepts Object and return String signature,
     * of all obj methods with access modifier: "final".
     */
    String printFinalMethodsSignaturesInfo(Object object);

    /**
     * Method accepts Object and invoke methods with private acces modifier;
     * can throw new (IllegalAccessException | InvocationTargetException).
     */
    void callPrivateMethods(Object object);

    /**
     * Method accepts Object obj & Object[] args and
     * invoke methods with private acces modifier, set args & return String;
     * can throw new (IllegalAccessException | InvocationTargetException).
     */
    String callPrivateMethodsAndSetParameters(Object object, Object[] argument);

    /**
     * The method accept a Class and shows all the class's
     * ancestors and all the interfaces that the class implements;
     */
    String printSuperAndInterfaceInfo(Class<?> clazz);

    /**
     * The method accept Object and changes all its private fields to their null value (null, 0, false etc);
     * can throw new IllegalAccessException.
     */
    Object setNullValues(Object object) throws IllegalAccessException;
}
