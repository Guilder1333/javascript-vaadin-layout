package org.vaadin.plugin.client.ui;

import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.JavaScriptConnectorHelper;
import com.vaadin.client.communication.HasJavaScriptConnectorHelper;
import com.vaadin.client.ui.AbstractLayoutConnector;
import com.vaadin.client.ui.JavaScriptWidget;
import com.vaadin.shared.ui.JavaScriptComponentState;

/**
 * Created by Alexander on 05.09.2016.
 */
public class AbstractJavaScriptLayoutConnector extends AbstractLayoutConnector implements HasJavaScriptConnectorHelper {

    private final JavaScriptLayoutConnectorHelper helper = new JavaScriptLayoutConnectorHelper(this) {
        @Override
        protected void showInitProblem(
                java.util.ArrayList<String> attemptedNames) {
            getWidget().showNoInitFound(attemptedNames);
        }
    };

    @Override
    protected void init() {
        super.init();
        helper.init();
    }

    @Override
    public JavaScriptConnectorHelper getJavascriptConnectorHelper() {
        return helper;
    }

    @Override
    public JavaScriptLayoutState getState() {
        return (JavaScriptLayoutState) super.getState();
    }

    @Override
    public void onUnregister() {
        super.onUnregister();
        helper.onUnregister();
    }


    @Override
    public JavaScriptPanel getWidget() {
        return (JavaScriptPanel) super.getWidget();
    }

    @Override
    public void onConnectorHierarchyChange(ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
        helper.onConnectorHierarchyChange();
    }

    @Override
    public void updateCaption(ComponentConnector connector) {
        helper.updateCaption(connector);
    }
}
