package net.clonecomputers.lab.sigmatester;

import java.util.Arrays;

import org.jscience.mathematics.number.Rational;
import org.jscience.mathematics.vector.DenseMatrix;
import org.jscience.mathematics.vector.DenseVector;
import org.jscience.mathematics.vector.Matrix;

public class SigmaGameConverter {

	public static final Rational NEG_ONE = Rational.valueOf(-1, 1);

	public static SigmaGame convertToSigmaGame(Matrix<Rational> toConvert) {
		int dim = Math.max(toConvert.getNumberOfRows(), toConvert.getNumberOfColumns());
		DenseMatrix<Rational> matrix = null;
		@SuppressWarnings("unchecked")
		DenseVector<Rational>[] matrixArray = new DenseVector[dim];
		Rational[] deltaArray = new Rational[dim];
		if(toConvert.getNumberOfRows() < dim) {
			for(int i = 0; i < toConvert.getNumberOfRows(); ++i) {
				matrixArray[i] = DenseVector.valueOf(toConvert.getRow(i));
			}
			Arrays.fill(matrixArray, toConvert.getNumberOfRows() + 1, dim - 1, toConvert.getRow(0));
			matrix = DenseMatrix.valueOf(matrixArray);
		} else if(toConvert.getNumberOfColumns() < dim) {
			for(int i = 0; i < toConvert.getNumberOfColumns(); ++i) {
				matrixArray[i] = DenseVector.valueOf(toConvert.getColumn(i));
			}
			Rational[] fillerArray = new Rational[dim];
			Arrays.fill(fillerArray, Rational.ZERO);
			DenseVector<Rational> filler = DenseVector.valueOf(fillerArray);
			Arrays.fill(matrixArray, toConvert.getNumberOfColumns() + 1, dim - 1, filler);
			matrix = DenseMatrix.valueOf(matrixArray).transpose();
		} else {
			matrix = DenseMatrix.valueOf(toConvert);
		}
		for(int i = 0; i < dim; ++i) {
			Rational deltaValue = Rational.ONE;
			DenseVector<Rational> row = matrix.getRow(i);
			for(int j = 0; j < dim; ++j) {
				Rational value = row.get(j);			
				if(!value.equals(Rational.ZERO)) deltaValue = deltaValue.times(value);
			}
			if(deltaValue.equals(NEG_ONE)) deltaValue = Rational.ZERO;
			deltaArray[i] = deltaValue;
		}
		return new SigmaGame(matrix, DenseVector.valueOf(deltaArray));
	}

}
