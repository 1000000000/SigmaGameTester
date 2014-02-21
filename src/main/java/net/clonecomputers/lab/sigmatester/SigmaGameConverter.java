package net.clonecomputers.lab.sigmatester;

import java.util.Arrays;

import Jama.Matrix;

public class SigmaGameConverter {

	public static SigmaGame convertToSigmaGame(Matrix toConvert) {
		int dim = Math.max(toConvert.getRowDimension(), toConvert.getColumnDimension());
		Matrix matrix = null;
		double[][] matrixArray = new double[dim][dim];
		double[] deltaArray = new double[dim];
		if(toConvert.getRowDimension() < dim) {
			double[][] toConvertArray = toConvert.getArray();
			for(int i = 0; i < toConvert.getRowDimension(); ++i) {
				matrixArray[i] = toConvertArray[i];
			}
			Arrays.fill(matrixArray, toConvert.getRowDimension() + 1, dim - 1, toConvertArray[0]);
			matrix = Matrix.constructWithCopy(matrixArray);
		} else if(toConvert.getColumnDimension() < dim) {
			for(int i = 0; i < toConvert.getColumnDimension(); ++i) {
				matrixArray[i] = toConvert.getColumnVector(i);
			}
			double[] fillerArray = new double[dim];
			Arrays.fill(matrixArray, toConvert.getColumnDimension() + 1, dim - 1, fillerArray);
			matrix = Matrix.constructWithCopy(matrixArray).transpose();
		} else {
			matrix = toConvert.copy();
		}
		for(int i = 0; i < dim; ++i) {
			int deltaValue = 1;
			double[] row = matrix.getArray()[i];
			for(int j = 0; j < dim; ++j) {		
				if(row[j] != 0) {
					deltaValue *= Math.round(row[j]);
					row[j] = 1;
				}
			}
			if(deltaValue == -1) deltaValue = 0;
			deltaArray[i] = deltaValue;
		}
		return new SigmaGame(matrix, deltaArray);
	}

}
