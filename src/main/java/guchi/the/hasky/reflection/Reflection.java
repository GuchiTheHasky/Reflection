package guchi.the.hasky.reflection;

import guchi.the.hasky.reflection.victim.Victim;
import java.lang.reflect.*;
import java.util.Arrays;

public class Reflection {

    public Object createVictim(Class<?> cls) throws Throwable {
        Constructor<?> constructor = cls.getConstructor();
        return constructor.newInstance();
    }

    public void callMethodsWithoutParameters(Object obj) throws InvocationTargetException, IllegalAccessException {
        Class<?> cls = obj.getClass();
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            if (method.getParameters().length == 0){
                method.invoke(obj);
            }
        }
    }

    public String printFinalMethodsSignaturesInfo(Object obj) {
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getModifiers() == Modifier.FINAL) {
                return method.toGenericString() + "\n" + Arrays.toString(method.getParameters());
            }
        }
        return "Class doesn't have 'Final' in all methods signatures.";
    }

     public void callPrivateMethods(Object obj, int argument) throws InvocationTargetException, IllegalAccessException {
        Class<?> cls = obj.getClass();
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods)
        {
            if (Modifier.isPrivate(method.getModifiers()) && method.getParameterCount() == 0) {
                method.setAccessible(true);
                method.invoke(obj);
            }
            else if (Modifier.isPrivate(method.getModifiers()) && method.getParameterCount() > 0) {
                method.setAccessible(true);
                method.invoke(obj, argument);
            }
        }
    }

    public String printSuperAndInterfaceInfo(Class<?> clazz) {
        StringBuilder builder = new StringBuilder();
        builder.append(clazz.getSuperclass()).append("\n");

        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            builder.append(anInterface.toString());
        }
        return builder.toString();
    }

    public Object setNullValues(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            if (!field.canAccess(obj)){
                field.setAccessible(true);
                if (field.getGenericType().equals(String.class)){
                    field.set(obj, null);
                } else if (field.getGenericType().equals(int.class)){
                    field.set(obj, 0);
                } else if (field.getGenericType().equals(boolean.class)){
                    field.set(obj, false);
                }
            }
        }
        Field[] superFields = clazz.getSuperclass().getDeclaredFields();
        for (Field field : superFields){
            if (!field.canAccess(obj)){
                field.setAccessible(true);
                if (field.getGenericType().equals(int.class)){
                    field.set(obj, 0);
                }
            }
        }
        return obj;
    }


    public static void main(String[] args) throws Throwable {
        Reflection reflection = new Reflection();
        Victim victim = new Victim();


    }
}

/*Reflection:
1. Метод принимает класс и возвращает созданный объект этого класса ++
2. Метод принимает object и вызывает у него все методы без параметров ++
3. Метод принимает object и выводит на экран все сигнатуры методов в который есть final ++
4. Метод принимает Class и выводит все не публичные методы этого класса ++
5. Метод принимает Class и выводит всех предков класса и все интерфейсы которое класс имплементирует ++
6. Метод принимает объект и меняет все его приватные поля на их нулевые значение (null, 0, false etc) ++
*/
