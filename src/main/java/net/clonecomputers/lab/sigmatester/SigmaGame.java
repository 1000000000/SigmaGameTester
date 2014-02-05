package net.clonecomputers.lab.sigmatester;

import org.jscience.mathematics.number.Rational;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

public class SigmaGame {

	private final Matrix<Rational> adjMatrix;
	private final Vector<Rational> delta;

	public SigmaGame(Matrix<Rational> adjacencyMatrix, Vector<Rational> delta) {
		System.out.println();
		System.out.println(adjacencyMatrix);
		System.out.println();
		System.out.println(delta);
		adjMatrix = adjacencyMatrix;
		this.delta = delta;
	}

	public Matrix<Rational> getAdjacencyMatrix() {
		return adjMatrix;
	}

	public Vector<Rational> getDelta() {
		return delta;
	}

	public String toString() {
		String[] matrixLines = adjMatrix.toString().split("\n");
		String[] deltaLines = delta.toString().split(" ");
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < matrixLines.length; ++i) {
			str.append(matrixLines[i]);
			str.append(" ");
			str.append(deltaLines[i]);
			str.append("\n");
		}
		return str.toString();
	}
}
