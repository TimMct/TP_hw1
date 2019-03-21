package hw1.hw1;

import org.junit.runner.*;
import org.junit.runner.notification.*;

public class TestRunner {
	public static void main(String[] args) {
		Result rezultat = JUnitCore.runClasses(TestPolinom.class);//rulez clasa de testare
		System.out.println("Toate testele au trecut cu succes? " + rezultat.wasSuccessful());//afisez daca a mers totul cu succes
		if (!rezultat.wasSuccessful()) {
			System.out.println("Teste esuate:");
			for (Failure esec : rezultat.getFailures()) {//afisez ce nu a functionat bine
				System.out.println(esec.toString());
			}
		}
	}
}
