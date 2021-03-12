public class CellQueue{

  private Plateau modele;
  private CellQueue suivant;

  public CellQueue(Plateau p){
    this.modele = p;

  }

  public CellQueue getSuivant(){

    return this.suivant;
  }

  public void setSuivant(CellQueue c){
    this.suivant = c;
  }

  public Plateau getPlateau(){
    return this.modele;
  }
  /**Add a cell to the end of the Queue
  *
  *@param p The board added on the new cell
  */
  public void addCell(Plateau p){
    if(this.suivant == null){
      this.suivant = new CellQueue(p);
    }
    else{
      this.suivant.addCell(p);
    }

  }
}
