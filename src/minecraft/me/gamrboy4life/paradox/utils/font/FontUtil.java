package me.gamrboy4life.paradox.utils.font;
import java.awt.Font;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

@SuppressWarnings("NonAtomicOperationOnVolatileField")
public class FontUtil {
    public static volatile int completed;
    public static MinecraftFontRenderer normal;
    private static Font normal_;

    private static Font getFont(Map<String, Font> locationMap, String location, int size) {
        Font font = null;

        try {
            if (locationMap.containsKey(location)) {
                font = locationMap.get(location).deriveFont(Font.PLAIN, size);
            } else {
                InputStream is = Minecraft.getMinecraft().getResourceManager()
                        .getResource(new ResourceLocation("font/" + location)).getInputStream();
                font = Font.createFont(0, is);
                locationMap.put(location, font);
                font = font.deriveFont(Font.PLAIN, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", Font.PLAIN, +10);
        }

        return font;
    }

    public static boolean hasLoaded() {
        return completed >= 3;
    }

    public static void bootstrap() {
        new Thread(new Runnable() {
			@Override
			public void run() {
			    Map<String, Font> locationMap = new HashMap<String, Font>();
			    normal_ = getFont(locationMap, "keifont.ttf", 19);
			    completed++;
			}
		}).start();
        new Thread(new Runnable() {
			@Override
			public void run() {
			    Map<String, Font> locationMap = new HashMap<String, Font>();
			    completed++;
			}
		}).start();
        new Thread(new Runnable() {
			@Override
			public void run() {
			    Map<String, Font> locationMap = new HashMap<String, Font>();
			    completed++;
			}
		}).start();

        while (!hasLoaded()) {
            try {
                //noinspection BusyWait
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        normal = new MinecraftFontRenderer(normal_, true, true);
    }
}