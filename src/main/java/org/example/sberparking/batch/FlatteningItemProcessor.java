package org.example.sberparking.batch;

import org.springframework.batch.item.ItemProcessor;

import java.util.Iterator;
import java.util.List;

public class FlatteningItemProcessor<I, O> implements ItemProcessor<I, O> {

    private ItemProcessor<I, List<O>> delegate;
    private Iterator<O> currentIterator;

    @Override
    public O process(I item) throws Exception {
        if (currentIterator == null || !currentIterator.hasNext()) {
            List<O> items = delegate.process(item);
            if (items == null || items.isEmpty()) return null;
            currentIterator = items.iterator();
        }

        return currentIterator.hasNext() ? currentIterator.next() : null;
    }

    public void setDelegate(ItemProcessor<I, List<O>> delegate) {
        this.delegate = delegate;
    }
}

