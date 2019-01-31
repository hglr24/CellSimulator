### Part 1

#### Encapsulation

    Cell - try to keep personal information about the cells private. Other objects that are not the cell
    do not need to know about these fields. 

#### Inheritance 
    Abstract Grid class is used as a way to hold and keep track of all the cells. An implementing
    subclass, say 2DGrid, would define an exact way in which to organize the data structure. In this way,
    the interface 

#### Open Closed
    We would like to keep the interface on the highest level the same, regardless of the exact simulation.
    We want the Cell to be open for extension, in order to easily add new kinds of cells, but it 
    should be closed to modification in that the way cells communicate with others should not 
    change on a high level
#### Exceptions
    Use enum types wherever possible, so that you can only choose from a finite set, and invalid 
    inputs are easily caught, rather than an arbitrary integer mapping, where numbers outside the
    bounds may be caught. Various assertions can also be made as a "sanity" check. 
#### Good Parts
    Kept generic to be flexible wherever possible, so adding new simulation types is not as painful
    
### Part 2

#### Linkage
    The state of the cells needs to be communicated to the GUI (Visualization) so that the grid 
    can be redrawn/updated. The GUI inputs also change the simulation parameters/type, which 
    requires my part to adapt. My portion also needs to take the information from the XML 
    Reader/Initialization in order to 
    know how to start up, and set the initial states.
   
