# Cell Society Refactoring 

## Configuration
* Use exceptions and try catch statements instead of if statements to check validity and switch to default parameters.
* Move exceptions to new class to be able to check validity outside of construction of SimulationInfo object.
* Replace magic numbers used for parameter defaults with variables.

## Simulation
* Finding Neighbors has a lot of magic numbers and a degree of duplication. A better design would be to have one neighbor calculating method, and enums for various types of neighbor configurations. This makes adding new types much more easy. 
* This will mainly involve adjusting the BasicGrid class, and creating a new enumeration. 
* 

## Visualization
* Communication
    * Magic numbers: Some detected magic numbers only exist for testing purposes and will be removed when some features are fully implemented. Others are legitimately problematic and will be resolved unless they are geometric angle constants (i.e. sqrt(3)/2).
    * Many instances of single-line if statements were present and resolved.
    * Wildcard imports were resolved except for those that import other packages of our code (i.e. import Simulation.\*;) since our packages are small and importing every individual dependency would be almost as much data as the single-line blanket import and much more disorderly.
* Flexibility - No issues
* Modularity
    * Dependencies in GUIManager will be reduced at a later time, there is currently another team member editing elements of it, and there is not much time left in this class period.
* Code Smells - No issues
* Java Notes
    * Exceptions will be logged at a later time.



