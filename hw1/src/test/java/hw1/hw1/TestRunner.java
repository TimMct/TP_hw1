package hw1.hw1;

import org.junit.runner.*;
import org.junit.runner.notification.*;

public class TestRunner {
	public static void main(String[] args) {
		Result rezultat = JUnitCore.runClasses(TestPolinom.class);
		System.out.println("Toate testele au trecut cu succes? " + rezultat.wasSuccessful());
		if (!rezultat.wasSuccessful()) {
			System.out.println("Teste esuate:");
			for (Failure esec : rezultat.getFailures()) {
				System.out.println(esec.toString());
			}
		}
	}
}
