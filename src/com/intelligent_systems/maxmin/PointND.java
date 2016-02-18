package com.intelligent_systems.maxmin;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Serhii on 15.02.2016.
 */
public class PointND {
    private Vector<Double> X; //[1.2, 2.3 , ..... 3.1] Vector dimension N
    private int from_file_cluster; //id of cluster which we get from file
    private int categorized_cluster; //id of cluster which our method set.
    
    public int getCategorized_cluster() {
        return categorized_cluster;
    }

    public void setCategorized_cluster(int categorized_cluster) {
        this.categorized_cluster = categorized_cluster;
    }

    public Vector<Double> getX() {
        return X;
    }


    public PointND(int capacity) {
        X = new Vector<Double>(capacity);
    }
    public void set_cluster(PointND z1, PointND z2, PointND z3) {
        double d1=z1.distance(this);
        double d2=z2.distance(this);
        double d3=z3.distance(this);
        double dMin=d3;
        this.setCategorized_cluster(z3.getCategorized_cluster());
        if(dMin>d2) {dMin=d2;this.setCategorized_cluster(z2.getCategorized_cluster());}
        if(dMin>d1) {dMin=d1;this.setCategorized_cluster(z1.getCategorized_cluster());}
    }

    @Override
    public String toString() {
        return X +
               ", from_file_cluster=" + from_file_cluster +
                ", categorized_cluster=" + categorized_cluster;
    }

    public PointND(String string4D,int size) {
        //string = "5.1 3.5 1.4 0.2 1"  size = 4

        String[] arr = string4D.split(" ");
        X = new Vector<Double>(size);
        X.clear();
        for (int i = 0; i < size; i++) {
            X.add(Double.parseDouble(arr[i]));
        }
        from_file_cluster =Integer.parseInt(arr[size]);

    }




    public PointND(Vector<Double> x) {
        X = x;
    }


    public double distance(PointND Second) {
        double sum_of_sqrs = 0;
        Iterator iter1 = X.iterator();
        Iterator iter2 = Second.X.iterator();
        while (iter1.hasNext() && iter2.hasNext()) {
            Double x1 = (Double) iter1.next();
            Double y1 = (Double) iter2.next();
            double d = x1 - y1;
            sum_of_sqrs += d * d;
        }
        return Math.sqrt(sum_of_sqrs);
    }




}
