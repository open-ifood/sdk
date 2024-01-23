package org.openifood.dto.order;

import lombok.Value;

import java.util.List;

@Value
public class Customer {
    Long id;
    Long accountId;
    String uuid;
    String email;
    String companyGroup;
    List<String> tags;
    String name;
    String cpf;
    Integer signUpDate;
    String country;
    String locale;
    Boolean active;
    String tenantId;
    List<Phone> phones;
}
