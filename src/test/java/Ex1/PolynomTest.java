package Ex1;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.*;

class PolynomTest {

	@Test
	void testPolynomString() {
		String[] ans = {"-24.0x^2+58.0x-5.0","0","-2.0x^2-4.0x","0","26.0x^2+58.0x-5.0","-24.0x^2+38.0x+5.0","9.0x^2+6.0x","-2.0"};
		String[] polynom = {"0-24x^2+35x-5+23x","2+0-4-0-0+2","-2x^2-4x","0","24x^2+35x-5+23x+2x^2"
				,"x-24x^2+35x+5+2x","3x^2+2x+3x^2-5x+3x^2+3x+5x+x","2-4"};
		for (int i = 0; i < polynom.length; i++) {
			Polynom m = new Polynom(polynom[i]);
			Polynom ansPol = new Polynom(ans[i]);
			assertEquals(ansPol, m);
			assertEquals(0, m.toString().compareTo(ansPol.toString()));
		}
	}

	@Test
	void testF() 
	{
		double[] ans = {-5,-15.6,96.2,452.8};
		String[] str = {"x", "3+1.4X^3-34x", "5x^3-3+2x", "14x^3-5"};
		Polynom_able pol = new Polynom();
		for (int i = 0; i < str.length; i++) 
		{
			pol.add(new Polynom(str[i]));

		}
		for (int i = 0; i < str.length; i++) {
			if(Math.abs(ans[i]-pol.f(i)) > Monom.EPSILON)
			{
				fail("Should be: "+ans[i]+" but the answer was: "+pol.f(i));
			}
		}
	}

	@Test
	void testAddPolynom_able() {

		String ans = "20.4x^3-31.0x-5.0";
		String[] str = {"x", "3+1.4X^3-34x", "5x^3-3+2x", "14x^3-5"};
		Polynom_able pol = new Polynom();
		for (int i = 0; i < str.length; i++) 
		{
			pol.add(new Polynom(str[i]));

		}
		Polynom ansPol = new Polynom(ans);
		assertEquals(ansPol, pol);
	}

	@Test
	void testAddMonom() {
		Polynom ans = new Polynom("10x^3+2x^2+2x+5");
		Polynom Pol = new Polynom("5x^3-3x+5");
		Monom m = new Monom("5x^3");
		Monom m2 = new Monom("5x");
		Monom m3 = new Monom("2x^2");
		Pol.add(m);
		Pol.add(m2);
		Pol.add(m3);
		assertEquals(ans, Pol);
	}

	@Test
	void testSubstract() {
		
		Polynom_able polAns = new Polynom("10.4x^3-36.0x-5.0");
		Polynom_able Pol = new Polynom("20.4x^3-31x-5");
		Polynom_able pol2 = new Polynom("10.0x^3+5.0x");
		Pol.substract(pol2);
		assertEquals(polAns, Pol);
		Polynom_able pol3 = new Polynom("10.0x^3+5.0x");
		Polynom_able ans2 = new Polynom("0.4x^3-41.0x-5.0");
		Pol.substract(pol3);
		assertEquals(ans2, Pol);
		
	}

	@Test
	void testMultiplyPolynom_able() {
		Polynom ans = new Polynom("-20.0x^8-144.0x^6-324.0x^4-216.0x^2");
		Polynom pol = new Polynom("5x^2+6");
		Polynom_able pol2 = new Polynom("2x^3+6x");
		pol.multiply(pol2);
		pol.multiply(pol2);
		pol.multiply(new Polynom("-1"));
		assertEquals(ans, pol);
		
		
	}

	@Test
	void testRoot() {
		double eps = Monom.EPSILON;
		double ans1 = -0.49999997615814207;
		Polynom pol = new Polynom("2x+4x^2");
		double x1 = -0.1;
		double x2 = -1;
		double x0  = pol.root(-0.1, -1, eps);
		if(Math.abs(ans1-x0)>eps)
		{
			fail("Should be: "+ans1 +" but was" + x0);
		}
		ans1 = -1.4655712321400642;
		Polynom pol2 = new Polynom("5x^3+5x^2+5");
		x1=-2;
		x2=2;
		x0 = pol2.root(x1, x2, eps);
		if(Math.abs(ans1-x0)>eps)
		{
			fail("Should be: "+ans1 +" but was" + x0);
		}
		ans1 = 2.2360679906834653;
		Polynom pol3 = new Polynom("x^2-5");
		x1 = 4;
		x2 = 4;
		x0 = pol3.root(x0, x1, eps);
		if(Math.abs(ans1-x0)>eps)
		{
			fail("Should be: "+ans1 +" but was" + x0);
		}
	}

	@Test
	void testCopy() {
		Polynom m = new Polynom("2x^2");
		function m_copied = m.copy();
		assertEquals(true, m_copied.equals(m));
		
		Polynom temp = new Polynom("1");
		m.add(temp);
		assertEquals(false, m_copied.equals(m));//false
		
		temp = new Polynom("-1");
		m.add(temp);
		assertEquals(true, m_copied.equals(m));//true
	}

	@Test
	void testDerivative() {
		Polynom_able p1 = new Polynom("-3.2x^2-5x+5");
		p1 = p1.derivative();
		Polynom p1Ans = new Polynom("-6.4x-5.0");
		assertEquals(p1Ans, p1);
		Polynom p2Ans = new Polynom("-6.4");
		p1 = p1.derivative();
		assertEquals(p2Ans, p1);
		Polynom_able p2 = new Polynom("5x^3-3x+5");
		Polynom p3Ans = new Polynom("15.0x^2-3.0");
		p2 = p2.derivative();
		assertEquals(p3Ans, p2);
		Polynom p4ans = new Polynom("30.0x");
		p2 = p2.derivative();
		assertEquals(p4ans, p2);
	}

	@Test
	void testArea() {
		double eps = Monom.EPSILON;
		Polynom p1 = new Polynom("x");
		double x1 = 0;
		double x2 = 4;
		double area = p1.area(x1, x2, eps);
		double ans = 7.999999797380996;
		if(Math.abs(ans-area)>eps)
		{
			fail("Should be: "+ans +" but was" + area);
		}
		Polynom p2 = new Polynom("-x^3+4x^2");
		area = p2.area(x1, x2, eps);
		ans = 21.333333354561304;
		if(Math.abs(ans-area)>eps)
		{
			fail("Should be: "+ans +" but was" + area);
		}
		
		Polynom p3 = new Polynom("6x^5-4x");
		x1 = -1;
		x2 = 0;
		area = p3.area(x1, x2, eps);
		ans = 1.08866210;
		if(Math.abs(ans-area)>eps)
		{
			fail("Should be: "+ans +" but was" + area);
		}
	}

//
//	@Test
//	void testInitFromString() {
//		fail("Not yet implemented");
//	}

}
