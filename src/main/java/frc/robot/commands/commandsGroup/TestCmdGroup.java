package frc.robot.commands.commandsGroup;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ElevatorUpBalls;
import frc.robot.commands.TestDashboardCmd;

public class TestCmdGroup extends CommandGroup {

    public TestCmdGroup() {
        addSequential(new TestDashboardCmd());
        addSequential(new ElevatorUpBalls());
    }
}