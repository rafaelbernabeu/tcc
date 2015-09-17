import play.jobs.Job;
import play.jobs.OnApplicationStop;

/**
 * Created by Rafael on 17/09/2015.
 */
@OnApplicationStop
public class BootstrapStop extends Job {

    @Override
    public void doJob() throws Exception {
        System.out.println("BYE!");
    }

}
