/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Mateen Qureshi and Omar El-Etr
 * Date: Mar 8, 2017
 * Time: 8:59:27 PM
 *
 * Project: csci205_hw
 * Package: hw01
 * File: OutputNeuron
 * Description:
 *
 * ****************************************
 */
package hw01;

import java.util.ArrayList;

/**
 * A class representing an output neuron. It is a child class of Neuron.
 *
 * @author Mateen Qureshi and Omar El-Etr
 */
public class OutputNeuron extends Neuron {

    /**
     * this is the target output for an instance of an output neuron.
     */
    private double targetOutput;

    /**
     * this represents the final output
     */
    private int finalOutput;

    /**
     * this is the error between target output and actual output
     */
    private double error;

    /**
     * this is the square of the error between target output and actual output
     */
    private double squaredError;

    /**
     * Constructor with one parameter
     *
     * @param layer
     * @param targetOutput
     */
    public OutputNeuron(OutputLayer layer) {
        super(layer);
        this.setUpInsAndWeights(this.currLayer.getPrevLayer());
        this.updateNeuron();
        this.delta = 0;
        this.finalOutput = 0;
        this.squaredError = 0;
        this.error = 0;

    }

    public int getFinalOutput() {
        return finalOutput;
    }

    public void setFinalOutput() {
        int temp = (int) Math.round(this.getActivationOutput());
        this.finalOutput = temp;
    }

    public double getSquaredError() {
        return this.squaredError;
    }

    @Override
    public double getTargetOutput() {
        return targetOutput;
    }

    @Override
    public void setTargetOutput(double targetOutput) {
        this.targetOutput = targetOutput;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    /**
     * sets the squared error for each Neuron object
     */
    public void updateSquaredError() {
        this.updateError();
        this.squaredError = 2 * this.error;
    }

    @Override
    public void updateNeuron() {
//        super.updateNeuron(); //To change body of generated methods, choose Tools | Templates.
        this.updateSquaredError();
        this.changeDelta();
        this.updateInsAndWeights();
    }

    /**
     * Updates the weights of the inputs leading in and consequently updates the
     * activation output. It does so according to any changes in delta
     */
    @Override
    public void updateInsAndWeights() {
        ArrayList<Neuron> inputList = this.currLayer.getPrevLayer().getNeuronList();   //Assigns the neuronList of the previous Layer to inputList, which is a reference to an ArrayList of Neurons
        int numInputs = inputList.size();
        for (int n = 0; n < this.getNumInputs(); n++) { //For each Neuron in the inputList, the change of weight is calculated, added to the old weight and the new weight is reassigned to the corresponding Neuron through inputData
            double changeOfWeight = ANN.LEARNING_RATE * inputList.get(n).getActivationOutput() * this.getDelta();
            double newWeight = this.getInputData().get(n) + changeOfWeight;
            this.setInputData(n, newWeight);
        }

    }

    public void updateError() {
        this.error = this.targetOutput - this.getActivationOutput();
    }

    @Override
    public void changeDelta() {
//        this.error = this.getActivationOutput() - targetOutput; //To change body of generated methods, choose Tools | Templates.
        this.delta = this.getActivationOutput() * (1 - this.getActivationOutput()) * this.error;
        this.changeTheta();
    }

    public void changeTheta() {
        double changeInTheta = ANN.LEARNING_RATE * -1 * this.delta;
        double newTheta = this.getTheta() + changeInTheta;
        this.setTheta(newTheta);
    }

    @Override
    public void calculateActivation() {
        super.calculateActivation(); //To change body of generated methods, choose Tools | Templates.
        this.updateSquaredError();
        System.out.println(
                "Activation output for output neuron is " + this.getActivationOutput());

    }

}
