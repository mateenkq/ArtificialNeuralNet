/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Mateen Qureshi and Omar El-Etr
 * Date: Mar 9, 2017
 * Time: 9:38:39 PM
 *
 * Project: csci205_hw
 * Package: hw01
 * File: HiddenNeuron
 * Description:
 *
 * ****************************************
 */
package hw01;

import java.util.ArrayList;

/**
 *
 * @author Mateen Qureshi and Omar El-Etr
 */
public class HiddenNeuron extends Neuron {

    public HiddenNeuron(Layer currentLayer) {
        super(currentLayer);
        this.setNumInputs(currentLayer.getPrevLayer().getNeuronList().size());
        this.setUpInsAndWeights(this.currLayer.getPrevLayer());
//        this.updateNeuron();
        this.delta = 0;
    }

    /**
     * Changes the value assigned to delta by scaling the strength of connection
     * between nodes
     */
    @Override
    public void changeDelta() {
        ArrayList<Neuron> nextLayerNeurons = this.nextLayer.getNeuronList(); //To change body of generated methods, choose Tools | Templates.
        double sumWeightsTimesDeltas = 0;
        int numNeuronsInNextLayer = this.nextLayer.getNeuronList().size();
        for (int n = 0; n < numNeuronsInNextLayer; n++) {
            ArrayList<Double> nextData = nextLayerNeurons.get(n).getInputData();
            double currWeight = nextData.get(n);
            double adjacentDelta = nextLayerNeurons.get(n).getDelta();
            sumWeightsTimesDeltas += adjacentDelta * currWeight;

        }
        double newDelta = this.getActivationOutput() * (1 - this.getActivationOutput()) * sumWeightsTimesDeltas;
        this.setDelta(newDelta);
        this.changeTheta();
    }

    @Override
    public void updateNeuron() {
//        super.updateNeuron(); //To change body of generated methods, choose Tools | Templates.
        this.changeDelta();
        this.updateInsAndWeights();
    }

    /**
     * Changes the value assigned to the variable theta of the neuron to reflect
     * the change in delta
     */
    public void changeTheta() {
        double changeInTheta = ANN.LEARNING_RATE * -1 * this.delta;
        double newTheta = this.getTheta() + changeInTheta;
        this.setTheta(newTheta);
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

}
