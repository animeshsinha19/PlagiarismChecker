package server.plagiarism.engine.parsetreebased.nodes;

/**

 Constructor template for Function:
 new Function (n)
 Interpretation:
 n is the name of the variable
 */


public class Function {

    // Constructor for Variable

    public Function(String name){
        this.name = name;
    }

    // Returns the name of the Function
    public String getName(){
        return name;
    }


    private String name;
}

