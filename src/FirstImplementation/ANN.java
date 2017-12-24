/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Mateen Qureshi and Omar El-Etr
 * Date: Mar 8, 2017
 * Time: 2:51:01 AM
 *
 * Project: csci205_hw
 * Package: hw01
 * File: ANN
 * Description:
 *
 * ****************************************
 */
package hw01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Mateen Qureshi and Omar El-Etr
 */
public class ANN {

    public final static double LEARNING_RATE = 0.3;
    /**
     * A boolean that shows if the ANN is trained or not
     */
    private boolean isTrained;

    /**
     *
     *
     * the number of inputs to be fed into the ANN
     */
    private int numInputs;

    /**
     * The number of outputs to be expected from the ANN
     */
    private int numOutputs;

    /**
     * An integer representing the number of hidden layers to be put into the
     * ANN
     */
    private int numHiddenLayers;

    /**
     * The number of neurons in each hidden layer
     */
    private int numHiddenNeurons;

    /**
     * An ArrayList representing the inputs to the ANN
     */
    private ArrayList<Double> inputList;

    /**
     * An ArrayList representing the expected output from the ANN
     *
     */
    private ArrayList<Double> expOutputList;

    /**
     * An ArrayList representing the actual output from the ANN
     */
    private ArrayList<Double> actualOutputList;

    /**
     * A double representing the maximum Sum Squared Error allowed
     */
    private double maxSSError;

    /**
     * A double representing the actual Sum Squared Error
     */
    private double actualSSError;

    /**
     * An ArrayList consisting of the multiple layers of Layers that comprise
     * the ANN
     */
    ArrayList<Layer> layerList;

    /**
     * The layer representing the output Layer in the ANN
     */
    private OutputLayer outputLayer;

    /**
     * The layer representing the input Layer in the ANN
     */
    private InputLayer inputLayer;

    /**
     * The constructor for ANNs
     *
     * @param inTraining
     * @param numInputs
     * @param numOutputs
     * @param numHiddenLayers
     * @param numHiddenNeurons
     * @param maxSSError
     */
    public ANN(boolean inTraining, int numInputs, int numOutputs,
               int numHiddenLayers, int numHiddenNeurons, double maxSSError) {
        this.isTrained = isTrained;
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        this.numHiddenLayers = numHiddenLayers;
        this.numHiddenNeurons = numHiddenNeurons;
        this.maxSSError = maxSSError;
        this.actualSSError = 0;
        this.inputList = new ArrayList();
        this.expOutputList = new ArrayList();
        this.actualOutputList = new ArrayList();
        this.outputLayer = null;
        this.inputLayer = null;
        this.layerList = new ArrayList();
        this.setUpANN(numInputs, numOutputs,
                      numHiddenLayers, numHiddenNeurons);
    }

    public int getNumInputs() {
        return numInputs;
    }

    /**
     * Sets up the ANN according to the inputs received
     *
     * @param numInputs is the name of the number of inputs to be fed into the
     * ANN
     * @param numOutputs is the name of the number of outputs to be fed into the
     * ANN
     * @param numHiddenLayers is the name of the number of HiddenLayer objects
     * that will be instantiated
     * @param numHiddenNeurons is the name of the number of HiddenNeuron objects
     * that will be instantiated for each HiddenLayer
     */
    public void setUpANN(int numInputs, int numOutputs,
                         int numHiddenLayers,
                         int numHiddenNeurons) {
        InputLayer inLayer = new InputLayer(numInputs);

        Layer prevLayer = inLayer;
        this.inputLayer = inLayer;
        this.layerList.add(this.inputLayer);

        for (int i = 0; i < numHiddenLayers; i++) {
            Layer hiddenLayer = new HiddenLayer(numHiddenNeurons, prevLayer);
            this.layerList.add(hiddenLayer);
            hiddenLayer.setPrevLayer(prevLayer);
            prevLayer.setNextLayer(hiddenLayer);
            prevLayer = hiddenLayer;
        }
        OutputLayer outLayer = new OutputLayer(numOutputs, prevLayer);
        this.outputLayer = outLayer;
        outLayer.setPrevLayer(prevLayer);
        prevLayer.setNextLayer(outLayer);
        this.layerList.add(this.outputLayer);

    }

    /**
     * This is the method that enables backward propagation. It proceeds from
     * the last Layer to the first
     */
    public void updateANN() {
        for (int i = this.layerList.size() - 1; i > 0; i--) {
            this.layerList.get(i).updateLayer();
        }

    }

