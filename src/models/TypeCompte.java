package models;

public enum TypeCompte {
    // This will call enum constructor with one
    // int argument
    COURANT(false), 
    EPARGNE(true);
    
    // declaring private variable for getting values
    private boolean type;
    
    // getter method
    public boolean getType()
    {
        return this.type;
    }
    
    // enum constructor - cannot be public or protected
    private TypeCompte(boolean type)
    {
        this.type= type;
    }
}
