# DoubleClickListener for Android
A simple double click listener to implement instagram-like double tap behaviour.

It works for all views implementing OnClickListener, for example Buttons or ImageViews.
It supports both single and double clicks for the same view, the time delay is adjustable.

Usage example:

```java
Button myButton = (Button)findViewById(R.id.some_id);
myButton.setOnClickListener(new DoubleClickListener() {
            @Override
            protected void onSingleClick(View v) {
                // do something when single clicked
            }

            @Override
            protected void onDoubleClick(View v) {
                // do something when double clicked
            }
        });
```

# DoubleItemClickListener for Android
A simple double click listener to implement instagram-like double tap behaviour.

It works for all views implementing OnItemClickListener, for example ListViews or GridViews.
It supports both single and double clicks for the same view, the time delay is adjustable.

Usage example:

```java
GridView myGridView = (GridView)findViewById(R.id.some_id);
myGridView.setOnItemClickListener(new DoubleItemClickListener() {
            @Override
            public void onSingleClick(AdapterView<?> parent, View v, int position, long id) {
                // do something when single clicked
            }

            @Override
            public void onDoubleClick(AdapterView<?> parent, View v, int position, long id) {
               // do something when double clicked
            }
        });
```
