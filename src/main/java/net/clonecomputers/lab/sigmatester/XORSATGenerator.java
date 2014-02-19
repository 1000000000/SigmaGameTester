package net.clonecomputers.lab.sigmatester;

import javax.swing.JOptionPane;

import Jama.Matrix;

public class XORSATGenerator {

	public static void main(String[] args) {
		Matrix matrix;
		if(args.length >= 2) {
			matrix = generateXorSat(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		} else {
			String k = JOptionPane.showInputDialog("Enter k");
			String n = JOptionPane.showInputDialog("Enter n");
			matrix = generateXorSat(Integer.parseInt(k), Integer.parseInt(n));
		}
		JOptionPane.showMessageDialog(null, matrix);
		matrix.print(2, 0);
		System.out.printf("Rank of Matrix: %d", matrix.rank());
		/*if(JOptionPane.showConfirmDialog(null, "Convert to Sigma Game?", "Continue?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			SigmaGame game = SigmaGameConverter.convertToSigmaGame(matrix);
			JOptionPane.showMessageDialog(null, game);
			System.out.println(game);
		}*/
	}
	
	public static Matrix generateXorSat(int k, int n) {
		double[][] prematrix = new double[k][n];
		for (int i = 0; i < prematrix.length; ++i) {
			for (int j = 0; j < prematrix[i].length; ++j) {
				prematrix[i][j] = Math.floor(Math.random()*3) - 1;
			}
		}
		return new Matrix(prematrix, k, n);
	}

}
