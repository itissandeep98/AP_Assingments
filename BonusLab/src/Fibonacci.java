
public class Fibonacci implements Runnable{
    private int n;
    private int result;

    Fibonacci(int x){
        this.n=x;
        this.result=-1;

    }

    public int getResult() {
        return result;
    }
    public int getvalue(){
        return n;
    }

    private int fib(int x){
        if(producer.memo[x]!=0) return producer.memo[x];
        if(x<2) return  x;
        producer.memo[x-1]=fib(x-1);
        producer.memo[x-2]=fib(x-2);
        producer.memo[x]=producer.memo[x-1]+producer.memo[x-2];
        return producer.memo[x];
    }

    @Override
    public void run() {
        result=fib(n);
    }
}
