package net.clonecomputers.lab.sigmatester;

import java.util.Collections;
import java.util.Set;

import Jama.Matrix;

public class SigmaGame {

	private final Matrix adjMatrix;
	private final Matrix delta;

	public SigmaGame(Matrix adjacencyMatrix, Matrix delta) {
		if (adjacencyMatrix.getRowDimension() != adjacencyMatrix.getColumnDimension()) throw new IllegalArgumentException("Adjacency matrix must be square");
		if (delta.getColumnDimension() != 1) throw new IllegalArgumentException("delta must be a column vector");
		if (adjacencyMatrix.getColumnDimension() != delta.getRowDimension()) throw new IllegalArgumentException("The adjancey matrix must have the same dimensions as the delta vector");
		adjMatrix = adjacencyMatrix;
		this.delta = delta;
	}

	public Matrix getAdjacencyMatrix() {
		return adjMatrix;
	}

	public Matrix getDelta() {
		return delta;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < adjMatrix.getArray().length; ++i) {
			str.append("|");
			for(double d : adjMatrix.getArray()[i]) {
				str.append(Math.round(d));
				str.append(' ');
			}
			str.deleteCharAt(str.length()-1);
			str.append("| |");
			str.append(Math.round(delta.get(i, 0)));
			str.append("|\n");
		}
		return str.toString();
	}
	
	public Set<boolean[]> solve() {
		if(adjMatrix.rank() == adjMatrix.getRowDimension()) {
			return Collections.singleton(convertDoubleArrayToBooleanArray(adjMatrix.solve(delta).getColumnVector(0)));
		} else {
			throw new RuntimeException("Finish SigmaGame.solve()");
		}
	}
	
	public static boolean[] convertDoubleArrayToBooleanArray(double[] array) {
		boolean[] newArray = new boolean[array.length];
		for(int i = 0; i < array.length; ++i) {
			newArray[i] = array[i] != 0;
		}
		return newArray;
	}
}
