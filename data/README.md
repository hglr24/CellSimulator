# Defining format of XML for each simulation

### Define probability order for each simulation:
#### Game of Life: 
1. Probability dead 
2. Probability alive
#### Segregation: 
1. Probability empty
2. Probability agent 1
3. Probability agent 2
#### Predator-Prey: 
1. probability empty
2. probability fish
3. probability shark
#### Fire: 
1. Probability empty
2. Probability tree, 
3. Probability burning
#### Percolation: 
1. Probability open
2. Probability closed

### Define parameter order for each simulation:
### Game of Life: 
None
#### Segregation:
1. Satisfaction percentage
#### Predator-Prey:
1. Fish reproduction chronons (integer)
2. Shark reproduction chronons(integer)
3. Starting shark energy
#### Fire:
1. Probability of tree catching fire w/ burning adjacent (decimal)
#### Percolation
None
#### RPS: 
1. Win Threshold

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
6. RPS:
    * 0 = Rock
    * 1 = Paper
    * 2 = Scissors

### Define how initialized
1. Put "Random" in GridConfiguration to generate states randomly based off of probabilities
2. Put "True Random" in GridConfiguration to generate states completely randomly
3. Put an integer array expressing the desired initial states of cells matching size of entered width and height

### Standardized Parameter Names
### Game of Life: 
None
#### Segregation:
1. "satisfactionPercentage"
#### Predator-Prey:
1. "fishReproductionTime"
2. "sharkReproductionTime"
3. "sharkEnergy"
#### Fire:
1. "burnProbability"
#### Percolation
None
#### RPS: 
1. "winThreshold"
