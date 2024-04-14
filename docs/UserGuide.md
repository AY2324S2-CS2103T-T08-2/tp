---
layout: page
title: User Guide
---

## Introduction <a name="introduction"></a>

A warm welcome to all home bakers to our Strack.io User Guide! Strack.io is designed to cater to all your needs, be it creating your menu, recording of customer information, or even tracking of orders. Strack.io aims to help you reduce the number of errors and improve your overall quality of life when tracking and managing orders. Here, you will find all the necessary information you need to kickstart your Strack.io journey. Don't forget to **make snacks, keep track, and use Strack!**

--------------------------------------------------------------------------------------------------------------------

## Purpose <a name="purpose"></a>

This User Guide serves both as a tutorial and as an instruction manual to our Strack.io application.
To start learning using our app, you can take a look at the Quick Start section, and try out the different commands for yourself.
If you are not sure about the format of any command, you can refer to the Features section for a detailed explanation.
At the end of this User Guide, we have also provided a glossary of terminologies and a succinct list of commands for your easy reference during daily usage.

As you read through this user guide, you will learn about:
* How to use different commands provided by our app, illustrated by comprehensive examples
* Constraints of input format in order for our app to run normally
* Auxiliary capabilities of Strack.io application, including saving and exportation of data.

## Overview

Strack.io is a **desktop app for home bakers to manage contacts of their customers, optimized for use via 
a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type
fast, Strack.io can get your customer and order management tasks done faster than traditional GUI apps.

