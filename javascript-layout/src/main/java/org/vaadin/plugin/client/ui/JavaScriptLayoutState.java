package org.vaadin.plugin.client.ui;

import com.vaadin.shared.JavaScriptConnectorState;
import com.vaadin.shared.ui.AbstractLayoutState;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alexander on 05.09.2016.
 */
public class JavaScriptLayoutState extends AbstractLayoutState implements JavaScriptConnectorState {
    private Set<String> callbackNames = new HashSet<>();
    private Map<String, Set<String>> rpcInterfaces = new HashMap<>();

    public JavaScriptLayoutState() {
    }

    @Override
    public Set<String> getCallbackNames() {
        return this.callbackNames;
    }

    public void setCallbackNames(Set<String> callbackNames) {
        this.callbackNames = callbackNames;
    }

    @Override
    public Map<String, Set<String>> getRpcInterfaces() {
        return this.rpcInterfaces;
    }

    public void setRpcInterfaces(Map<String, Set<String>> rpcInterfaces) {
        this.rpcInterfaces = rpcInterfaces;
    }
}
