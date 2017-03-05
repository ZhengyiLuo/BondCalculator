package bondCalculator;
/**
 * @Time: Feb 17, 2017
 * @Author: zen
 */

public class Calculator implements BondCalculator {
    final double DIFFERENCE = 0.000000001;
    public String output;

    @Override
    public double CalcPrice(double coupon, int years, double face, double rate) {
        long startTime = System.nanoTime();
        double discont = Math.pow(rate + 1, years);
        
        // Summing the given equation using the formula of the product of
        // geometric series, we can get this formula
        // PV = C * (1/r - 1/(r(1+r)^y)) + FV/((1+r)^y) where C = coupon, r =
        // rate, y = years, and FV = face value
        //By using this formula, we can avoid using complicated loops in our program.
        double result = face * coupon * (1 / rate - 1 / (rate * discont)) + face / discont;
        long endtime = System.nanoTime();
        
        output = new String("Time Elapsed in millseconds: " + Long.toString(endtime - startTime));
        return result;
    }

    @Override
    public double CalcYield(double coupon, int years, double face, double price) {
        long startTime = System.nanoTime();
        long endtime = 0;
        double rate = 1;
        double result = 1;
        double discont = 0;
        double area = 0.5;
       
        // Use binary search to find the best approximation
        while (Math.abs(result) > DIFFERENCE) {
            discont = Math.pow(rate + 1, years);
            result = face * coupon * (1 / rate - 1 / (rate * discont)) + face / discont - price;
            if (result > 0) {
                rate += area;
            } else {
                rate -= area;
            }
            area /= 2;
            
            //Time out the program if the calculation takes more than 5 seconds
            if (System.nanoTime() - startTime > 5E9) {
            	rate = 0.0;
            	output = "Application Timeout, please enter a valid input";
				return rate;
			}
        }

        //Since the program is using binary search to a precision of 10^(-7), and the calculation for each operation
        //is almost unit time, so the total for the alogrithm is small.
        endtime = System.nanoTime();
        output = new String("Time Elapsed in millseconds: " + Long.toString(endtime - startTime));
        return rate;
    }

}
