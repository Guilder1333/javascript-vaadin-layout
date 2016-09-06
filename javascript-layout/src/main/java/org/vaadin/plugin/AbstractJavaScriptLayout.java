package org.vaadin.plugin;

import com.vaadin.server.JavaScriptCallbackHelper;
import com.vaadin.shared.communication.ServerRpc;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.JavaScriptFunction;
import org.vaadin.plugin.client.ui.JavaScriptLayoutState;

/**
 * Created by Alexander on 05.09.2016.
 */
public abstract class AbstractJavaScriptLayout extends AbstractLayout {
    private JavaScriptCallbackHelper callbackHelper = new JavaScriptCallbackHelper(this);

    public AbstractJavaScriptLayout() {

    }

    @Override
    protected <T extends ServerRpc> void registerRpc(T implementation,
                                                     Class<T> rpcInterfaceType) {
        super.registerRpc(implementation, rpcInterfaceType);
        callbackHelper.registerRpc(rpcInterfaceType);
    }

    protected void addFunction(String functionName, JavaScriptFunction function) {
        callbackHelper.registerCallback(functionName, function);
    }

    protected void callFunction(String name, Object... arguments) {
        callbackHelper.invokeCallback(name, arguments);
    }

    @Override
    protected JavaScriptLayoutState getState() {
        return (JavaScriptLayoutState) super.getState();
    }
}
