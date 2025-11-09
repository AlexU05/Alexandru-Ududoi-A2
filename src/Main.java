import controller.Controller;
import controller.ControllerImp1;
import model.expression.ArithExp;
import model.expression.ValueExp;
import model.expression.VarExp;
import model.statement.*;
import model.type.Type;
import model.value.BooleanValue;
import model.value.IntegerValue;
import model.value.StringValue;
import repository.Repository;
import repository.RepositoryImp1;
import state.*;
import view.TextMenu;
import view.ViewImp1;
import view.command.ExitCommand;
import view.command.RunExampleCommand;


class Main {
    public static void main(String[] args) {
            Statement ex1 = new CompoundStatement(
                    new VariableDeclarationStatement(Type.INTEGER, "v"),
                    new CompoundStatement(
                            new AssignmentStatement("v", new ValueExp(new IntegerValue(2))),
                            new PrintStatement(new VarExp("v"))
                    )
            );
            ExecutionStack exeStack1 = new ListExecutionStack();
            exeStack1.push(ex1);
            SymbolTable symTable1 = new MapSymbolTable();
            Out out1 = new ListOut();
            FileTable fileTable1 = new MapFileTable();
            ProgramState prg1 = new ProgramState(exeStack1, symTable1, out1, fileTable1);
            Repository repo1 = new RepositoryImp1(prg1, "log1.txt");
            Controller ctr1 = new ControllerImp1(repo1);

            Statement ex2 = new CompoundStatement(
                    new VariableDeclarationStatement(Type.INTEGER, "a"),
                    new CompoundStatement(
                            new VariableDeclarationStatement(Type.INTEGER, "b"),
                            new CompoundStatement(
                                    new AssignmentStatement("a", new ArithExp(
                                            new ValueExp(new IntegerValue(2)),
                                            new ArithExp(
                                                    new ValueExp(new IntegerValue(3)),
                                                    new ValueExp(new IntegerValue(5)),
                                                    "*"
                                            ),
                                            "+"
                                    )),
                                    new CompoundStatement(
                                            new AssignmentStatement("b", new ArithExp(
                                                    new VarExp("a"),
                                                    new ValueExp(new IntegerValue(1)),
                                                    "+"
                                            )),
                                            new PrintStatement(new VarExp("b"))
                                    )
                            )
                    )
            );
            ExecutionStack exeStack2 = new ListExecutionStack();
            exeStack2.push(ex2);
            SymbolTable symTable2 = new MapSymbolTable();
            Out out2 = new ListOut();
            FileTable fileTable2 = new MapFileTable();
            ProgramState prg2 = new ProgramState(exeStack2, symTable2, out2, fileTable2);
            Repository repo2 = new RepositoryImp1(prg2, "log2.txt");
            Controller ctr2 = new ControllerImp1(repo2);

            Statement ex3 = new CompoundStatement(
                    new VariableDeclarationStatement(Type.BOOLEAN, "a"),
                    new CompoundStatement(
                            new VariableDeclarationStatement(Type.INTEGER, "v"),
                            new CompoundStatement(
                                    new AssignmentStatement("a", new ValueExp(new BooleanValue(true))),
                                    new CompoundStatement(
                                            new IfStatement(
                                                    new VarExp("a"),
                                                    new AssignmentStatement("v", new ValueExp(new IntegerValue(2))),
                                                    new AssignmentStatement("v", new ValueExp(new IntegerValue(3)))
                                            ),
                                            new PrintStatement(new VarExp("v"))
                                    )
                            )
                    )
            );
            ExecutionStack exeStack3 = new ListExecutionStack();
            exeStack3.push(ex3);
            SymbolTable symTable3 = new MapSymbolTable();
            Out out3 = new ListOut();
            FileTable fileTable3 = new MapFileTable();
            ProgramState prg3 = new ProgramState(exeStack3, symTable3, out3, fileTable3);
            Repository repo3 = new RepositoryImp1(prg3, "log3.txt");
            Controller ctr3 = new ControllerImp1(repo3);


            Statement fileExample = new CompoundStatement(
                    new VariableDeclarationStatement(Type.STRING, "varf"),
                    new CompoundStatement(
                            new AssignmentStatement("varf", new ValueExp(new StringValue("test.in"))),
                            new CompoundStatement(
                                    new OpenRFileStatement(new VarExp("varf")),
                                    new CompoundStatement(
                                            new VariableDeclarationStatement(Type.INTEGER, "varc"),
                                            new CompoundStatement(
                                                    new ReadFileStatement(new VarExp("varf"), "varc"),
                                                    new CompoundStatement(
                                                            new PrintStatement(new VarExp("varc")),
                                                            new CompoundStatement(
                                                                    new ReadFileStatement(new VarExp("varf"), "varc"),
                                                                    new CompoundStatement(
                                                                            new PrintStatement(new VarExp("varc")),
                                                                            new CloseRFileStatement(new VarExp("varf"))
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            );

            ExecutionStack exeStack4 = new ListExecutionStack();
            exeStack4.push(fileExample);
            SymbolTable symTable4 = new MapSymbolTable();
            Out out4 = new ListOut();
            FileTable fileTable4 = new MapFileTable();
            ProgramState prg4 = new ProgramState(exeStack4, symTable4, out4, fileTable4);
            Repository repo4 = new RepositoryImp1(prg4, "log4.txt");
            Controller ctr4 = new ControllerImp1(repo4);


            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCommand("0", "exit"));
            menu.addCommand(new RunExampleCommand("1", "ex1", ctr1));
            menu.addCommand(new RunExampleCommand("2", "ex2", ctr2));
            menu.addCommand(new RunExampleCommand("3", "ex3", ctr3));
            menu.addCommand(new RunExampleCommand("4", "file example", ctr4));
            menu.show();
        }
    }
