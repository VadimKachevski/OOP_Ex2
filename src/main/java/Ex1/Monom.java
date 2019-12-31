
package Ex1;

import java.util.Comparator;


/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp()
	{
		return _Comp;
	}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	/**
	 * f
	 * This function returns - the Function value with the given x 
	 */
	public double f(double x) { // returns the value of the monom with given X
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	public Monom(String s)
	{
		String orgString = s;
		s = s.replaceAll("\\s+","");
		int sign = 1;
		if(s==null || s.length() == 0)
		{
			this._coefficient = 0;
			this._power = 0;
			return;
		}
		s=s.replace('X', 'x');
		int x_Index=s.indexOf('x');
		int power_Index = s.indexOf('^');
		if((x_Index == -1 && power_Index != -1) || (x_Index ==1 && power_Index == 1 && x_Index+1 != power_Index) || s.compareTo("-")==0)
		{
			throw new RuntimeException("The Monom: "+ orgString +" is not valid has to be in the format of aX^b");	
		}
		if(x_Index != -1 && power_Index == -1 && x_Index != s.length()-1)
		{
			throw new RuntimeException("The Monom: "+ orgString +" is not valid has to be in the format of aX^b");	
		}
		if(s.charAt(0) == '-')
		{
			sign = -1;
			s = s.substring(1);
			x_Index=s.indexOf('x');
			power_Index = s.indexOf('^');
		}
		if(x_Index != -1)
		{
			try 
			{
				if(s.charAt(0) == 'x')
				{
					this._coefficient = 1*sign;
				}
				else
				{
					this._coefficient = Double.parseDouble(s.substring(0,x_Index)) * sign;
				}
			}
			catch(Exception e)
			{
				throw new RuntimeException("The Monom: "+ orgString +" is not valid has to be in the format of aX^b");	
			}
		}
		else
		{
			try
			{
				this._coefficient = Double.parseDouble(s) * sign;
				this._power = 0;
				return;
			}
			catch (Exception e) {
				throw new RuntimeException("The Monom: "+ orgString +" is not valid has to be in the format of aX^b");	
			}
		}
		if(power_Index != -1)
		{
			try
			{
				this._power = Integer.parseInt(s.substring(power_Index+1));
			}
			catch (Exception e) {
				throw new RuntimeException("The Monom: "+ orgString +" is not valid has to be in the format of aX^b");	
			}
		}
		else
		{
			if(x_Index == -1)
			{
			this._power = 0;
			}
			else
			{
				this._power = 1;
			}
		}
	}
	public void add(Monom m)
	{
		if(this._power == m._power)
		{
			this._coefficient+= m._coefficient;
		}
	}
	public void substract(Monom m)
	{
		if(this._power == m._power)
		{
			this._coefficient-= m._coefficient;
		}
	}
	public void multipy(Monom d) 
	{
		this._coefficient = d._coefficient*this._coefficient;
		this._power = d.get_power()+this._power;
	}
	/**
	 * toString
	 * this function returns visual presentation of the Monom as a String
	 */
	public String toString() {
		if(this._coefficient == 0)
		{
			return 0+"";
		}
		if(this.get_power()==0)
		{
			return  this._coefficient+"";

		}
		if(this.get_power()==1)
		{
			return this._coefficient+"x";

		}
		return this._coefficient+"x^"+this.get_power();
	}
	@Override
	public boolean equals(Object other) {

		if(other instanceof Monom)
		{
			Monom m = (Monom) other;
			if(this._coefficient==0 && m._coefficient==0)
				return true;
			if(Math.abs(this._coefficient - m._coefficient)<EPSILON && this._power==m._power)
			{
				return true;
			}
			return false;
		}
		else
		{
			if(other instanceof Polynom)
			{
				Polynom p = new Polynom(this.toString());
				return p.equals(other);
			}
			if(other instanceof function)
			{
				ComplexFunction cf = new ComplexFunction(other.toString());
				return cf.equals(this);
			}
			return false;
		}
	}
	//****************** Private Methods and Data *****************


	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;

	@Override
	public function initFromString(String s) {
		function ans = new Monom(s);
		return ans;
	}
	@Override
	public function copy() {
		function ans = new Monom(this);
		return ans;
	}


}
