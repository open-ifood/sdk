package org.openifood.dto.marketplace;

import com.google.gson.JsonObject;
import lombok.Value;

@Value
public class SectionCard {
    String id;
    String cardType;
    JsonObject data;
}
