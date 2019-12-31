package Ex1;

import java.util.ArrayList;
import java.util.Iterator;

import Ex1.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{

	ArrayList<Monom> Monoms;


	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		Monoms = new ArrayList<Monom>();
		Monoms.add(new Monom(Monom.ZERO));
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		if(s.length()!=0)
		{
			Monoms = new ArrayList<Monom>();
			boolean newMonom = true;
			String Mon="";
			for (int i = 0; i < s.length(); i++)
			{
				if(newMonom)
				{
					if(s.charAt(i) != '+')
					{
						Mon = Mon.concat(s.charAt(i)+"");
					}
					newMonom=false;
				}
				else
				{
					if(s.charAt(i)=='-' || s.charAt(i)=='+')
					{
						newMonom=true;
						this.add(new Monom(Mon));
						if(s.charAt(i)=='-')
						{
							Mon = "-";
						}
						else
						{
							Mon = "";
						}
					}
					else
					{
						Mon = Mon.concat(s.charAt(i)+"");
					}
				}
			}
			this.add(new Monom(Mon));
		}
		deal_WithZero();
		Monoms.sort(Monom._Comp);
	}
	/**
	 * f
	 * This function returns the function value with the given x 
	 */
	@Override
	public double f(double x) {
		double ans = 0;
		for (Monom monom : Monoms) {
			ans+= monom.f(x);
		}
		return ans;
	}

	@Override
	public void add(Polynom_able p1) {
		// TODO Auto-generated method stub
		Iterator<Monom> itr = p1.iteretor();

		boolean added = true;
		while(itr.hasNext())
		{
			added = true;
			Monom m = itr.next();
			for (Monom monom : Monoms) {
				if(m.get_power() == monom.get_power() && added)
				{
					monom.add(m);
					added=false;
				}
			}
			if(added)
			{
				Monoms.add(m);
			}
		}
		deal_WithZero();
		Monoms.sort(Monom._Comp);
		if(Monoms.get(Monoms.size()-1).isZero())
		{
			Monoms.remove(Monoms.size()-1);
		}
	}

	@Override
	public void add(Monom m1) {
		// TODO Auto-generated method stub
		for (Monom monom : Monoms) {
			if(m1.get_power() == monom.get_power())
			{
				monom.add(m1);
				return;
			}
		}
		Monoms.add(m1);
		deal_WithZero();
		Monoms.sort(Monom._Comp);
	}

	private void deal_WithZero() {
		// TODO Auto-generated method stub
		int size = Monoms.size(); 
		for (int i = 0; i < size; i++) {
			if(Monoms.get(i).isZero())
			{
				Monoms.remove(i);
				i--;
				size = Monoms.size();
			}
		}
	}
	@Override
	public void substract(Polynom_able p1) {
		// TODO Auto-generated method stub
		Polynom_able Pol = (Polynom_able) p1.copy();
		Iterator<Monom> itr = Pol.iteretor();
		boolean added = true;
		while(itr.hasNext())
		{
			added = true;
			Monom m = itr.next();
			m.multipy(Monom.MINUS1);
			for (Monom monom : Monoms) {
				if(m.get_power() == monom.get_power() && added)
				{
					monom.add(m);
					added=false;
				}
			}
			if(added)
			{
				Monoms.add(m);
			}
		}
		deal_WithZero();
		Monoms.sort(Monom._Comp);
	}

	@Override
	public void multiply(Polynom_able p1) {
		// TODO Auto-generated method stub
		if(p1.isZero() || this.isZero())
		{
			Monoms = new ArrayList<Monom>();
			Monoms.add(new Monom(Monom.ZERO));
		}
		ArrayList<Monom> newMonoms = new ArrayList<Monom>();
		Polynom_able newp1 = (Polynom_able) p1.copy();
		for (Monom monom : Monoms) {
			Iterator<Monom> itr = newp1.iteretor();
			while(itr.hasNext())
			{
				Monom m = new Monom(itr.next());
				m.multipy(monom);
				newMonoms.add(m);
			}
		}
		Polynom pol = new Polynom();
		for (Monom monom : newMonoms) {
			pol.add(monom);
		}
		this.Monoms = pol.Monoms;
	}
	@Override
	public boolean equals(Object p1) {
		// TODO Auto-generated method stub
		if(p1 instanceof Polynom_able)
		{
			Polynom_able p2 = (Polynom_able) p1;
			if(p2==null || this==null)
			{
				throw new RuntimeException("ERR one of the variables is null");
			}
			Iterator<Monom> itr = p2.iteretor();
			boolean ans = true ;
			int count = 0 ;
			while(itr.hasNext())
			{
				Monom m = new Monom(itr.next());

				if(count>=Monoms.size()) 
				{
					return false;
				}
				else if(!Monoms.get(count).equals(m))	
				{
					return false;
				}
				count++;
			}

			if(count!=Monoms.size())
				return false;

			return ans;
		}
		else
		{
			if(p1 instanceof Monom)
			{
				Polynom m = new Polynom(p1.toString());
				return this.equals(m);
			}
			if(p1 instanceof ComplexFunction)
			{
				ComplexFunction cf = new  ComplexFunction(p1.toString());
				return cf.equals(this);
			}
			return false;
		}
	}

	@Override
	public boolean isZero() {
		for (Monom monom : Monoms) {
			if(!monom.isZero())
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		// f(x0) > 0 && f(x1) < 0
		// TODO Auto-generated method stub
		if((this.f(x0) * this.f(x1)) > 0)
		{
			throw new RuntimeException("No poits in the between "+x0+" and "+x1);
		}
		if(Math.abs(this.f(x0))<=eps)
		{
			return x0;
		}
		if(Math.abs(this.f(x1))<=eps)
		{
			return x1;
		}
		if(f(x0) >= 0 && f(x1) <= 0)
		{
			if(f(x0)*f((x0+x1)/2) < 0)
			{
				return root(x0,(x0+x1)/2,eps);
			}
			else
			{
				return root((x0+x1)/2,x1,eps);
			}
		}
		else
		{
			return root(x1,x0,eps);
		}
	}

	@Override
	public function copy() {
		Polynom_able ans = new Polynom();
		for(int i=0;i<Monoms.size();i++) {
			ans.add(new Monom(Monoms.get(i)));
		}
		return ans;
	}

	@Override
	public Polynom_able derivative() {
		// TODO Auto-generated method stub
		Polynom_able ans = new Polynom();
		for (Monom monom : Monoms) {
			ans.add(monom.derivative());
		}
		return ans;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		if(x0>x1)
		{
			return 0;
		}
		// TODO Auto-generated method stub
		double counter = x0;
		double sum=0;
		while(counter+eps < x1)
		{
			if(this.f(counter) >= 0)
			{
				sum+=(this.f(counter) * eps);
			}
			counter +=eps;
		}

		return sum;
	}
	/**
	 * Iterator
	 * this function returns the Iterator Pointer Of the given data structure
	 */
	@Override
	public Iterator<Monom> iteretor() {
		return Monoms.iterator();
	}
	@Override
	public void multiply(Monom m1) {
		// TODO Auto-generated method stub
		Monom m2 = new Monom(m1);
		for (Monom monom : Monoms) {
			monom.multipy(m2);
		}
	}
	/**
	 * toString
	 * this function returns visual presentation of the Polynom as a String
	 */
	@Override
	public String toString()
	{
		if(this.isZero())
		{
			return "0";
		}

		String ans="";
		for(int i=0;i<Monoms.size();i++)
		{
			String str = Monoms.get(i).toString();
			if(i>0)
			{
				if(str.charAt(0) != '-')
				{
					ans = ans.concat('+'+"");
				}
			}
			ans = ans.concat(str);
		}
		return ans;
	}
	
	@Override
	public function initFromString(String s) {
		function ans = new Polynom(s);
		return ans;
	}
}
