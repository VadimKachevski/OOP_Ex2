

package Ex1;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Ex1.*;

class ComplexFunctionTest {
	public static final double EPS = 0.00001;
	@Test
	void testConstructors() {

		try {
			String oper = null;
			Monom mx = new Monom("1");
			function pe = null;

			ComplexFunction cf20 = new ComplexFunction(oper,mx,mx);
			fail("the operation should not be valid");
		}
		catch(NullPointerException e)
		{
			fail("one of the parameters is Null");
		}
		catch(RuntimeException e)
		{
			System.err.println("The catch is good, the ComplexFunction was given bad parameters");
		}	
		try {
			function pe = null;
			ComplexFunction	cf20 = new ComplexFunction(pe);
			fail("the operation should not be valid");
		}
		catch(NullPointerException e)
		{
			fail("one of the parameters is Null");
		}
		catch(Exception e)
		{
			System.err.println("The catch is good, the ComplexFunction was given bad parameters");
		}

		Monom m1 = new Monom(2,2); //2x^2
		Monom m2 = new Monom(3,3); //3x^3
		ComplexFunction cf = new ComplexFunction("plus", m1,m2); //2x^2+3x^3
		cf.mul(m2); //(2x^2+3x^3)*3x^3
		Polynom p = new Polynom();
		p.add(m1);
		p.add(m2);
		p.multiply(m2);//(2x^2+3x^3)*3x^3
		double v = 4.0;
		double dp = p.f(v);
		double dcf = cf.f(v);
		double dd = Math.abs(dp-dcf);
		if(dd>EPS) {
			fail("ERR: should got the same value from: "+p+"should be: "+dp+"  and "+cf+"should be "+dcf);
		}
		Polynom m3 = new Polynom("+2 x ^ 2 + 1 "); //2x^2
		Polynom m4 = new Polynom("+ 1 x ^ 3");
		ComplexFunction cf2 = new ComplexFunction("div",m3,m4 );
		assertEquals(Double.POSITIVE_INFINITY , cf2.f(0));
		Monom s =new Monom("1");
		Polynom m5 = new Polynom(s.toString());
		Polynom m6 = new Polynom("+2 x ^ 2 - 1 ");
		cf2 = new ComplexFunction("div" , m6 , m4);
		String ans = "mul("+m5.toString()+","+cf2.toString()+")";
		function px = new ComplexFunction(ans);
		assertEquals(Double.NEGATIVE_INFINITY , px.f(0));
	}

	@Test
	void testOfString() {
		Polynom p1 = new Polynom();
		p1.add( new Monom(2,2));
		Polynom p2 = new Polynom();
		p2.add(new Monom(3,3));
		Monom m1 = new Monom(2,2);
		Monom m2 = new Monom(3,3);
		ComplexFunction cf = new ComplexFunction("plus", m1,m2);
		ComplexFunction cf3 = new ComplexFunction("plus", p1,p2);
		cf.mul(m2);
		cf3.mul(m2);
		String s = cf.toString();
		function cf2 = cf.initFromString(s);
		if(!cf.equals(cf2)) {
			fail("ERR: "+cf+" should be equals to "+cf2);
		}
		if(!cf.equals(cf3)) {
			fail("ERR: "+cf+" should be equals to "+cf3);
		}
		ComplexFunction x1 = new ComplexFunction("plus", m1,m2);
		x1.mul(x1);
		x1.div(x1);
		x1.comp(x1);
		x1.plus(x1);
		function y = new ComplexFunction(x1.initFromString(x1.toString()).toString());
		assertEquals(x1, y);
		cf = new ComplexFunction("plus", m1,m2);
		Monom m5 = new Monom(2,2);
		p1 = new Polynom(m5.toString());
		cf.mul(p1);
		cf.div(p1);
		cf.comp(p1);
		function x =cf.copy();
		assertEquals(x, cf);
	}

