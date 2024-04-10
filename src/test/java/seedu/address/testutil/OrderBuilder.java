package seedu.address.testutil;

import java.util.HashMap;
import java.util.Map;

import seedu.address.model.order.Order;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;
import seedu.address.model.order.stage.StageContext;
import seedu.address.model.order.stage.StageState;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Order objects.
 * Example usage: <br>
 *     {@code Order order = new OrderBuilder().withIndex(1).withProductQuantity("Cupcakes", "2").build();}
 */
public class OrderBuilder {
    public static final int DEFAULT_ID = 1;
    public static final Person DEFAULT_PERSON = TypicalPersons.ALICE;
    public static final StageContext DEFAULT_STAGE_CONTEXT = new StageContext();

    private int id;
    private Map<Product, Quantity> productMap;
    private Person person;
    private StageContext stageContext;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        id = DEFAULT_ID;
        productMap = new HashMap<>();
        person = DEFAULT_PERSON;
        stageContext = DEFAULT_STAGE_CONTEXT;
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        productMap = orderToCopy.getProductMap();
        id = orderToCopy.getId();
        person = orderToCopy.getCustomer();
        stageContext = orderToCopy.getStageContext();
    }

    /**
     * Sets the index of the {@code Order} that we are building.
     */
    public OrderBuilder withIndex(int id) {
        this.id = id;
        return this;
    }

    /**
     * Adds to the productMap of the {@code Order} that we are building.
     *
     * @param product the name of the product to add
     * @param quantity the number of the products to add
     */
    public OrderBuilder withProductQuantity(String product, String quantity) {
        Product productToAdd = new Product(product);
        Quantity quantityToAdd = new Quantity(Integer.parseInt(quantity));
        this.productMap.put(productToAdd, quantityToAdd);
        return this;
    }

    /**
     * Adds to the productMap of the {@code Order} that we are building.
     *
     * @param product the name of the product to add
     * @param quantity the number of the products to add
     */
    public OrderBuilder withProductPriceQuantity(String product, String cost, String sales, String quantity) {
        Product productToAdd = new Product(product, cost, sales);
        Quantity quantityToAdd = new Quantity(Integer.parseInt(quantity));
        this.productMap.put(productToAdd, quantityToAdd);
        return this;
    }

    /**
     * Sets the {@code Person} of the {@code Order} that we are building.
     */
    public OrderBuilder withPerson(Person person) {
        this.person = person;
        return this;
    }

    /**
     * Sets the {@code StageContext} of the {@code Order} that we are building.
     */
    public OrderBuilder withStage(StageState state) {
        this.stageContext = new StageContext(state);
        return this;
    }

    /**
     * Builds the order
     *
     * @return the order
     */
    public Order build() {
        Order order = new Order(this.id);
        order.setProductMap(this.productMap);
        order.setStageContext(this.stageContext);
        return order;
    }
}
