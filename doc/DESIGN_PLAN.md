Design Plan
===========

### Configuration
* Use a two dimensional list (a list of lists of cells) to represent cells and their states (allows for changing the
size of the cell representation).
* Format of the xml file needs to flexible for different kinds of games

### Visualization
* Uniform grid to represent the cells
* Different visualization for each state
* Step or autorun button to let user run simulation through the user interface

### Simulation
* Calculate the state of each cell
* Update the cells
* Run next iteration

### Initial Thoughts
1.) Knows what rules to apply being read from xml file. Only depends on relevant cells.
2.) Cell knows about its relevant cells through looking at one away in its row and column index. It can update itself without 
affecting relevant cells by having a current state and a "next" state.
3.) The grid is just for visualization, behavior would be update the visualization. All of the the cells need to know 
about their place in the grid.
4.) Configuration file must contain the rules and the initial states of the cells. Also labels mapping the images of 
different states.
5.) GUI is updated with a legend mapping between state and some color or other visualization change. Also a step/autoplay
with autoplay having some kind of speed control. (Possibly how many steps). Include title.
* Create a copy of all of the cell states and update all current states based off of copy or have each cell have current
state and next state
* All cells need to know their place in the grid and be able to access the states around them
* Needed in configuration file: rules, initial states, come back to configuration of xml file


### Class Design
1.) Cell
* private void setNextState();
* public State getCurrentState();
* boolean changed();

2.) GUIManager
* In charge of visualization
* Switch between simulation

3.) Simulation
* Get each of the cells and step through the simulation, getting them to update
* Creates all of the cells
* Creates scene that will be used to visualize the simulation

4.) RuleKeeper
* Read XML file and get rules, pass allowed states to State, pass initial state to simulation, pass grid size and 
gameInformation to GUIManager
* Decides what next state of center cell should be when passed in 3x3 list of cells
* private void update();

5.) State
* Enumerate what states are allowed
