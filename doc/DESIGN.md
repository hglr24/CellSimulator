# Design

## High Level

The goal was to create a cell simulation project that could easily extended to simulate 
a wide variety of cellular automata based simulations. This involves providing an XML template for desired initial states, a GUI that effectively visualizes simulations and allows users to interact with various parameters, and building a back end that allows for easy addition of new simulations, neighborhood types, various grid changes, cell types, rules, parameters, etc..

## Adding new features

### Simulation
A completely new simulation requires creating a subclass implementation for a State, Grid,  Cell, and RuleSet that inherits from the 
existing interface/hierarchy. Specific details such as Grid type and Neighborhood type can be chosen here. The "rules" of the simulation
mut be specified in the RuleSet, and the exact neighborhood finding algorithm must be implemented in the grid. Any additional special 
details such as additional parameters must be specified in the Cell and RuleSet. The State enumeration specifies which states cells can be in. Specifying a new state for a simulation type requires defining a label, color, and numerical value in the enumeration parameters.

### Configuration
The configuration takes in an initial XML file. The reader then parses each of the elements of the XML and passes them to a SimulationInfo object, which takes in all of the strings and converts them to the desired values and data structures that completely represent the initial configuration of the simulation. The relevant parameters are title, simulation type, initial integer configuration or "Random" string, width, height, shape, parameters, probabilities, neighborhood, edge, grid size, outline, and state colors.
The configuration also contain the XMLWriter that saves the current configuration of the cell. This relies on the SimulationInfo object being passed back to the configuration, along with the Grid object that will be saved as the current configuration of the simulation. To create a new simulation, from an XML file, create a new XML file with the format 
```
<?xml version="1.0" encoding="UTF-8" ?>
<data media="Simulation">
    <Title>  </Title>
    <SimulationType></SimulationType>
    <GridConfiguration>  </GridConfiguration>
    <GridWidth>  </GridWidth>
    <GridHeight>  </GridHeight>
    <Shape></Shape>
    <SimParameters>  </SimParameters>
    <RandomProbabilities>  </RandomProbabilities>
    <Neighborhood>  </Neighborhood>
    <Edge>  </Edge>
    <GridSize>  </GridSize>
    <OutlineFlag>  </OutlineFlag>
    <StateColors>  </StateColors>
</data>
```
Where the SimulationType must be one of the available simulations, GridConfiguration is either an integer array matching the height and width in length or "Random" or "True Random", width and height are integers, shape is either "Triangle", "Hexagon", or "Square", SimParameters is a comma separated list of doubles or integers, RandomProbabilities is a comma separated list of doubles, Neighborhood is the either "Square", "Triangle", "Hexagon", "Cardinal", "Knight", "I", or "Y", Edge is either "Toroidal", "Bounded", or "Infinite", GridSize is the integer size of each cell in the grid, OutlineFlag is either "True" or "False", and StateColors are colors that will represent the color of the cells.

### Visualization
Once the incoming XML file is validated, a new GUIManager object is created. Each GUI has an index that identifies it that is consistent with the other indices of the stored simulation objects for easy pairing of information. Within GUIManager, it is very easy to add a new control using events that call methods from other classes once activated. The main view is organized using a borderpane, and within each pane, there are many more layers of organization such that it would not be difficult to place a new node in a certain location (i.e. the gridpane used to store nodes for the right pane). It is easy to switch to an alternate display style for the simulation window - most of the elements in the visualization are tied to a CSS stylesheet making it easy to change properties of object categories without delving deep into the code. 

For the grid itself, all of the code used for drawing and positioning the grid as a whole is tied to an abstract grid class. Therefore, all that would need to happen for a new type of cell shape to be displayed would be to define a new subclass of shapegrid (which extends gridpane and contains relevant spacing data for that shape type) and a new subclass of gridshape (which extends javafx polygon and contains details of how to draw a particular shape in its subclass).

Error window display is handled through a public ErrorBox class that can be called and displayed anywhere in the code, so a new feature outside of the UI could easily call an ErrorBox in only two lines to display to the user that there was a problem.

## Assumptions 
We assumed that the grid size would remain within the limits that a reasonably powerful computer could handle.