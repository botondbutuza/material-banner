# Material Design Component Banner

A banner displays an important, succinct message, and provides actions for users to address (or dismiss the banner). It requires a user action to be dismissed.

[Material Design Components documentation.](https://material.io/design/components/banners.html)

![Material Banner](https://i.imgur.com/jMd25GE.gif)

## Usage
The only way of creating a Material Banner is through its static `.make()` method.

There's a few overloads available, depending on how much customisation is needed, but all methods need a `view` to append the banner to. This *must* be a `CoordinatorLayout`, and the library *will* throw an exception if none is found in the view hierarchy.

Using the default dismiss and no primary action set:

```
BannerNotification.make(view: View, message: CharSequence): BannerNotification

BannerNotification.make(view: View, @StringRes messageResId: Int): BannerNotification
```

Customising the actions available through the Builder object:

```
BannerNotification.make(view: View, builder: Builder): BannerNotification

Builder()
  .setMessage(msg: CharSequence)
  .setPrimaryAction(action: Builder.Action)
  .overrideDismiss(action: Builder.Action)
```
