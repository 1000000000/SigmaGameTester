package net.clonecomputers.lab.sigmatester;

import Jama.Matrix;

public class SigmaGame {

	private final Matrix adjMatrix;
	private final double[] delta;

	public SigmaGame(Matrix adjacencyMatrix, double[] delta) {
		if (adjacencyMatrix.getRowDimension() != adjacencyMatrix.getColumnDimension()) throw new IllegalArgumentException("Adjacency matrix must be square");
		if (adjacencyMatrix.getRowDimension() != delta.length) throw new IllegalArgumentException("The adjancey matrix must have the same dimensions as the delta vector");
		adjMatrix = adjacencyMatrix;
		this.delta = delta;
	}

	public Matrix getAdjacencyMatrix() {
		return adjMatrix;
	}

	public double[] getDelta() {
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
			str.append(Math.round(delta[i]));
			str.append("|\n");
		}
		return str.toString();
	}
}
