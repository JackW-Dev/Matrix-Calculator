import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val scanner = Scanner(System.`in`)
    do {
        println("1. Add matrices")
        println("2. Multiply matrix to a constant")
        println("3. Multiply matrices")
        println("4. Transpose matrix")
        println("5. Determinant")
        println("6. Inverse")
        println("0. Exit")
        print("Your choice: ")
        val userChoice: Int = scanner.nextInt()

        when(userChoice) {
            1 -> {
                print("Enter the size of the first matrix: ")
                val matrixA: ArrayList<ArrayList<Double>> = readMatrixFromConsole(scanner)
                print("Enter the size of the second matrix: ")
                val matrixB: ArrayList<ArrayList<Double>> = readMatrixFromConsole(scanner)
                println("The addition result is: ")
                printMatrix(addMatrix(matrixA, matrixB))
            }
            2 -> {
                print("Enter the size of the matrix: ")
                val matrix: ArrayList<ArrayList<Double>> = readMatrixFromConsole(scanner)
                print("Enter the constant: ")
                val const: Double = scanner.nextDouble()
                println("The multiplication result is: ")
                printMatrix(multiplyMatrixByConstant(matrix, const))
            }
            3 -> {
                print("Enter the size of the first matrix: ")
                val matrixA: ArrayList<ArrayList<Double>> = readMatrixFromConsole(scanner)
                print("Enter the size of the second matrix: ")
                val matrixB: ArrayList<ArrayList<Double>> = readMatrixFromConsole(scanner)
                println("The multiplication result is: ")
                printMatrix(multiplyMatrices(matrixA, matrixB))
            }
            4 -> {
                println("1. Main diagonal")
                println("2. Side diagonal")
                println("3. Vertical line")
                println("4. Horizontal line")
                print("Your choice: ")
                val userChoiceTranspose: Int = scanner.nextInt()

                print("Enter the size of the matrix: ")
                val matrix: ArrayList<ArrayList<Double>> = readMatrixFromConsole(scanner)

                when (userChoiceTranspose) {
                    1 -> printMatrix(transposeMatrixMain(matrix))
                    2 -> printMatrix(transposeMatrixSide(matrix))
                    3 -> printMatrix(transposeMatrixVertical(matrix))
                    4 -> printMatrix(transposeMatrixHorizontal(matrix))
                }
            }
            5 -> {
                print("Enter the size of the matrix: ")
                val matrix: ArrayList<ArrayList<Double>> = readMatrixFromConsole(scanner)
                println("The determinant is: ")
                println(matrixDeterminant(matrix))
            }
            6 -> {
                print("Enter the size of the matrix: ")
                val matrix: ArrayList<ArrayList<Double>> = readMatrixFromConsole(scanner)
                println("The inverse is: ")
                printMatrix(inverseMatrix(matrix))
            }
        }
        println("")
    } while(userChoice != 0)
}

fun printMatrix(matrix: ArrayList<ArrayList<Double>>) {
    for (i in 0 until matrix.size){
        for (j in 0 until matrix[0].size) {
            print(matrix[i][j])
            if (j != matrix[0].size - 1){
                print(" ")
            }
        }
        println("")
    }
}

fun readMatrixFromConsole(scanner: Scanner): ArrayList<ArrayList<Double>> {
    val rows = scanner.nextInt()
    val columns = scanner.nextInt()

    val matrix: ArrayList<ArrayList<Double>> = ArrayList()

    println("Enter the matrix:")

    for (i in 0 until rows) {
        val tempArrList: ArrayList<Double> = ArrayList()
        for (j in 0 until columns) {
            tempArrList.add(scanner.nextDouble())
        }
        matrix.add(tempArrList)
    }
    return matrix
}

fun addMatrix(matrixA: ArrayList<ArrayList<Double>>, matrixB: ArrayList<ArrayList<Double>>): ArrayList<ArrayList<Double>> {
    val resultMatrix: ArrayList<ArrayList<Double>> = ArrayList()

    for (i in 0 until matrixA.size) {
        resultMatrix.add(ArrayList())
    }

    if (matrixA.size == matrixB.size) {
        for (i in 0 until matrixA.size){
            for (j in 0 until matrixA[0].size) {
                resultMatrix[i].add(j, matrixA[i][j] + matrixB[i][j])
            }
        }
    } else {
        print("ERROR")
    }
    return resultMatrix
}

fun multiplyMatrixByConstant(matrix: ArrayList<ArrayList<Double>>, constant: Double): ArrayList<ArrayList<Double>> {
    for (i in 0 until matrix.size){
        for (j in 0 until matrix[0].size) {
            matrix[i][j] = matrix[i][j] * constant
        }
    }
    return matrix
}

