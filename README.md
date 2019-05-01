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

The service object is kept minimalistic at the moment. It only consists
of a `String` representing the name and a `List<ServiceCapability>` to
describe what the service offers. 

```
interface ServiceConfiguration {
    val name: String
    val capabilities: List<ServiceCapability>
}
```

Each of these `ServiceCapability` objects defines a certain entry-
point into the service. 

```
interface ServiceCapability
```

Capabilities can provide UI elements like `Activity`, `Fragment` or 
simply `View` objects - or other Android related objects like a 
(implicit) `BroadcastReceiver`. Outside of the UI and Android sphere, 
they can simply serve as processors for data, access databases or 
webservices or execute other tasks. 

## Capabilities

In the sample application there are currently three different 
implementations of the `ServiceCapability` interface to showcase
different use-cases. Of course the examples don't show every possible
use-case, but rather a small subset. 

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

The `app` module acts as the host in this scenario. Each service has
a dependency on the `app` module and exposes its configuration to
the `ServiceRegistry` whenever the service is available.

```
interface ServiceRegistry {
    fun register(service: ServiceConfiguration)
    fun getList(): List<ServiceConfiguration>
}
```

At the moment (since it's basically just a proof of concept) the 
registry is kept as simple as possible. The implementation simply
keeps reference of the list and returns it, as well as offers a static
instance of the registry.

## How Services are registered

Since the services can be loaded during runtime - i.e. they are unknown
to the `app` module at compile time - they can not be referenced in a
traditional way. 

To ensure that each service is created and injected into the 
`ServiceRegistry` as soon as possible, a `ContentProvider` is created
in each service that does exactly this:

```
class ServiceProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        ServiceRegistry.getInstance().register(DashboardServiceConfiguration())
        return true
    }
}
```

The `ContentProvider` is added to the `AndroidManifest.xml` file,
which will be merged into the final `AndroidManifest.xml` once the 
module has been loaded. This ensures a seamless and early instantiation.

[(This is also how Firebase is initialized on Android)](https://firebase.googleblog.com/2016/12/how-does-firebase-initialize-on-android.html) 
 
# How to extend

To extend the sample project, simply create a new (dynamic-) feature
module. Create a `ContentProvider` (see: `ServiceProvider.kt`), add 
it to the `AndroidManifest.xml` of your new module (and make sure
that the `authority` is unique) and inject the configuration
of your newly created service into the `ServiceRegistry`. Done.

If you extended existing `ServiceCapability` interfaces, they
will automatically be used. If you created new interfaces, you also
have to find the right spot in the `app` module to utilize them.
