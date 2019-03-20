package hw1.hw1.view;

import hw1.hw1.model.*;
import hw1.hw1.controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Window extends JFrame{
private static final long serialVersionUID = 1L;
	
	private JPanel header;
	private JPanel poly1;
	private JPanel poly2;
	private JPanel check;
	private JPanel oper;
	private JPanel result;
	private JPanel rest;
	
	private JLabel example;
	private JLabel atten;
	private JLabel input1;
	private JLabel input2;
	private JLabel question;
	private JLabel output;
	private JLabel r;
	
	private JButton sum;
	private JButton sub;
	private JButton mul;
	private JButton div;
	private JButton der;
	private JButton itg;
	
	private JTextField str1;
	private JTextField str2;
	private JTextField str3;
	private JTextField str4;
	
	private JComboBox<String> choose; 
	
	
	
	public Window() {
		super("Polynomial Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(7, 1));
		
		//panoul header
		header = new JPanel();
		header.setLayout(new FlowLayout());
		example = new JLabel("Example of input: 2x^2 +3x^1 -5x^0");
		atten = new JLabel("");//        ATTENTION: POLYNOMIAL INCORRECT!!!
		header.add(example);
		header.add(atten);
		add(header);
		
		//panoul pt primul polinom
		poly1 = new JPanel();
		poly1.setLayout(new FlowLayout());
		input1 = new JLabel("Put the 1st polynomial: ");
		str1 = new JTextField(30);
		poly1.add(input1);
		poly1.add(str1);
		add(poly1);
		
		//panoul pt al doilea polinom
		poly2 = new JPanel();
		poly2.setLayout(new FlowLayout());
		input2 = new JLabel("Put the 2nd polynomial: ");
		str2 = new JTextField(30);
		poly2.add(input2);
		poly2.add(str2);
		add(poly2);
		
		//panou pt alegerea derivarii/integrarii
		check = new JPanel();
		check.setLayout(new FlowLayout());
		question = new JLabel("Choose which polynomial you want to derivate/integrate: ");
		choose = new JComboBox<String>(new String[]{"First", "Second"});
		check.add(question);
		check.add(choose);
		add(check);
		
		//panoul cu butoanele de operatii
		oper = new JPanel();
		oper.setLayout(new GridLayout(2, 3));
		
		sum = new JButton("Addition");
		sum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Polinom firstPoly =  Parse.parseFull(str1.getText());
				Polinom secPoly =  Parse.parseFull(str2.getText());
				if(firstPoly != null && secPoly != null) {
					String result = firstPoly.add(secPoly).toString();
					str3.setText(result);
				}else{
					str3.setText("0x^0");
					atten.setText("        ATTENTION: POLYNOMIAL INCORRECT!!!");
				}
			}
		});
		
		sub = new JButton("Subtraction");
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Polinom firstPoly =  Parse.parseFull(str1.getText());
				Polinom secPoly =  Parse.parseFull(str2.getText());
				if(firstPoly != null && secPoly != null) {
					String result = firstPoly.sub(secPoly).toString();
					str3.setText(result);
				}else{
					str3.setText("0x^0");
					atten.setText("        ATTENTION: POLYNOMIAL INCORRECT!!!");
				}
			}
		});
		
		mul = new JButton("Multiplication");
		mul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Polinom firstPoly =  Parse.parseFull(str1.getText());
				Polinom secPoly =  Parse.parseFull(str2.getText());
				if(firstPoly != null && secPoly != null) {
					String result = firstPoly.mul(secPoly).toString();
					str3.setText(result);
				}else {
					str3.setText("0x^0");
					atten.setText("        ATTENTION: POLYNOMIAL INCORRECT!!!");
				}
			}
		});
		
		div = new JButton("Division");
		div.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Polinom firstPoly =  Parse.parseFull(str1.getText());
				Polinom secPoly =  Parse.parseFull(str2.getText());
				Polinom rest = new Polinom();
				if(firstPoly != null && secPoly != null) {
					String result = firstPoly.div(secPoly, rest).toString();
					str3.setText(result);
					str4.setText(rest.toString());
				}else {
					str3.setText("0x^0");
					str4.setText("0x^0");
					atten.setText("        ATTENTION: POLYNOMIAL INCORRECT!!!");
				}
					
				
			}
		});
		
		der = new JButton("Derivation");
		der.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Polinom poly;
				if(choose.getSelectedItem() == "First") 
					poly = Parse.parseFull(str1.getText());
				else
					poly = Parse.parseFull(str2.getText());
				if(poly != null) {
					String result = poly.der().toString();
					str3.setText(result);
				}else{
					str3.setText("0x^0");
					atten.setText("        ATTENTION: POLYNOMIAL INCORRECT!!!");
				}
			}
		});
		
		itg = new JButton("Integration");
		itg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Polinom poly;
				if(choose.getSelectedItem() == "First") 
					poly = Parse.parseFull(str1.getText());
				else
					poly = Parse.parseFull(str2.getText());
				if(poly != null) {
					String result = poly.itg().toString();
					str3.setText(result);
				}else {
					str3.setText("0x^0");
					atten.setText("        ATTENTION: POLYNOMIAL INCORRECT!!!");
				}
			}
		});
		
		oper.add(sum);
		oper.add(sub);
		oper.add(mul);
		oper.add(div);
		oper.add(der);
		oper.add(itg);
		add(oper);
		
		
		//panoul cu rezultatul( si catul)
		result = new JPanel();
		result.setLayout(new FlowLayout());
		output = new JLabel("Result: ");
		str3 = new JTextField(30);
		str3.setEditable(false);
		result.add(output);
		result.add(str3);
		add(result);
		
		
		//panoul pentru restul de la impartire
		rest = new JPanel();
		rest.setLayout(new FlowLayout());
		r = new JLabel("(The rest of division)");
		str4 = new JTextField(30);
		str4.setEditable(false);
		rest.add(r);
		rest.add(str4);
		add(rest);
		
		setVisible(true);
	}
}