	@Test
	void testComplexFunction() {
		String s1 = "3.1+2.4x^2-x^4";
		String s2 = "5+2x-3.3x+0.1x^5";
		String[] s3 = {"x-1","x-2", "x-3", "x-4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		for(int i=1;i<s3.length;i++) {
			p3.multiply(new Polynom(s3[i]));
		}
		ComplexFunction cf = new ComplexFunction("plus", p1,p2);
		ComplexFunction cf4 = new ComplexFunction("div", new Monom("x"),p3);
		cf.div(p1);
		String s = cf.toString();
		function cf5 = cf4.initFromString(s);
		if(!cf.equals(cf5)) {
			fail("ERR: "+cf+" should be equals to "+cf5);
		}
		int size=10;
		for(int i=0;i<size;i++) {
			double x = Math.random();
			double d = cf.f(x);
			double d5 = cf5.f(x);
			assertEquals(d,d5,EPS);
		}
		Polynom l1 = new Polynom("-1.0x^4");
		Polynom l2 = new Polynom("-1x^5");
		function f = new ComplexFunction("plus",l1,l2);
		double t = 0.000001;
		Polynom p4 = new Polynom("-1.0x^4+-1x^5");
		assertEquals(p4.f(t), f.f(t),EPS);
	}
	@Test
	void equalsTest()
	{
		ComplexFunction a = new ComplexFunction("2x");
		function f1 = a.initFromString("Plus(x,x)");
		function f2 = a.initFromString("2x");
		assertEquals(f1, f2);
		function f3 = a.initFromString("plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)");
		function f4 = a.initFromString("Plus(div(x+1,x^3 - 3 x^2 -10x+24),2)");
		assertEquals(f3, f4);
		function p =new Monom("0");
		function p2 = p.copy();
		assertEquals(p2, p);
		ComplexFunction x = new ComplexFunction(p.toString());
		assertEquals(x, p);
		assertEquals(x, p2);
		Monom test = new Monom("2");
		Polynom t3 = new Polynom(test.toString());
		function left = new Monom("2");
		ComplexFunction p3 = new ComplexFunction(left) ;
		assertEquals(p3, test);
		assertEquals(test, p3);
		assertEquals(p3, t3);
		assertEquals(p3, left);
		assertEquals(p3, p3);
		assertEquals(left, p3); 
		assertEquals(left, test);
		assertEquals(left, t3);
		assertEquals(left, left);
	}
	@Test
	void ComplexFunctionString()
	{
		ComplexFunction cf = new ComplexFunction("Plus(div(x+1,x^3 - 3 x^2 -10x+24),2)");
		Polynom p1 = new Polynom("x+1");
		Polynom p2 = new Polynom("x^3 - 3 x^2 -10x+24");
		ComplexFunction cf2 = new ComplexFunction("div",p1,p2);
		ComplexFunction cf3= new ComplexFunction(Operation.Divid,p1,p2);
		cf3.plus(new Monom("2"));
		Monom m = new Monom("1");
		Monom m2 = new Monom("1");
		cf2.plus(m);
		cf2.plus(m2);
		assertEquals(cf, cf2);
		assertEquals(cf, cf3);
		assertEquals(cf3, cf2);
		Polynom t1 = new Polynom("2x^2");
		Polynom t2 = new Polynom("4x^6");
		ComplexFunction c = new ComplexFunction("plus",t1,t2);
		c.plus(c);
		String ans = c.toString();
		ComplexFunction c1 = new ComplexFunction(ans);
		assertEquals(ans, c.toString());
		assertEquals(ans, c1.toString());
	}
	@Test
	void ComplexFunction_f()
	{
		double[] ansArr = {49.0/24.0,13.0/6.0,Double.POSITIVE_INFINITY,4.0/3.0,Double.POSITIVE_INFINITY,9.0/4.0};
		ComplexFunction cf = new ComplexFunction("Plus(div(x+1,x^3 - 3 x^2 -10x+24),2)");
		for (int i = 0; i < 6; i++) {
			assertEquals(ansArr[i], cf.f(i),Monom.EPSILON);
		}
	}
	@Test 
	void ComplexFunction_copy()
	{
		ComplexFunction cf = new ComplexFunction("Plus(div(x+1,x^3 - 3 x^2 -10x+24),2)");
		function f2 = cf.copy();
		assertEquals(cf, f2);
		cf.plus(new Monom("1"));
		cf.mul(new Monom("2"));
		if(cf.equals(f2))
		{
			fail("Should'nt be equals");
		}
		cf.div(new Monom("2"));
		cf.plus(new Monom("-1"));
		assertEquals(cf, f2);
	}
	@Test
	void ComplexFunction_plus()
	{
		ComplexFunction cf = new ComplexFunction("Plus(x+3,x^2)");
		Polynom p1 = new Polynom("x^2+x+3");
		assertEquals(p1, cf);
		cf = new ComplexFunction("plus(Plus(x+3,x^2),0)");
		p1 = new Polynom("x^2+x+3+0");
		function t = p1.copy();
		assertEquals(cf, t);
		cf = new ComplexFunction("plus (  Plus( 0+ , 0 ) , 0)");
		p1 = new Polynom("0 +0+0");
		t = p1.copy();
		assertEquals(cf, t);
		cf = new ComplexFunction("plus (  Plus( 1x^2+ , 1x^2 ) , 0)");
		p1 = new Polynom("1x^2+ 1x^2++0");
		t = p1.copy();
		assertEquals(cf, t);
	}
	@Test
	void ComplexFunction_mul() 
	{
		ComplexFunction cf = new ComplexFunction("mul(x ^ 1 , x ^ 2 )");
		Polynom p1 = new Polynom("x^1");
		cf.mul(p1);
		cf.mul(p1);
		cf.mul(cf);
		cf.mul(new ComplexFunction(cf.initFromString(cf.toString()).toString()));
		ComplexFunction cf2 = (ComplexFunction) cf.copy();	
		String ans ="mul(mul(mul(mul(mul(1.0x,1.0x^2),1.0x),1.0x),mul(mul(mul(1.0x,1.0x^2),1.0x),1.0x)),mul(mul(mul(mul(1.0x,1.0x^2),1.0x),1.0x),mul(mul(mul(1.0x,1.0x^2),1.0x),1.0x)))";
		ComplexFunction cf3 = new ComplexFunction(ans);
		assertEquals(cf2, cf3);
		assertEquals(cf.f(1) , p1.f(1),EPS);
	}
	@Test
	void ComplexFunction_div() 
	{
		ComplexFunction cf = new ComplexFunction("div(x^5,x)");
		Polynom p1 = new Polynom("x^4");
		assertEquals(cf, p1);
		cf = new ComplexFunction("div(x,x)");
		p1 = new Polynom("1");
		function fx = p1.copy();
		assertEquals(cf, fx);
		cf = new ComplexFunction("div(0,x)");
		p1 = new Polynom("0");
		fx = p1.copy();
		assertEquals(cf, fx);
		cf = new ComplexFunction("div(x^3,-1)");
		p1 = new Polynom("-x^3");
		fx = p1.copy();
		assertEquals(cf, fx);
		cf = new ComplexFunction("div(div(x^3,-1),x)");
		p1 = new Polynom("-x^2");
		fx = p1.copy();
		assertEquals(cf, fx);
		cf = new ComplexFunction("div(div(div(div(x^3,-1),x),x),x)");
		cf.mul(new Monom("-1"));
		p1 = new Polynom("1");
		fx = p1.copy();
		assertEquals(cf, fx);
	}
	@Test
	void ComplexFunction_max() 
	{
		ComplexFunction cf = new ComplexFunction("max(x,x^2)");
		Polynom p1 = new Polynom("x^2");
		for (int i = 1; i < 10; i++) {
			assertEquals(cf.f(i), p1.f(i) , EPS);
		}
		cf = new ComplexFunction("max(x,div(x^2,x))");
		p1 = new Polynom("x");
		for (int i = 1; i < 10; i++) {
			assertEquals(cf.f(i), p1.f(i) ,EPS);
		}
		cf = new ComplexFunction("max(x^3,x)");
		p1 = new Polynom("x^3");
		Polynom p2=new Polynom("x");
		for (double i = 0; i < 10; i+=0.1) {
			if(i>=0 && i<=1)
			{
				assertEquals(cf.f(i), p2.f(i),EPS);
			}
			else
			{
				assertEquals(cf.f(i), p1.f(i) ,EPS);
			}	
		}
	}

