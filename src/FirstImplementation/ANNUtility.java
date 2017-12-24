/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Mateen Qureshi and Omar El-Etr
 * Date: Mar 15, 2017
 * Time: 10:15:32 PM
 *
 * Project: csci205_hw
 * Package: hw01
 * File: ANNUtility
 * Description:
 *
 * ****************************************
 */
package hw01;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Mateen Qureshi and Omar El-Etr
 */
public class ANNUtility {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        ANN currANN;
        System.out.println("Welcome!");
        System.out.print(
                "Do you wish to create a new ANN? If so enter y. Else an ANN will be read in: ");
        Scanner in = new Scanner(System.in);
        String choice = in.next();
        System.out.println();
        if (choice.equals("y") || choice.equals("Y") || choice.equals("yes") || choice.equals(
                "Yes") || choice.equals("YES")) {

            System.out.print(
                    "Do you wish to enter Training Mode? if so enter y. Else you will enter the classification mode. ");
            choice = in.next();
            System.out.println();
            if (choice.equals("y") || choice.equals("Y") || choice.equals("yes") || choice.equals(
                    "Yes") || choice.equals("YES")) {
                System.out.print("Please enter the number of Inputs: ");
                choice = in.next();
                System.out.println();
                int numInputs = Integer.parseInt(choice);
                System.out.print("Please enter the number of hidden layers: ");
                choice = in.next();
                int numHiddenLayers = Integer.parseInt(choice);
                System.out.println();
                System.out.print(
                        "Please enter the number of Hidden Neurons in each Hidden Layer: ");
                choice = in.next();
                int numHiddenNeurons = Integer.parseInt(choice);
                System.out.println();
                System.out.print(
                        "Please enter the number of outputs in the ANN: ");
                choice = in.next();
                int numOutputs = Integer.parseInt(choice);
                System.out.println();
                System.out.print(
                        "Please enter the max sum squared error to be allowed for this ANN: ");
                choice = in.next();
                double maxSSError = Double.parseDouble(choice);
                System.out.println();
                currANN = new ANN(true, numInputs, numOutputs, numHiddenLayers,
                                  numHiddenNeurons, maxSSError);
                System.out.print(
                        "Please enter the name of the training data file: ");
                String fileName = in.next();
                System.out.println();
                System.out.println(
                        "Would you like to enter edge weights from an external file? ");
                System.out.print("y/n: ");

                System.out.println();
                if (choice.equals("y") || choice.equals("Y") || choice.equals(
                        "yes") || choice.equals(
                                "Yes") || choice.equals("YES")) {
                    System.out.print("Please enter external file name: ");
                    String weightsFileName = in.next();
                    currANN.readWeights(weightsFileName);
                }
                currANN.processCSVAndTrainANN(fileName);

            }

            else {
                System.out.print("Please enter the number of Inputs: ");
                choice = in.next();
                System.out.println();
                int numInputs = Integer.parseInt(choice);
                System.out.print("Please enter the number of hidden layers: ");
                choice = in.next();
                int numHiddenLayers = Integer.parseInt(choice);
                System.out.println();
                System.out.print(
                        "Please enter the number of Hidden Neurons in each Hidden Layer: ");
                choice = in.next();
                int numHiddenNeurons = Integer.parseInt(choice);
                System.out.println();
                System.out.print(
                        "Please enter the number of outputs in the ANN: ");
                choice = in.next();
                int numOutputs = Integer.parseInt(choice);
                System.out.println();
                System.out.print(
                        "Please enter the max sum squared error to be allowed for this ANN: ");
                choice = in.next();
                double maxSSError = Double.parseDouble(choice);
                System.out.println();
                currANN = new ANN(true, numInputs, numOutputs, numHiddenLayers,
                                  numHiddenNeurons, maxSSError);
                System.out.print(
                        "Please enter the name of the file with the inputs");
                String inputFile = in.next();
                System.out.println();
                System.out.print(
                        "Please enter the name of the training data file: ");
                String trainingFileName = in.next();
                System.out.println();
                currANN.processCSVAndTrainANN(trainingFileName);
                currANN.executeClassificationModeForTrainedANN(inputFile);
            }
        }
    }

}
