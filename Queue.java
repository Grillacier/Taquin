public class Queue{

  private CellQueue courant;

  public Queue(Plateau p){
    this.courant = new CellQueue(p);
  }
/**Add a cell at the end of the Queue
*
*@param p Board added to the new last cell
 */
  public void addPlateau(Plateau p){
    this.courant.addCell(p);

  }
  public boolean hasNext(){
    if(this.courant.getSuivant() == null){
      return false;
    }
    return true;
  }
  /** Extract the board of the current cell and set the current cell to the next one
  *
  *@return The board of the Cell
  */
  public Plateau extract(){
    CellQueue cible = this.courant;
    this.courant = this.courant.getSuivant();
    return cible.getPlateau();
  }
}
