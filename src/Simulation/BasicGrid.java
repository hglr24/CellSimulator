package Simulation;

abstract class BasicGrid<E> implements Grid<E> {

    protected GameOfLifeCell[][] cells;
    protected int height;
    protected int width;
    protected RuleSet ruleSet;

    @Override
    public boolean validLocation(Location location) {
        assert(location instanceof SquareLocation);
        SquareLocation sl = (SquareLocation) location;
        return sl.getX() >= 0 && sl.getY() >= 0 && sl.getX()< getWidth() && sl.getY() < getHeight();
    }

    @Override
    public void update(){
        for(Cell[] r: getCells()){
            for(Cell c: r){
                c.setNeighbors(this);
                c.determineState(this.getRuleSet());
            }
        }

        for(Cell[] r: getCells()){
            for(Cell c: r){
                c.update();
            }
        }
    }


    @Override
    public GameOfLifeCell[][] getCells() {
        return cells;
    }

    @Override
    public Cell getCell(Location location) {
        if (!validLocation(location) || !(location instanceof SquareLocation))
            throw new IllegalArgumentException("Invalid Location");
        SquareLocation sl = (SquareLocation) location;
        return cells[sl.getX()][sl.getY()];
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public RuleSet getRuleSet() {
        return ruleSet;
    }

}
