package hw1.hw1.model;

import hw1.hw1.controller.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Polinom {
	private ArrayList<Monom> monoms;
	private static final double GEN_CST = 3.14f;//constanta generala...pi
	
	public Polinom(ArrayList<Monom> mon)
	{
		monoms= mon;
	}
	
	public Polinom() {
		monoms = new ArrayList<Monom>();
	}
	
	public Polinom(String poli) {//constructor pentru string
		 Parse.parseFull(poli);
	}
	
	public void addMonom(Monom mon) {//metoda de adaugat un monom
		this.monoms.add(mon);
	}
	
	public void remMonom(int i) {
		this.monoms.remove(i);
	}
	
	
	public int grad() {
		return this.monoms.size() - 1;
	}
	
	
	public void removeZero(ArrayList<Monom> monoms) {
		for(int k=0; k < monoms.size() && monoms.size() > 1; k++) //elimin posibile zero-uri la exponenti mai mari
			if(monoms.get(0).getCoef() == 0)
				monoms.remove(0);
	}
	
	
	public Polinom add(Polinom poli) {
		int g1 = this.grad(), g2 = poli.grad(), flag = 0, skip = g1 - g2;//cate pozitii vreau sa omit din lista
		if(skip < 0) {//presupun ca primul polinom are grad mai mare, in caz contrar modific un flag
			flag = 1;
			skip = -skip;
		}
		ArrayList<Monom> nPoli = new ArrayList<Monom>();
		Iterator<Monom> i= this.monoms.iterator(), j= poli.monoms.iterator();
		if(flag == 0) {	
			while(skip > 0) {
				nPoli.add(i.next());//adaug din polinomul cu grad mai mare
				skip--;
			}
		}else {
			while(skip > 0) {
				nPoli.add(j.next());//polinomul cu grad mai mic
				skip--;
			}
		}
		while(i.hasNext() && j.hasNext()) {//de aici iau de la ambele polinoame
			nPoli.add(i.next().add(j.next()));
		}
		this.removeZero(nPoli);
		return new Polinom(nPoli);
	}
	
	
	
	public Polinom sub(Polinom poli) {
		int g1 = this.grad(), g2 = poli.grad(), flag = 0, skip = g1 - g2;//cate pozitii vreau sa omit din lista
		if(skip < 0) {//presupun ca primul polinom are grad mai mare, in caz contrar modific un flag
			flag = 1;
			skip = -skip;
		}
		ArrayList<Monom> nPoli = new ArrayList<Monom>();
		Iterator<Monom> i= this.monoms.iterator(), j= poli.monoms.iterator();
		if(flag == 0) {	
			while(skip > 0) {
				nPoli.add(i.next());//adaug din polinomul cu grad mai mare
				skip--;
			}
		}else {
			while(skip > 0) {
				Monom temp = j.next();
				temp.setCoef(-temp.getCoef());
				nPoli.add(temp);//polinomul cu grad mai mic
				skip--;
			}
		}
		while(i.hasNext() && j.hasNext()) {//de aici iau de la ambele polinoame
			nPoli.add(i.next().sub(j.next()));
		}
		this.removeZero(nPoli);
		return new Polinom(nPoli);
	}
	
	
	public Polinom mul(Polinom poli) {
		ArrayList<Monom> prepare = new ArrayList<Monom>(), nPoli = new ArrayList<Monom>();
		for(Monom first: this.monoms)
			for(Monom second: poli.monoms)
				prepare.add(first.mul(second));
		double sum;
		for(int i=this.grad() + poli.grad(); i>=0; i--) {
			sum=0;
			for(Monom temp: prepare)
				if(temp.getExp() == i)//adun monoamele cu acelasi exponent
					sum+=temp.getCoef();
			nPoli.add(new Monom(sum, i));
		}
		this.removeZero(nPoli);
		return new Polinom(nPoli); 
	}
	
	public Polinom div(Polinom poli, Polinom r) {
		if(this.grad() >= poli.grad()) {
			Polinom temp = new Polinom(), multiply = new Polinom(), rest = this;
			Monom mon;
			while(rest.grad() >= poli.grad())  {//restul de la final va avea gradul mai mic decat impartitorul
				mon = rest.monoms.get(0).div(poli.monoms.get(0));
				temp.addMonom(mon);
				for(int i = mon.getExp() - 1; i >= 0; i--) {
					temp.addMonom(new Monom(0, i));//polinom completat cu zero-uri
				}
				multiply.addMonom(mon);
				
				rest = rest.sub(poli.mul(temp));
				for(int i = 0; i <= mon.getExp(); i++) {
					temp.remMonom(0);
				}
			}
			int fill = multiply.monoms.get(0).getExp() + 1 - multiply.monoms.size();//cate zero-uri trebuie adaugate
			if(fill > 0)
				for(int i = fill - 1; i >= 0; i--)
					multiply.addMonom(new Monom(0, i));
			this.removeZero(rest.monoms);
			for(Monom m: rest.monoms) {
				r.addMonom(m);
			}
			return multiply;
		}else
			return null;//caz special cand impartitorul are grad mai mare
	}
	
	
	
	
	public Polinom itg() {
		ArrayList<Monom> nPoli = new ArrayList<Monom>();
		
		for(Monom m: this.monoms) {
			if(m.getExp() == 0) {
				nPoli.add(m.itg());
				nPoli.add(new Monom(GEN_CST * Math.random(), 0));//adaug si o constant 
			}else
				nPoli.add(m.itg());
		}
		for(int k=0; k < nPoli.size() && nPoli.size() > 1; k++) //elimin posibile zero-uri la exponenti mai mari
			if(nPoli.get(0).getCoef() == 0)
				nPoli.remove(0);
		return new Polinom(nPoli);
	}
	
	public Polinom der() {
		ArrayList<Monom> nPoli = new ArrayList<Monom>();
		for(Monom m: this.monoms) {
			nPoli.add(m.der());
		}
		if(nPoli.size() - 1 > 0 && nPoli.get(nPoli.size() - 1).getCoef() == 0)
			nPoli.remove(nPoli.size() - 1);
		return new Polinom(nPoli);
	}
	
	
	@Override
	public String toString() {
		String poli="";
		for(Monom m: this.monoms) {
			poli+=m.toString() + "  ";
		}
		return poli;
	}
}
