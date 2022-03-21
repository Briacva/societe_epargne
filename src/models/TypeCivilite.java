package models;

public enum TypeCivilite {
    // This will call enum constructor with one
    // int argument
    HOMME("M."), 
    FEMME("Mme.");
    
    // declaring private variable for getting values
    private String type;
    
    // getter method
    public String getType()
    {
        return this.type;
    }
    
    // enum constructor - cannot be public or protected
    private TypeCivilite(String type)
    {
        this.type= type;
    }
}
