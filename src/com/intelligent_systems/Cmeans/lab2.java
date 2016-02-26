package com.intelligent_systems.Cmeans;


import com.intelligent_systems.data.PointND;
import com.intelligent_systems.data.Points;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class lab2 {

    public static void main(String[] args) throws IOException {
        Points irises = new Points(new FileReader("iris.data.txt"), 4);
        final int K = 3;

        ArrayList<PointND> centers = new ArrayList<PointND>();


        for (int i = 0; i < K; i++) {
            //Призначаємо перші К - центрами кластерів
            centers.add(irises.get(i));
        }
        //Прив'язуємо до центрів найближчі точки
        irises.assignIrises(centers);
        double lastdispersion = Double.MAX_VALUE;
        double dispersion = 0.0;
        ArrayList<PointND> newCenters = new ArrayList<PointND>();
        boolean center_changed=true;

        while (center_changed){//Math.abs(lastdispersion - dispersion) > 0.00001) {
            lastdispersion=dispersion;
            center_changed=false;
            if (centers.size()==newCenters.size())//not first time
            {
                    centers=newCenters;
            }
            dispersion = 0.0;
            newCenters = new ArrayList<PointND>();
            for (PointND center : centers) {
                int clusterId = center.getCategorized_cluster();
                ArrayList<PointND> points_from_cluster = irises.getPointsFromCluster(clusterId);
                PointND new_center = new PointND(4);
                new_center = new_center.setmidPoint(points_from_cluster);
                dispersion += new_center.getDispersion(points_from_cluster);
                newCenters.add(new_center);
                center_changed=center.distance(new_center)>0.00000005 || center_changed;
            }


            irises.assignIrises(newCenters);
            System.out.println(dispersion);
        }
//        while (lastid>=0)
//        {
//            System.out.println(lastid);
//            lastid=irises.findIdOfMaxDistance(centers);
//
//        }
//
//
//
        //irises.print();
//        System.out.println("\n"+centers.size());
//
        //Print:
        for (PointND center : centers) {
            System.out.println(center);
            ArrayList<PointND> points_from_cluster = irises.getPointsFromCluster(center.getCategorized_cluster());
            for (PointND pointND : points_from_cluster) {
                System.out.println("    " + pointND);
            }
            int count = points_from_cluster.size(); //irises.print(center.getCategorized_cluster());
            System.out.println();

            System.out.println("Count of cluster =" + count);
            System.out.println();
        }

    }

    private static void test() {
        //        PointND z1=irises.get(49);
//        int q=irises.findIdOfMaxDistance(z1);//індекс найбільш віддаленого
//        PointND z2=irises.get(q);
//
//        int qz=irises.findIdOfMaxDistance(z1,z2);
//        //System.out.println(qz);
//        PointND xqz=irises.get(qz);
//
//        int qzz=irises.findIdOfMaxDistance(z1,z2,xqz);
//        System.out.println(qzz);
//        irises.assignIrises(z1,z2,xqz);
    }
}
