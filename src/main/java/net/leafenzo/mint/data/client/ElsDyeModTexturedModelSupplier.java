/*
 * SOURCES:
 * DrGames - https://github.com/drgmes/doctor-who-mod/blob/bc8c0a8bf55a969ce209dc4f52eacec1e5715376/fabric/src/main/java/net/drgmes/dwm/fabric/helpers/ModelHelper.java#L142
 */

package net.leafenzo.mint.data.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.data.client.SimpleModelSupplier;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class ElsDyeModTexturedModelSupplier extends SimpleModelSupplier {
    private final Map<String, Identifier> textures = new HashMap<>();
    private RenderLayer renderLayer;

    public ElsDyeModTexturedModelSupplier(Identifier parent) {
        super(parent);
    }

    @Override
    public JsonElement get() {
        JsonObject jsonObject = (JsonObject) super.get();
        JsonObject texturesJsonObject = new JsonObject();
        if (this.renderLayer == RenderLayer.getTranslucent()) jsonObject.addProperty("render_type", "translucent");
        textures.forEach((textureName, id) -> texturesJsonObject.addProperty(textureName, id.toString()));
        jsonObject.add("textures", texturesJsonObject);
        return jsonObject;
    }

    public ElsDyeModTexturedModelSupplier addTexture(String textureName, Identifier id) {
        this.textures.put(textureName, id);
        return this;
    }

    public ElsDyeModTexturedModelSupplier setRenderType(RenderLayer renderLayer) {
        this.renderLayer = renderLayer;
        return this;
    }
}
