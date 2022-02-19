# SFeatureList
A Xposed module aim to modify Samsung Features.

Conventional decoded CSC feature somehow not working properly on Android 12 so I created this module to inject into `SemCscFeature` class to modify returned result.
Modified features can be found in the source code. Also tried to inject into `FloatingFeature`s, should work as well.
