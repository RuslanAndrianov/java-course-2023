package edu.hw11.Task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.MethodVisitor;
import org.jetbrains.annotations.NotNull;

public class FibonacciImplementation implements Implementation {
    @Override
    public @NotNull ByteCodeAppender appender(@NotNull Target implementationTarget) {
        return new FibonacciByteCodeAppender();
    }

    @Override
    public @NotNull InstrumentedType prepare(@NotNull InstrumentedType instrumentedType) {
        return instrumentedType;
    }

    public ByteCodeAppender.Size apply(
        MethodVisitor methodVisitor,
        Implementation.Context implementationContext,
        MethodDescription instrumentedMethod) {

        throw new UnsupportedOperationException();
    }
}
