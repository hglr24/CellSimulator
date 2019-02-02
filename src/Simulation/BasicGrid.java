package Simulation;

abstract class BasicGrid<E> implements Grid<E> {

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

}
