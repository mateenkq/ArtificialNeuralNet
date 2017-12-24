/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Mateen Qureshi and Omar El-Etr
 * Date: Mar 8, 2017
 * Time: 12:24:51 AM
 *
 * Project: csci205_hw
 * Package: hw01
 * File: Node
 * Description:
 *
 * ****************************************
 */
package hw01;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class representing a Neuron which may take an input representing the layer
 * and produces an output after applying an activation (sigmoid) function on the
 * net
 *
 * @author Mateen Qureshi and Omar El-Etr
 *
 */
public class Neuron {

//    /**
//     * This is the current ANNo
//     */
//    private ANN currANN;
    /**
     * this is the number of neurons with input edges to the current Neuron
     */
    private int numInputs;

//    public final static double LEARNING_RATE = 0.3;
    /**
     * this is the target output for an instance of a Neuron.
     */
    private double targetOutput;

    /**
     * this represents the final output
     */
    private int finalOutput;

    /**
     * This is the theta used to calculate the net function
     */
    public double theta;

    /**
     * this is the error between target output for a Neuron and its actual
     * output
     */
    private double error;

    /**
     * this is the square of the error
     */
    private double squaredError;

    public double getSquaredError() {
        return squaredError;
    }

    /**
     * Each element of inputData corresponds to the weight assigned to the
     * Neuron in that particular position in the previous Layer
     */
    private ArrayList<Double> inputData;

    // A double representing the output from the neuron after applying the Activation function on the net obtained from the inputs provided by the previous Layer
    private double activationOutput;

    // a Layer object representing the next Layer to the current Neuron
    Layer nextLayer;

    // a Layer object representing the layer in which the neuron is
    Layer currLayer;

    /**
     * A Layer object representing the previous Layer
     */
    Layer prevLayer;

    // delta is the error gradient for the neuron
    double delta;

    /**
     * Neuron instantiated with an input that is a Layer object
     *
     * @param currLayer - a Layer representing the Layer object in which the
     * Neuron instance is to be placed.
     */
    public Neuron(Layer currLayer) {
//        this.currANN = currANN;
        this.numInputs = 0;
        this.nextLayer = null;
        this.prevLayer = null;
        this.currLayer = currLayer;
        this.inputData = new ArrayList<>();
        this.activationOutput = 0;
        this.finalOutput = 0;
        this.delta = 0;
        this.targetOutput = 0;
        this.theta = 0;

    }

    public Layer getCurrLayer() {
        return currLayer;
    }

    public void setCurrLayer(Layer currLayer) {
        this.currLayer = currLayer;
    }

    public Layer getPrevLayer() {
        return prevLayer;
    }

    public void setPrevLayer(Layer prevLayer) {
        this.prevLayer = prevLayer;
    }

    public int getNumInputs() {
        return numInputs;
    }

    public void setNumInputs(int numInputs) {
        this.numInputs = numInputs;
    }

    public int getFinalOutput() {
        return finalOutput;
    }

    public void setFinalOutput() {

    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public double getTargetOutput() {
        return targetOutput;
    }

    public void setTargetOutput(double targetOutput) {
        this.targetOutput = targetOutput;
    }

    public ArrayList<Double> getInputData() {
        return inputData;
    }

    public void setInputData(int n, Double weight) {
        this.inputData.set(n, weight);
    }

    public void replaceInputData(ArrayList<Double> newWeights) {
        this.inputData = newWeights;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public double getActivationOutput() {
        return activationOutput;
    }

    public void setActivationOutput(double activationOutput) {
        this.activationOutput = activationOutput;
    }

    public Layer getNextLayer() {
        return nextLayer;
    }

    public void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }

    /**
     * Sets up the input data according to the set of neurons (layer) feeding
     * into a neuron and the weights for each neuron connection
     *
     * @param previousLayer
     */
    public void setUpInsAndWeights(Layer previousLayer) {
        ArrayList<Neuron> inputList = previousLayer.getNeuronList();   //getNeuronList() is not written yet --> goes into Layer class
        int numberOfInputs = inputList.size();
        Random r = new Random();    // A new Random object
        for (int n = 0; n < numberOfInputs; n++) {
            double inputValue = inputList.get(n).getActivationOutput();
            double rangeMin = -2.4 / numberOfInputs;
            double rangeMax = 2.4 / numberOfInputs;
            double rWeight = rangeMin + ((rangeMax - rangeMin) * r.nextDouble()); // Assigns a random number in the range [-2.4/n, 2.4/n] to rWeight where n is the number of input edges to the neuron
            this.inputData.add(rWeight); // Put the neuron feeding into the current neuron and the corresponding weight for that edge in the inputData

        }
    }

    public void readWeights(ArrayList<Double> weightList) {
        if (weightList.size() == this.inputData.size()) {
            int i = 0;
            this.inputData = weightList;
        }
    }

    /**
     * Updates the weights of the inputs leading in and consequently updates the
     * activation output. It does so according to any changes in delta
     */
    public void updateInsAndWeights() {
        ArrayList<Neuron> inputList = this.prevLayer.getNeuronList();   //Assigns the neuronList of the previous Layer to inputList, which is a reference to an ArrayList of Neurons
        for (int n = 0; n < this.numInputs; n++) { //For each Neuron in the inputList, the change of weight is calculated, added to the old weight and the new weight is reassigned to the corresponding Neuron through the inputData
            double changeOfWeight = ANN.LEARNING_RATE * inputList.get(n).getActivationOutput() * this.getDelta();
            double newWeight = this.inputData.get(n) + changeOfWeight;
            this.inputData.set(n, newWeight);
        }

    }

    public void changeDelta() {
    }

    /**
     * Calculates the net function for a certain neuron
     *
     * @param inNeuronList
     * @return net
     */
    public double calculateNet(ArrayList<Neuron> inNeuronList,
                               ArrayList<Double> inputData) {
        ArrayList<Double> inputList = new ArrayList<>();
        double net = 0;
        for (Neuron n : inNeuronList) {
            inputList.add(n.getActivationOutput());
        }
        for (int n = 0; n < this.numInputs; n++) {
            net += inputList.get(n) * inputData.get(
                    n);

        }

        double finalNet = net - this.theta;
        return finalNet;
    }

    /**
     * Calculates the activation output for a Neuron instance and assigns it to
     * this.activationOutput
     */
    public void calculateActivation() {

        ArrayList<Neuron> neuronList = this.currLayer.getPrevLayer().getNeuronList();
        this.numInputs = neuronList.size();
        ArrayList<Double> inputList = new ArrayList<>();
        for (Neuron n : neuronList) {
            inputList.add(n.getActivationOutput());
        }
        double net = this.calculateNet(neuronList, this.inputData);

        this.activationOutput = 1 / (1 + Math.pow((Math.E), (-1 * net)));
        if (this instanceof OutputNeuron) {
            System.out.println(
                    "For weights" + this.inputData + "and input List " + inputList + " we have net " + net + " theta " + this.theta);
            System.out.println("target" + this.targetOutput);
            System.out.println("Activation output is " + this.activationOutput);
        }
        this.setFinalOutput();
    }

    public void updateNeuron() {
//        this.changeDelta();
//        this.updateInsAndWeights();
//        this.calculateActivation();

    }

}
