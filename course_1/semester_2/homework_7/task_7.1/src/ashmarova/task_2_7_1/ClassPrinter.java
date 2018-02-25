package ashmarova.task_2_7_1;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class, which contains methods of getting information about class
 */
public class ClassPrinter {
    private final static String TAB = "    ";

    /**
     * Returns string with class describing
     * @param toDescribe is class, which is need to describe
     * @return string with describing class
     */
    public static String getInformationAboutClass(Class toDescribe) {
        StringBuilder builder = new StringBuilder();

        TreeSet<String> modifiers = getModifiers(toDescribe.getModifiers());
        String className = toDescribe.getSimpleName();
        String superClass = toDescribe.getSuperclass().getSimpleName();
        TreeSet<String> interfaces = getInterfaces(toDescribe);

        bondingStrings(modifiers, builder, false);
        builder.append(" ").append(className);
        if (!superClass.equals(null)) {
            builder.append(" extends ").append(superClass);
        }
        if (interfaces.size() != 0) {
            builder.append(" implements");
            bondingStrings(interfaces, builder, true);
        }

        builder.append(" {\n");
        addConstructors(builder, toDescribe);
        addFields(builder, toDescribe);
        addMethods(builder, toDescribe);
        builder.append("}");
        return builder.toString();
    }

    /**
     * adds fields to string builder
     * @param builder is builder to add
     * @param toDescribe is class to get fields
     */
    private static void addFields(StringBuilder builder, Class toDescribe) {
        Field fields[] = toDescribe.getDeclaredFields();
        for (Field field : fields) {
            TreeSet<String> fieldModifiers = getModifiers(field.getModifiers());
            addTabs(1, builder);
            bondingStrings(fieldModifiers, builder, false);
            builder.append(" ").append(field.getType().getSimpleName()).append(" ").append(field.getName()).append(";\n");
        }
    }

    /**
     * adds methods to string builder
     * @param builder is builder to add
     * @param toDescribe is class to get methods
     */
    private static void addMethods(StringBuilder builder, Class toDescribe) {
        Method[] declaredMethods = toDescribe.getDeclaredMethods();
        for (Method method : declaredMethods) {
            TreeSet<String> modifiersOfMethod = getModifiers(method.getModifiers());
            TreeSet<String> exceptions = getExceptionsOfMethod(method);
            TreeSet<String> params = getParametersOfMethod(method);

            addTabs(1, builder);
            bondingStrings(modifiersOfMethod, builder, false);
            builder.append(" ").append(method.getName());

            builder.append("(");
            if (params.size() != 0) {
                bondingStrings(params, builder, true);
            }
            builder.append(")");
            if (exceptions.size() != 0) {
                builder.append(" throws ");
                bondingStrings(exceptions, builder, true);
            }
            builder.append(";\n");
        }
    }

    /**
     * adds constructors to string builder
     * @param builder is builder to add
     * @param toDescribe is class to get constructors
     */
    private static void addConstructors(StringBuilder builder, Class toDescribe) {
        Constructor constructors[] = toDescribe.getConstructors();
        for (Constructor constructor : constructors) {
            TreeSet<String> constructorsModifiers = getModifiers(constructor.getModifiers());
            TreeSet<String> constructorsException = getExceptionsOfConstructor(constructor);
            TreeSet<String> constructorsParams = getParametersOfConstructor(constructor);
            addTabs(1, builder);
            bondingStrings(constructorsModifiers, builder, false);
            builder.append(" ").append(toDescribe.getSimpleName()).append(" ");
            if (!constructorsException.isEmpty()) {
                builder.append(" throws");
                bondingStrings(constructorsException, builder, true);
            }
            builder.append("(");
            if (!constructorsParams.isEmpty()) {
                bondingStrings(constructorsParams, builder, true);
            }
            builder.append(");\n");
        }
    }

    /**
     * Adds tabulation to beginnings of lines
     * @param numberOfTubs is number of tabulations in beginnings if string
     * @param builder to make string
     */
    private static void addTabs(int numberOfTubs, StringBuilder builder) {
        for (int i = 0; i < numberOfTubs; i++) {
            builder.append(TAB);
        }
    }

