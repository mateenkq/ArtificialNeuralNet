/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Mateen Qureshi and Omar El-Etr
 * Date: Mar 8, 2017
 * Time: 2:53:39 AM
 *
 * Project: csci205_hw
 * Package: hw01
 * File: OutputLayer
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
public class OutputLayer extends Layer {

    /**
     * This denotes the total error(sum squared) / objective function
     */
    private double SSError;

    /**
     *
     * @param numOutputs
     */
    public OutputLayer(int numOutputs, Layer prevLayer) {
        super();
        this.setPrevLayer(prevLayer);
        this.addNeurons(numOutputs);
        this.SSError = 0;
    }

    public double getSSError() {
        return SSError;
    }

    @Override
    public void addNeurons(int numOutputs) {
        for (int i = 0; i < numOutputs; i++) {
            OutputNeuron newNeuron = new OutputNeuron(this);
            this.addToNeuronList(newNeuron);
        }
    }

    public void updateSSError() {
        double totalSSError = 0;
        for (Neuron n : this.getNeuronList()) {

            totalSSError += n.getSquaredError();

        }
        this.SSError = totalSSError / 2;
    }

    @Override
    public void updateLayer() {
        super.updateLayer(); //To change body of generated methods, choose Tools | Templates.
        this.updateSSError();
    }

    public void putExpectedOutputs(ArrayList<Double> expOutputList) {
        ArrayList<Neuron> nList = this.getNeuronList();
        for (int i = 0; i < expOutputList.size(); i++) {
            System.out.println("Putting target output" + expOutputList.get(i));
            this.getNeuronList().get(i).setTargetOutput(expOutputList.get(i));
            System.out.println(
                    "Target output is now " + this.getNeuronList().get(i).getTargetOutput());
        }
    }

    @Override
    public void calculateActivation() {
        super.calculateActivation(); //To change body of generated methods, choose Tools | Templates.
        this.updateSSError();
    }

}
