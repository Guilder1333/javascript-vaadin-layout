# JavaScript Vaadin Layout
Vaadin plugin, makes able to create javascript layouts. Doesn't contain any useful components, but adds abstract class which can be extended to make able creation of layout based on JavaScript code.

Extend AbstractJavaScriptLayout class to make your JavaScript layout, the same way you do for JavaScript components.

##Additional JavaScript Connector Methods
```
object WidgetWrapper {
  Widget widget;
  ComponentConnector connector;
  String connectorId;
  Element element;
}
```

WidgetWrapper is simple JSON object. You can simply access to its properties from JS. WidgetWrapper is made to simplify access to the widget main parameters (widget itself, vaadin connector and connector id). Element can be used to add it in the DOM. Widget is required for adopt/orphan.

```
/**
 * Returns array of wrapper objects for child widgets.
 */
WidgetWrapper[] getChildComponents();

/**
 * Adopts child widget. Same as GWT adopt method.
 * Can be called multiple times. First call adopts component, next calls do nothing.
 */
void adopt(Widget widget)

/**
 * Orphans child widget. Same as GWT orphan method.
 * Can be called multiple times. Orphans only component which is child of your component.
 */
void orphan(Widget widget);
```

##Additional Implementable Methods

```
object CaptionEventWrapper {
  Widget widget;
  Element element;
  ComponentConnector connector;
  String connectorId;
  String caption;
  JsArrayString styles;
  String error;
  boolean showError;
  boolean required;
  boolean enabled;
  boolean captionAsHtml;
}
```

CaptionEventWrapper is simple JSON object, so it can be accesses from JS. First fout parameters are the same as for WidgetWrapper.

``void onConnectorHierarchyChange();``
Same as original method of ``AbstractLayoutConnector``, but bas no parameters.

``void updateCaption(CaptionEventWrapper event);``
Same as original method of ``AbstractLayoutConnector``, but argument object already contains all useful parameters for caption creation.

