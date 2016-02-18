package com.intelligent_systems.maxmin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Serhii on 18.02.2016.
 */
public class Points {
    public ArrayList<PointND> getIrises() {
        return irises;
    }
    final double ALPHA=0.5;
    private ArrayList<PointND> irises=new ArrayList<PointND>();
    public PointND get(int i) {
        if (i >= 0 && i < irises.size())
            return irises.get(i);
        else {
            System.out.println("out of index");
            return new PointND(0);
        }
    }
    public void assignIrises(PointND z1,PointND z2,PointND z3)
    {
        z1.setCategorized_claster(1);
        z2.setCategorized_claster(2);
        z3.setCategorized_claster(3);
        for (PointND irise : irises) {
            if (irise.getX() == z1.getX() || irise.getX() == z2.getX() ||irise.getX() == z3.getX()) {
                continue;
            }
            irise.set_cluster(z1, z2, z3);


        }
    }

    public void assignIrises(ArrayList<PointND> centers)
    {
        for (int i = 0; i < centers.size(); i++) {
            centers.get(i).setCategorized_claster(i+1);
        }
        for (PointND irise : irises) {
            if(inList(centers,irise)) continue;
            int clusterid=IdClusterMinToCenters(centers,irise);
            irise.setCategorized_claster(clusterid);
        }
    }

    public Points(ArrayList<PointND> irises) {
        this.irises = irises;
    }
    public Points() {
        this.irises = new ArrayList<PointND>();
    }

    public Points(FileReader fileReader) throws IOException {

        BufferedReader br = new BufferedReader(fileReader);
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {

                line = workWithLine(irises, br, sb, line);
            }
        } finally {
            br.close();
        }
    }

    public int findMaxDistance(PointND point) {
        double maxDistance = 0;
        int id = -1;
        for (int i = 0; i < irises.size(); i++) {

            if (point.distance(irises.get(i)) > maxDistance) {
                maxDistance = point.distance(irises.get(i));
                id = i;
            }
           // System.out.println(point.distance(irises.get(i))+" "+i);
        }
        return id;
    }

    private static String workWithLine(ArrayList<PointND> irises, BufferedReader br, StringBuilder sb, String line) throws IOException {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
        if (line!=null &&line.length()>0)
            irises.add(new PointND(line));
        return line;
    }

    public void print() {
        for (PointND irise : irises) {
            System.out.println(irise);
        }

    }

    public void print(int claster) {
        for (PointND irise : irises) {
            if(irise.getCategorized_claster()==claster)
            System.out.print(irise.getX()+" ");
        }

    }

    public int findMaxDistance(PointND z1, PointND z2) {
        double maxDistance = 0;
        int id = -1;
        for (int i = 0; i < irises.size(); i++) {
            if (irises.get(i).getX() == z1.getX() || irises.get(i).getX() == z2.getX()) {
                continue;
            }
            //не є центрами
            double d1=z1.distance(irises.get(i));
            double d2=z2.distance(irises.get(i));
            double dmin=Math.min(d1,d2);
            if (dmin > maxDistance) {
                maxDistance = dmin;
                id = i;
            }
            // System.out.println(point.distance(irises.get(i))+" "+i);
        }
        if (maxDistance<=ALPHA*z1.distance(z2)) {
            System.out.println("Only 2 centers");
            return -1;
        }
        return id;
    }

    public int findMaxDistance(PointND z1, PointND z2, PointND z3) {

        double maxDistance = 0;
        int id = -1;
        for (int i = 0; i < irises.size(); i++) {

            if (irises.get(i).getX() == z1.getX() || irises.get(i).getX() == z2.getX() || irises.get(i).getX() == z3.getX()) {
                continue;
            }
            //не є центрами
            double d1=z1.distance(irises.get(i));
            double d2=z2.distance(irises.get(i));
            double d3=z3.distance(irises.get(i));
            double dmin=Math.min(d1,d2);
            dmin=Math.min(dmin,d3);

            if (dmin > maxDistance) {
                maxDistance = dmin;
                id = i;
            }
            // System.out.println(point.distance(irises.get(i))+" "+i);
        }
        double d_tipova=ALPHA*((z1.distance(z2)+z1.distance(z3)+z2.distance(z3))/3);
        if (maxDistance<=d_tipova) {
            System.out.println("Only 3 centers");
            return -1;
        }
        return id;
    }


    public int findMaxDistance(ArrayList<PointND> centers) {

        double maxDistance = 0;
        int id = -1;
        for (int i = 0; i < irises.size(); i++) {
            if(inList(centers,irises.get(i))) continue;

            double dmin=minToCenters(centers,irises.get(i));
            if (dmin > maxDistance) {
                maxDistance = dmin;
                id = i;
            }
            // System.out.println(point.distance(irises.get(i))+" "+i);
        }
        double d_tipova=ALPHA*d_tipova(centers);
        if (maxDistance>d_tipova) {
            centers.add(irises.get(id));
            return id;
        } else return -1;
    }

    private boolean inList(ArrayList<PointND> centers, PointND pointND) {
        for (PointND center : centers) {
            if(center.distance(pointND)==0) return true;

        }
        return false;
    }

    private double minToCenters(ArrayList<PointND> centers, PointND pointND) {
        double dmin=Double.MAX_VALUE;
        for (PointND center : centers) {
            dmin=Math.min(dmin,center.distance(pointND));
        }
        return dmin;
    }

    private int IdClusterMinToCenters(ArrayList<PointND> centers, PointND pointND) {
        double dmin = Double.MAX_VALUE;
        int id = 0;
        for (PointND center : centers) {
            double d = center.distance(pointND);
            if (dmin > d) {
                id = center.getCategorized_claster();
                dmin = d;
            }
        }
        return id;
    }

    private double d_tipova(ArrayList<PointND> centers) {
        double average=0.0;
        for (int i = 0; i < centers.size()-1; i++) {
            for (int j = i+1; j < centers.size(); j++) {
                average+=centers.get(i).distance(centers.get(j));
            }

        }
        return average/centers.size();
    }
}
