///* *****************************************
// * CSCI205 - Software Engineering and Design
// * Spring 2017
// *
// * Name: Mateen Qureshi and Omar El-Etr
// * Date: Mar 8, 2017
// * Time: 2:51:28 AM
// *
// * Project: csci205_hw
// * Package: hw01
// * File: Weight
// * Description:
// *
// * ****************************************
// */
//package hw01;
//
//import java.util.Random;
//
///**
// *
// * @author Mateen Qureshi
// *
// * this class represents the weight assigned to a connection between two
// * adjacent neurons
// */
//public class Weight {
//
//    private final static double LEARNING_RATE = 0.3;
//
//    // instance variable weight represents a numerical quantity representing the weight
//    private double weight;
//
//// instance variable prevNeuron represents the previous Neuron i.e. the neuron which is providing the output to the second neuron
//    private Neuron prevNeuron;
//
//// instance variable nextNeuron represents the next Neuron i.e. the neuron which is taking the output of the first neuron as input
//    private Neuron nextNeuron;
//
//// instance variable weightDifference represents the change in the weight that has to be implemented
//    private double weightDiff;
//
//    public Weight(double weight, Neuron prevNeuron, Neuron nextNeuron,
//                  double weightDiff) {
//        this.weight = assignRandomWeight();
//        this.prevNeuron = null;
//        this.nextNeuron = null;
//        this.weightDiff = 0;
//    }
//
//    /**
//     * assignRandomWeight return a random double in the range [-2.4/m, 2.4/m]
//     * where m is the number of input edges
//     *
//     * @return rWeight - a double
//     */
//    private double assignRandomWeight() {
//        double rangeMin = -2.4 / this.nextNeuron.getNumInputs();
//        double rangeMax = 2.4 / this.nextNeuron.getNumInputs();
//        Random r = new Random();
//        double rWeight = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
//        return rWeight;
//    }
//
//    public void updateWeightDiff() {
//
//    }
//}