    /**
     * Reads input from a file into the ANN
     *
     * @param fileNameStr is a String representing the name of the file from
     * which input is to be read
     * @throws FileNotFoundException if the file name provided as input is not
     * correct
     */
    public void readInputs(String fileNameStr) throws FileNotFoundException {
        this.inputList.clear();
        File inputFile = new File(fileNameStr);
        Scanner scanner = new Scanner(inputFile);
        String[] listOfInputsStrings = scanner.nextLine().split(",");
        ArrayList<Neuron> inputNeurons = this.inputLayer.getNeuronList();
        for (int i = 0; i < this.numInputs; i++) {
            Neuron n = inputNeurons.get(i);
            Double newInput = Double.parseDouble(listOfInputsStrings[i]);
            this.inputList.add(newInput);
            n.setActivationOutput(newInput);

        }
    }

    /**
     * This method takes in two ArrayLists denoting the Inputs and expected
     * Outputs to the ANN and trains the ANN accordingly; while the actual sum
     * squared error for the actual outputs is greater than the maximum allowed
     * sum squared error, the ANN will be updated
     *
     * @param inList
     * @param expOutList
     */
    public void trainANN(ArrayList<Double> inList,
                         ArrayList<Double> expOutList) {
        this.inputList = inList;
        this.expOutputList = expOutList;
        this.inputLayer.putInputs(inList);
        this.outputLayer.putExpectedOutputs(expOutputList);
        for (Neuron n : this.outputLayer.getNeuronList()) {
            System.out.println(
                    "The new targert output is " + n.getTargetOutput());
        }
        do {
            this.calculateActivation();
            this.updateANN();
            System.out.println(
                    "Error difference is " + (this.actualSSError - this.maxSSError));
        } while (Math.abs(this.actualSSError) > Math.abs(this.maxSSError));

    }

    /**
     * @see
     * http://www.instanceofjava.com/2016/08/create-csv-file-in-java-code-example.html
     * @param fileName is the name of the CSV file in which the weights are to
     * be stored
     */
    public void saveWeights(String fileName) {
        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter(fileName);

            for (Layer l : this.layerList.subList(1, this.layerList.size())) {
                for (Neuron n : l.getNeuronList()) {
                    for (Double d : n.getInputData()) {
                        csvWriter.append(Double.toString(d));
                        csvWriter.append(',');

                    }
                    csvWriter.append('\n');
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                csvWriter.flush();
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * For a given csv file name, this method uses the contents of the file to
     * assign weights to each connection between Neurons
     *
     * @param fileName is the name of the csv file from which weights are to be
     * read
     * @throws FileNotFoundException if the file is not found
     */
    public void readWeights(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        ArrayList<Neuron> listOfAllNeurons = new ArrayList<>();
        for (Layer l : this.layerList.subList(1, this.layerList.size())) {
            for (Neuron n : l.getNeuronList()) {
                listOfAllNeurons.add(n);
            }
        }
        for (int n = 0; n < listOfAllNeurons.size(); n++) {
            String[] nodeConnectionsList = scanner.nextLine().split(",");
            ArrayList<Double> newWeights = new ArrayList<>();
            for (String s : nodeConnectionsList) {
                newWeights.add(Double.parseDouble(s));
            }
            listOfAllNeurons.get(n).replaceInputData(newWeights);
        }

    }

    /**
     * Calculates the activation output for each Neuron object in the ANN by
     * calling the calculateActivation() method on each Layer in the ANN.
     * Proceeds from the first Layer to the last. In the end, it updates the
     * actual sum squared error to the sum squared error in the outputLayer
     */
    public void calculateActivation() {
        for (Layer l : this.layerList) {
            l.calculateActivation();
        }
        this.actualSSError = this.outputLayer.getSSError();
    }

    public boolean checkIfTrained(String fileName) throws FileNotFoundException {
        File csvFile = new File("TrainingData.csv");
        Scanner scanner = new Scanner(csvFile);
        ArrayList<Double> inList = new ArrayList<>();
        ArrayList<Double> expOutList = new ArrayList<>();

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] inputArray = input.split(",");
            inList.clear();
            expOutList.clear();
            int i;
            for (i = 0; i < this.numInputs; i++) {
                System.out.println(inputArray[i]);
                double inputAsDouble = Double.parseDouble(inputArray[i]);
                inList.add(inputAsDouble);
            }
            System.out.println("The input Array is" + inList);

            for (i = this.numInputs; i < this.numInputs + this.numOutputs; i++) {
                double inputAsDouble = Double.parseDouble(inputArray[i]);
                expOutList.add(inputAsDouble);
            }
            System.out.println("The exp output array is " + expOutList);
            this.inputList = inList;
            this.expOutputList = expOutList;
            this.inputLayer.putInputs(inList);
            this.outputLayer.putExpectedOutputs(expOutList);
            this.calculateActivation();
            for (Neuron n : this.outputLayer.getNeuronList()) {
                System.out.println(
                        "Activation output for this cycle" + n.getActivationOutput() + "while target output is " + n.getTargetOutput());

            }

            if (Math.abs(this.actualSSError) > Math.abs(this.maxSSError)) {
                System.out.println(
                        "This is false because of " + inList + " which should be giving output " + expOutList);
                return false;
            }

        }
        System.out.println(
                "This is true because of " + inList + "giving output " + expOutList);

        return true;
    }

    /**
     * This takes in a csv file and processes it to produce an ArrayList of
     * inputs and expected outputs, assigns it to the current ANN and then
     * trains the ANN on these data until the Sum Squared Error is less than the
     * maximum allowed error
     *
     * @param fileName
     * @throws java.io.FileNotFoundException
     */
    public void processCSVAndTrainANN(String fileName) throws FileNotFoundException {
        File csvFile = new File("TrainingData.csv");
//        String curDir = System.getProperty("user.dir");
//        System.out.println(curDir);
//        System.out.println(csvFile.exists());
//        System.out.println(new File("TrainingData.csv").getAbsoluteFile());
        while (this.checkIfTrained(fileName) == false) {
            Scanner scanner = new Scanner(csvFile);
            ArrayList<Double> inList = new ArrayList<>();
            ArrayList<Double> expOutList = new ArrayList<>();

            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                String[] inputArray = input.split(",");
                inList.clear();
                expOutList.clear();
                int i;
                System.out.println("The arraylist as a string is " + inputArray);
                for (i = 0; i < this.numInputs; i++) {
                    System.out.println(inputArray[i]);
                    double inputAsDouble = Double.parseDouble(inputArray[i]);
                    inList.add(inputAsDouble);
                }
                System.out.println("The input Array is" + inList);

                for (i = this.numInputs; i < this.numInputs + this.numOutputs; i++) {
                    double inputAsDouble = Double.parseDouble(inputArray[i]);
                    expOutList.add(inputAsDouble);
                }
                System.out.println("The output array is " + expOutList);
                this.trainANN(inList, expOutList);

            }
        }
        for (Neuron n : this.outputLayer.getNeuronList()) {
            n.setFinalOutput();
        }
        this.isTrained = true;
        this.saveWeights("SavedWeights.csv");

    }

