package bondCalculator;

/**
 * @Time: Feb 17, 2017
 * @Author: zen
 */

public interface BondCalculator {
    
    
    /**
     * @param coupon
     * The coupon rate
     * @param years
     * The time of the bond
     * @param face
     * The face value of the bond
     * @param rate
     * The interest rate
     * @return 
     * The Price of the coupon
     */
    public double CalcPrice(double coupon, int years, double face, double rate);
    
    /**
     * @param coupon
     * The coupon rate
     * @param years
     * The time of the bond
     * @param face
     * The face value of the bond
     * @param price
     * The price of the coupon
     * @return 
     * The yield of the coupon
     */
    public double CalcYield(double coupon, int years, double face, double price);

}
