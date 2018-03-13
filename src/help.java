import java.util.concurrent.CountDownLatch;

class help implements Runnable {
    public static int count=0;
    private CountDownLatch a;
    public help(CountDownLatch a){
        this.a=a;
    }
    public  void run() {
        synchronized(this) {
                count++;
                a.countDown();
                //System.out.println("I am "+Thread.currentThread().getName()+"  "+count);

        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args){
        CountDownLatch counta=new CountDownLatch(100);
        help syncThread = new help(counta);
        for(int i=0;i<100;i++){
            new Thread(syncThread,i+"").start();
        }
        try{
            counta.await();
            System.out.println(""+help.count);
        }
        catch (InterruptedException ex){

        }

    }
}