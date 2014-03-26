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
			Matrix submatrix = findInvertibleSubmatrix(adjMatrix);
			//TODO Solve
			throw new RuntimeException("Finish Me!");
		}
	}
	
	public static Matrix findInvertibleSubmatrix(Matrix bigMatrix) {
		if(bigMatrix.getColumnDimension() != bigMatrix.getRowDimension()) throw new IllegalArgumentException("Matrix must be square");
		int size = bigMatrix.getColumnDimension();
		int rank = bigMatrix.rank();
		if(rank == bigMatrix.getColumnDimension()) return bigMatrix;
		int[][] subsets = new int[nChooseK(size, rank)][rank];
		int max = (int) ((Math.pow(2, rank) - 1) * Math.pow(2, size - rank));
		int placeInSubsetsArray = 0;
		for(int testSubset = (int) (Math.pow(2, rank) - 1); testSubset <=  max; ++ testSubset) {
			int[] testSubsetArray = new int[rank];
			int digitPlace = 0;
			int i = 0;
			while(digitPlace < size && i < testSubsetArray.length) {
				if((testSubset & (1 << digitPlace)) != 0) {
					testSubsetArray[i++] = digitPlace;
				}
				digitPlace++;
			}
			if (digitPlace == size && i == testSubsetArray.length) {
				subsets[placeInSubsetsArray++] = testSubsetArray;
			}
		}
		for(int[] rowSubset : subsets) {
			for(int[] colSubset : subsets) {
				Matrix testMatrix = bigMatrix.getMatrix(rowSubset, colSubset);
				if(testMatrix.rank() == rank) return testMatrix;
			}
		}
		throw new RuntimeException("Was not able to find matrix: CHECK ME!!!!");
	}
	
	public static int nChooseK(int n, int k) {
		if(k > n/2) {
			k = n - k;
		}
		if(k < 0) return 0;
		int nCk = 1;
		for(int testK = 0; testK <= k; ++testK) {
				nCk = (nCk * (n - k))/(k + 1);
		}
		return k;
	}
	
	public static boolean[] convertDoubleArrayToBooleanArray(double[] array) {
		boolean[] newArray = new boolean[array.length];
		for(int i = 0; i < array.length; ++i) {
			newArray[i] = array[i] != 0;
		}
		return newArray;
	}
}
