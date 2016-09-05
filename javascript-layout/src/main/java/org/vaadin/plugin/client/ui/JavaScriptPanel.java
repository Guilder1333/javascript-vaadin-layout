package org.vaadin.plugin.client.ui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;

/**
 * Created by Alexander on 05.09.2016.
 */
public class JavaScriptPanel extends FlowPanel {
    public void showNoInitFound(ArrayList<String> attemptedNames) {
        String message = "Could not initialize JavaScriptLayoutConnector because no JavaScript init function was found. Make sure one of these functions are defined: <ul>";
        for (String name : attemptedNames) {
            message += "<li>" + name + "</li>";
        }
        message += "</ul>";

        getElement().setInnerHTML(message);
    }

    public void adoptWidget(Widget widget) {
        if (widget.getParent() != this) {
            adopt(widget);
        }
    }

    public void orphanWidget(Widget widget) {
        if (widget.getParent() == this) {
            orphan(widget);
        }
    }
}
