/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Mateen Qureshi and Omar El-Etr
 * Date: Mar 8, 2017
 * Time: 2:52:43 AM
 *
 * Project: csci205_hw
 * Package: hw01
 * File: InputLayer
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
public class InputLayer extends Layer {

    /**
     *
     * @param numNeurons
     */
    public InputLayer(int numNeurons) {
        super();
        this.addNeurons(numNeurons);
    }

    /**
     * Overrides the parent class method. The only modification is that instead
     * of Neuron objects, InputNeuron objects are added to the neuronList
     *
     * @param numNeurons is the number of Neurons to be present in inputLayer
     */
    @Override
    public void addNeurons(int numNeurons) {
        for (int i = 0; i < numNeurons; i++) {
            Neuron newNeuron = new InputNeuron(this);
            this.addToNeuronList(newNeuron);
        }
    }

    /**
     * This method puts inputs taken in through an ArrayList of Doubles into the
     * InputLayer by assigning each input to the activationOutput of the
     * corresponding Neuron in the neuronList
     *
     * @param inList is the ArrayList of Doubles that is taken in as the inputs
     * to be implemented
     */
    public void putInputs(ArrayList<Double> inList) {
        if (inList.size() == this.getNeuronList().size()) {
            ArrayList<Neuron> nList = this.getNeuronList();
            for (int i = 0; i < inList.size(); i++) {
                this.getNeuronList().get(i).setActivationOutput(inList.get(i));
            }
        }
    }
}
