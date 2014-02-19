package net.clonecomputers.lab.sigmatester;

import java.util.Set;

import org.jscience.mathematics.number.Rational;
import org.jscience.mathematics.vector.Matrix;
import org.jscience.mathematics.vector.Vector;

public class SigmaGame {

	private final Matrix<Rational> adjMatrix;
	private final Vector<Rational> delta;

	public SigmaGame(Matrix<Rational> adjacencyMatrix, Vector<Rational> delta) {
		if (!adjacencyMatrix.isSquare()) throw new IllegalArgumentException("Adjacency matrix must be square");
		if (adjacencyMatrix.getNumberOfRows() != delta.getDimension()) throw new IllegalArgumentException("The adjancey matrix must have the same dimensions as the delta vector");
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
	
	public Set<Vector<Rational>> solve() {
		
	}
	
	public int corank() {
		return adjMatrix.
	}
}
