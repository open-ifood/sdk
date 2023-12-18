# Creating an order

This documentation explain steps and the payload responsible for concrete create a new order.

## Properties used

```properties
ifood.sdk.product.identifier=streamfood_partner/v1.0.0
```

## 1. Creating a cart

Firstly, choose the cart items.

```java

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
class CreateOrderBeanExample {

    @Inject
    IFoodService iFoodService;

    public void createOrder() {
        String merchantId = "4a1dc2e1-9c4e-45cb-964c-7140221e9298";
        
        IFoodMerchant merchant = iFoodService.merchant(merchantId);

        List<IFoodProduct> products = merchant.products();

        List<IFoodCategory> categories = merchant.productsByCategory();

        IFoodCart cart = IFoodCart.initialize(products)
                .addItem(
                        CartItem.builder()
                                .product(IFoodProduct.fromCode("08717f80-df2f-4524-bbd1-b99e212e26fd"))
                                .note("Observation for the kitchen")
                                .quantity(1)
                                .choices(List.of(
                                        Choice.builder()
                                                .code("050501")
                                                .items(List.of(
                                                        GarnishItem.builder()
                                                                .code("f522b71d-8185-43ef-858b-f109f6cf4fd6")
                                                                .quantity(1)
                                                                .build()
                                                ))
                                                .build()
                                ))
                                .build()
                );
    }
}
```

## 2. Aggregate checkout information

```java

@ApplicationScoped
class CreateOrderBeanExample {

    @Inject
    IFoodService iFoodService;

    public void createOrder() {
        List<IFoodPaymentMethod> paymentMethods = merchant.paymentMethods();

        IFoodOrder = iFoodService.order()
                .address(
                        iFoodService.account()
                                .address()
                                .getById(821518220)
                )
                .merchant(merchant)
                .cart(cart)
                .deliveryAdditionalInfo("Additional Information for deliveryman")
                .deliveryMethod(IFoodDeliveryMethod.DEFAULT)
                .test(false)
                .paymentMethod(IFoodPaymentMethods.PIX)
                .create();
    }

}

```



