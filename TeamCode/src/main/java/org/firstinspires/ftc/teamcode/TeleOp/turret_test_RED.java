package org.firstinspires.ftc.teamcode.TeleOp;

import org.firstinspires.ftc.teamcode.Turret_Tracking;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class turret_test_RED extends LinearOpMode {

    @Override

    public void runOpMode() throws InterruptedException { //init
        DcMotor turret = hardwareMap.dcMotor.get("TR");

        turret.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Turret_Tracking tracking = new Turret_Tracking();

        waitForStart(); //end of init

        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {

            follower.update();

            Pose current_robot_pos = follower.getPose();

            if (check_shooting_zone(current_robot_pos)) {
                turret.setTargetPosition(tracking.fix_to_goal_RED(current_robot_pos));
                turret.setPower(1);
                turret.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
        }
    }

    public boolean check_shooting_zone(Pose pose) {
        if (pose.getY() >= Math.abs(pose.getX() - 72) + 72) return true;  // Y >= |x-72| + 72
        if (pose.getY() <= -Math.abs(pose.getX() - 72) + 72) return true; // Y <= -|x-72| + 72
        return false;
    }

}


