package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.order.Deadline;
import seedu.address.model.order.Order;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    //need to include a means to pass this here from constructor
    private final FilteredList<Order> filteredOrders;
    private final FilteredList<Product> filteredMenu;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredOrders = new FilteredList<>(this.addressBook.getOrderList());
        filteredMenu = new FilteredList<>(this.addressBook.getMenuList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return addressBook.hasOrder(order);
    }

    @Override
    public Order getOrder(int id) {
        return addressBook.getOrder(id);
    }

    @Override
    public void addOrder(Order newOrder, Person person) {
        newOrder.setCustomer(person);
        addressBook.addOrder(newOrder);
        updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
    }

    @Override
    public void deleteOrder(int id) {
        addressBook.removeOrder(id);
    }

    @Override
    public void completeOrder(int id) {
        addressBook.completeOrder(id);
    }

    /**
     * Replaces the given order {@code target} with {@code editedPerson}.
     * {@code target} must exist in the order list.
     */
    public void setOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);

        addressBook.setOrder(target, editedOrder);
    }

    @Override
    public Order editOrder(Order target, Product currProduct, Quantity newQuantity) {
        requireAllNonNull(target, currProduct, newQuantity);

        return addressBook.editOrder(target, currProduct, newQuantity);
    }

    @Override
    public Order editOrderDeadline(Order target, Deadline deadline) {
        requireAllNonNull(target, deadline);

        return addressBook.editOrderDeadline(target, deadline);
    }

    @Override
    public Order goToNextStage(Order target) {
        requireNonNull(target);

        return addressBook.goToNextStage(target);
    }

    public boolean orderIdExists(int orderId) {
        return addressBook.orderIdExists(orderId);
    }

    @Override
    public void clearCompletedOrders() {
        addressBook.clearCompletedOrders();
    }

    @Override
    public int getOrderListSize() {
        return addressBook.getOrderListSize();
    }

    @Override
    public boolean hasProduct(Product product) {
        requireNonNull(product);
        return addressBook.hasProduct(product);
    }

    @Override
    public void addProduct(Product product) {
        addressBook.addProduct(product);
        updateFilteredMenuList(PREDICATE_SHOW_ALL_PRODUCTS);
    }

    @Override
    public void deleteProduct(Product product) {
        addressBook.removeProduct(product);
    }

    @Override
    public void setProduct(Product target, Product editedProduct) {
        requireAllNonNull(target, editedProduct);

        addressBook.setProduct(target, editedProduct);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public Optional<Person> findPersonByPhoneNumber(String phoneNumber) {
        ObservableList<Person> persons = getFilteredPersonList();
        for (Person person : persons) {
            if (person.getPhone().value.equals(phoneNumber)) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    //=========== Filtered Order List Accessors =============================================================
    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return filteredOrders;
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {
        requireNonNull(predicate);
        filteredOrders.setPredicate(predicate);
    }

    @Override
    public Order findOrderByIndex(int id) {
        return addressBook.findOrderByIndex(id);
    }

    @Override
    public void clearOrderFilter() {
        filteredOrders.setPredicate(null); // Passing null removes the filter
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons)
                && filteredOrders.equals(otherModelManager.filteredOrders);
    }

    //=========== Filtered Menu List Accessors =============================================================
    @Override
    public ObservableList<Product> getFilteredMenuList() {
        return filteredMenu;
    }

    @Override
    public void updateFilteredMenuList(Predicate<Product> predicate) {
        requireNonNull(predicate);
        filteredMenu.setPredicate(predicate);
    }

    @Override
    public Product findProductByIndex(int id) {
        return addressBook.findProductByIndex(id);
    }

    public void refreshCustomer(Person oldCustomer, Person newCustomer) {
        addressBook.refreshCustomer(oldCustomer, newCustomer);
    }
}
