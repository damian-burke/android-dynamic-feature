# Service Oriented Application

This is an example project for `dynamic features` in a service-oriented
approach. There are multiple core modules which are included through
the `app` module. Also there are multiple feature modules, which 
depend on the `app` module as satellites. 

The `app` module doesn't know anything about the specific features.
Instead, the `core-feature` module exposes certain base capabilities 
that define for example possible entry points into features.

In this simplified approach, we have a 
`ButtonCapability` to inject features either into the Activity menu,
or as a Button in our `Button Navigation` (which in this case is just
a `LinearLayout` that displays buttons).

# Dependency Graph

![dependency graph](/art/dependency-graph.png?raw=true "Dependency Graph")
                        
# Service Configuration

- each one has a name / identifier
- list of capabilities that can will be exposed to the app, can be used to display entry points (configured by the service)

## Capabilities

- `FeatureCapability` as empty base interface
- example: `ViewCapability`, `ImageProcessorCapability`
- `ViewCapability` can inflate and return a View instance
- `ImageProcessorCapability` performs certain processing on a `Bitmap`
- services can have multiple capabilities: 
  - these capabilities are basically entry points into the app
  - could also be non-ui services, for example image processing (`ImageGreyscaleCapability` that takes a `Bitmap` as input)  


`DashboardViewCapability`
- 
Inflates and returns a custom layout that includes a button. Clicking
this button starts the `DashboardActivity` to guide the user into the 
service / feature.

`ProfileViewCapability`
- 
Inflates and returns a Button. This capability also adds a 
`View.OnAttachStateChangeListener` to the returned `View`
allowing self-contained managing of for example `Animations`.

When clicking the button, the `ProfileActivity` will be started.

`ImageGreyscaleCapability`
- 
This capability extends `ImageProcessorCapability` which provides a
function with the signature
```
process(bitmap: Bitmap): Bitmap
```

This capability reduces the image saturation to 0, creating a greyscale
effect. It's an example of a service that doesn't provide, expose or
inject any UI elements but instead simply offers a service.

# Service Registry

## How Services are registered

- ContentProvider is used to inject the `ServiceConfiguration` into the `ServiceRegistry` which is hosted in the `app` module

# How to extend

- Create feature module
- Create `ServiceConfiguration` 
- Create 1..n `ServiceCapability` subclasses
- Create `ContentProvider` that injects the `ServiceConfiguration`

# Limitations


