import java.time.Duration;
import java.time.Instant;

public class Fibonacci {
    private int n;
    private int result;
    private String computation_time;

    Fibonacci(int x){
        this.n=x;
        this.result=-1;

    }

    public int getResult() {
        return result;
    }
    public void setResult(){
        Instant start=Instant.now();
        result=fib(this.n);
        Instant finish=Instant.now();
        computation_time= Duration.between(start,finish).toString();
    }
    public int getvalue(){
        return n;
    }

    public int fib(int x){
        if(producer.memo[x]!=0) return producer.memo[x];
        if(x<2) return  x;
        producer.memo[x-1]=fib(x-1);
        producer.memo[x-2]=fib(x-2);
        producer.memo[x]=producer.memo[x-1]+producer.memo[x-2];
        return producer.memo[x];
    }
    @Override
    public String toString(){
        return "Fibonacci "+n;
    }

    public String getTime() {
        return computation_time;
    }
}
