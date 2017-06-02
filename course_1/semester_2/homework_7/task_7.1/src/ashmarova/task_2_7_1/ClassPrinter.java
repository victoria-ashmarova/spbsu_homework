package ashmarova.task_2_7_1;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

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
        int numberOfTabs = 0;

        getModifiers(toDescribe.getModifiers(), builder);
        getClassName(toDescribe, builder);
        getSuperClass(toDescribe, builder);
        getInterfaces(toDescribe, builder);

        builder.append(" {\n");
        getFields(toDescribe, builder, numberOfTabs + 1);
        builder.append(" \n");
        getConstructor(toDescribe, builder, numberOfTabs + 1);
        builder.append(" \n");
        getMethods(toDescribe, builder, numberOfTabs + 1);
        builder.append("}\n");
        return builder.toString();
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
     * gets simple name of class
     * @param toDescribe is class to describe
     * @param builder to collect string with describing
     */
    private static void getClassName(Class toDescribe, StringBuilder builder) {
        String className = toDescribe.getSimpleName();
        builder.append(" class ").append(className);
    }

    /**
     * gets modifiers of class
     * @param mods is modifiers
     * @param builder to collect string with describing
     */
    private static void getModifiers(int mods, StringBuilder builder) {
        if (Modifier.isPublic(mods)) {
            builder.append("public ");
        }
        if (Modifier.isPrivate(mods)) {
            builder.append("private ");
        }
        if (Modifier.isProtected(mods)) {
            builder.append("protected ");
        }
        if (Modifier.isAbstract(mods)) {
            builder.append("abstract ");
        }
        if (Modifier.isStatic(mods)) {
            builder.append("static ");
        }
        if (Modifier.isNative(mods)) {
            builder.append("native ");
        }
        if (Modifier.isTransient(mods)) {
            builder.append("transient ");
        }
        if (Modifier.isVolatile(mods)) {
            builder.append("volatile ");
        }
    }

    /**
     * gets super class
     * @param toDescribe is class to get its super class
     * @param builder to collet string with describing
     */
    private static void getSuperClass(Class toDescribe, StringBuilder builder) {
        Class superClass = toDescribe.getSuperclass();
        if (!superClass.equals("")) {
            builder.append(" extends ").append(superClass.getSimpleName());
        }
    }

    /**
     * gets interfaces of class
     * @param toDescibe is class to get interface
     * @param builder to collect string with describing
     */
    private static void getInterfaces(Class toDescibe, StringBuilder builder) {
        Class interfaces[] = toDescibe.getInterfaces();
        if (interfaces.length > 0) {
            builder.append("  implements");
            for (int i = 0; i < interfaces.length; i++) {
                builder.append(" ").append(interfaces[i].getSimpleName());
            }
        }
    }

    /**
     * gets constructors of class
     * @param toDescribe is class to get constructors
     * @param builder to collect string with describing
     * @param numberOfTabs is number of tabulations in beginnings if string
     */
    private static void getConstructor(Class toDescribe, StringBuilder builder, int numberOfTabs) {
        Constructor constructors[] = toDescribe.getDeclaredConstructors();
        for (int i = 0; i < constructors.length; i++) {
            addTabs(numberOfTabs, builder);
            builder.append(toDescribe.getSimpleName());
            getParametersOfConstructor(constructors[i], builder);
            getExceptionsOfConstructor(constructors[i], builder);
        }
    }

    /**
     * gets parameters of class constructor
     * @param constructor to get parameter to collect string with information about class
     * @param builder
     */
    private static void getParametersOfConstructor(Constructor constructor, StringBuilder builder) {
        builder.append("(");
        Class paramTypes[] = constructor.getParameterTypes();
        for (int i = 1; i < paramTypes.length; i++) {
            Class paramType = paramTypes[i];
            builder.append(paramType.getSimpleName());
            if (i < paramTypes.length - 1) {
                builder.append(", ");
            }
        }
        builder.append(");\n");
    }

    /**
     * gets exception of class constructor
     * @param constructor to gets exceptions
     * @param builder to collect information about class
     */
    private static void getExceptionsOfConstructor(Constructor constructor, StringBuilder builder) {
        Class exceptions[] = constructor.getExceptionTypes();
        if (exceptions.length > 0) {
            builder.append(" throws");
            for (int i = 0; i < exceptions.length; i++) {
                builder.append(" ").append(exceptions[i].getSimpleName());
                if (i != exceptions.length - 1) {
                    builder.append(",");
                }
            }
        }
    }

    /**
     * gets methods from class
     * @param toDescribe is class to get method
     * @param builder to collect string with information about class
     * @param numberOfTabs is number of tabulations in beginnings if string
     */
    private static void getMethods(Class toDescribe, StringBuilder builder, int numberOfTabs) {
        Method declaredMethods[] = toDescribe.getDeclaredMethods();
        for (Method method : declaredMethods) {
            addTabs(numberOfTabs, builder);
            getModifiers(method.getModifiers(), builder);
            builder.append(method.getReturnType().getName()).append(" ");
            builder.append(method.getName());
            getParametersOfMethod(method, builder);
            getExceptionsOfMethod(method, builder);
            builder.append(";\n");
        }
    }

    /**
     * gets parameters of method
     * @param method to get parameters
     * @param builder to collect string with information about class
     */
    private static void getParametersOfMethod(Method method, StringBuilder builder) {
        Class paramTypes[] = method.getParameterTypes();
        builder.append("(");
        for (int i = 0; i < paramTypes.length; i++) {
            Class paramType = paramTypes[i];
            if (i != 0) {
                builder.append(", ");
            }
            builder.append(paramType.getSimpleName());
        }
        builder.append(")");

    }

    /**
     * gets exception of method
     * @param method to gets exceptions
     * @param builder to collect information about class
     */
    private static void getExceptionsOfMethod(Method method, StringBuilder builder) {
        Class exceptions[] = method.getExceptionTypes();
        if (exceptions.length > 0) {
            builder.append(" throws");
            for (int i = 0; i < exceptions.length; i++) {
                builder.append(" ").append(exceptions[i].getSimpleName());
                if (i != exceptions.length - 1) {
                    builder.append(",");
                }
            }
        }
    }

    /**
     * gets fields of class
     * @param toDescribe is class to get information
     * @param builder to collect information about class
     * @param numberOfTabs is number of tabulations in beginnings if string
     */
    private static void getFields(Class toDescribe, StringBuilder builder, int numberOfTabs) {
        Field declaredFields[] = toDescribe.getDeclaredFields();
        for (int i = 0; i < declaredFields.length - 1; i++) {
            Field field = declaredFields[i];
            addTabs(numberOfTabs, builder);
            Class fieldType = field.getType();
            getModifiers(field.getModifiers(), builder);
            builder.append(fieldType.getSimpleName()).append(" ").append(field.getName());
            builder.append(";\n");
        }
    }
}
