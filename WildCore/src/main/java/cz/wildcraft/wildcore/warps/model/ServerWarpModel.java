package cz.wildcraft.wildcore.warps.model;

import java.util.Date;

public class ServerWarpModel {

    private String warp_name;
    private String description;
    private String icon;
    private String world;
    private Long x;
    private Long y;
    private Long z;
    private Date time_created;

    public ServerWarpModel(String warp_name, String description, String icon, String world, Long x, Long y, Long z, Date time_created) {
        this.warp_name = warp_name;
        this.description = description;
        this.icon = icon;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.time_created = time_created;
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

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public Long getZ() {
        return z;
    }

    public void setZ(Long z) {
        this.z = z;
    }

    public Date getTime_created() {
        return time_created;
    }

    public void setTime_created(Date time_created) {
        this.time_created = time_created;
    }
}
