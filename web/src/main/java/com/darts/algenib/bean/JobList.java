package com.darts.algenib.bean;

import com.darts.algenib.session.job.CleanupUnresolvedBindersWithPairOfTrademarksJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by jpc on 2/18/14.
 */
@Named
@ApplicationScoped
public class JobList {
    private static final Logger LOG = LoggerFactory.getLogger(JobList.class);
    private List<JobItem> items = new ArrayList<>();
    @Inject
    private CleanupUnresolvedBindersWithPairOfTrademarksJob cleanupLatamUnresolvedBindersJob;

    @PostConstruct
    public void init(){
        items.add(new JobItem("Cleanup LATAM unresolved binders job") {
            @Override
            public Future<Void> call() {
                return cleanupLatamUnresolvedBindersJob.process(this);
            }
        });
    }

    public List<JobItem> getItems() {
        return items;
    }
}
