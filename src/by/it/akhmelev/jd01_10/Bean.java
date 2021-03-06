package by.it.akhmelev.jd01_10;

public class Bean {

    @Param(a = 2, b = 10)
    static double sum(int a, int b) {
        return a + b;
    }

    @Param(a = 2, b = 10)
    double max(int a, int b) {
        return a > b ? a : b;
    }

    double min(int a, int b) {
        return a < b ? a : b;
    }

    @Param(a = 2, b = 9)
    static double avg(int a, int b) {
        return (a + b) / 2.0;
    }


}
