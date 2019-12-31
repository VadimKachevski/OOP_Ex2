package Ex1;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.*;

class MonomTest {




	@Test
	void testDerivative() {
		
		String[] monomsAns = {"3","-1","-6.4x","15x^2","0","0"};
		String[] monoms = {"3x","-x","-3.2x^2","5x^3","2","0"};
		for (int i = 0; i < monoms.length; i++) {
			Monom m1 = new Monom(monoms[i]).derivative();
			Monom m2 = new Monom(monomsAns[i]);
			if(!m1.equals(m2))
			{
				fail("Error with Monom "  + m1.toString() +" should be the derivative of: "+ monoms[i]+" and the ans sould be: "+monomsAns[i] );
			}
		}	
	}
	@Test
	void testF() {
		double[] ans = {2.0,-1,-12.8,0,-32.0,-16.0};
		String[] monoms = {"2", "-x","-3.2x^2","0","-2x^2","-3.2x^1"};
		for (int i = 0; i < monoms.length; i++) {
			Monom m1 = new Monom(monoms[i]);
			assertEquals(ans[i], m1.f(i),Monom.EPSILON);
		}
	}

	@Test
	void testIsZero() {
		Monom m1 = new Monom(Monom.ZERO);
		Monom m2 = new Monom("");
		Monom m3 = new Monom("0");
		assertEquals(m3.isZero(), m1.isZero());
		assertEquals(m3.isZero(), m2.isZero());
	}

	@Test
	void testMonomString() {
		String[] ans = {"2.0","-1.0x","-3.2x^2","0","-2.0x^2","-3.2x"};
		String[] monoms = {"2", "-x","-3.2x^2","0","-2x^2","-3.2x^1"};
		for(int i=0;i<monoms.length;i++)
		{
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			Monom m2 = new Monom(s);
			assertEquals(0,ans[i].compareTo(m.toString()));
			assertEquals(m, m2);
		}
		
	}

	@Test
	void testAdd() {
		String[] ans = {"7x","-6x","2.8x^2","13x^3","7","0","2x^3"};
		String[] monoms1 = {"3x","-x","-3.2x^2","5x^3","2","0","2x^3"};
		String[] monoms2 = {"4x","-5x","6x^2","8x^3","5","0","0"};
		for (int i = 0; i < monoms2.length; i++)
		{
			Monom ansMonom = new Monom(ans[i]);
			Monom m1 = new Monom(monoms1[i]);
			Monom m2 = new Monom(monoms2[i]);
			m1.add(m2);
			assertEquals(ansMonom,m1);
		}
	}

	@Test
	void testSubstract() {
		String[] ans = {"-x","4x","-9.2x^2","-3x^3","-3","0","2x^3"};
		String[] monoms1 = {"3x","-x","-3.2x^2","5x^3","2","0","2x^3"};
		String[] monoms2 = {"4x","-5x","6x^2","8x^3","5","0","0"};
		for (int i = 0; i < monoms2.length; i++)
		{
			Monom ansMonom = new Monom(ans[i]);
			Monom m1 = new Monom(monoms1[i]);
			Monom m2 = new Monom(monoms2[i]);
			m1.substract(m2);
			assertEquals(ansMonom,m1);
		}
	}

	@Test
	void testMultipy() {
		int counter=0;
		String[] ans = {"10","-10x","12x^2","0","16x^3","-5x","5x^2","-6x^3","0","-8x^4","-16x^2","16x^3","-19.2x^4","0","-25.6x^5","0","0","0","0","0","25x^3","-25x^4","30x^5","0","40x^6"};
		String[] monoms1 = {"2","-x","-3.2x^2","0","5x^3"};
		String[] monoms2 = {"5","-5x","6x^2","0","8x^3"};
		for (int i = 0; i < monoms1.length; i++)
		{
			
			for (int j = 0; j < monoms2.length; j++) {
				Monom m1 = new Monom(monoms1[i]);
				Monom m2 = new Monom(monoms2[j]);
				Monom ansMonom = new Monom(ans[counter++]);
				m1.multipy(m2);
				assertEquals(ansMonom,m1);
			}
			
		}
	}


	@Test
	void testInitFromString() {
		String[] ans = {"2.0","-1.0x","-3.2x^2","0","-2.0x^2","-3.2x"};
		String[] monoms = {"2", "-x","-3.2x^2","0","-2x^2","-3.2x^1"};
		for(int i=0;i<monoms.length;i++)
		{
			function m = new Monom(monoms[i]);
			String s = m.toString();
			function m2 = new Monom(s);
			assertEquals(0,ans[i].compareTo(m.toString()));
			assertEquals(m, m2);
		}
	}

	@Test
	void testCopy() {
		Monom m = new Monom("2x^2");
		function m_copied = m.copy();
		assertEquals(true, m_copied.equals(m));
		
		Monom temp = new Monom("x^2");
		m.add(temp);
		assertEquals(false, m_copied.equals(m));//false
		
		temp = new Monom("-x^2");
		m.add(temp);
		assertEquals(true, m_copied.equals(m));//true
	}

}
