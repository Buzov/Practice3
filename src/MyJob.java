

public class MyJob implements Runnable {

    int jobid;

    public MyJob(int id) {
        jobid = id;
    }

    @Override
    public void run() {
        System.out.println("doing job " + jobid);

    }

}
