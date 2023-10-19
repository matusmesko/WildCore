package cz.wildcraft.wildcore.warps.model;

import java.util.Date;

public class PlayerWarpModel {

    private String warp_name;
    private String description;
    private String owner;
    private int visited;
    private String world;
    private String icon;
    private String time_created;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public PlayerWarpModel(String warp_name, String description, String owner, int visited, String world, String icon, String time_created, double x, double y, double z, float yaw, float pitch) {
        this.warp_name = warp_name;
        this.description = description;
        this.owner = owner;
        this.visited = visited;
        this.world = world;
        this.icon = icon;
        this.time_created = time_created;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public String getWarp_name() {
        return warp_name;
    }

    public void setWarp_name(String warp_name) {
        this.warp_name = warp_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getVisited() {
        return visited;
    }

    public void setVisited(int visited) {
        this.visited = visited;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTime_created() {
        return time_created;
    }

    public void setTime_created(String time_created) {
        this.time_created = time_created;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }
}
