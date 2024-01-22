package org.openifood.dto.marketplace.response;

import lombok.Builder;
import lombok.Value;
import org.openifood.dto.marketplace.HomeSection;

import java.util.List;

@Value
@Builder
public class HomeResponse {
    /** UUID_V4 representing the current filter home page */
    String id;
    String alias;
    List<HomeSection> sections;
}
