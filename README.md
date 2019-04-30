# Service Oriented Application

This is an example project for `dynamic features` in a service-oriented
approach. There are multiple core modules which are included through
the `app` module. Also there are multiple feature modules, which 
depend on the `app` module as satellites. 

The `app` module doesn't know anything about the specific features.
Instead, the `core-feature` module exposes certain base capabilities 
that define for example possible entry points into features.

In this simplified approach, we have `MenuCapability` and 
`ButtonCapability` to inject features either into the Activity menu,
or as a Button in our `Button Navigation` (which in this case is just
a `LinearLayout` that displays buttons).

# Dependency Graph

      [feature-dashboard]     [feature-profile]
                        \     /
                         [app]
                         /   \      
                  [core-rx] [core-other] 
                        
# Service Configuration

## Capabilities

# Service Registry

## How Services are registered

# How to extend

# Limitations

