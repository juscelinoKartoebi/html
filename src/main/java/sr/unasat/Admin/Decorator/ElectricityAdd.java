package sr.unasat.Admin.Decorator;

public class ElectricityAdd extends AddDecorator{
    final String name = "ELECTRICITY";

    public ElectricityAdd(Add newAdd) {
        super(newAdd);
    }

    @Override
    public String getName(){
        return name;
    }
}
