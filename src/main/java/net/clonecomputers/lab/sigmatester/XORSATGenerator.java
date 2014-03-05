package net.clonecomputers.lab.sigmatester;

import java.awt.Font;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import Jama.LUDecomposition;
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
		UIManager.put("OptionPane.messageFont", new Font("Monospaced", Font.PLAIN, 12));
		JOptionPane.showMessageDialog(null, toString(matrix));
		matrix.print(2, 0);
		System.out.printf("Rank of Matrix: %d\n", matrix.rank());
		if(JOptionPane.showConfirmDialog(null, "Convert to Sigma Game?", "Continue?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			SigmaGame game = SigmaGameConverter.convertToSigmaGame(matrix);
			JOptionPane.showMessageDialog(null, game);
			System.out.println(game);
			JOptionPane.showMessageDialog(null, "Rank = " + game.getAdjacencyMatrix().rank());
			System.out.println("Rank = " + game.getAdjacencyMatrix().rank());
			if(game.getAdjacencyMatrix().rank() == game.getAdjacencyMatrix().getRowDimension() && JOptionPane.showConfirmDialog(null, "Solve?", "Continue?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				String result = Arrays.deepToString(game.solve().toArray());
				JOptionPane.showMessageDialog(null, result);
				System.out.println(result);
			}
			if(JOptionPane.showConfirmDialog(null, "LUDecomposition?", "Continue?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				LUDecomposition lup = new LUDecomposition(game.getAdjacencyMatrix());
				JOptionPane.showMessageDialog(null, "Matrix is nonsingular? " + lup.isNonsingular());
				System.out.println("Matrix is nonsingular? " + lup.isNonsingular());
				JOptionPane.showMessageDialog(null, "L = \n" + toString(lup.getL()));
				System.out.println("L = \n" + toString(lup.getL()));
				JOptionPane.showMessageDialog(null, "U = \n" + toString(lup.getU()));
				System.out.println("U = \n" + toString(lup.getU()));
				JOptionPane.showMessageDialog(null, "Pivot = " + Arrays.toString(lup.getPivot()));
				System.out.println("Pivot = " + Arrays.toString(lup.getPivot()));
				JOptionPane.showMessageDialog(null, "Determinant = " + lup.det());
				System.out.println("Determinant = " + lup.det());
				JOptionPane.showMessageDialog(null, "Solution = \n" + toString(lup.solve(game.getDelta())));
				System.out.println("Solution = \n" + toString(lup.solve(game.getDelta())));
			}
		}
	}
	
	public static String toString(Matrix a) {
		return toString(a, 2, 0);
	}
	
	public static String toString(Matrix a, int w, int d) {
		try {
			StringWriter sw = new StringWriter();
			a.print(new PrintWriter(sw), w, d);
			sw.flush();
			String val = sw.toString();
			sw.close();
			return val;
		} catch(IOException e) {
			e.printStackTrace();
			return "";
		}
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
