package com.darts.algenib.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jpc on 23/02/2016.
 */
public class BatchIterator<E> implements Iterator<List<E>> {
    private List<E> list;
    private int batchSize;
    private int i = 0;

    public BatchIterator(List<E> list, int batchSize){
        this.list = list;
        this.batchSize = batchSize;
    }

    @Override
    public boolean hasNext() {
        return i < list.size();
    }

    @Override
    public List<E> next() {
        int p = Math.min(i + batchSize, list.size());
        final List<E> batch = list.subList(i, p);
        this.i = p;
        return batch;
    }

    public static void main(String[] args) {
        for (Iterator<List<Long>> it = new BatchIterator<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L), 2); it.hasNext();){
            final List<Long> batch = it.next();
            System.out.println(batch.stream().map(l -> Long.toString(l)).collect(Collectors.joining(",")));
        }
    }
}
