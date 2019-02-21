# Cell Society Code Review

## Long Methods

### src/Configuration/CheckParameters
The major design flaws in this method are mostly a result of the switch case involving each of our simulation enumerations. By instead including a number of parameters, number of probabilities, and possibly even default values for parameters and probabilities in the enumeration, we could have shortened the method and gotten rid of multiple magic numbers.

Another way to improve readability would be to extract separate processes into another method. For instance, error checking/validation for argument length seems like a modular step, so one could create a separate method for that portion. 

### src/Configuration/XMLWriter
This class was made too hastily at the end. It was redone as the code masterpiece in ih52 analysis which shortened the method quite a bit, by creating a separate method to append a child element in the XML and having a map between the element of the XML and its value instead of having separate values for each element.

### src/Configuration/SimulationInfo
Once again, a major problem with this method is having to check if the simulation should have two states or more to implement the random defaults. This could have been fixed by predefining the ranges for each of the states and having the method simply put the predefined state for the simulation that matched the generated random number into the array that was passed out instead of manually stating the ranges based off of the number of possible states for each simulation.

Within each state number case, there is duplicated control flow that could be reduced with calls to more general methods.

## Best Practices

### Readable Code
Our project had a lot of readability issues, but they were all the same issue: One line if/for statements don't technically need curly braces, but they should be there to improve readability.

### Exception Handling
In our exception handling, we completed the logic to handle the exception, but we forgot to preserve the original exception itself, which can be informative. This should be logged and perhaps examined.

### Switch/Case Statements to Determine Object Declarations
To avoid scenarios where we would be instantiating a certain object type from a common parent class, we could use the factory design patter, specifically utilizing reflection to cut down the need to have large, mostly duplicated switch-case statements throughout our code.

### Copies Returned by Getters
It would have been better coding practice to give copies of each of the objects, such as the integer array in the SimulationInfo object, instead of the actual object, if we do not want the caller to be able to change the reference object. Although this wasn't a big deal because the value was only used at the initialization of the grid, it would have been better practice and possibly more vital for different expansions of the project to instead pass a copy.


## Resolutions
1. Elaborate characteristic values for different possible subclasses in the enumeration of the given types instead of a switch case statement later in the code.
2. Avoid using magic numbers by either including them in the aforementioned enumerations or setting them equal to characteristic labels.
3. Don't try to do too much in one method. If there is a modular part of the process, write a new method with an informative name, and extract the logic. This improves readability, and also makes it easier to change specific components of the logic. 