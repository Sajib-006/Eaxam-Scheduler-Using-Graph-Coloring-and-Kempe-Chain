package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Graph {
    int V;
    int[] deg ;
    int []sorted_deg;
    int []exam_time;
    double penalty;
    ArrayList<Integer> chain;
    ArrayList<Integer> []adj;
    String input_crs ;
    String input_stu ;

    public Graph(int v, String input_crs, String input_stu) {
        this.input_crs = input_crs;
        this.input_stu = input_stu;
        V = v;
        deg = new int[V+1];
        Arrays.fill(deg,0);
        sorted_deg = new int[V+1];
        chain = new ArrayList<Integer>();
        penalty = 0;
        adj = new ArrayList[V+1];
        for(int i=0;i<V+1;i++)
            adj[i] = new ArrayList<Integer>();
    }
    void makeEdge(int u,int v) {
        boolean present = false;
        Iterator<Integer> it = adj[u].iterator();
        while(it.hasNext()){
            if(it.next()==v) {
                present = true;
                break;
            }
        }
        if(!present) {
            adj[u].add(v);
            adj[v].add(u);
            deg[u]++;
            deg[v]++;
        }

    }
    void sortByLargestDegree(){

        int[] sorted_deg = IntStream.range(0, deg.length)
                .boxed().sorted((j, i) -> Integer.compare(deg[j],deg[i]) )
                .mapToInt(ele -> ele).toArray();
        //for(int i=0;i< deg.length;i++) System.out.println(sorted_deg[i]);
    }
    void colorByLargestDegree() throws IOException {
        FileWriter myWriter = new FileWriter("output.txt");
        exam_time = new int[V+1];
        Arrays.fill(exam_time, -1);
        //sortByLargestDegree();
        //System.out.println(deg.length);
        //for(int i=1;i< deg.length;i++) System.out.print(deg[i]+" ");
        IndexComparator comparator = new IndexComparator(deg);
        Integer[] indexes = comparator.createIndexArray();
        Arrays.sort(indexes, comparator);
        //System.out.println("hiiii");

        //for(int i=0;i< deg.length-1;i++) System.out.print(indexes[i]+" ");
        //System.out.println();
        boolean []blocked = new boolean[V+1];
        int u,v;
        Arrays.fill(blocked,false);
        u = indexes[0];
        exam_time[u] = 1;
        for(int i=1 ; i<V; i++){
            int slot;
            u = indexes[i];
            Iterator<Integer> it = adj[u].iterator();
            while(it.hasNext()){
                v = it.next();
                if(exam_time[v]!=-1)
                    blocked[exam_time[v]] = true;
            }
            for(slot=1; slot<V+1; slot++){
                if(!blocked[slot]) break;
            }
            exam_time[u] = slot;
            System.out.println(u+" "+exam_time[u]);
            Arrays.fill(blocked,false);
            myWriter.write(u+" "+exam_time[u]+"\n");

        }
        myWriter.close();
        //for(int i=1; i<V+1; i++) System.out.println(i+": "+exam_time[i]);
        int total_slots = -2;
        for(int i=0; i<V+1; i++) {
            if(exam_time[i]>total_slots) total_slots = exam_time[i];
        }
        penalty = calculatePenalty(exam_time);
        System.out.println("TimeSlots: "+ total_slots);
        System.out.println("Penalty: "+ calculatePenalty(exam_time));

    }
    void colorByRandomSelection() throws IOException {
        FileWriter myWriter = new FileWriter("output.txt");
        exam_time = new int[V+1];
        Arrays.fill(exam_time, -1);
        boolean []blocked = new boolean[V+1];
        Arrays.fill(blocked,false);

        int u,v;
        u = 1;
        exam_time[u] = 1;
        for(int i=2; i<V+1; i++){
            int slot;
            u = i;
            Iterator<Integer> it = adj[u].iterator();
            while(it.hasNext()){
                v = it.next();
                if(exam_time[v]!=-1)
                    blocked[exam_time[v]] = true;
            }
            for(slot=1; slot<V+1; slot++){
                if(!blocked[slot]) break;
            }
            exam_time[u] = slot;
            System.out.println(u+" "+exam_time[u]);
            Arrays.fill(blocked,false);
            myWriter.write(u+" "+exam_time[u]+"\n");
        }
        myWriter.close();
        //for(int i=1; i<V+1; i++) System.out.println(i+": "+exam_time[i]);
        int total_slots = -2;
        for(int i=0; i<V+1; i++) {
            if(exam_time[i]>total_slots) total_slots = exam_time[i];
        }
        penalty = calculatePenalty(exam_time);
        System.out.println("TimeSlots: "+ total_slots);
        System.out.println("Penalty: "+ calculatePenalty(exam_time));
    }
    void colorByLargestEnrollment(TreeMap<Integer,Integer> map) throws IOException {
        FileWriter myWriter = new FileWriter("output.txt");
        exam_time = new int[V+1];
        Arrays.fill(exam_time, -1);
        boolean []blocked = new boolean[V+1];
        Arrays.fill(blocked,false);
        int u,v;

        Map.Entry<Integer,Integer> entry = map.entrySet().iterator().next();
        u = entry.getKey();
        exam_time[u] = 1;

        Set<Integer>set = map.keySet();
        Iterator<Integer>itr = set.iterator();
        while (itr.hasNext()) {
            u = itr.next();
            int slot;
            //System.out.println(res + ": " + map.get(res));
            Iterator<Integer> it = adj[u].iterator();
            while(it.hasNext()){
                v = it.next();
                if(exam_time[v]!=-1)
                    blocked[exam_time[v]] = true;
            }
            for(slot=1; slot<V+1; slot++){
                if(!blocked[slot]) break;
            }
            exam_time[u] = slot;
            System.out.println(u+" "+exam_time[u]);
            Arrays.fill(blocked,false);
            myWriter.write(u+" "+exam_time[u]+"\n");
        }
        myWriter.close();
        //for(int i=1; i<V+1; i++) System.out.println(i+": "+exam_time[i]);
        int total_slots = -2;
        for(int i=0; i<V+1; i++) {
            if(exam_time[i]>total_slots) total_slots = exam_time[i];
        }
        penalty = calculatePenalty(exam_time);
        System.out.println("TimeSlots: "+ total_slots);
        System.out.println("Penalty: "+ calculatePenalty(exam_time));

    }
    void colorBySaturationDegree(){

    }
    void colorGraph(){
        exam_time = new int[V];
        Arrays.fill(exam_time, -1);
        //sortByLargestDegree();
        System.out.println(deg.length);
        for(int i=0;i< deg.length;i++) System.out.print(deg[i]+" ");
        IndexComparator comparator = new IndexComparator(deg);
        Integer[] indexes = comparator.createIndexArray();
        Arrays.sort(indexes, comparator);
        System.out.println("hiiii");

        for(int i=0;i< deg.length;i++) System.out.print(indexes[i]+" ");
        System.out.println();
        int new_slot=0;
        int colored=0;
        boolean []blocked = new boolean[V];
        int u,v;
        Arrays.fill(blocked,false);

        while(colored!=V-1){
            new_slot++;
            for(int i=0; i<V-1; i++){
                u = indexes[i];
                System.out.println("u: "+u);
                if(exam_time[u]==-1){
                    if(!blocked[u]){
                        exam_time[u] = new_slot;
                        colored++;
                        System.out.println("colored: "+colored+"   slot no: "+new_slot);
                        Iterator<Integer> it = adj[u].iterator();
                        while(it.hasNext()){
                            v = it.next();
                            blocked[v] = true;
                        }
                    }
                }


            }
            Arrays.fill(blocked,false);


        }
        for(int i=1; i<V; i++) System.out.println(i+": "+exam_time[i]);
    }
    double calculatePenalty(int []exam_time) throws FileNotFoundException {
        Scanner sc =  new Scanner(new File(input_stu), "UTF-8");
        String cur_line = null;
        int edgeCnt=0;
        int total_students = 0;
        int a,b;
        int penalty = 0;
        while(sc.hasNextLine()){
            total_students++;
            cur_line = sc.nextLine();
            //System.out.println(cur_line);
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
                        a = course[i];
                        b = course[j];
                        if(Math.abs(exam_time[a]-exam_time[b])==1) penalty+=16;
                        else if(Math.abs(exam_time[a]-exam_time[b])==2) penalty+=8;
                        else if(Math.abs(exam_time[a]-exam_time[b])==3) penalty+=4;
                        else if(Math.abs(exam_time[a]-exam_time[b])==4) penalty+=2;
                        else if(Math.abs(exam_time[a]-exam_time[b])==5) penalty+=1;

                    }
                }
            }catch (NumberFormatException e){

            }



        }
        //System.out.println("Edges:" + edgeCnt);
        return penalty/total_students;
    }
    public void kempeChainUtil(int u, int c_v)
    {
        chain.add(u);
        int i=0;
        int x;
        while(i<adj[u].size()) {
            x = adj[u].get(i);
            if (exam_time[x] == c_v && !chain.contains(x))
            {
                kempeChainUtil(x, exam_time[u]);
            }
            i++;
        }
    }

    //    void kempeChainUtil(int u, int c_v){
