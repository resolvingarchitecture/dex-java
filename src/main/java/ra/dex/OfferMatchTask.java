package ra.dex;

import ra.common.tasks.BaseTask;
import ra.common.tasks.TaskRunner;

import java.util.Set;

public class OfferMatchTask extends BaseTask {

    private DEXService service;

    public OfferMatchTask(DEXService service, TaskRunner taskRunner) {
        super(OfferMatchTask.class.getName(), taskRunner);
        this.service = service;
    }

    @Override
    public Boolean execute() {
        Set<Offer> matched = service.match();
        if(!matched.isEmpty()) {

        }
        return true;
    }
}
