package com.intelligent_systems.maxmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Serhii on 15.02.2016.
 */
public class PointND {
    private Vector<Double> X;
    private int capacity;

    public int getCategorized_claster() {
        return categorized_claster;
    }

    public void setCategorized_claster(int categorized_claster) {
        this.categorized_claster = categorized_claster;
    }

    public Vector<Double> getX() {
        return X;
    }

    private int from_file_claster;
    private int categorized_claster;

    public PointND(int capacity) {
        X = new Vector<Double>(capacity);
        X_fill_zeros(capacity);
        this.capacity = capacity;
    }
    public void set_cluster(PointND z1, PointND z2, PointND z3) {
        double d1=z1.distance(this);
        double d2=z2.distance(this);
        double d3=z3.distance(this);
        double dmin=d3;
        this.setCategorized_claster(z3.getCategorized_claster());
        if(dmin>d2) {dmin=d2;this.setCategorized_claster(z2.getCategorized_claster());}
        if(dmin>d1) {dmin=d1;this.setCategorized_claster(z1.getCategorized_claster());}
    }

    @Override
    public String toString() {
        return X +
               ", from_file_claster=" + from_file_claster +
                ", categorized_claster=" + categorized_claster;
    }

    public PointND(String string4D) {

        String[] arr = string4D.split(" ");
        X = new Vector<Double>(4);
        X.clear();
        for (int i = 0; i < 4; i++) {
            X.add(Double.parseDouble(arr[i]));
        }
        from_file_claster=Integer.parseInt(arr[4]);
        this.capacity = 4;
    }

    public void X_fill_zeros(int capacity) {
        X.clear();
        for (int i = 0; i < capacity; i++) {
            X.add(0.0);
        }
    }

    private void randomize() {
        for (int i = 0; i < capacity; i++) {
            this.X.set(i, i * i*1.0);
        }
    }


    private void fromkeyboard() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        System.out.println("Enter Vector with capacity =" + capacity);
        for (int i = 0; i < capacity; i++) {
            System.out.print("Enter x(" + i + ")= ");
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.X.set(i, Double.parseDouble(str));
        }
    }

    private void fromkeyboard_with_capacity() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = "";

        System.out.print("Capacity= ");
        str = reader.readLine();
        capacity = Integer.parseInt(str);
        X_fill_zeros(capacity);

        System.out.println("Enter Vector with capacity =" + capacity);
        for (int i = 0; i < capacity; i++) {

            System.out.print("Enter x(" + i + ")= ");
            str = reader.readLine();


            this.X.set(i, Double.parseDouble(str));
        }
    }

    public PointND(Vector<Double> x) {
        X = x;
    }

    public double sum() {
        double sum = 0;
        for (Double Double : X) {
            sum += Double;

        }
        return sum;
    }

    public double distance(PointND Second) {
        double sum_of_sqrs = 0;
        Iterator iter1 = X.iterator();
        Iterator iter2 = Second.X.iterator();
        while (iter1.hasNext() && iter2.hasNext()) {
            Double xi_firstVector = (Double) iter1.next();
            Double xi_secondVector = (Double) iter2.next();
            double d = xi_firstVector - xi_secondVector;
            sum_of_sqrs += d * d;
        }
        return Math.sqrt(sum_of_sqrs);
    }

    public static void main(String[] args) throws IOException {
        //test11();
        PointND gg = new PointND("6.8 2.8 4.8 1.4 2");
        System.out.println(gg);
    }

    private static void test11() throws IOException {
        int capacity = 10;
        PointND gg = new PointND(capacity);

        gg.fromkeyboard_with_capacity();


        PointND g2 = new PointND(capacity);

        g2.fromkeyboard_with_capacity();


        System.out.println("Distance " + g2.distance(gg));

        System.out.println(gg.X);
        System.out.println(g2.X);
    }


}
