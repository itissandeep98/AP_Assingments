import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class producer extends Thread {
    private static volatile ArrayList<Fibonacci> list;
    private static volatile ArrayList<Fibonacci> answers_list;
    public static volatile int[] memo;
    private static volatile Thread[] thread_list;
    private static int thread_capacity = 0;
    private static producer p;
    private static HashMap<Integer,Fibonacci> map;


    private producer(int num){
        list= new ArrayList<Fibonacci>();
        thread_capacity=num;
        memo=new int[50];
        thread_list=new Thread[num];
        answers_list=new ArrayList<Fibonacci>();
        map= new HashMap<Integer, Fibonacci>();
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter the total number of consumer threads to be generated");
        int num_threads=scan.nextInt();

        p=new producer(num_threads);

        Thread t=new Thread(() -> {
            try {
                calculate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        p.start();
        t.start();
        p.join();
        t.join();
    }




    private void show_answers() throws InterruptedException {
        for(int i=0;i<thread_capacity;i++){
            if(thread_list[i]!=null && thread_list[i].isAlive()){
                thread_list[i].join();
            }
        }
        System.out.println("Answers SO FAR");
        for(Fibonacci f:answers_list){
            System.out.println("\t"+f.getvalue()+"\t"+f.getResult()+"\tTime: "+f.getTime().substring(2));
        }
    }

    private synchronized void  operate() throws InterruptedException {
        Scanner scan=new Scanner(System.in);
        while (true){
            while (list.size()==thread_capacity)
                wait();
            System.out.println("Enter your number");
            String inp=scan.next();
            if(inp.equals("exit")){
                show_answers();
                System.exit(0);
            }
            else if(inp.equals("show")){
                show_answers();
            }
            else{
                try {
                    int n = Integer.parseInt(inp);
                    if(map.containsKey(n)){
                        answers_list.add(map.get(n));
                    }
                    else{
                        map.put(n,new Fibonacci(n));
                        list.add(map.get(n));
                        notifyAll();
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("\tCan't process that");                                                                                                                                                                                                                                                                                             
                }
            }
        }
    }
    private static int find_thread(){
        int i=0;
        while (thread_list[i]!=null && thread_list[i].isAlive()){
            i++;
            if(i>=thread_capacity){
                i=0;
            }
        }
        return i;
    }

    private static void calculate() throws InterruptedException {
        while (true) {
            while (list.size() == 0)
                continue;
            answers_list.add(list.remove(0));
            int i = find_thread();
            thread_list[i] = new Thread(answers_list.get(answers_list.size() - 1));
            thread_list[i].start();
        }
    }


    @Override
    public void run() {
        try {
            operate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
