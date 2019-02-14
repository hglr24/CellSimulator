package Simulation;

import java.util.List;

import static Simulation.PercolationState.OPEN;
import static Simulation.PercolationState.PERCOLATED;

/**
 * Percolation Rules
 */
public class PercolationRuleSet extends RuleSet {
    private boolean hasPercolated;

    @Override
    public void setParameters(double[] parameters) {
        super.setParameters(parameters);
    }

    /**
     * Rule Application. Checks to see if bottom row has any percolated cells
     * @param neighbors
     * @param baseCell
     * @param grid
     * @return
     */
    @Override
    public State applyRules(List<Cell> neighbors, Cell baseCell, Grid grid) {
        PercolationCell cell = (PercolationCell) baseCell;
        if (cell.currentState == OPEN && !hasPercolated) {
            for (Cell c : neighbors) {
                if (c.getCurrentState() == PERCOLATED) {
                    if (cell.getYPosition() == grid.getHeight() - 1) {
                        hasPercolated = true;
                    }
                    return PERCOLATED;
                }
            }
        }
        return cell.getCurrentState();
    }
}
