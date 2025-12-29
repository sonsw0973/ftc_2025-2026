package org.firstinspires.ftc.teamcode;

import com.pedropathing.geometry.Pose;

public class Turret_Tracking {
    public Turret_Tracking() {}

    private final double TICKS_PER_REV = 537.7;
    private final double GEAR_RATIO = 1.0;

    private final Pose RED_GOAL = new Pose(130, 135, 0);
    private final Pose BLUE_GOAL = new Pose(14, 130, 0);

    public int fix_to_goal_RED(Pose robot_pos) {
        double dy = RED_GOAL.getY() - robot_pos.getY();
        double dx = RED_GOAL.getX() - robot_pos.getX();
        double target_Rad = Math.atan2(dy, dx) - robot_pos.getHeading(); //모두 rad값
        return RadToTicks(target_Rad); //목표 엔코더 tick값 반환
    }

    public int fix_to_goal_BLUE(Pose robot_pos) {
        double dy = BLUE_GOAL.getY() - robot_pos.getY();
        double dx = BLUE_GOAL.getX() - robot_pos.getX();
        double target_Rad = Math.atan2(dy, dx) - robot_pos.getHeading(); //모두 rad값 반환 ㅇㅇ
        return RadToTicks(target_Rad);
    }

        int RadToTicks(double Rad){
            while (Rad > Math.PI) {
                Rad -= 2 * Math.PI;
            }
            while (Rad < -Math.PI) {
                Rad += 2 * Math.PI;
            }

            double ticks = (Rad / (2 * Math.PI)) * TICKS_PER_REV * GEAR_RATIO;

            return (int) ticks;
        }

    }
