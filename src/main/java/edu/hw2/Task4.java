package edu.hw2;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task4 {

    public static CallingInfo callInfo() {

        StackTraceElement[] stack = Thread.currentThread().getStackTrace();

        // 0 - getStackTrace
        // 1 - callingInfo
        // 2 - искомая функция
        String className = stack[2].getClassName();
        String methodName = stack[2].getMethodName();

        return new CallingInfo(className, methodName);
    }


    public record CallingInfo(String className, String methodName) {}
}
