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
    private static volatile boolean flag=true;


    private producer(int num){
        list= new ArrayList<Fibonacci>();
        thread_capacity=num;
        memo=new int[100];
        thread_list=new Thread[num];
        answers_list=new ArrayList<Fibonacci>();
        map= new HashMap<Integer, Fibonacci>();
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter the total number of consumer threads to be generated");
        int num_threads=scan.nextInt();

        p=new producer(num_threads);
        p.create_thread();
        p.start();
        p.join();
    }

    private void create_thread(){
        for(int i=0;i<thread_capacity;i++) {

            thread_list[i] = new Thread(() -> {
                p.calculate();
            });

            thread_list[i].start();
        }
    }



    private void show_answers() throws InterruptedException {
        flag=false;
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
            System.out.println("Enter your number");
            String inp=scan.next();
            if(inp.equals("exit")){
                show_answers();
                return;
            }
            else if(inp.equals("show")){

                show_answers();
                flag=true;
                create_thread();
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
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("\tCan't process that");                                                                                                                                                                                                                                                                                             
                }
            }
        }
    }

    private  void calculate() {
        while (flag) {
            Fibonacci f=null;
            synchronized (list) {
                while (list.size() == 0 && flag){
                    continue;
                }
                if (list.size() > 0) {
                    f = list.remove(0);
                    answers_list.add(f);
                }
            }

            if(f!=null){
                f.setResult();
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
