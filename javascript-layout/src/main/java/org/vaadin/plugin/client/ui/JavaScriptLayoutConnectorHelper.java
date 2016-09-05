package org.vaadin.plugin.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.JavaScriptConnectorHelper;
import com.vaadin.client.ui.AbstractFieldConnector;
import com.vaadin.client.ui.Icon;
import com.vaadin.shared.AbstractFieldState;
import com.vaadin.shared.communication.URLReference;

import java.util.List;

/**
 * Created by Alexander on 05.09.2016.
 */
public class JavaScriptLayoutConnectorHelper extends JavaScriptConnectorHelper {
    private final AbstractJavaScriptLayoutConnector connector;
    private boolean initialized = false;
    private boolean hasConnectionWrapper = false;

    public JavaScriptLayoutConnectorHelper(AbstractJavaScriptLayoutConnector connector) {
        super(connector);
        this.connector = connector;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public JavaScriptObject getConnectorWrapper() {
        final JavaScriptObject connectorWrapper = super.getConnectorWrapper();
        if (!hasConnectionWrapper) {
            extendConnectorWrapper(connectorWrapper);
            hasConnectionWrapper = true;
        }
        return connectorWrapper;
    }

    protected void extendConnectorWrapper(JavaScriptObject connectorWrapper) {
        extendLayoutMethods(connectorWrapper);
    }

    public void onConnectorHierarchyChange() {
        if (!initialized) {
            initialized = initJavaScript();
        }

        if (initialized) {
            JavaScriptObject connectorWrapper = getConnectorWrapper();
            invokeIfPresent(connectorWrapper, "onConnectorHierarchyChange");
        }
    }

    public void updateCaption(ComponentConnector connector) {
        if (!initialized) {
            initialized = initJavaScript();
        }

        if (initialized) {
            URLReference iconUrl = connector.getState().resources.get("icon");
            String iconUrlString = iconUrl != null ? iconUrl.getURL() : null;
            Icon icon = connector.getConnection().getIcon(iconUrlString);

            JsArrayString styles = JavaScriptObject.createArray().cast();
            for (String style : connector.getState().styles) {
                styles.push(style);
            }
            String error = connector.getState().errorMessage;
            boolean showError = error != null;
            if (showError && connector.getState() instanceof AbstractFieldState) {
                AbstractFieldState fieldState = (AbstractFieldState) connector.getState();
                showError = !fieldState.hideErrors;
            }

            boolean required = false;
            if (connector instanceof AbstractFieldConnector) {
                required = ((AbstractFieldConnector) connector).isRequired();
            }

            JavaScriptObject captionWrapper = createCaptionWrapper(
                    connector.getWidget(),
                    connector.getWidget().getElement(),
                    connector,
                    connector.getConnectorId(),
                    connector.getState().caption,
                    icon.getElement(),
                    styles,
                    error,
                    showError,
                    required,
                    connector.isEnabled(), connector.getState().captionAsHtml);
            JavaScriptObject connectorWrapper = getConnectorWrapper();
            invokeIfPresent(connectorWrapper, "updateCaption", captionWrapper);
        }
    }

    private JsArray<JavaScriptObject> getChildComponents() {
        List<ComponentConnector> childComponents = connector.getChildComponents();
        JsArray<JavaScriptObject> array = JavaScriptObject.createArray(childComponents.size()).cast();
        for (ComponentConnector cc : childComponents) {
            array.push(createWidgetWrapper(cc, cc.getWidget(), cc.getConnectorId(), cc.getWidget().getElement()));
        }
        return array;
    }

    private void adopt(Widget widget) {
        connector.getWidget().adoptWidget(widget);
    }

    private void orphan(Widget widget) {
        connector.getWidget().orphanWidget(widget);
    }

    private static native void extendLayoutMethods(JavaScriptObject wrapper) /*-{
        wrapper['getChildComponents'] = function() {
            return h.@org.vaadin.plugin.client.ui.JavaScriptLayoutConnectorHelper::getChildComponents()();
        };

        wrapper['adopt'] = function(widget) {
            return h.@org.vaadin.plugin.client.ui.JavaScriptLayoutConnectorHelper::adopt(*)(widget);
        };

        wrapper['orphan'] = function(widget) {
            return h.@org.vaadin.plugin.client.ui.JavaScriptLayoutConnectorHelper::orphan(*)(widget);
        };
    }-*/;

    private static native void invokeIfPresent(
            JavaScriptObject connectorWrapper, String functionName, Object... arguments)
    /*-{
        if (typeof connectorWrapper[functionName] == 'function') {
            connectorWrapper[functionName].apply(connectorWrapper, arguments);
        }
    }-*/;

    private static native JavaScriptObject createWidgetWrapper(ComponentConnector connector, Widget widget, String connectorId, Element element) /*-{
        return { widget: widget, connector: connector, connectorId: connectorId, element: element };
    }-*/;

    private static native JavaScriptObject createCaptionWrapper(Widget widget, Element element, ComponentConnector connector, String connectorId, String caption, Element icon, JsArrayString styles, String error, boolean showError, boolean required, boolean enabled, boolean captionAsHtml) /*-{
        return {
            widget: widget,
            element: element,
            connector: connector,
            connectorId: connectorId,
            caption: caption,
            icon: icon,
            styles: styles,
            error: error,
            showError: showError,
            required: required,
            enabled: enabled,
            captionAsHtml: captionAsHtml};
    }-*/;
}
