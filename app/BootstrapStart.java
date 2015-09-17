import play.jobs.Job;
import play.jobs.OnApplicationStart;

/**
 * Created by Rafael on 17/09/2015.
 */
@OnApplicationStart
public class BootstrapStart extends Job {

    @Override
    public void doJob() throws Exception {
        System.out.println("TCC - Ciencia da Computacao!");
    }

}
