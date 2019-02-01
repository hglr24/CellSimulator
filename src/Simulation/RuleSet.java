package Simulation;

import java.util.List;

interface RuleSet {
    State determineState(List<Cell> neighbors, Location location);
}
