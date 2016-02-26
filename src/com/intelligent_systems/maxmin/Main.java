package com.intelligent_systems.maxmin;


import com.intelligent_systems.data.PointND;
import com.intelligent_systems.data.Points;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        Points irises=new Points(new FileReader("iris.data.txt"),4);
       // Points irises=new Points(new FileReader("2d.txt"),3);

        ArrayList<PointND> centers=new ArrayList<PointND>();
      //  test();


        int lastid=0;
        centers.add(irises.get(lastid));
        while (lastid>=0)
        {
            System.out.println(lastid);
            lastid=irises.findIdOfMaxDistance(centers);

        }

        irises.assignIrises(centers);

        //irises.print();
        System.out.println("\n"+centers.size());

        for (PointND center : centers) {
            System.out.println(center);

            int count = irises.print(center.getCategorized_cluster());
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
