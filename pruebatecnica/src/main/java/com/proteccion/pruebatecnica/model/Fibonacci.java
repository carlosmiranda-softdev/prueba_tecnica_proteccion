package com.proteccion.pruebatecnica.model;

import java.util.ArrayList;

/**
 * Class for fibonacci serie
 */
public class Fibonacci {
    /**
     * Fibonacci serie array
     */
    private ArrayList<Number> serie;

    /**
     * Size of the fibonacci serie array
     */
    private int size;

    /**
     * Default constructor that initialize serie array
     */
    public Fibonacci() {
        this.serie = new ArrayList<>();
    }

    /**
     * Getter of the serie array
     *
     * @return serie The array of the fibonacci serie
     */
    public ArrayList<Number> getSerie() {
        return serie;
    }

    /**
     * Setter of the serie array
     *
     * @param serie This is the array used to set the new value
     */
    public void setSerie(ArrayList<Number> serie) {
        this.serie = serie;
    }

    /**
     * Getter of the size of the fibonacci serie array
     *
     * @return size of the array
     */
    public int getSize() {
        return size;
    }

    /**
     * Setter of the size field
     *
     * @param size This is the size of the array
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Method for calculate the Fibonacci serie according to
     * 2 numbers given and a fixed size.
     *
     * @param firstNumber  This is the base first number of the fibonacci serie
     * @param secondNumber This is the base second number of the fibonacci serie
     * @param n            This is the number of elements to reach in the fibonacci serie
     */
    public void execute(int firstNumber, int secondNumber, int n) {

        serie.add(firstNumber);
        serie.add(secondNumber);

        for (int i = 0; i < n; i++) {
            int nextNumber = firstNumber + secondNumber;
            firstNumber = secondNumber;
            secondNumber = nextNumber;
            serie.add(secondNumber);
        }

        setSerie(reverseArrayList(serie));
    }

    /**
     * Method for reverse an array
     *
     * @param list This is the array to reverse
     * @return the array in reverse order
     */
    private ArrayList<Number> reverseArrayList(ArrayList<Number> list) {
        ArrayList<Number> revArrayList = new ArrayList<>();

        for (int i = list.size() - 1; i >= 0; i--) {
            revArrayList.add(list.get(i));
        }

        return revArrayList;
    }
}
