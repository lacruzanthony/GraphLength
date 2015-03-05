import java.io.File;
import java.io.IOException;

import java.util.Scanner;

public class GraphLength {

    public static void main(String[] args) {
        //Initializing vars for the try-catch block
        int matrixSize = 0;
        int pathLength = 0;
        int[][] inputMatrix = null;
        Scanner readFile;

        GraphLength callerMethod = new GraphLength();

        try {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Enter the name of your adjacency list file");
            readFile = new Scanner(new File(userInput.next()));

            System.out.println("Enter the number of nodes in your graph");
            matrixSize = userInput.nextInt();

            System.out.println("Enter the path length you want to work with");
            pathLength = userInput.nextInt();

            inputMatrix = new int[matrixSize][matrixSize];

            //Populates the initial vertices in the matrix.
            while (readFile.hasNextInt()) {
                int firstVertex = readFile.nextInt();
                int secondVertex = readFile.nextInt();
                inputMatrix[firstVertex][secondVertex] = 1;
                inputMatrix[secondVertex][firstVertex] = 1;
            }
        } catch(IOException e){
            System.out.println("Error: File not found. Check the path and try again.");
            System.exit(0);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Your input file contains nodes that fall out of your " +
                    "provided number of nodes (Example: You said 5 nodes when the input file " +
                    "contains the number 7). Please check this and try again.");
        }

        //PRINT STATEMENT FOR INPUT MATRIX
        System.out.println("Input adjacency matrix");

        for (int i = 0; i < matrixSize; i++) {
            System.out.print("   " + i);
        }
        System.out.println();

        for (int i = 0; i < matrixSize; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < matrixSize; j++) {
                System.out.print("[" + inputMatrix[i][j] + "] ");
            }
            System.out.println();
        }
        System.out.println();
        //END INPUT MATRIX

        int[][] resultantMatrix = callerMethod.matrixToPower(inputMatrix, pathLength);

        //PRINT STATEMENT FOR OUTPUT MATRIX
        System.out.println("Number of Paths of Length " + pathLength);

        for (int i = 0; i < matrixSize; i++) {
            System.out.print("   " + i);
        }
        System.out.println();

        for (int i = 0; i < matrixSize; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < matrixSize; j++) {
                System.out.print("[" + resultantMatrix[i][j] + "] ");
            }
            System.out.println();
        }
        //END OUTPUT MATRIX
    }

    public int[][] matrixMultiply(int[][] firstMatrix, int[][] secondMatrix) {
        int arraySize = firstMatrix.length;
        int[][] outputMatrix = new int[arraySize][arraySize];

        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                for (int k = 0; k < arraySize; k++) {
                    outputMatrix[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        return outputMatrix;
    }

    public int[][] matrixToPower(int[][] matrixToRaise, int power) {
        int[][] result = matrixToRaise;
        for (int n = 1; n < power; n++) {
            result = matrixMultiply(result, matrixToRaise);
        }
        return result;
    }
}