//        chain.clear();
//        int x;
//        Queue<Integer> q = new LinkedList<>();
//        q.add(u);
//        while(q.size()!=0){
//            u = q.remove();
//            for(int i=0; i<adj[u].size(); i++){
//                x = adj[i].get(i);
//                if(exam_time[x]==c_v && !chain.contains(x)){
//                    chain.add(x);
//                    q.add(x);
//                }
//            }
//        }
//        //for()
//    }
    void kempeChain(int itr) throws FileNotFoundException {

        Queue<Integer> q = new LinkedList<>();
        Random rand = new Random();

        int u,v;
        for(int cnt=0; cnt<itr; cnt++){
            chain.clear();
            u = rand.ints(1,V).findFirst().getAsInt();

            while(adj[u].size()==0){
                u = rand.ints(1,V).findFirst().getAsInt();
            }

            v = adj[u].get(0);
            int c_v;
            c_v = exam_time[v];
            int time_slot1 = exam_time[u];
            int time_slot2 = exam_time[v];

            kempeChainUtil(u,c_v);

            int i=0;
            while(i< chain.size()){
                if(exam_time[chain.get(i)] == time_slot1)
                {
                    exam_time[chain.get(i)] = time_slot2;
                }
                else exam_time[chain.get(i)] = time_slot1;
                i++;
            }
        }

        double newPenalty = calculatePenalty(exam_time);
        if(newPenalty<penalty) penalty = newPenalty;
        System.out.println("Penalty after applying kempe-chain: "+ penalty);

    }

}