	@Test
	void ComplexFunction_min() 
	{
		ComplexFunction cf = new ComplexFunction("min(x,x^2)");
		Polynom p1 = new Polynom("x");
		for (double i = 1; i < 10; i+=0.1) {
			assertEquals(cf.f(i), p1.f(i) ,EPS);
		}
		cf = new ComplexFunction("min(x^3,x)");
		p1 = new Polynom("x^3");
		Polynom p2=new Polynom("x");
		for (double i = 0; i < 10; i+=0.5) {
			if(i>=0 && i<=1)
			{
				assertEquals(cf.f(i), p1.f(i) , EPS);
			}
			else
			{
				assertEquals(cf.f(i), p2.f(i) , EPS);
			}	
		}
	}
	@Test
	void ComplexFunction_comp() 
	{
		ComplexFunction cf = new ComplexFunction("plus(x^5-3x,x^2)");
		Polynom p1 = new Polynom("x");
		cf.comp(p1);
		Polynom p2 = new Polynom("x^5-3x+x^2");
		for (double i = 1; i < 10; i+=0.1) {
			assertEquals(p1.f(p2.f(i)), cf.f(i),EPS);
		}
		cf = new ComplexFunction("plus(x^5-3x,x^2)");
		p1 = new Polynom("-x");
		cf.comp(p1);
		p2 = new Polynom("x^5-3x-x^2");
		for (double i = 1; i < 10; i+=0.1) {
			assertEquals(p1.f(p2.f(i)), cf.f(i),EPS);
		}
	}
	@Test
	void ComplexFunction_equals() {
		Monom m1 = new Monom(2,2);
		Monom m2 = new Monom(3,3);
		ComplexFunction cf = new ComplexFunction("plus",m1,m2);
		cf.mul(m1);
		cf.div(m1);
		cf.max(m1);
		cf.min(m1);
		function x =cf.initFromString(cf.toString());
		ComplexFunction cf2 = new ComplexFunction("plus",m1,m2);
		cf2.mul(m1);
		cf2.div(m1);
		cf2.max(m1);
		cf2.min(m1);
		assertEquals(x, cf2);
		ComplexFunction cf1 = new ComplexFunction("mul(x ^ 1 , x ^ 2 )");
		Polynom p1 = new Polynom("x^1");
		cf1.mul(p1);
		cf1.mul(p1);
		cf1.mul(cf1);
		cf1.mul(new ComplexFunction(cf1.initFromString(cf1.toString()).toString()));
		ComplexFunction cf21 = (ComplexFunction) cf1.copy();	
		String ans ="mul(mul(mul(mul(mul(1.0x,1.0x^2),1.0x),1.0x),mul(mul(mul(1.0x,1.0x^2),1.0x),1.0x)),mul(mul(mul(mul(1.0x,1.0x^2),1.0x),1.0x),mul(mul(mul(1.0x,1.0x^2),1.0x),1.0x)))";
		ComplexFunction cf3 = new ComplexFunction(ans);
		assertEquals(cf21, cf3);
		assertEquals(cf1.f(1) , p1.f(1),EPS);
		Polynom test1 = new Polynom("x^2+2");
		ComplexFunction test2 = new ComplexFunction("plus(x^2,2)");
		for (int i= 0;  i < 10; i++) 
		{
			test2.plus(new Monom(i,1));
			test1.add(new Monom(i,1));
			assertEquals(test1, test2);
		}		
	}
}

