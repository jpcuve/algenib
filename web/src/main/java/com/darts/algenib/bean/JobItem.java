package com.darts.algenib.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by jpc on 2/18/14.
 */
public abstract class JobItem<E> implements Callable<Future<E>> {
    private static final Logger LOG = LoggerFactory.getLogger(JobItem.class);
    private String id = UUID.randomUUID().toString();
    private String name = id;
    private Future<E> future;
    private int numerator;
    private int denominator;
    private List<String> messages = new ArrayList<>();

    protected JobItem() {
    }

    protected JobItem(String name) {
        this.name = name;
    }

    public Future<E> getFuture() {
        return future;
    }

    public synchronized void addMessage(String message){
        LOG.debug("message: {}", message);
        messages.add(message);
    }

    public synchronized void addMessage(String format, Object... args){
        final String message = String.format(format, args);
        LOG.debug("message: {}", message);
        messages.add(message);
    }

    public String start(){
        LOG.debug("start task: {}", name);
        if (future == null){
            request();
            try{
                future = call();
            } catch(Throwable t){
                LOG.error("failure calling job", t);
            }
        }
        return null;
    }

    public String reset(){
        LOG.debug("reset task: {}", name);
        if (future != null){
            if (!future.isDone()) future.cancel(true);
            future = null;
            request();
        }
        return null;
    }

    public void request(){
        messages.clear();
        progress(0, 0);
    }

    public void init(){
        addMessage("Job started: %s", new Date());
    }

    public void progress(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public void done(){
        progress(1, 1);
        addMessage("Job completed: %s", new Date());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue(){
        return numerator == 0 ? 0 : numerator * 100 / denominator;
    }

    public boolean isRunning(){
        return future != null;
    }

    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }
}
