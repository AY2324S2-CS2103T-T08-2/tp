package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.order.Deadline;
import seedu.address.model.order.Order;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Order> PREDICATE_SHOW_ALL_ORDERS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Product> PREDICATE_SHOW_ALL_PRODUCTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the given order {@code target} with {@code editedPerson}.
     * {@code target} must exist in the order list.
     */
    void setOrder(Order target, Order editedOrder);

    /**
     * Adds the given order {@code newOrder} to the given person {@code person} into the orderlist.
     * @param newOrder Order object to be added to the orderlist.
     * @param person Person object to be attached to the order, which will be added to the orderlist.
     */
    void addOrder(Order newOrder, Person person);

    /**
     * Deletes the given order.
     * The order must exist in the address book.
     */
    void deleteOrder(int id);

    void completeOrder(int id);

    /**
     * Sets the quantity of the product in the order.
     * If the product is not in the order yet, add the product and set its quantity.
     * @param currProduct Product of which quantity to be editted.
     * @param newQuantity new Quantity of the specified product.
     */
    Order editOrder(Order target, Product currProduct, Quantity newQuantity);

    /**
     * Edits the deadline of an existing order in the address book.
     * This method updates the deadline of a specified order with a new deadline.
     * @param target The order whose deadline is to be updated. Must not be null.
     * @param deadline The new deadline to set for the order. Must not be null.
     * @return The updated order with the new deadline set.
     */
    Order editOrderDeadline(Order target, Deadline deadline);

    /**
     * Advances the target order to the next stage.
     *
     * @param target order to update
     * @return a copy of modified order.
     */
    Order goToNextStage(Order target);

    /**
     * Method to check if the orderId has an Order in the addressbook.
     *
     * @param orderId orderId to check if an Order exists in the addressbook
     * @return boolean value if Order exists for this orderId
     */
    boolean orderIdExists(int orderId);

    /**
     * Method to clear the completed order list in the addressbook.
     */
    void clearCompletedOrders();

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Finds a person based on their phone number.
     *
     * @param phoneNumber The phone number to search for.
     * @return An Optional containing the found Person, or an empty Optional if no person with the phone number exists.
     */
    Optional<Person> findPersonByPhoneNumber(String phoneNumber);

    Order findOrderByIndex(int id);
    /**
     * Returns an unmodifiable view of the filtered person list.
     *
     * @return an unmodifiable view of the filtered person list.
     */
    ObservableList<Order> getFilteredOrderList();
    /**
     * Updates the filter of the filtered order list to filter by the given {@code predicate}.
     *
     * @param predicate predicate to update the filtered order list with.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOrderList(Predicate<Order> predicate);

    /**
     * Returns the number of orders in the order list.
     *
     * @return the number of orders in the order list
     */
    int getOrderListSize();

    void clearOrderFilter();

    /**
     * Returns true if a product with the same identity as {@code product} exists in the menu.
     */
    boolean hasProduct(Product product);

    /**
     * Returns true if an order exists in the OrderList.
     */
    boolean hasOrder(Order order);

    /**
     * Returns an order given its order index in the order list.
     */
    Order getOrder(int id);

    /**
     * Adds the given product.
     * {@code person} must not already exist in the menu.
     */
    void addProduct(Product product);

    /**
     * Deletes the given product.
     * The product must exist in the menu.
     */
    void deleteProduct(Product target);

    /**
     * Replaces the given product {@code target} with {@code editedProduct}.
     * {@code target} must exist in the menu.
     * The person identity of {@code editedProduct} must not be the same as another existing product in the menu.
     */
    void setProduct(Product target, Product editedProduct);

    /**
     * Returns an unmodifiable view of the filtered menu list.
     *
     * @return an unmodifiable view of the filtered menu list.
     */
    ObservableList<Product> getFilteredMenuList();

    /**
     * Updates the filter of the filtered menu list to filter by the given {@code predicate}.
     *
     * @param predicate predicate to update the filtered menu list with.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMenuList(Predicate<Product> predicate);

    /**
     * Gets a product in the ProductMenu by its zero-based Index.
     *
     * @param id the zero-based Index of the product to find for
     * @return the product to search for
     */
    Product findProductByIndex(int id);

    /**
     * Refreshes the order list to display up-to-date customer information in orders
     * after customer is edited.
     *
     * @param oldCustomer customer before the change.
     * @param newCustomer customer after the change.
     */
    void refreshCustomer(Person oldCustomer, Person newCustomer);
}
