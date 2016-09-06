/**
 * Created by Alexander on 06.09.2016.
 */
window.org_vaadin_plugin_TestLayout = function() {

    this.onStateChanged = function() {

    };

    this.onConnectorHierarchyChange = function() {
        var widgets = this.getChildComponents();
        var width = this.getElement().offsetWidth;
        var height = this.getElement().offsetHeight;
        for(var i = 0; i < widgets.length; i++) {
            var w = widgets[i];
            w.element.style.left = Math.random() * width/2;
            w.element.style.top = Math.random() * height/2;
            w.element.style.position = "absolute";
            this.getElement().appendChild(w.element);
            this.adopt(w.widget);
        }
    };

    this.getElement().style.position = "relative";
};