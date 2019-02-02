# Defining format of XML for each simulation

### Define parameter order for each simulation:
1. Game of Life: No parameters
2. Segregation: Satisfaction percentage (decimal)
3. Predator-Prey: fish reproduction chronons (integer), shark reproduction chronons(integer),
starting shark energy
4. Fire: Probability of tree catching fire w/ burning adjacent (decimal)
5. Percolation: Probability of cell being open

### Define mapping of states for each simulation:
1. Game of Life: 
    * 0 = Dead
    * 1 = Alive
2. Segregation: 
    * 0 = Empty
    * 1 = Agent 1
    * 2 = Agent 2
3. Predator-Prey:
    * 0 = Empty
    * 1 = Fish
    * 2 = Shark
4. Fire:
    * 0 = Empty
    * 1 = Tree
    * 2 = Burning
5. Percolation:
    * 0 = Open
    * 1 = Full
    * 2 = Blocked


