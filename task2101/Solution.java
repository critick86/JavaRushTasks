package com.javarush.task.task21.task2101;

import java.util.Arrays;
import java.util.BitSet;

/*
Определяем адрес сети
*/
public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    private static byte[] getBitArray(byte[] array) {
        byte[] result = new byte[32];
        BitSet bitSet = BitSet.valueOf(array);
        byte[] tempArray = new byte[8];
        int k = 0;
        for (int j = 0; j < 32; ++j) {
            try {
                tempArray[k] = (byte) (bitSet.get(j) ? 1 : 0);
                k++;
            } catch (Exception ignored) {
            }
            if ((j + 1) % 8 == 0) {
                int kk = j;
                for (byte b : tempArray) {
                    result[kk] = b;
                    kk--;
                }
                k = 0;
            }
        }
        return result;
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] netAddress = new byte[4];
        byte[] bitArrayIp = getBitArray(ip);
        byte[] bitArrayMask = getBitArray(mask);
        byte[] preResult = new byte[32];

        for (int i = 0; i < bitArrayIp.length; i++) {
            preResult[i] = (byte) (bitArrayIp[i] & bitArrayMask[i]);
        }
        byte[] results = new byte[(preResult.length + 7) / 8];
        byte byteValue = 0;
        int index;
        for (index = 0; index < preResult.length; index++) {

            byteValue = (byte) ((byteValue << 1) | preResult[index]);

            if (index % 8 == 7) {
                results[index / 8] = (byte) byteValue;
            }
        }

        if (index % 8 != 0) {
            results[index / 8] = (byte) (byteValue << (8 - (index % 8)));
        }
        return results;
    }

    public static void print(byte[] bytes) {
        byte[] bitArray = getBitArray(bytes);
        for (int i = 0; i < bitArray.length; i++) {
            System.out.print(bitArray[i]);
            if ((i + 1) % 8 == 0) System.out.print(" "); //spaces between blocks of 8 bits
        }
        System.out.println();
    }
}
