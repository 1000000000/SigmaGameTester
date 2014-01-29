package net.clonecomputers.lab.sigmatester;

import javax.swing.JOptionPane;

import org.jscience.mathematics.number.Rational;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.Matrix;

public class XORSATGenerator {

	public static void main(String[] args) {
		Matrix<Rational> matrix;
		if(args.length >= 2) {
			matrix = generateXorSat(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		} else {
			String k = JOptionPane.showInputDialog("Enter k");
			String n = JOptionPane.showInputDialog("Enter n");
			matrix = generateXorSat(Integer.parseInt(k), Integer.parseInt(n));
		}
		System.out.println(matrix);
	}
	
	public static Matrix<Rational> generateXorSat(int k, int n) {
		Rational[][] prematrix = new Rational[k][n];
		for (int i = 0; i < prematrix.length; ++i) {
			for (int j = 0; j < prematrix[i].length; ++j) {
				prematrix[i][j] = Rational.valueOf((long) (Math.random()*3) - 1, 1);
			}
		}
		return DenseMatrix.valueOf(prematrix);
	}

}