    /**
     * Takes in the names of input files and weight files and executes it on an
     * ANN that is untrained
     *
     * @param inputFileStr is the name of the file from which inputs are to be
     * read in
     * @param weightsFileStr is the name of the file from which weights are to
     * be read in
     * @throws FileNotFoundException if either of the file names provided are
     * incorrect
     */
    public void executeClassificationModeForUntrainedANN(String inputFileStr,
                                                         String weightsFileStr) throws FileNotFoundException {
        File weightsFile = new File(weightsFileStr);

        this.readInputs(inputFileStr);
        this.readWeights(weightsFileStr);
        this.calculateActivation();
        this.printAndSaveOutputs();

    }

    /**
     * This handles the classification mode for an ANN that has already been
     * trained.
     *
     * @param inputFileStr is the name of the File that provides the input to
     * the ANN
     * @throws FileNotFoundException if the file name provided is incorrect
     */
    public void executeClassificationModeForTrainedANN(String inputFileStr) throws FileNotFoundException {
        this.readInputs(inputFileStr);
        this.calculateActivation();
        this.printAndSaveOutputs();
    }

    /**
     * Prints the outputs to the console and saves them to a file "output.csv"
     */
    public void printAndSaveOutputs() {
        FileWriter outputWriter = null;
        String outputString = "";
        System.out.println(this.inputList);
        for (Neuron n : this.outputLayer.getNeuronList()) {
            System.out.println(n.getActivationOutput());
            System.out.println(n.getInputData());
        }
        try {
            outputWriter = new FileWriter("output.csv");
            ArrayList<Neuron> outputNeuronList = this.outputLayer.getNeuronList();
            for (int i = 0; i < this.numOutputs - 1; i++) {
                int output = outputNeuronList.get(i).getFinalOutput();
                outputString += Integer.toString(output);
                outputString += (',');

            }
            int output = outputNeuronList.get(this.numOutputs - 1).getFinalOutput();
            outputString += output;
            System.out.println(outputString);
            outputWriter.append(outputString);
            outputWriter.append('\n');

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputWriter.flush();
                outputWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
