/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Mateen Qureshi and Omar El-Etr
 * Date: Mar 9, 2017
 * Time: 4:09:20 PM
 *
 * Project: csci205_hw
 * Package: hw01
 * File: InputNeuron
 * Description:
 *
 * ****************************************
 */
package hw01;

/**
 *
 * @author Mateen Qureshi and Omar El-Etr
 */
public class InputNeuron extends Neuron {

    /**
     * neuronInput represents the input that goes into the ANN through this
     * instance of an InputNeuron
     */
    private double neuronInput;

    public InputNeuron(Layer currLayer) {
        super(currLayer);
//        this.neuronInput = 0;
//        this.setActivationOutput(input);

    }

//    public double getNeuronInput() {
//        return neuronInput;
//    }
//    public void setNeuronInput(double neuronInput) {
//        this.neuronInput = neuronInput;
//        this.calculateActivation();
//
//    }
    @Override
    public void calculateActivation() {
//        this.setActivationOutput(this.neuronInput); //To change body of generated methods, choose Tools | Templates.
    }

}
