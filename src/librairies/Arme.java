package librairies;

import java.util.HashMap;
import java.util.Map;

public abstract class Arme {
    public static Map<String, Map<String, Float>> efficacites = new HashMap<String, Map<String, Float>>() {
        {
            put("Mlegere", new HashMap<String, Float>() {
                {
                    put("Infanterie", 0.6f);
                    put("Bazooka", 0.55f);
                    put("Tank", 0.15f);
                    put("DCA", 0.1f);
                    put("Helico", 0.3f);
                    put("Bombardier", 0.0f);
                    put("Convoit", 0.4f);
                }
            });
            put("Canon", new HashMap<String, Float>() {
                {
                    put("Infanterie", 0.45f);
                    put("Bazooka", 0.45f);
                    put("Tank", 0.55f);
                    put("DCA", 0.6f);
                    put("Helico", 0.3f);
                    put("Bombardier", 0.0f);
                    put("Convoit", 0.7f);
                }
            });
            put("Mlourde", new HashMap<String, Float>() {
                {
                    put("Infanterie", 1f);
                    put("Bazooka", 0.8f);
                    put("Tank", 0.3f);
                    put("DCA", 0.3f);
                    put("Helico", 1.1f);
                    put("Bombardier", 0.7f);
                    put("Convoit", 0.5f);
                }
            });
            put("Missiles", new HashMap<String, Float>() {
                {
                    put("Infanterie", 0.5f);
                    put("Bazooka", 0.5f);
                    put("Tank", 0.7f);
                    put("DCA", 0.4f);
                    put("Helico", 0.7f);
                    put("Bombardier", 0.7f);
                    put("Convoit", 0.7f);
                }
            });
            put("Bombes", new HashMap<String, Float>() {
                {
                    put("Infanterie", 1f);
                    put("Bazooka", 1f);
                    put("Tank", 1f);
                    put("DCA", 0.7f);
                    put("Helico", 0.0f);
                    put("Bombardier", 0.0f);
                    put("Convoit", 1f);
                }
            });
        }
    };
}
