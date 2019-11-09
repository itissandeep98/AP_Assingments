import java.util.ArrayList;

public class consumer implements Runnable {


    consumer(){

    }
    public int find_thread(){
        int i=0;
        while (producer.thread_list[i]!=null && producer.thread_list[i].isAlive()){
            i++;
            if(i>=producer.thread_capacity){
                i=0;
            }
        }
        return i;
    }




    private  synchronized void calculate() throws InterruptedException {

        while (producer.list.size()==0)
            wait();
        producer.answers_list.add(producer.list.remove(0));
        int i=find_thread();
        producer.thread_list[i]=new Thread(producer.answers_list.get(producer.answers_list.size()-1));
        producer.thread_list[i].start();
        notify();
    }

    @Override
    public void run() {
        try {
            calculate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