# Table of Contents
1. [Introduction](#introduction)
2. [Purpose](#purpose)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `strackio.jar` from [here](https://github.com/AY2324S2-CS2103T-T08-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Strack.io.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar strackio.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all customers and orders.
   
   * `menu pn/Cupcake pc/2 ps/5` : Adds a product named `Cupcake` to the menu.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the customer list.

   * `delete c/3` : Deletes the 3rd contact shown in the current customer list.

   * `clear` : Deletes all customers contacts, orders and products in Strack.io.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help: `help`

You can show a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a product to the menu: `menu`

You can start off by adding a product to be displayed on the product menu.

Format: `menu pn/PRODUCT_NAME pc/PRODUCT_COSTS ps/PRODUCT_SALES`

* `PRODUCT_COSTS` refer to the costs incurred to make the product.
* `PRODUCT_SALES` refer to how much the product is being sold for.
* `PRODUCT_NAME` refer to the name of the product being sold.
* The order of which `pc/PRODUCT_COSTS`, `ps/PRODUCT_SALES` or `pn/PRODUCT_NAME`
is being input does not matter.
* Decimals are supported.

Example:
* `menu pn/Cupcake pc/1 ps/3.50` adds the product `Cupcake` to the menu.

![add product command](images/AddProductCommand.png)
<br>![add product command result](images/AddProductCommandResult.png)

### Editing a product on the menu: `edit`

You can edit an existing product on the product menu.

Format: `edit m/MENU_ID [pn/PRODUCT_NAME] [pc/PRODUCT_COSTS] [ps/PRODUCT_SALES]`

* Edits the product of the specified `MENU_ID`. The `MENU_ID` refers to the number reflected on the product menu beside the product name. The `MENU_ID` **must be a positive integer** 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

<div markdown="span" class="alert alert-primary">:heavy_exclamation_mark: **Note:**  
Editing a product on the menu will not update the products on existing orders.
</div>

Example:
* `edit m/1 pn/Pie` Edits the product name of the product with `MENU_ID` of `1` to be `Pie`.

![edit product command](images/EditProductCommand.png)
<br>![edit product command result](images/EditProductCommandResult.png)

### Deleting a product from the menu: `delete`

You can delete the specified product from the menu.

Format: `delete m/MENU_ID`

* Deletes the product of the specified `MENU_ID`.
* The `MENU_ID` refers to the number reflected on the product menu beside the product name.
* The `MENU_ID` **must be a positive integer** 1, 2, 3, ...

Example:
* `delete m/1` deletes the product on the menu with a `MENU_ID` of `1`.

![delete product command](images/DeleteProductCommand.png)
<br>![delete product command result](images/DeleteProductCommandResult.png)

### Adding a customer: `add`

You can add a customer to Strack.io.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

* Names must be unique.

<div markdown="span" class="alert alert-primary">:heavy_exclamation_mark: **Note:**  
Adding two customers with the same name, will result in future order creation to be tagged to the first customer added
with the same phone number.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0).
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all customers and orders: `list`

You can show a list of all customers and orders in your address book.

Format: `list`

### Editing a customer: `edit`

You can edit an existing customer in your address book.

Format: `edit c/CUSTOMER_ID [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person of the specified `CUSTOMER_ID`. `CUSTOMER_ID` refers to the number shown beside the customer's name. `CUSTOMER_ID` **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the customer will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit c/1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the person with `CUSTOMER_ID` of `1` to be `91234567` and `johndoe@example.com` respectively.
*  `edit c/2 n/Betsy Crower t/` Edits the name of the person with `CUSTOMER_ID` of `2` to be `Betsy Crower` and clears all existing tags.

### Locating customers and orders: `find`

You can find customers based on name, phone number, address or email and find orders based on `ORDER_ID` in Strack.io.

Format: `find [n/NAME] [a/ADDRESS] [p/PHONE_NUMBER] [e/EMAIL] [o/ORDER_ID]`

* The search is case-insensitive. e.g. `hans` will match `Hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* At least one of the optional fields must be provided and only choose one field.
* You can add different keywords for one chosen field and customers with any matching keyword will be included.
* Only full words will be matched e.g. `Han` will not match `Hans`.
* Persons matching at least one of the specified information will be returned (i.e. `OR` search).<br>
  Examples:
* `n/Hans n/Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find n/John` returns `john` and `John Doe`.
* `find n/alex n/John` returns `Alex`, `john` and `John Doe`.<br>
* `find a/Lorong` returns customers with address that includes `Lorong`.
* `find p/85012345 p/12345678` returns customers with phone number of `85012345` and `12345678`.
* `find o/4 o/6` returns `Order 4` and `Order 6`.

![result for finding order](images/FindCommand.png)

### Deleting a customer: `delete`

You can delete the specified customer from Strack.io.

Format: `delete c/CUSTOMER_ID`

* Deletes the customer of the specified `CUSTOMER_ID`.
* The `CUSTOMER_ID` refers to the number shown beside the customer name in the displayed customer contact.
* The `CUSTOMER_ID` **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete c/2` deletes the person with `CUSTOMER_ID` of `2` in the address book.
* `find Betsy` followed by `delete c/1` deletes the person with `CUSTOMER_ID` of `1` in the results of the `find` command.

 ![Deleting customer 2](images/DeleteCommand.png)
 ![Result for deleting customer 2](images/DeleteCommandResult.png)

### Creating of orders: `order`

Now that you have some customers and a product menu set up, you can now create and assign an order to a specified customer in Strack.io.

Format: `order p/PHONE_NUMBER [by/DEADLINE]`

* Orders are assigned to person with specified `PHONE_NUMBER`.
* There must be existing customers in the customer list and products in the menu.
* `DEADLINE` is an optional fields that is used to keep track of an order's deadline.
* * The format for deadline dates are dd/MM/yyyy.
* For single digit days or months, please precede them with a zero.
* Leaving `DEADLINE` blank will make the order's deadline marked as `Not Specified`.
* Strack.io will prompt users to input products using the product command.
* Follow up with products to be added to the order using the following format. Format: `product m/MENU_ID pq/PRODUCT_QUANTITY`.
* You can refer to the menu for the `MENU_ID`, i.e. `1. Cupcake` `MENU_ID` is `1`.
* This can be repeated as many times as necessary.

Examples:
* `order p/87438807 by/08/04/2024` will create an order for person with phone number `87438807` with a deadline `08/04/2024`, start adding products for the order to be shown. <br>

![input for creating order for alex](images/OrderCommand.png)
![result for creating order for alex](images/OrderCommandResult.png)
<br>

### Adding of products to order: `product`

You can add products on the menu into the most recently created order.

Format: `product m/MENU_ID pq/PRODUCT_QUANTITY`
* You can refer to the menu for the `MENU_ID`, i.e. `1. Cupcake` `MENU_ID` is `1`.
* This can be repeated as many times as necessary within one session of using Strack.io.
* This means closing Strack.io will no longer allow you to add products to the order you created previously.

Examples:
* Assuming you have already created an order in this session for the phone number `87438807`, using `product m/1 pq/2` and `product m/2 pq/2` will add products corresponding to `MENU_ID` 1 and 2 in the menu, in this example it would be `cupcakes` and `cookies` respectively. <br>

![input for adding products for alex](images/ProductCommand.png)
![input for adding products for alex](images/ProductCommandResult.png)
  <br>

### Editing of orders: `edit`

You can edit an existing order of a customer in Strack.io.

Format: `edit o/ORDER_ID m/MENU_ID pq/PRODUCT_QUANTITY`

* `ORDER_ID` is a unique number for each order.
* `ORDER_ID` refers to the number shown under order id in the displayed customer's contact or the order number
  in the order list
* Products are edited based on `MENU_ID`.
* To remove product from order, specify `PRODUCT_QUANTITY` as `0`.

Example:
![before state for EditOrderCommand](images/EditOrderCommandBefore.png)
* `edit o/4 m/3 pq/10` will edit the order with `ORDER_ID` of `4` and change the product associated with `MENU_ID` of `3`
  which is `tarts` quantity to `10`.

![before state for EditOrderCommand](images/EditOrderCommandAfter.png)

### Editing of an order's deadline: `edit`
You can edit the deadline of an existing order of a specific customer in Strack.io.

Format: `edit o/ORDER_ID by/DEADLINE`
* `ORDER_ID` is a unique number for each order.
* `ORDER_ID` refers to the number shown under order id in the displayed customer's contact or the order number 
in the order list
* The format for deadline is dd/MM/yyyy

Example:
* `edit o/1 by/07/04/2024` or `edit o/1 by/12/10/2024` will edit the deadline of the order with `Order_ID` of `1` to `07/04/2024` or `12/10/2024` respectively. <br>

![input for adding products for alex](images/EditDeadlineCommand.png)
![input for adding products for alex](images/EditDeadlineCommandResult.png)
<br>

### Staging of orders: `stage`

You can move an order to the next stage, in the chain of the four stages defined by Strack.io, in order namely: 
`Under Preparation`, `Ready for Delivery`, `Sent for delivery` and `Received by customer`. 
However, you cannot go back to a previous stage.

Format: `stage o/ORDER`

* `ORDER_ID` is a unique number for each order.
* Any order just created will be in `Under Preparation` stage.

Example:
* Suppose the order with `ORDER_ID` 1 is in initial `Under Preparation` stage.

![Before running stage command](images/StageCommandBefore.png)

* Running `stage o/1` once will move the order with `ORDER_ID` 1 to `Ready for Delivery`.

![Run stage command once](images/StageCommandOnce.png)

* Running `stage o/1` three or more times will move the order with `ORDER_ID` 1 to `Received by customer`.

![Run stage command three times](images/StageCommandTrice.png)

### Completing of orders: `complete`

You can demarcate an order in Strack.io as complete. Strack.io will collate completed orders into a csv file.
The csv file can be accessed in this directory: `[JAR file location]/data/completedorders.csv`

Format: `complete ORDER_ID`

* `ORDER_ID` is a unique number for each order.
* `ORDER_ID` can accept *multiple, unique* `ORDER_ID` to be marked as completed as shown in the example below.
* `ORDER_ID` with at least one *non-valid* `ORDER_ID` value will be invalid.

Example:
![Before state for CompleteCommand](images/CompleteCommandBefore.png)
* `complete 1 2 3` will complete orders with `ORDER_ID`s of `1`, `2` and `3`.

![After state for CompleteCommand](images/CompleteCommandAfter.png)
* This would be collated in the csv file in this directory: `[JAR file location]/data/completedorders.csv`

![CSV state for CompleteCommand](images/CompleteCommandCSV.png)

### Cancelling of orders: `cancel`

You can remove an ongoing order in your address book. Orders removed using `cancel` will not be logged into
`completedorders.csv`.

Format: `cancel ORDER_ID`

* `ORDER_ID` is a unique number for each order.

Example:
* `cancel 3` will cancel order with `ORDER_ID` of `3`.

![result for cancelling order](images/CancelOrder1.png)
![result for cancelling order](images/CancelOrder2.png)

### Clearing all entries: `clear`

You can clear all entries from Strack.io.

Format: `clear`

<div markdown="span" class="alert alert-primary">:heavy_exclamation_mark: **Note:**
Clearing all orders and customer contacts from Strack.io will reset the ID of the next created order
</div>

### Exiting the program: `exit`

You can exit the program using a command.

Format: `exit`

### Saving the data

Strack.io data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Strack.io data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Strack.io will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause Strack.io to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Strack.io home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues and constraints

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **Constraint on number of customers, menu items and orders**: <= 1 billion, which should be sufficient for almost all homemade bakers. Our app might be unable to handle numbers larger than that.
3. **Some fields, such as names of customers**, are not wrapped in GUI. You can drag the window wider to see full text, which should be sufficient to accommodate almost all common names.
4. **Customer Names** can only contain alphanumeric characters. If the name involves slashes or special alphabets, you have to use an alternative, such as using double space in place of slash.
5. **Error Messages and suggestions** may not always be given in the most straight-forward way, often because slashes in commands may be interpreted in a few different ways. Refer to the user guide if you think error messages and suggestions shown in the app are unclear.

--------------------------------------------------------------------------------------------------------------------
## Glossary

| Term                         | Description                                                                  |
|------------------------------|------------------------------------------------------------------------------|
| **Cancel**                   | The deletion of an order without fulfilling it                               |
| **Complete**                 | The deletion of an order after fulfilling it                                 |
| **Dates**                    | In the format dd/MM/yyyy e.g. 23/03/2024                                     |
| **Product**                  | The entity you will be selling to your customers                             |
| **Precede**                  | Add to the front of                                                          |
| **Stage**                    | The status of an order                                                       |
| **Demarcate**                | Mark or distinguish                                                          |
| **CSV**                      | File that ends in .csv. Refers to a spreadsheet file                         |
| **JSON**                     | File that stores data, ends in .json                                         |
| **Command Line Interface**   | The text-based chat box at the top of Strack.io where you can input commands |
| **Graphical User Interface** | The digital interface you see and interact with such as buttons              |
| **Tag**                      | A word you can associate a customer with                                     |
| **Log**                      | To store in `completedorders.csv`                                             |

-------------------------------------------------------------------------------------------------------------------
## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add product to menu** | `menu pn/PRODUCT_NAME pc/PRODUCT_COSTS ps/PRODUCT_SALES`<br> e.g., `menu pn/cupcake pc/5 ps/10`
**Edit product on menu** | `edit m/MENU_ID [pn/PRODUCT_NAME] [pc/PRODUCT_COSTS] [ps/PRODUCT_SALES]`<br> e.g., `edit m/1 pc/20`
**Delete product on menu** | `delete m/MENU_ID`
**Add customer** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**List all contacts and orders** | `list`
**Edit customer** | `edit c/CUSTOMER_ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit c/2 n/James Lee e/jameslee@example.com`
**Find customer/order** | `find [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [o/ORDER_ID]`<br> e.g., `find n/James n/Jake` `find o/1`
**Delete customer** | `delete c/CUSTOMER_ID`<br> e.g., `delete c/3`
**Create order** | `order p/PHONE_NUMBER [by/DEADLINE]`<br> e.g., `order p/22224444 by/02/04/2024`
**Add product to order** | `product m/MENU_ID pq/PRODUCT_QUANTITY`<br> e.g., `product m/1 pq/10`
**Edit order deadline** | `edit o/ORDER_ID by/DEADLINE`<br> e.g., `edit o/1 by/02/04/2024`
**Edit order** | `edit o/ORDER_ID m/MENU_ID pq/PRODUCT_QUANTITY`<br> e.g., `edit o/1 m/1 pq/10`
**Stage order** | `stage o/ORDER_ID`<br> e.g., `stage o/1`
**Complete order** | `complete ORDER_ID`<br> e.g., `complete 1`
**Cancel order** | `cancel ORDER_ID`<br> e.g., `cancel 1`
**Clear** | `clear`
**Exit** | `exit`

--------------------------------------------------------------------------------------------------------------------
