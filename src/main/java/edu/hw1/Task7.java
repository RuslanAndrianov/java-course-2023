package edu.hw1;

public class Task7 {
    private Task7() {}

    public static int rotateLeft(int n, int shift) {

        if (n >= 0 && shift >= 0) {

            String[] bits = (Integer.toBinaryString(n)).split("");
            int shiftModArrLength = (shift >= bits.length) ? (shift % bits.length) : shift;
            String[] temp = new String[shiftModArrLength];

            System.arraycopy(bits, 0, temp, 0, shiftModArrLength);
            System.arraycopy(bits, shiftModArrLength, bits, 0, bits.length - shiftModArrLength);
            System.arraycopy(temp, 0, bits, bits.length - shiftModArrLength, shiftModArrLength);

            return Integer.parseInt(String.join("", bits), 2);
        }
        return -1;
    }

    public static int rotateRight(int n, int shift) {

        if (n >= 0 && shift >= 0) {

            String[] bits = (Integer.toBinaryString(n)).split("");
            int shiftModArrLength = (shift >= bits.length) ? (shift % bits.length) : shift;
            String[] temp = new String[shiftModArrLength];

            System.arraycopy(bits, bits.length - shiftModArrLength, temp, 0, shiftModArrLength);
            System.arraycopy(bits, 0, bits, shiftModArrLength, bits.length - shiftModArrLength);
            System.arraycopy(temp, 0, bits, 0, shiftModArrLength);

            return Integer.parseInt(String.join("", bits), 2);
        }
        return -1;
    }
}
