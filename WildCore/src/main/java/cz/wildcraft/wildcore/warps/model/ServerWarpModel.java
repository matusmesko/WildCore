package cz.wildcraft.wildcore.warps.model;

import org.bukkit.World;

import java.util.Date;

public class ServerWarpModel {

    private String warp_name;
    private String description;
    private String icon;
    private String world;
    private double x;
    private double y;
    private double z;

    private float pitch;
    private float yaw;
    private Date time_created;

    public ServerWarpModel(String warp_name, String description, String icon, String world, double x, double y, double z, Date time_created, float yaw, float pitch) {
        this.warp_name = warp_name;
        this.description = description;
        this.icon = icon;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.time_created = time_created;
        this.pitch = pitch;
        this.yaw = yaw;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
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

    public Date getTime_created() {
        return time_created;
    }

    public void setTime_created(Date time_created) {
        this.time_created = time_created;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }
}
