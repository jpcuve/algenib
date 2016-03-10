package com.darts.algenib.session.job;

import com.darts.algenib.bean.JobItem;

import javax.validation.constraints.NotNull;
import java.util.concurrent.Future;

/**
 * Created by jpc on 8/10/15.
 */
public interface Job<E> {
    Future<E> process(@NotNull JobItem jobItem);

}
