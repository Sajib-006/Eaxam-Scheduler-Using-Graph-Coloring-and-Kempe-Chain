package com.company;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        //System.out.println("Toronto/hello world");
        String input_crs = "Toronto/tre-s-92.crs";
        String input_stu = "Toronto/tre-s-92.stu";
        //String input_crs = "yor-f-83.crs";
        //String input_stu = "yor-f-83.stu";
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
                    //System.out.println(course+": "+enrolled);
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
        int V = cnt;
        Graph g2 = new Graph(V,input_crs,input_stu);
        System.out.println("Total Vertex: "+ V);
        Scanner sc =  new Scanner(stu_file, "UTF-8");
        String cur_line = null;
        int edgeCnt=0;

        while(sc.hasNextLine()){
            cur_line = sc.nextLine();
            String[] parts = cur_line.split(" ");
            int []course = new int[parts.length];
            try{
                for(int i=0; i< parts.length; i++)
                {
                    course[i] = Integer.parseInt(parts[i]);
                }
                for(int i=0; i<course.length; i++){
                    for (int j = i + 1; j < course.length; j++) {
                        edgeCnt++;
                        g2.makeEdge(course[i], course[j]);
                    }
                }
            }catch (NumberFormatException e){

            }


        }
        System.out.println("Edges:" + edgeCnt);

        System.out.println();
        sorted_map.putAll(map);
        //System.out.println("Sorted Map: "+ sorted_map);

        //g2.colorByLargestDegree();
        //g2.colorByRandomSelection();
        g2.colorByLargestEnrollment(sorted_map);

        g2.kempeChain(400);



    }
}
