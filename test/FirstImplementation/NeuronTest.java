/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Mateen Qureshi and Omar El-Etr
 * Date: Mar 23, 2017
 * Time: 2:50:22 AM
 *
 * Project: csci205_hw
 * Package: hw01
 * File: NeuronTest
 * Description:
 *
 * ****************************************
 */
package hw01;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Mateen Qureshi
 */
public class NeuronTest extends TestCase {

    static final double EPSILON = 1.0E-12;

    public NeuronTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setUpInsAndWeights method, of class Neuron.
     */
    @Test
    public void testSetUpInsAndWeights() {
        System.out.println("setUpInsAndWeights");
        Layer currentLayer = new Layer();
        Layer previousLayer = new Layer();
        currentLayer.setPrevLayer(previousLayer);
        previousLayer.setNextLayer(currentLayer);
        ArrayList<Neuron> listOfNeurons = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            listOfNeurons.add(new Neuron(previousLayer));
        }
        Neuron instance = new Neuron(currentLayer);
        instance.setUpInsAndWeights(previousLayer);
        assertEquals(instance.getInputData().size(), 3);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of readWeights method, of class Neuron.
     */
    @Test
    public void testReadWeights() {
        System.out.println("readWeights");
        Layer currLayer = new Layer();
        ArrayList<Double> weightList = new ArrayList<>();
        weightList.add(new Double(-0.6));
        weightList.add(new Double(0.5));
        Neuron instance = new Neuron(currLayer);
        instance.readWeights(weightList);
        assertEquals(instance.getInputData().get(0), -0.6, EPSILON);
    }

    /**
     * Test of changeDelta method, of class Neuron.
     */
}
