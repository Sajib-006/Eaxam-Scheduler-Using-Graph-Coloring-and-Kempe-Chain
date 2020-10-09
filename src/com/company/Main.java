package com.company;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Toronto/hello world");
        //String input_crs = "Toronto/car-f-92.crs";
        //String input_stu = "Toronto/car-f-92.stu";
        String input_crs = "yor-f-83.crs";
        String input_stu = "yor-f-83.stu";
        File crs_file = new File(input_crs);
        File stu_file = new File(input_stu);
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        MapValueComparator mvc = new MapValueComparator(map);
        TreeMap<Integer, Integer> sorted_map = new TreeMap<Integer, Integer>(mvc);

        String last="";
        String line="";
        BufferedReader input = null;
        int cnt=0;
        try {
            input = new BufferedReader(new FileReader(input_crs));

            int course,enrolled;
            while ((line = input.readLine()) != null) {
                cnt++;
                String[] parts = line.split(" ");
                try{
                    course = Integer.parseInt(parts[0]);
                    enrolled = Integer.parseInt(parts[1]);
                    System.out.println(course+": "+enrolled);
                    map.put(course,enrolled);
                }catch (NumberFormatException e){

                }


            }
            System.out.println("line Count: "+ cnt);
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
            input.close();
        }
        int V = cnt-1;
        //System.out.println(cnt);
        Graph g2 = new Graph(V,input_crs,input_stu);
        System.out.println("Total Vertex: "+ V);
        Scanner sc =  new Scanner(stu_file, "UTF-8");
        String cur_line = null;
        int edgeCnt=0;

        while(sc.hasNextLine()){
            cur_line = sc.nextLine();
            //System.out.println(cur_line);
            String[] parts = cur_line.split(" ");
            int []course = new int[parts.length];

            for(int i=0; i< parts.length; i++)
            {
                course[i] = Integer.parseInt(parts[i]);
                //System.out.print(a+"..");
            }
            //System.out.println();
            //System.out.println("courses: "+course.length);
            //int x=0,y=0;
            for(int i=0; i<course.length; i++){
                //y=0;
                for (int j = i + 1; j < course.length; j++) {
                    edgeCnt++;
                    g2.makeEdge(course[i], course[j]);
                    //x++;
                    //y++;
                }
            }
            //System.out.println("x: "+x);

        }
        System.out.println("Edges:" + edgeCnt);

        System.out.println();
        g2.colorByLargestDegree();
       // g2.colorByRandomSelection();
        sorted_map.putAll(map);
        //System.out.println("Sorted Map: "+ sorted_map);
        //g2.colorByLargestEnrollment(sorted_map);
        g2.kempeChain(1000);

        //g2.colorGraph();
//        int []deg = new int[]{2,7,6,5,1,4,8};
//        int[] sortedIndice = IntStream.range(0, deg.length)
//                .boxed().sorted((i, j) -> Integer.compare(deg[i],deg[j]) )
//                .mapToInt(ele -> ele).toArray();
//
//        for(int i=0;i< deg.length;i++) System.out.println(sortedIndice[i]);
//        //Arrays.sort(sortedIndice,Collections.reverseOrder());
//        IndexComparator comparator = new IndexComparator(deg);
//        Integer[] indexes = comparator.createIndexArray();
//        Arrays.sort(indexes, comparator);
//        for(int i=0;i< deg.length;i++) System.out.println(indexes[i]);

//        Graph g = new Graph(12);
//        g.makeEdge(1,2);
//        g.makeEdge(2,4);
//        g.makeEdge(3,4);
//        g.makeEdge(4,9);
//        g.makeEdge(1,8);
//        g.makeEdge(9,10);
//        g.makeEdge(8,9);
//        g.makeEdge(8,11);
//        g.makeEdge(10,11);
//        g.makeEdge(7,8);
//        g.makeEdge(7,11);
//        g.makeEdge(5,6);
//        g.makeEdge(8,10);
//        g.makeEdge(4,11);
//        g.makeEdge(6,7);
//        g.makeEdge(11,5);
//        //g.colorGraph();
//        g.colorByLargestDegree();

//        Graph g3 = new Graph(5,input_crs,input_stu);
//        g3.makeEdge(1, 2);
//        g3.makeEdge(1, 3);
//        g3.makeEdge(2, 3);
//        g3.makeEdge(2, 5);
//        g3.makeEdge(3, 5);
//        g3.makeEdge(5, 4);
//        System.out.println("Coloring of graph 3 ");
//        g3.colorByLargestDegree();
//        g3.colorByRandomSelection();

    }
}
