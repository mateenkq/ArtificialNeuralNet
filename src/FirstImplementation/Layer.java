/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Mateen Qureshi and Omar El-Etr
 * Date: Mar 8, 2017
 * Time: 2:14:47 AM
 *
 * Project: csci205_hw
 * Package: hw01
 * File: Layer
 * Description:
 *
 * ****************************************
 */
package hw01;

import java.util.ArrayList;

/**
 *
 * This class represents a layer in an Artificial Neural Network which consists
 * of multiple neurons. This layer would be connected on either one side or both
 * sides to other layers.
 *
 * @author Mateen Qureshi and Omar El-Etr
 */
public class Layer {

    /**
     * prevLayer is a reference to the preceding layer of neurons
     */
    private Layer prevLayer;

    /**
     * nextLayer is a reference to the succeeding layer of neurons
     */
    private Layer nextLayer;

    /**
     * An ArrayList that contains all the Neurons in this instance of Layer
     */
    private ArrayList<Neuron> neuronList;

    /**
     * Constructor with no parameter
     *
     */
    public Layer() {
        this.prevLayer = null;
        this.nextLayer = null;
        this.neuronList = new ArrayList<>();
//        this.addNeurons(numNeurons);
    }

//    /**
//     * Constructor with two parameters
//     *
//     * @param prevLayer - a reference to a Layer object representing the Layer
//     * of neurons immediately preceding the current layer
//     * @param numNeurons - an integer representing the number of neurons to be
//     * put into the layer
//     */
//    public Layer(int numNeurons, Layer prevLayer) {
//        this.prevLayer = prevLayer;
//        this.nextLayer = null;
//        this.neuronList = null;
//        this.addNeurons(numNeurons);
//    }
    public Layer getPrevLayer() {
        return prevLayer;
    }

    public void setPrevLayer(Layer prevLayer) {
        this.prevLayer = prevLayer;
    }

    public Layer getNextLayer() {
        return nextLayer;
    }

    public void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
        for (Neuron n : this.neuronList) {
            n.setNextLayer(nextLayer);
        }
    }

    public ArrayList<Neuron> getNeuronList() {
        return neuronList;
    }

    public void addToNeuronList(Neuron n) {
        this.neuronList.add(n);
    }

    /**
     * Adds the specified number of Neuron objects to the instance variable
     * neuronList
     *
     * @param numNeurons is the number of Neuron objects to be added to
     * neuronList
     */
    public void addNeurons(int numNeurons) {
        for (int i = 0; i < numNeurons; i++) {
            Neuron newNeuron = new Neuron(this);
            this.neuronList.add(newNeuron);
        }
    }

    /**
     * This carries out backward propagation for each Neuron in the Layer
     */
    public void updateLayer() {
        for (Neuron n : this.neuronList) {
            n.updateNeuron();
        }
    }

    /**
     * Calculates the activation output for each Neuron object in the Layer
     */
    public void calculateActivation() {
        for (Neuron n : this.neuronList) {
            n.calculateActivation();
        }
    }
}
