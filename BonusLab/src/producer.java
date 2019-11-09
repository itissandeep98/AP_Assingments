import java.util.ArrayList;
import java.util.Scanner;

public class producer extends Thread {
    public static ArrayList<Fibonacci> list;
    public static int thread_capacity = 0;
    public static int memo[];
    public static Thread thread_list[];
    public static consumer c;
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
        c=new consumer();
        producer p=new producer(num_threads);
        p.start();
    }




    private void show_answers() throws InterruptedException {
        for(int i=0;i<thread_capacity;i++){
            if(thread_list[i]!=null && thread_list[i].isAlive()){
                thread_list[i].join();
            }
        }
        System.out.println("Answers SO FAR");
        for(Fibonacci f:answers_list){
            System.out.println("\t\t"+f.getResult());
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
                    new Thread(c).start();
                    notifyAll();
                }
                catch (NumberFormatException e){
                    System.out.println("\tCan't process that");
                }
            }
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
