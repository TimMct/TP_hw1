package hw1.hw1;


import hw1.hw1.controller.Parse;
import hw1.hw1.model.*;
import junit.framework.*;
public class TestPolinom extends TestCase{
	
	public static Polinom p1, p2;
	
	
	public  void setUp() {//initializare
		p1 = new Polinom();
		p1.addMonom(new Monom(-3, 3));
		p1.addMonom(new Monom(5, 2));
		p1.addMonom(new Monom(-2, 1));
		p1.addMonom(new Monom(0, 0));
	
		p2 = new Polinom();
		p2.addMonom(new Monom(2, 2));
		p2.addMonom(new Monom(1, 1));
		p2.addMonom(new Monom(-1, 0));
	}
	
	//cate 2 testari pt fiecare operatie
	
	//testari pentru parsare pt ambele polinoame
	public  void testParse1() {
		String poli = "+2x^    2+1x      ^1- 1x  ^0";
		assertEquals(p2, Parse.parseFull(poli));
	}
	public  void testParse2() {
		String poli = "-3x^3  +5x^    2- 2x^1";
		assertEquals(p1, Parse.parseFull(poli));
	}
	
	//testari pentru adunare: directa si inversa
	public  void testAdd1() {
		Polinom p3 = new Polinom();
		p3.addMonom(new Monom(-3, 3));
		p3.addMonom(new Monom(7, 2));
		p3.addMonom(new Monom(-1, 1));
		p3.addMonom(new Monom(-1, 0));
		assertEquals(p3, p1.add(p2));
	}
	public  void testAdd2() {
		Polinom p3 = new Polinom();
		p3.addMonom(new Monom(-3, 3));
		p3.addMonom(new Monom(7, 2));
		p3.addMonom(new Monom(-1, 1));
		p3.addMonom(new Monom(-1, 0));
		assertEquals(p3, p2.add(p1));
	}
	
	
	//testari pentru scadere: directa si inversa
	public  void testSub1() {
		Polinom p3 = new Polinom();
		p3.addMonom(new Monom(-3, 3));
		p3.addMonom(new Monom(3, 2));
		p3.addMonom(new Monom(-3, 1));
		p3.addMonom(new Monom(1, 0));
		assertEquals(p3, p1.sub(p2));
	}
	public  void testSub2() {
		Polinom p3 = new Polinom();
		p3.addMonom(new Monom(3, 3));
		p3.addMonom(new Monom(-3, 2));
		p3.addMonom(new Monom(3, 1));
		p3.addMonom(new Monom(-1, 0));
		assertEquals(p3, p2.sub(p1));
	}
	
	//testari pentru inmultire: directa si inversa
	public  void testMul1() {
		Polinom p3 = new Polinom();
		p3.addMonom(new Monom(-6, 5));
		p3.addMonom(new Monom(7, 4));
		p3.addMonom(new Monom(4, 3));
		p3.addMonom(new Monom(-7, 2));
		p3.addMonom(new Monom(2, 1));
		p3.addMonom(new Monom(0, 0));
		assertEquals(p3, p1.mul(p2));
	}
	public  void testMul2() {
		Polinom p3 = new Polinom();
		p3.addMonom(new Monom(-6, 5));
		p3.addMonom(new Monom(7, 4));
		p3.addMonom(new Monom(4, 3));
		p3.addMonom(new Monom(-7, 2));
		p3.addMonom(new Monom(2, 1));
		p3.addMonom(new Monom(0, 0));
		assertEquals(p3, p2.mul(p1));
	}
	
	
	//testari pentru impartire: directa si inversa
	public  void testDiv1() {
		Polinom p3 = new Polinom(), p4 = new Polinom();
		p4.addMonom(new Monom(-3/2.0, 1));
		p4.addMonom(new Monom(13/4.0, 0));
		assertEquals(p4, p1.div(p2, p3));
	}
	public  void testDiv2() {
		Polinom p3 = new Polinom();
		assertEquals(null, p2.div(p1, p3));
	}
	
	
	//testari pentru derivare: directa si inversa
	public  void testDer1() {
		Polinom p3 = new Polinom();
		p3.addMonom(new Monom(-9, 2));
		p3.addMonom(new Monom(10, 1));
		p3.addMonom(new Monom(-2, 0));
		assertEquals(p3, p1.der());
	}
	public  void testDer2() {
		Polinom p3 = new Polinom();
		p3.addMonom(new Monom(4, 1));
		p3.addMonom(new Monom(1, 0));
		assertEquals(p3, p2.der());
	}

	
	////testari pentru integrare: directa si inversa
	public  void testItg1() {
		Polinom p3 = new Polinom();
		p3.addMonom(new Monom(-3/4.0, 4));
		p3.addMonom(new Monom(5/3.0, 3));
		p3.addMonom(new Monom(-1, 2));
		p3.addMonom(new Monom(0, 1));
		p3.addMonom(new Monom(0, 0));
		Polinom p4 = p1.itg();
		p4.getMonoms().get(4).setCoef(0);//constanta este generata aleator, deci pt testare o setez la zero
		assertEquals(p3, p4);
	}
	public  void testItg2() {
		Polinom p3 = new Polinom();
		p3.addMonom(new Monom(2/3.0, 3));
		p3.addMonom(new Monom(1/2.0, 2));
		p3.addMonom(new Monom(-1, 1));
		p3.addMonom(new Monom(0, 0));
		Polinom p4 = p2.itg();
		p4.getMonoms().get(3).setCoef(0);
		assertEquals(p3, p4);
	}
	
	
	
	public  void tearDown() {
		p1 = null;
		p2 = null;
	}
	
	
}
