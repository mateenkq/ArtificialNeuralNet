/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Mateen Qureshi and Omar El-Etr
 * Date: Mar 8, 2017
 * Time: 2:53:13 AM
 *
 * Project: csci205_hw
 * Package: hw01
 * File: HiddenLayer
 * Description:
 *
 * ****************************************
 */
package hw01;

/**
 *
 * @author Mateen Qureshi and Omar El-Etr
 */
public class HiddenLayer extends Layer {

    public HiddenLayer(int numHiddenNeurons, Layer prevLayer) {
        super();
        this.setPrevLayer(prevLayer);
        this.addNeurons(numHiddenNeurons);
    }

    /**
     *
     * @param numNeurons is the number of HiddenNeurons to be added to the
     * NeuronList
     */
    @Override
    public void addNeurons(int numNeurons) {
        for (int i = 0; i < numNeurons; i++) {
            HiddenNeuron newNeuron = new HiddenNeuron(this);
            this.addToNeuronList(newNeuron);
        }
    }

}
