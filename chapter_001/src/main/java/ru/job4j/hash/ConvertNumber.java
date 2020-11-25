package ru.job4j.hash;

public class ConvertNumber {
    public static String binary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 31; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 8 == 0 ? " " : "");
            num /= 2;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        int cap = 100;
        System.out.println(binary(cap));
        System.out.println(binary(cap - 1));
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        System.out.println(binary(n));
        System.out.println();
        cap = 128;
        System.out.println(binary(cap));
        System.out.println(binary(cap - 1));
        n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        System.out.println(binary(n));
    }
}
