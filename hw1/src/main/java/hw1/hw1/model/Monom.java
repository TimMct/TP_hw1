package hw1.hw1.model;



public class Monom {
	private double coef;//mai bine cu un tip generic
	private int exp;
	
	public Monom(double c, int e) {//constructor normal
		coef= c;
		exp= e;
	}
	
	public Monom(String mon) {//constructor pentru string
		//monomoul arata asa, fara semn: ax^b
		String[] coefExp = mon.split("[x^]");
		try {
    		coef= Integer.parseInt(coefExp[0]);
        	exp = Integer.parseInt(coefExp[2]);
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
	}
	
	public double getCoef() {
		return coef;
	}
	
	public void setCoef(double c) {
		this.coef = c;
	};
	
	public int getExp() {
		return exp;
	}
		
	//Operatii pentru monoame
	public Monom add(Monom mon) {//adunare
		if(this.exp == mon.exp) {
			return new Monom(this.coef + mon.coef, this.exp);
		}
		return null;//polinom
	}
	
	public Monom sub(Monom mon) {//scadere
		if(this.exp == mon.exp) {
			return new Monom(this.coef - mon.coef, this.exp);
		}
		return null;//polinom
	}
	
	public Monom mul(Monom mon) {//inmultire
		return new Monom(this.coef * mon.coef, this.exp + mon.exp);
	}
	
	
	public Monom div(Monom mon) {//impartire
		if(this.exp - mon.exp >= 0)
			return new Monom(this.coef / mon.coef, this.exp - mon.exp);
		else
			return new Monom(0, 0);//exponent negativ
	}
	
	public Monom der() {//derivare
		if(exp > 0)
			return new Monom(coef * exp, exp - 1);
		else
			return new Monom(0, 0);
	}
	
	public Monom itg() {//integrare simpla fara constanta, va fi tratat in polinom
		if(exp >= 0)
			return new Monom(coef / (exp + 1), exp + 1);
		else
			return new Monom(0 ,0);//integrare cu logaritm natural 
	}
	
	@Override
	public String toString() {
		coef *= 100;
		coef = (int)coef;
		coef /=100;//ca sa afiseze doar 2 zecimale
		if(coef >= 0)
			return "+"+coef+"X^"+exp;
		else
			return coef+"X^"+exp;
	}
}

