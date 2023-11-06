import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        checkRightYear(year);
        printResult(calculateMaxPercentForSurviving(getN(year), calculateAndGetYYPercent()));
    }

    static final int CALENDAR_START_YEAR = 2002;
    static final double PERCENT_STEP = 0.005;

    public static void checkRightYear(int year) throws Exception {
        int CALENDAR_FINISH_YEAR = 2021;
        if (year < CALENDAR_START_YEAR) {
            throw new Exception("year must be more than 2001");
        }
        if (year > CALENDAR_FINISH_YEAR) {
            throw new Exception("year must be less than 2022");
        }
    }

    public static double[] calculateAndGetYYPercent() {
        double[] yYPercent = new double[21];
        yYPercent[0] = 1;
        for (int x = 1; x + 1 < yYPercent.length; x++) {
            yYPercent[x] = ((Constants.MOEX_RATE[x]) / Constants.MOEX_RATE[x - 1]);
        }
        return yYPercent;
    }

    public static double calculateMaxPercentForSurviving(int n, double[] yYPercent) {
        int INTEGER_CONVERSION_FACTOR = 100;
        int INTEGER = 1;
        final int START_COUNT_NUMBER = 1;
        double endYear = START_COUNT_NUMBER;
        double inflation;
        double yearSpend;
        double beginYear;
        double percent = 0;
        while (endYear > 0) {
            percent = percent + PERCENT_STEP;
            inflation = START_COUNT_NUMBER;
            yearSpend = percent;
            endYear = START_COUNT_NUMBER;
            for (int x = n; ((x + 1 < Constants.INFLATION_RATE.length)); ) {
                yearSpend = yearSpend * inflation;
                beginYear = endYear - yearSpend;
                inflation = ((Constants.INFLATION_RATE[x]) / INTEGER_CONVERSION_FACTOR + INTEGER);
                endYear = beginYear * yYPercent[x];
                x++;
            }
        }
        return percent;
    }

    public static void printResult(double percent) {
        int INTEGER_CONVERSION_FACTOR = 100;
        if (percent > 0.99)
            System.out.println(String.format("%.1f", percent * INTEGER_CONVERSION_FACTOR));
        else {
            System.out.println(((String.format("%.1f", ((percent - PERCENT_STEP) * INTEGER_CONVERSION_FACTOR)))));
        }
    }

    public static int getN(int year) {
        int n = year - CALENDAR_START_YEAR;
        return n;
    }
}