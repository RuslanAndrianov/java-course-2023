package edu.hw11.Task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"MultipleStringLiterals", "MagicNumber"})
public class FibonacciByteCodeAppender implements ByteCodeAppender {
    @Override
    public @NotNull Size apply(
        @NotNull MethodVisitor methodVisitor,
        Implementation.@NotNull Context context,
        @NotNull MethodDescription instrumentedMethod
    ) {
        Label l1 = new Label();

        methodVisitor.visitCode();

        // Если n < 2
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.ICONST_2);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, l1);

        // То вернуть n;
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.I2L);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        // Вернуть fib(n - 1) + fib(n - 2);
        methodVisitor.visitLabel(l1);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitInsn(Opcodes.ISUB);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "MyGeneratedClass", "fib", "(I)J");
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.ICONST_2);
        methodVisitor.visitInsn(Opcodes.ISUB);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "MyGeneratedClass", "fib", "(I)J");
        methodVisitor.visitInsn(Opcodes.LADD);
        methodVisitor.visitInsn(Opcodes.LRETURN);
        methodVisitor.visitEnd();
        return new Size(5, 2);
    }
}
