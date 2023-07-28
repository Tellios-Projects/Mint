package net.leafenzo.mint.util;

import com.google.common.base.Preconditions;
import net.minecraft.block.MapColor;

public class ModMapColor {




    private MapColor(int id, int color) {
        if (id < 0 || id > 63) {
            throw new IndexOutOfBoundsException("Map colour ID must be between 0 and 63 (inclusive)");
        }
        this.id = id;
        this.color = color;
        MapColor.COLORS[id] = this;
    }

    public int getRenderColor(MapColor.Brightness brightness) {
        if (this == CLEAR) {
            return 0;
        }
        int i = brightness.brightness;
        int j = (this.color >> 16 & 0xFF) * i / 255;
        int k = (this.color >> 8 & 0xFF) * i / 255;
        int l = (this.color & 0xFF) * i / 255;
        return 0xFF000000 | l << 16 | k << 8 | j;
    }

    public static MapColor get(int id) {
        Preconditions.checkPositionIndex(id, COLORS.length, "material id");
        return MapColor.getUnchecked(id);
    }

    private static MapColor getUnchecked(int id) {
        MapColor mapColor = COLORS[id];
        return mapColor != null ? mapColor : CLEAR;
    }

    public static int getRenderColor(int colorByte) {
        int i = colorByte & 0xFF;
        return MapColor.getUnchecked(i >> 2).getRenderColor(MapColor.Brightness.get(i & 3));
    }

    public byte getRenderColorByte(MapColor.Brightness brightness) {
        return (byte)(this.id << 2 | brightness.id & 3);
    }

    public static enum Brightness {
        LOW(0, 180),
        NORMAL(1, 220),
        HIGH(2, 255),
        LOWEST(3, 135);

        private static final MapColor.Brightness[] VALUES;
        public final int id;
        public final int brightness;

        private Brightness(int id, int brightness) {
            this.id = id;
            this.brightness = brightness;
        }

        public static MapColor.Brightness validateAndGet(int id) {
            Preconditions.checkPositionIndex(id, VALUES.length, "brightness id");
            return MapColor.Brightness.get(id);
        }

        static MapColor.Brightness get(int id) {
            return VALUES[id];
        }

        static {
            VALUES = new MapColor.Brightness[]{LOW, NORMAL, HIGH, LOWEST};
        }
    }
}
