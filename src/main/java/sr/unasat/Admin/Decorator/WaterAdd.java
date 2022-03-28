package sr.unasat.Admin.Decorator;

public class WaterAdd extends AddDecorator{
    final String name = "WATER";

    public WaterAdd(Add newAdd) {
        super(newAdd);
    }

    @Override
    public String getName(){
        return name;
    }
}
