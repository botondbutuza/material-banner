# Material Design Component Banner
Just a material banner, really.

## Usage
The only way of creating a Material Banner is through its static `.make()` method. There's a few overloads available, depending on how much customisation is needed (`message` vs `Builder` object), but all methods need a `view` to append the banner to. This *must* be a `CoordinatorLayout`, and the library *will* throw an exception if none is found in the view hierarchy.
