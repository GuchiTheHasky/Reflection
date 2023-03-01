package guchi.the.hasky.reflection;

import java.lang.reflect.*;
import java.util.Arrays;

public class Reflection implements ReflectionMethods {

    @Override
    public Object createVictim(Class<?> clazz) throws Throwable {
        Constructor<?> constructor = clazz.getConstructor();
        return constructor.newInstance();
    }

    @Override
    public void callMethodsWithoutParameters(Object object) {
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            if (method.getParameters().length == 0) {
                try {
                    method.invoke(object);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public String printFinalMethodsSignaturesInfo(Object object) {
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        StringBuilder builder = new StringBuilder();
        for (Method method : methods) {
            if (method.getModifiers() == Modifier.FINAL) {
                builder.append(method.getName()).append(" : ").append(Arrays.toString(method.getParameters()));
            }
        }
        return builder.toString();
    }

    @Override
    public void callPrivateMethods(Object object) {
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (Modifier.isPrivate(method.getModifiers()) && method.getParameterCount() == 0) {
                method.setAccessible(true);
                try {
                    method.invoke(object);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public String callPrivateMethodsAndSetParameters(Object object, Object... argument) {
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (Modifier.isPrivate(method.getModifiers()) && method.getParameterCount() > 0) {
                method.setAccessible(true);
                StringBuilder builder = new StringBuilder();
                try {
                    builder.append(method.invoke(object, argument));
                    return builder.toString();
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    @Override
    public String printSuperAndInterfaceInfo(Class<?> clazz) {
        StringBuilder builder = new StringBuilder();
        builder.append(clazz.getSuperclass()).append("\n");
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            builder.append(anInterface.toString());
        }
        return builder.toString();
    }

    @Override
    public Object setNullValues(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.canAccess(object)) {
                field.setAccessible(true);
                if (field.getGenericType().equals(String.class)) {
                    field.set(object, null);
                } else if (field.getGenericType().equals(int.class)) {
                    field.set(object, 0);
                } else if (field.getGenericType().equals(boolean.class)) {
                    field.set(object, false);
                }
            }
        }
        Field[] superFields = clazz.getSuperclass().getDeclaredFields();
        for (Field field : superFields) {
            if (!field.canAccess(object)) {
                field.setAccessible(true);
                if (field.getGenericType().equals(int.class)) {
                    field.set(object, 0);
                }
            }
        }
        return object;
    }
}
