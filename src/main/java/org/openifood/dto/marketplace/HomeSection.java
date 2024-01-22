package org.openifood.dto.marketplace;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class HomeSection {
    String id;
    String type;
    List<SectionCard> cards;
}
