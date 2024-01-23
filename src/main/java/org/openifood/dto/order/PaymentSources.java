package org.openifood.dto.order;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@RequiredArgsConstructor
public class PaymentSources {

    List<PaymentSource> sources;
}
