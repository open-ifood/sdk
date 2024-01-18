package org.openifood.dto.marketplace;

import lombok.Value;

import java.util.List;

@Value
public class HomeSection {
    String id;
    String type;
    List<SectionCard> cards;
}
