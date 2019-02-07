# Defining format of XML for each simulation

### Define parameter order for each simulation:
1. Game of Life: Probability dead, probability alive
2. Segregation: Satisfaction percentage (decimal), probability empty, probability agent 1, probability agent 2
3. Predator-Prey: fish reproduction chronons (integer), shark reproduction chronons(integer),
starting shark energy, probability empty, probability fish, probability shark
4. Fire: Probability of tree catching fire w/ burning adjacent (decimal), probability empty, probability tree, 
probability burning
5. Percolation: Probability open, probability closed

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


