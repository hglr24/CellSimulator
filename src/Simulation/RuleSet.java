package Simulation;

import java.util.List;

interface RuleSet{
    State applyRules(List<Cell> neighbors, Cell cell, Grid grid);
}
