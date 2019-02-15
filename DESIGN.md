# Design
               
               ## High Level
               
               The goal was to create a cell simulation project that could easily extended to simulate 
               a wide variety of cellular automata based simulations. This involves providing an XML template for desired initial states, a GUI that 
               effective visualizes simulations and allows users to interact with various parameters, and building a back end that allows for an easy
               addition of new simulations, neighborhood types, various grid changes, cell types, rules, parameters, etc..
               
               ## Adding new features
               
               ### Simulation
               A completely new simulation requires creating a subclass implementation for a State, Grid,  Cell, and RuleSet that inherits from the 
               existing interface/hierarchy. Specific details such as Grid type and Neighborhood type can be chosen here. The "rules" of the simulation
               mut be specified in the RuleSet, and the exact neighborhood finding algorithm must be implemented in the grid. Any additional special 
               details such as additional parameters must be specified in the Cell and RuleSet. The State enumeration specifies which states cells can be in.
               
               ### Configuration
               
               ### Visualization
               
               
               ## Assumptions 