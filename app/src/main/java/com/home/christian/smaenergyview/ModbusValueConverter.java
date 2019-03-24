package com.home.christian.smaenergyview;

public class ModbusValueConverter {
    public static Integer dataToInt(short[] data, Integer offset) {
        return dataToInt(data, offset, 1)[0];
    }

    public static Integer[] dataToInt(short[] data, Integer offset, Integer count) {
        Integer[] result = new Integer[count];
        for (int i = 0; i < data.length / 2; i++) {
            result[i] += (data[(i * 2)] & 0x0000FFFF);
            result[i] += (data[(i * 2) + 1] & 0x0000FFFF) << 16;
        }
        return result;
    }

    public static long[] dataToLong(short[] data, Integer offset, Integer count) {
        long[] result = new long[count];
        for (int i = 0; i < data.length / 4; i++) {
            result[i] += (data[(i * 2)] & 0x000000000000FFFF);
            result[i] += (data[(i * 2) + 1] & 0x000000000000FFFF) << 16;
            result[i] += (data[(i * 2) + 2] & 0x000000000000FFFF) << 32;
            result[i] += (data[(i * 2) + 3] & 0x000000000000FFFF) << 48;
        }
        return result;
    }

    public static long dataToLong(short[] data, Integer offset) {
        return dataToLong(data, offset, 1)[0];
    }

}
