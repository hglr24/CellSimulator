Cell Society
====

This project implements a cellular automata simulator.

Names: Lucas Liu, Harry Ross, Ian Hanus

## Timeline

    Start Date: 1/24/19
    
    Finish Date: 2/12/19
    
    Hours Spent: 100+

## Primary Roles
    Lucas - Simulation
    Harry - Visualization
    Ian   - Configuration

## Resources Used
    In-Class Material, Labs

## Running the Program

    Main class: UIpackage/CellSocietyMain
    
    Data files needed: Simulation Files in data folder. Css file in resources.
    
    Interesting data files: Example simulations provided in data folder. 
    Additional details concerning mappings also provided in data/README.md
    
    Known Bugs:
    
### Assumptions or Simplifications
    Predator Prey: Decided that a "group of fish in a cell" may move and be eaten at
    the same time, thus representing a school a fish being partially eaten. A fish and shark
    cannot occupy the same cell at the end of an iteration.
    
    Ant: Presumably all ants start at home base.
    
    Percolation: The simulation stops once percolation is achieved
    
    

### Features Implemented

#### Simulations Available 
    Game of Life, Fire, Percolation, Segregation, Predator Prey, Rock Paper Scissors, Ant
    
#### Grid
    Variety of neigborhood types
    Variety of grid shapes
    Finite and Toroidal Options

#### Errors Checked For in Configuration
    Parameters of the wrong type
    Wrong number of parameters
    Probabilities must be doubles and add up to approximately 1
    All fields in the XML must be filled out
    Strings in xml must map to SimulationType, Shape, Edge, and Neighbor enums

## Impressions
    Lucas: Good first group project
    Ian: Great team, interesting and challenging project
