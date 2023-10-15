package cz.wildcraft.wildcore.utils;

import org.bukkit.Bukkit;

public enum Version {
    v1_7_R1, v1_7_R2, v1_7_R3, v1_7_R4, v1_8_R1, v1_8_R2, v1_8_R3, v1_9_R1, v1_9_R2, v1_10_R1, v1_11_R1, v1_12_R1, v1_13_R1, v1_13_R2, v1_13_R3, v1_14_R1, v1_14_R2, v1_15_R1, v1_15_R2, v1_16_R1, v1_16_R2, v1_16_R3, v1_17_R1, v1_17_R2, v1_18_R1, v1_18_R2, v1_19_R1, v1_19_R2, v1_20_R1, v1_20_R2;

    private static Version currentVersion;

    private Integer value;

    static {
        currentVersion = null;
        loadCurrentVersion();
    }

    public static void loadCurrentVersion() {
        if (currentVersion != null)
            return;
        String[] packagePathComponents = Bukkit.getServer().getClass().getPackage().getName().split("\\.");
        String currentVersionName = packagePathComponents[packagePathComponents.length - 1];
        Version[] arrayOfVersion;
        int i;
        byte b;
        for (arrayOfVersion = values(), i = arrayOfVersion.length, b = 0; b < i; ) {
            Version version = arrayOfVersion[b];
            if (!version.name().equalsIgnoreCase(currentVersionName)) {
                b++;
                continue;
            }
            currentVersion = version;
        }
    }

    public static boolean isCurrentVersionLowerThan(Version checkedVersion) {
        return (currentVersion.getValue().intValue() < checkedVersion.getValue().intValue());
    }

    Version() {
        try {
            this.value = Integer.valueOf(name().replaceAll("[^\\d.]", ""));
        } catch (Exception exception) {}
    }

    public Integer getValue() {
        return this.value;
    }
}

