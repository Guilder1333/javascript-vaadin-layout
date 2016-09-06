package org.vaadin.plugin;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Alexander on 06.09.2016.
 */
@JavaScript("vaadin://scripts/TestLayoutConnector.js")
public class TestLayout extends AbstractJavaScriptLayout {
    private final List<Component> components = new ArrayList<>();

    @Override
    public void addComponent(Component c) {
        components.add(c);
        super.addComponent(c);
    }

    @Override
    public void removeComponent(Component c) {
        components.remove(c);
        super.removeComponent(c);
    }

    @Override
    public void replaceComponent(Component oldComponent, Component newComponent) {
        ListIterator<Component> iterator = components.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next() == oldComponent) {
                super.removeComponent(oldComponent);
                super.addComponent(newComponent);
                iterator.set(newComponent);
            }
        }
    }

    @Override
    public int getComponentCount() {
        return components.size();
    }

    @Override
    public Iterator<Component> iterator() {
        return components.iterator();
    }
}
