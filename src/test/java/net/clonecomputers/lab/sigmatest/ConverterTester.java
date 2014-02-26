package net.clonecomputers.lab.sigmatest;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import net.clonecomputers.lab.sigmatester.SigmaGame;
import net.clonecomputers.lab.sigmatester.SigmaGameConverter;
import net.clonecomputers.lab.sigmatester.XORSATGenerator;

import org.junit.Test;

import Jama.Matrix;


public class ConverterTester {
	
	@Test
	public void testConvertToSigmaGame() {
		Set<Matrix> matrices = new HashSet<Matrix>();
		for(int i = 7; i < 37; i += 3) {
			matrices.add(XORSATGenerator.generateXorSat(i, i));
		}
		for(int i = 4; i < 34; i += 3) {
			matrices.add(XORSATGenerator.generateXorSat(i, 10));
		}
		for(int i = 4; i < 34; i += 3) {
			matrices.add(XORSATGenerator.generateXorSat(10, i));
		}
		try {
			for (Matrix m : matrices) {
				SigmaGame g = SigmaGameConverter.convertToSigmaGame(m);
				for(int i = 0; i < m.getRowDimension(); ++i) {
					int rowSign = 1;
					for(int j = 0; j < m.getColumnDimension(); ++j) {
						assertEquals(Math.abs(m.get(i, j)), g.getAdjacencyMatrix().get(i, j), 0);
						if (Math.round(m.get(i, j)) == -1) {
							rowSign *= -1;
						}
					}
					assertEquals((rowSign + 1)/2, Math.round(g.getDelta().get(0, i)));
				}
			}
		} catch(Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
