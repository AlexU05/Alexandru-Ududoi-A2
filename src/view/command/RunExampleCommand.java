package view.command;

import controller.Controller;

public class RunExampleCommand extends Command {
    private final Controller ctr;
    public RunExampleCommand(String key, String desc, Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        try{
            ctr.allStep(); }

        catch (Exception e){
            System.out.println("Error: "+e.getMessage());}
        }
}
