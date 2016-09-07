/**
 * Created by Alexander on 06.09.2016.
 */
window.org_vaadin_plugin_TestLayout = function() {
    var self = this;
    var children = {};
    var captions = {};

    /**
     * Randomly positioning new child elements. We don't suppose that any element can be removed.
     */
    this.relayout = function() {
        var widgets = this.getChildComponents();
        var width = this.getElement().offsetWidth;
        var height = this.getElement().offsetHeight;
        if (!width || !height) {
            return;
        }
        var newChildren = {};
        for(var i = 0; i < widgets.length; i++) {
            var w = widgets[i];
            if (!children[w.connectorId]) {
                var left = Math.random() * width / 2 + "px";
                var top = Math.random() * height / 2;
                w.element.style.left = left;
                w.element.style.top = top + "px";
                w.element.style.position = "absolute";
                this.getElement().appendChild(w.element);
                this.adopt(w.widget);
                var caption = captions[w.connectorId];
                if (caption) {
                    caption.style.left = left;
                    caption.style.top = (top - 20) + "px";
                }
            }
            newChildren[w.connectorId] = w;
        }
        children = newChildren;
    };

    this.onStateChanged = function() {

    };

    this.onConnectorHierarchyChange = function() {
        this.relayout();
    };

    this.updateCaption = function(e) {
        if ((e.caption || e.icon) && !captions[e.connectorId]) {
            var x = e.element.offsetLeft;
            var y = e.element.offsetTop;
            var caption = document.createElement("DIV");
            caption.style.position = "absolute";
            caption.style.left = x + "px";
            caption.style.top = (y - 20) + "px";
            if (e.icon) {
                caption.appendChild(e.icon);
            }
            var span = document.createElement("SPAN");
            span.appendChild(document.createTextNode(e.caption || ""));
            caption.appendChild(span);
            this.getElement().appendChild(caption);
            captions[e.connectorId] = caption;
        }
    };

    this.getElement().style.position = "relative";
    this.addResizeListener(this.getElement(), function() {
        self.relayout();
    });
};