    /**
     * gets modifiers of class
     * @param mods is modifiers
     */
    public static TreeSet<String> getModifiers(int mods) {
        TreeSet<String> modifiers = new TreeSet<>();
        if (Modifier.isPublic(mods)) {
            modifiers.add("public");
        }
        if (Modifier.isPrivate(mods)) {
            modifiers.add("private");
        }
        if (Modifier.isProtected(mods)) {
            modifiers.add("protected");
        }
        if (Modifier.isAbstract(mods)) {
            modifiers.add("abstract");
        }
        if (Modifier.isStatic(mods)) {
            modifiers.add("static");
        }
        if (Modifier.isNative(mods)) {
            modifiers.add("native");
        }
        if (Modifier.isTransient(mods)) {
            modifiers.add("transient");
        }
        if (Modifier.isVolatile(mods)) {
            modifiers.add("volatile");
        }
        if (Modifier.isFinal(mods)) {
            modifiers.add("final");
        }
        return modifiers;
    }

    /**
     * gets interfaces of class
     * @param toDescribe is class to get interface
     */
    public static TreeSet<String> getInterfaces(Class toDescribe) {
        TreeSet<String> interfacesSet = new TreeSet<>();
        Class interfaces[] = toDescribe.getInterfaces();
        for (Class currentInterface : interfaces) {
            interfacesSet.add(currentInterface.getSimpleName());
        }
        return interfacesSet;
    }

    /**
     * gets parameters of class constructor
     * @param constructor to get parameter to collect string with information about class
     */
    public static TreeSet<String> getParametersOfConstructor(Constructor constructor) {
        TreeSet<String> constructorsParameters = new TreeSet<>();
        Class paramTypes[] = constructor.getParameterTypes();
        for (int i = 1; i < paramTypes.length; i++) {
            Class paramType = paramTypes[i];
            constructorsParameters.add(paramType.getSimpleName());
        }
        return constructorsParameters;
    }

    /**
     * gets exception of class constructor
     * @param constructor to gets exceptions
     */
    public static TreeSet<String> getExceptionsOfConstructor(Constructor constructor) {
        TreeSet<String> constructorsExceptions = new TreeSet<>();
        Class exceptions[] = constructor.getExceptionTypes();
        if (exceptions.length > 0) {
            for (int i = 0; i < exceptions.length; i++) {
                constructorsExceptions.add(exceptions[i].getSimpleName());
            }
        }
        return constructorsExceptions;
    }

    /**
     * gets methods from class
     * @param toDescribe is class to get method
     */
    public static TreeSet<String> getMethods(Class toDescribe) {
        TreeSet<String> methods = new TreeSet<>();
        Method declaredMethods[] = toDescribe.getDeclaredMethods();
        for (Method method : declaredMethods) {
            methods.add(method.getReturnType().getSimpleName() + " " + method.getName());
        }
        return methods;
    }

    /**
     * gets parameters of method
     * @param method to get parameters
     */
    public static TreeSet<String> getParametersOfMethod(Method method) {
        TreeSet<String> parameters = new TreeSet<>();
        Class paramTypes[] = method.getParameterTypes();
        for (Class param : paramTypes) {
            parameters.add(param.getSimpleName());
        }
        return parameters;
    }

    /**
     * gets exception of method
     * @param method to gets exceptions
     */
    public static TreeSet<String> getExceptionsOfMethod(Method method) {
        TreeSet<String> methodsExceptions = new TreeSet<>();
        Class exceptions[] = method.getExceptionTypes();
        if (exceptions.length > 0) {
            for (int i = 0; i < exceptions.length; i++) {
                methodsExceptions.add(exceptions[i].getSimpleName());
            }
        }
        return methodsExceptions;
    }

    /**
     * gets fields of class
     * @param toDescribe is class to get information
     */
    public static TreeSet<String> getFields(Class toDescribe) {
        TreeSet<String> fields = new TreeSet<>();
        Field declaredFields[] = toDescribe.getDeclaredFields();
        for (int i = 0; i < declaredFields.length - 1; i++) {
            Field field = declaredFields[i];
            Class fieldType = field.getType();
            fields.add(fieldType.getSimpleName());
        }
        return fields;
    }

    /**
     * bonds strings from set with string
     * @param set isSetWithStrings
     * @param builder to collectStrings
     * @param comma if strings must be separated with comma
     */
    public static void bondingStrings(Set<String> set, StringBuilder builder, boolean comma) {
        int counter = 0;
        for (String toAdd : set) {
            builder.append(toAdd);
            counter++;
            if (counter < set.size()) {
                if (comma) {
                    builder.append(",");
                }
                builder.append(" ");
            }
        }
    }
}
