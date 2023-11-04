import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        checkRightYear();
        calculateYYPercent();
        while (chanceToSurvive(n) == true) {
            tryMorePercent();
        }
        printResult();
    }

    private static int calendarStartYear = 2002;
    private static double percentStep = 0.005;
    static Scanner scanner = new Scanner(System.in);
    static int year = scanner.nextInt();
    static int n = year - calendarStartYear;
    static double percent = 0.005;
    static double yearSpend;
    static double[] yYPercent = new double[21];
    static double beginYear;
    static int startCountNumber = 1;
    static double endYear = startCountNumber;
    static double inflation = startCountNumber;
    static int integerConversionFactor = 100;
    static int integer = 1;

    public static void checkRightYear() throws Exception {
        if (year < 2002) {
            throw new Exception("year must be more than 2001");
        }
        if (year > 2021) {
            throw new Exception("year must be less than 2022");
        }
    }

    public static void calculateYYPercent() {
        yYPercent[0] = 1;
        for (int x = 1; x + 1 < yYPercent.length; x++) {
            yYPercent[x] = ((Constants.MOEX_RATE[x]) / Constants.MOEX_RATE[x - 1]);
        }
    }

    public static void tryMorePercent() {
        percent = percent + percentStep;
        inflation = startCountNumber;
        yearSpend = percent;
        endYear = startCountNumber;
    }

    public static boolean chanceToSurvive(int n) {
        for (int x = n; ((x + 1 < Constants.INFLATION_RATE.length)); ) {
            yearSpend = yearSpend * inflation;
            beginYear = endYear - yearSpend;
            inflation = ((Constants.INFLATION_RATE[x]) / integerConversionFactor + integer);
            endYear = beginYear * yYPercent[x];
            x++;
        }
        boolean survive = endYear > 0;
        return survive;
    }

    public static void printResult() {
        if (percent > 0.99)
            System.out.println(String.format("%.1f", percent * integerConversionFactor));
        else {
            System.out.println(((String.format("%.1f", ((percent - percentStep) * integerConversionFactor)))));
        }
    }
}