fun multiplyMatrices(matrixA: ArrayList<ArrayList<Double>>, matrixB: ArrayList<ArrayList<Double>>): ArrayList<ArrayList<Double>> {
    val resultMatrix: ArrayList<ArrayList<Double>> = ArrayList(matrixA.size)

    if(matrixA[0].size == matrixB.size) {
        for (i in 0 until matrixA.size) {
            resultMatrix.add(ArrayList(matrixB[0].size))
        }

        for (i in 0 until matrixA.size) {
            for (j in 0 until matrixB[0].size) {
                resultMatrix[i].add(j, 0.0)
                for (k in 0 until matrixB.size) {
                    resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j]
                }
            }
        }
    } else {
        print("ERROR")
    }
    return resultMatrix
}

fun transposeMatrixMain(matrix: ArrayList<ArrayList<Double>>): ArrayList<ArrayList<Double>> {
    val resultMatrix: ArrayList<ArrayList<Double>> = ArrayList()

    if (matrix.size > matrix[0].size) {
        for (i in 0 until matrix.size) {
            resultMatrix.add(ArrayList(matrix[0].size))
        }
    } else {
        for (i in 0 until matrix[0].size) {
            resultMatrix.add(ArrayList(matrix.size))
        }
    }

    for (i in 0 until matrix.size) {
        for (j in 0 until matrix[0].size) {
            resultMatrix[j].add(i, matrix[i][j])
        }
    }

    return resultMatrix
}

fun transposeMatrixSide(matrix: ArrayList<ArrayList<Double>>): ArrayList<ArrayList<Double>>{
    val resultMatrix: ArrayList<ArrayList<Double>> = ArrayList()

    if (matrix.size > matrix[0].size) {
        for (i in 0 until matrix.size) {
            resultMatrix.add(ArrayList(matrix[0].size))
        }
    } else {
        for (i in 0 until matrix[0].size) {
            resultMatrix.add(ArrayList(matrix.size))
        }
    }

    for (i in 0 until matrix.size) {
        matrix[i].reverse()
    }

    for (i in 0 until matrix.size) {
        for (j in 0 until matrix[0].size) {
            resultMatrix[j].add(i, matrix[i][j])
        }
    }

    for (i in 0 until resultMatrix.size) {
        resultMatrix[i].reverse()
    }

    return resultMatrix
}

fun transposeMatrixVertical(matrix: ArrayList<ArrayList<Double>>): ArrayList<ArrayList<Double>> {
    var resultMatrix: ArrayList<ArrayList<Double>> = ArrayList()

    for (i in 0 until matrix.size) {
        resultMatrix.add(ArrayList(matrix[0].size))
    }

    resultMatrix = matrix

    for (i in 0 until resultMatrix.size) {
        resultMatrix[i].reverse()
    }

    return resultMatrix
}

fun transposeMatrixHorizontal(matrix: ArrayList<ArrayList<Double>>): ArrayList<ArrayList<Double>> {
    var resultMatrix: ArrayList<ArrayList<Double>> = ArrayList()

    for (i in 0 until matrix[0].size) {
        resultMatrix.add(ArrayList(matrix.size))
    }

    resultMatrix = matrix

    resultMatrix.reverse()

    return resultMatrix
}

fun matrixDeterminant(matrix: ArrayList<ArrayList<Double>>): Double {
    var determinant = 0.0

    if (matrix.size == matrix[0].size) {
        when(matrix.size) {
            0 -> println("ERROR - NO MATRIX FOUND")
            1 -> determinant = matrix[0][0]
            2 -> determinant = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
            else -> {
                for (i in 0 until matrix.size) {
                    for (j in 0 until matrix.size) {
                        if (j > i) {
                            val ratio = matrix[j][i]/matrix[i][i]
                            for(k in 0 until matrix.size) {
                                matrix[j][k] -= ratio * matrix[i][k]
                            }
                        }
                    }
                }
                determinant = 1.0
                for(i in 0 until matrix.size) {
                    determinant *= matrix[i][i]
                }
            }
        }
    } else {
        println("ERROR - CAN ONLY FIND DETERMINANT OF SQUARE MATRICES")
    }
    return determinant
}

fun inverseMatrix(matrix: ArrayList<ArrayList<Double>>): ArrayList<ArrayList<Double>> {
    var resultMatrix: ArrayList<ArrayList<Double>> = matrix
    for (i in 0 until resultMatrix.size) {
        for (j in 0 until resultMatrix[0].size) {
            resultMatrix[i][j] = 0.0
        }
    }

    //Inverse matrix = 1/Det * Cofactor Matrix Transposed

    return resultMatrix
}