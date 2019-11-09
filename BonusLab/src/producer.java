import java.util.ArrayList;
import java.util.Scanner;

public class producer extends Thread {
    public static volatile ArrayList<Fibonacci> list;
    public static int thread_capacity = 0;
    public static volatile int memo[];
    public static Thread thread_list[];
//    public static consumer c;
    public static volatile ArrayList<Fibonacci> answers_list;


    producer(int num){
        list= new ArrayList<Fibonacci>();
        thread_capacity=num;
        memo=new int[50];
        thread_list=new Thread[num];
        answers_list=new ArrayList<Fibonacci>();
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter the total number of consumer threads to be generated");
        int num_threads=scan.nextInt();
//        c=new consumer();
        producer p=new producer(num_threads);
        p.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    calculate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }




    private void show_answers() throws InterruptedException {
        for(int i=0;i<thread_capacity;i++){
            if(thread_list[i]!=null && thread_list[i].isAlive()){
                thread_list[i].join();
            }
        }
        System.out.println("Answers SO FAR");
        for(Fibonacci f:answers_list){
            System.out.println("\t\t"+f.getResult()+"\tTime: "+f.getTime());
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
                return;
            }
            else if(inp.equals("show")){
                show_answers();
            }
            else{
                try {
                    int n = Integer.parseInt(inp);
                    list.add(new Fibonacci(n));
                    notifyAll();
                }
                catch (NumberFormatException e){
                    System.out.println("\tCan't process that");
                }
            }
        }
    }
    public static int find_thread(){
        int i=0;
        while (producer.thread_list[i]!=null && producer.thread_list[i].isAlive()){
            i++;
            if(i>=producer.thread_capacity){
                i=0;
            }
        }
        return i;
    }

    private static void calculate() throws InterruptedException {
        while (true) {
            while (producer.list.size() == 0)
                continue;
            producer.answers_list.add(producer.list.remove(0));
            int i = find_thread();
            producer.thread_list[i] = new Thread(producer.answers_list.get(producer.answers_list.size() - 1));
            producer.thread_list[i].start();

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
