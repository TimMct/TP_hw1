package hw1.hw1.controller;
import java.util.ArrayList;
import  hw1.hw1.model.*;

public class Parse {
	public static String parseSpaces(String str) {
    	String[] nonSpaces = str.split("\\s");
        str="";
        for (String a : nonSpaces) //rescriu stringul fara spatii
        	str+=a;
        for(int i=0; i<str.length(); i++)
        	if(str.charAt(i) == '^' && str.charAt(i+1) == '-') {
        		try {
        			throw new Exception("Atentie: Exponent negativ!");
        		}catch(Exception e) {
        			System.out.println(e.getMessage());//nu se proceseaza exponenti negativi
        			return null;
        		}
        	}
        return str;
    }
    public static String parseSigns(String str) {
    	String signs = new String();//string cu semnele coeficientilor
        if(str.charAt(0) == '-')
        	signs+="-";
        else
        	signs+="+";
        for(int i=1; i<str.length(); i++) {
        	if(str.charAt(i-1) != '^') {//verific sa nu fie cumva inainte caracterul exponential '^'
        		if(str.charAt(i) == '-') 
	        		signs+="-";
	        	else if(str.charAt(i) == '+') 
	        		signs+="+";
        	}
        }
        return signs;
	}
	
	
    public static Polinom parseTokens(String str, String signs) {
    		String temp = "";
        	if(str.charAt(0) == '+' || str.charAt(0) == '-') {//daca primul monom are semn in fata trebuie sa il scot
        		for(int i=1; i<str.length(); i++)
        			if(str.charAt(i-1) != '^' || str.charAt(i) != '+')//daca la exponent mai este semn '+' vreau sa il scot
        				temp+=str.charAt(i);
        	}else
        		temp = str;
        	String[] tokens = temp.split("[+-]");//impartire dupa semn
        	ArrayList<Monom> monoms = new ArrayList<Monom>();
            int i=0, exp=0, grad = 0; double coef=0; Monom mon;
            for(String a: tokens) {
            	mon = new Monom(a);
            	coef = mon.getCoef();
            	exp = mon.getExp();
            	if(i == 0) grad = exp;
            	if(signs.charAt(i) == '-') coef = -coef;
            	i++;
            	while(exp != grad)
            		monoms.add(new Monom(0, grad--));//daca sunt zero-uri in interior
            	monoms.add(new Monom(coef, grad--));
            }
            int fill = monoms.get(0).getExp() + 1 - monoms.size();
            if(fill > 0) {
            	for(int j = fill - 1; j >= 0; j--)
            		monoms.add(new Monom(0, j));
            }
            return new Polinom(monoms);
   	}
	
    
    
    public static Polinom parseFull(String str) {
    	Polinom p =new Polinom();
		p.addMonom(new Monom(0, 0));
    	
    	if(str != null) {
    		if(str == "") {//pentru cazul cand utilizatorul nu a introdus nimic
    			return p;//polinom nul
        	}
        	else {
        		String aux = Parse.parseSpaces(str);
        		String signs = Parse.parseSigns(aux);
            	return Parse.parseTokens(aux, signs);//polinom bun
        	}
    	}
    	return p;
    }
}
