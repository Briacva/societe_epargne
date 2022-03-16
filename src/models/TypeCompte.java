package models;

public enum TypeCompte {
    // This will call enum constructor with one
    // int argument
    COURANT(0), 
    EPARGNE(1);
    
    // declaring private variable for getting values
    private int type;
    
    // getter method
    public int getType()
    {
        return this.type;
    }
    
    // enum constructor - cannot be public or protected
    private TypeCompte(int type)
    {
        this.type= type;
    }
}
