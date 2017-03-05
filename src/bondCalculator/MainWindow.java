package bondCalculator;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;

/**
 * @Time: Feb 17, 2017
 * @Author: zen
 */

@SuppressWarnings("serial")
public class MainWindow extends Frame implements ActionListener, WindowListener {
	public final String README = "User Manual:\n Use the switch key to switch between input and output calcualtor.\n After you entered all the input, click Calculate to output the result\n Note that the application will timeout in the Yield"
			+ "culator when there is an invalid input\n"
			+ "The running time of each call to the application will be append at the end of the output.";
	private Button btn_Switch;
	private boolean toggle = false;

	private Label lb_CouponRate;
	private Label lb_Years;
	private Label lb_Face;
	private Label lb_Rate;
	private Label lb_Price;

	private TextArea are_readMe;
	private TextField fld_Coupon;
	private TextField fld_Years;
	private TextField fld_Face;
	private TextField fld_Rate;
	private TextField fld_Price;
	private TextField fld_Output;

	private Panel pnl_Main;
	private Panel pnl_Str;
	private Panel pnl_Fld;

	private Button btn_Calc;

	static Calculator calculator;

	public MainWindow() {

		setResizable(true);
		setLayout(new BoxLayout(this, 1));

		
		//Setting up the layout of the application
		are_readMe = new TextArea();
		add(are_readMe);
		are_readMe.setText(README);
		btn_Switch = new Button("Switch To Calculate Yield");
		btn_Calc = new Button("Claculate");

		add(btn_Switch);

		pnl_Main = new Panel();
		pnl_Fld = new Panel();
		pnl_Str = new Panel();

		pnl_Main.setLayout(new BoxLayout(pnl_Main, 2));
		pnl_Fld.setLayout(new BoxLayout(pnl_Fld, BoxLayout.Y_AXIS));
		pnl_Str.setLayout(new BoxLayout(pnl_Str, BoxLayout.Y_AXIS));

		lb_CouponRate = new Label("Enter an Coupon Rate: ");

		fld_Coupon = new TextField(10);
		fld_Coupon.addActionListener(this);

		lb_Years = new Label("Enter an Years: ");

		fld_Years = new TextField(10);
		fld_Years.addActionListener(this);

		lb_Face = new Label("Enter an Face Value: ");

		fld_Face = new TextField(10);
		fld_Face.addActionListener(this);

		lb_Rate = new Label("Enter an Yield/Rate: ");

		fld_Rate = new TextField(10);
		fld_Rate.addActionListener(this);

		lb_Price = new Label("Enter an Price: ");

		fld_Price = new TextField(10);
		fld_Price.addActionListener(this);

		fld_Output = new TextField(10);
		fld_Output.addActionListener(this);

		pnl_Main.add(pnl_Str);
		pnl_Main.add(pnl_Fld);
		add(pnl_Main);
		add(fld_Output);

		pnl_Str.add(lb_CouponRate);
		pnl_Str.add(lb_Years);
		pnl_Str.add(lb_Face);
		pnl_Str.add(lb_Rate);
		pnl_Str.add(lb_Price);
		pnl_Fld.add(fld_Coupon);
		pnl_Fld.add(fld_Years);
		pnl_Fld.add(fld_Face);
		pnl_Fld.add(fld_Rate);
		pnl_Fld.add(fld_Price);

		add(btn_Calc);
		
		//Setting up the listeners
		btn_Calc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toggle) {
					double couponRate = 0;
					int years = 0;
					double face = 0;
					double rate = 0;
					double price = 0;
					try {
						couponRate = Double.parseDouble(fld_Coupon.getText());
						years = Integer.parseInt(fld_Years.getText());
						face = Double.parseDouble(fld_Face.getText());
						rate = Double.parseDouble(fld_Rate.getText());
						
						if (couponRate < 0 || face < 0 || rate < 0 || years < 0) {
							throw new NumberFormatException();
						}
						price = calculator.CalcPrice(couponRate, years, face, rate);
						fld_Output.setText("The price of Bond is: " + price + " " + calculator.output);
			
					} catch (NumberFormatException e1) {
						fld_Output.setText("Please enter a valid number");
						
					} 

				

				} else {
					double couponRate = 0;
					int years = 0;
					double face = 0;
					double rate = 0;
					double price = 0;
					try {
						couponRate = Double.parseDouble(fld_Coupon.getText());
						years = Integer.parseInt(fld_Years.getText());
						face = Double.parseDouble(fld_Face.getText());
						price = Double.parseDouble(fld_Price.getText());
						
						if (couponRate < 0 || face < 0 || rate < 0 || years < 0) {
							throw new NumberFormatException();
						}

						rate = calculator.CalcYield(couponRate, years, face, price);
						fld_Output.setText("The yield of Bond is: " + rate + "  " + calculator.output);
					} catch (NumberFormatException excep) {
						fld_Output.setText("Please enter a valid number");
					}

					
				}
			}

		});

		btn_Switch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (toggle) {
					btn_Switch.setLabel("Switch To Calculate Price");
					fld_Price.setEnabled(true);
					fld_Rate.setEnabled(false);
				} else {
					btn_Switch.setLabel("Switch To Calculate Yield");
					fld_Price.setEnabled(false);
					fld_Rate.setEnabled(true);

				}
				toggle = !toggle;
			}
		});

		//Setting the fileds to default state.
		if (toggle) {
			btn_Switch.setLabel("Switch To Calculate Price");
			fld_Price.setEnabled(true);
			fld_Rate.setEnabled(false);
		} else {
			btn_Switch.setLabel("Switch To Calculate Yield");
			fld_Price.setEnabled(false);
			fld_Rate.setEnabled(true);

		}
		toggle = !toggle;
		addWindowListener(this);

		setTitle("Calculator"); 
		setSize(500, 500);  
		setVisible(true); 
	}

	// The entry main() method
	public static void main(String[] args) {
		calculator = new Calculator();
		new MainWindow();
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
	}

	@Override
	public void windowClosing(WindowEvent evt) {
		System.exit(0); // Terminate the program
	}

	@Override
	public void windowOpened(WindowEvent evt) {
		
	}

	@Override
	public void windowClosed(WindowEvent evt) {
	}

	@Override
	public void windowIconified(WindowEvent evt) {
	}

	@Override
	public void windowDeiconified(WindowEvent evt) {
	}

	@Override
	public void windowActivated(WindowEvent evt) {

	}

	@Override
	public void windowDeactivated(WindowEvent evt) {
	}
}
