# Shop Inventory Management System - Guitar Shop Customization

## Project Overview
This project is a customized inventory management system for a Guitar Shop. The shop sells guitars (products) that are composed of various parts such as strings, pickups, tuners, bridges, and amplifiers.

---

## 🌐 Live Deployment

**Try it now**: [Mountain Peak Guitar Shop](https://shop-inventory-management-production.up.railway.app/mainscreen)

## Working Directory
```
/d287-java-frameworks
```

## Customer Description
**Mountain Peak Guitar Shop** - A specialty guitar retailer offering custom and pre-built electric and acoustic guitars made from premium components.

---

## Changes Made for Each Requirement

### Part C: Customize HTML User Interface
**File:** `src/main/resources/templates/mainscreen.html`
**Lines:** 16, 20-21
**Changes:** 
- Line 16: Changed page title from "My Bicycle Shop" to "Mountain Peak Guitar Shop"
- Line 20: Updated heading from "Shop" to "Mountain Peak Guitar Shop"
- Context updated for guitar parts and products throughout the interface

---

### Part D: Add "About" Page
**File:** `src/main/resources/templates/about.html` (NEW FILE)
**Lines:** 1-35
**Changes:** Created new About page with:
- Company information about Mountain Peak Guitar Shop
- Description of products and services
- Navigation back to main screen

**File:** `src/main/java/com/example/demo/controllers/AboutController.java` (NEW FILE)
**Lines:** 1-16
**Changes:** Created controller to handle About page routing
- @GetMapping("/about") maps to about.html template

**File:** `src/main/resources/templates/mainscreen.html`
**Lines:** 23-24
**Changes:** Added navigation link to About page
```html
<a th:href="@{/about}" class="btn btn-info btn-sm mb-3">About Us</a>
```

---

### Part E: Add Sample Inventory
**File:** `src/main/java/com/example/demo/bootstrap/BootStrapData.java`
**Lines:** 43-127
**Changes:** 
- Lines 43-44: Added condition `if (partRepository.count() == 0 && productRepository.count() == 0)` to check if inventory is empty before loading
- Lines 46-93: Created 5 sample parts:
  1. Guitar Strings Set (OutsourcedPart - D'Addario) - Lines 46-53
  2. Humbucker Pickup (InhousePart - ID 1001) - Lines 55-62
  3. Locking Tuners (OutsourcedPart - Grover) - Lines 64-71
  4. Tremolo Bridge (InhousePart - ID 1002) - Lines 73-80
  5. Practice Amplifier (OutsourcedPart - Fender) - Lines 82-89
- Lines 95-127: Created 5 sample products:
  1. Stratocaster Style Electric Guitar - Lines 96-99
  2. Les Paul Style Electric Guitar - Lines 101-104
  3. Acoustic Dreadnought Guitar - Lines 106-109
  4. Semi-Hollow Body Guitar - Lines 111-115
  5. 7-String Metal Guitar - Lines 117-122
- Each part has minInv and maxInv values set
- Products are associated with appropriate parts

---

### Part F: Add "Buy Now" Button
**File:** `src/main/java/com/example/demo/controllers/AddProductController.java`
**Lines:** 191-205
**Changes:** Added `buyProduct` method:
- Line 192: @GetMapping("/buyProduct") annotation
- Lines 194-195: Retrieve product by ID
- Lines 197-202: Check if product is in stock and decrement inventory by 1
- Lines 200-201: Save updated product and return success page
- Lines 203-205: Return error page if out of stock

**File:** `src/main/resources/templates/mainscreen.html`
**Lines:** 92-94
**Changes:** Added "Buy Now" button in product table:
```html
<a th:href="@{/buyProduct(productID=${tempProduct.id})}" class="btn btn-success btn-sm mb-3"
   onclick="if(!(confirm('Purchase this product?')))return false">Buy Now</a>
```
- Button placed before Update and Delete buttons
- Includes confirmation dialog

**File:** `src/main/resources/templates/buyproductsuccess.html` (NEW FILE)
**Lines:** 1-17
**Changes:** Created success page for successful purchases
- Displays success message
- Auto-redirects to main screen after 2 seconds

**File:** `src/main/resources/templates/buyproducterror.html` (NEW FILE)
**Lines:** 1-15
**Changes:** Created error page for failed purchases (out of stock)
- Displays error message
- Provides link back to main screen

---

### Part G: Add Maximum and Minimum Inventory Fields
**File:** `src/main/java/com/example/demo/domain/Part.java`
**Lines:** 30-31, 75-87
**Changes:** 
- Lines 30-31: Added fields `int minInv;` and `int maxInv;`
- Lines 75-77: Added `getMinInv()` method
- Lines 79-81: Added `setMinInv(int minInv)` method
- Lines 83-85: Added `getMaxInv()` method
- Lines 87-89: Added `setMaxInv(int maxInv)` method

**File:** `src/main/resources/templates/InhousePartForm.html`
**Lines:** 24-27
**Changes:** Added input fields for minimum and maximum inventory:
```html
<p><input type="text" th:field="*{minInv}" placeholder="Minimum Inventory" class="form-control mb-4 col-4"/></p>
<p><input type="text" th:field="*{maxInv}" placeholder="Maximum Inventory" class="form-control mb-4 col-4"/></p>
```

**File:** `src/main/resources/templates/OutsourcedPartForm.html`
**Lines:** 24-27
**Changes:** Added input fields for minimum and maximum inventory:
```html
<p><input type="text" th:field="*{minInv}" placeholder="Minimum Inventory" class="form-control mb-4 col-4"/></p>
<p><input type="text" th:field="*{maxInv}" placeholder="Maximum Inventory" class="form-control mb-4 col-4"/></p>
```

**File:** `src/main/java/com/example/demo/bootstrap/BootStrapData.java`
**Lines:** 50-51, 59-60, 68-69, 77-78, 86-87
**Changes:** Set minimum and maximum inventory values for all sample parts:
- Guitar Strings: minInv=10, maxInv=100
- Humbucker Pickup: minInv=5, maxInv=50
- Locking Tuners: minInv=5, maxInv=40
- Tremolo Bridge: minInv=5, maxInv=30
- Practice Amplifier: minInv=3, maxInv=25

**File:** `src/main/resources/application.properties`
**Line:** 7
**Changes:** Renamed database file from `spring-boot-h2-db102` to `spring-boot-h2-db-guitarshop`
```properties
spring.datasource.url=jdbc:h2:file:~/spring-boot-h2-db-guitarshop
```

---

### Part H: Add Validation for Min/Max Inventory
**File:** `src/main/java/com/example/demo/validators/ValidInventoryRange.java` (NEW FILE)
**Lines:** 1-20
**Changes:** Created custom validation annotation:
- @Constraint annotation pointing to InventoryRangeValidator
- @Target(ElementType.TYPE) for class-level validation
- Default message: "Inventory must be between minimum and maximum values"

**File:** `src/main/java/com/example/demo/validators/InventoryRangeValidator.java` (NEW FILE)
**Lines:** 1-36
**Changes:** Created validator class implementing ConstraintValidator:
- Line 20: Validates inventory is not less than minimum
- Lines 21-25: Builds custom error message for low inventory
- Line 27: Validates inventory is not greater than maximum
- Lines 28-32: Builds custom error message for high inventory

**File:** `src/main/java/com/example/demo/domain/Part.java`
**Line:** 18
**Changes:** Added `@ValidInventoryRange` annotation to Part class to enable validation

**File:** `src/main/java/com/example/demo/validators/EnufPartsValidator.java`
**Lines:** 32-42
**Changes:** Modified to check if part inventory would fall below minimum when building products:
- Line 34: Calculate new inventory after building product
- Lines 35-37: Check if new inventory would be negative
- Lines 38-42: Check if new inventory would be below minimum and build custom error message

---

### Part I: Add Unit Tests
**File:** `src/test/java/com/example/demo/domain/PartTest.java`
**Lines:** 147-188
**Changes:** Added four unit tests for min/max inventory fields:

- Lines 147-154: `testGetMinInv()` - Tests getting minimum inventory value
```java
@Test
void testGetMinInv() {
    int minValue = 5;
    partIn.setMinInv(minValue);
    assertEquals(minValue, partIn.getMinInv());
    partOut.setMinInv(minValue);
    assertEquals(minValue, partOut.getMinInv());
}
```

- Lines 156-163: `testSetMinInv()` - Tests setting minimum inventory value
```java
@Test
void testSetMinInv() {
    int minValue = 10;
    partIn.setMinInv(minValue);
    assertEquals(minValue, partIn.getMinInv());
    partOut.setMinInv(minValue);
    assertEquals(minValue, partOut.getMinInv());
}
```

- Lines 165-172: `testGetMaxInv()` - Tests getting maximum inventory value
```java
@Test
void testGetMaxInv() {
    int maxValue = 100;
    partIn.setMaxInv(maxValue);
    assertEquals(maxValue, partIn.getMaxInv());
    partOut.setMaxInv(maxValue);
    assertEquals(maxValue, partOut.getMaxInv());
}
```

- Lines 174-181: `testSetMaxInv()` - Tests setting maximum inventory value
```java
@Test
void testSetMaxInv() {
    int maxValue = 200;
    partIn.setMaxInv(maxValue);
    assertEquals(maxValue, partIn.getMaxInv());
    partOut.setMaxInv(maxValue);
    assertEquals(maxValue, partOut.getMaxInv());
}
```

---

### Part J: Remove Unused Validators
**Files DELETED:**
- `src/main/java/com/example/demo/validators/DeletePartValidator.java`
- `src/main/java/com/example/demo/validators/ValidDeletePart.java`

**File:** `src/main/java/com/example/demo/domain/Part.java`
**Line:** 18 (previously had @ValidDeletePart)
**Changes:** Removed `@ValidDeletePart` annotation from Part class

**Reason for Removal:**
The DeletePartValidator was unused because part deletion validation is handled at the controller level in `AddPartController.java` (line 67-75) where it checks if the part has associated products before allowing deletion. The validator class and annotation were redundant and have been removed to clean up the codebase.

---

## Project Structure

```
/d287-java-frameworks/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── bootstrap/
│   │   │   │   └── BootStrapData.java (MODIFIED)
│   │   │   ├── controllers/
│   │   │   │   ├── AboutController.java (NEW)
│   │   │   │   ├── AddInhousePartController.java
│   │   │   │   ├── AddOutsourcedPartController.java
│   │   │   │   ├── AddPartController.java
│   │   │   │   ├── AddProductController.java (MODIFIED)
│   │   │   │   └── MainScreenControllerr.java
│   │   │   ├── DemoApplication.java
│   │   │   ├── domain/
│   │   │   │   ├── InhousePart.java
│   │   │   │   ├── OutsourcedPart.java
│   │   │   │   ├── Part.java (MODIFIED)
│   │   │   │   └── Product.java
│   │   │   ├── repositories/
│   │   │   │   ├── InhousePartRepository.java
│   │   │   │   ├── OutsourcedPartRepository.java
│   │   │   │   ├── PartRepository.java
│   │   │   │   └── ProductRepository.java
│   │   │   ├── service/
│   │   │   │   ├── InhousePartService.java
│   │   │   │   ├── InhousePartServiceImpl.java
│   │   │   │   ├── OutsourcedPartService.java
│   │   │   │   ├── OutsourcedPartServiceImpl.java
│   │   │   │   ├── PartService.java
│   │   │   │   ├── PartServiceImpl.java
│   │   │   │   ├── ProductService.java
│   │   │   │   └── ProductServiceImpl.java
│   │   │   └── validators/
│   │   │       ├── EnufPartsValidator.java (MODIFIED)
│   │   │       ├── InventoryRangeValidator.java (NEW)
│   │   │       ├── PriceProductValidator.java
│   │   │       ├── ValidEnufParts.java
│   │   │       ├── ValidInventoryRange.java (NEW)
│   │   │       └── ValidProductPrice.java
│   │   └── resources/
│   │       ├── application.properties (MODIFIED)
│   │       ├── static/
│   │       │   ├── css/demo.css
│   │       │   └── index.html
│   │       └── templates/
│   │           ├── about.html (NEW)
│   │           ├── buyproducterror.html (NEW)
│   │           ├── buyproductsuccess.html (NEW)
│   │           ├── confirmationaddpart.html
│   │           ├── confirmationaddproduct.html
│   │           ├── confirmationassocpart.html
│   │           ├── confirmationdeletepart.html
│   │           ├── confirmationdeleteproduct.html
│   │           ├── InhousePartForm.html (MODIFIED)
│   │           ├── mainscreen.html (MODIFIED)
│   │           ├── negativeerror.html
│   │           ├── OutsourcedPartForm.html (MODIFIED)
│   │           ├── productForm.html
│   │           └── saveproductscreen.html
│   └── test/
│       └── java/com/example/demo/
│           ├── DemoApplicationTests.java
│           ├── domain/
│           │   ├── InhousePartTest.java
│           │   ├── OutsourcedPartTest.java
│           │   ├── PartTest.java (MODIFIED)
│           │   └── ProductTest.java
│           ├── repositories/
│           │   └── InhousePartRepositoryTest.java
│           └── service/
│               └── InhousePartServiceTest.java
└── target/ (compiled classes)
```

---

## How to Run the Application

1. **Open the project in IntelliJ IDEA Ultimate Edition**
   - File → Open → Navigate to `/Users/tanner/Desktop/Java Frameworks/d287-java-frameworks`

2. **Ensure Java 17 and Maven are properly configured**
   - Project SDK should be set to Java 17
   - Maven will automatically download dependencies

3. **Run the application**
   - Navigate to `src/main/java/com/example/demo/DemoApplication.java`
   - Right-click and select "Run 'DemoApplication'"
   - Or use the green play button in IntelliJ

4. **Access the application**
   - Open browser and navigate to: `http://localhost:8080/mainscreen`
   - The application will automatically load with sample guitar shop inventory (first run only)

5. **Access H2 Database Console (optional)**
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:file:~/spring-boot-h2-db-guitarshop`
   - Username: `sa`
   - Password: (leave blank)

---

## Sample Inventory Details

### Parts (5 total):
1. **Guitar Strings Set** (Outsourced - D'Addario)
   - Price: $12.99, Inventory: 50, Min: 10, Max: 100

2. **Humbucker Pickup** (Inhouse - ID 1001)
   - Price: $89.99, Inventory: 30, Min: 5, Max: 50

3. **Locking Tuners** (Outsourced - Grover)
   - Price: $45.50, Inventory: 25, Min: 5, Max: 40

4. **Tremolo Bridge** (Inhouse - ID 1002)
   - Price: $75.00, Inventory: 20, Min: 5, Max: 30

5. **Practice Amplifier** (Outsourced - Fender)
   - Price: $129.99, Inventory: 15, Min: 3, Max: 25

### Products (5 total):
1. **Stratocaster Style Electric Guitar** - $899.99, Stock: 5
   - Parts: Strings, Pickup, Tuners

2. **Les Paul Style Electric Guitar** - $1,299.99, Stock: 3
   - Parts: Strings, Pickup

3. **Acoustic Dreadnought Guitar** - $599.99, Stock: 8
   - Parts: Strings, Tuners

4. **Semi-Hollow Body Guitar** - $1,499.99, Stock: 2
   - Parts: Strings, Pickup, Bridge

5. **7-String Metal Guitar** - $1,099.99, Stock: 4
   - Parts: Strings, Pickup, Tuners, Bridge

---

## Features

### Parts Management
- Add, update, delete, and search guitar parts
- Two types: Inhouse Parts (with Part ID) and Outsourced Parts (with Company Name)
- Min/Max inventory tracking with validation
- Cannot delete parts associated with products

### Products Management
- Add, update, delete, and search guitars
- Associate parts with products
- Inventory decrements when products are built
- Product price must exceed sum of parts prices

### Buy Now Feature
- Purchase products directly from the product list
- Decrements product inventory by 1
- Does NOT affect part inventory
- Success/error messages displayed

### About Page
- Company information
- Products and services description
- Easy navigation

### Validation Rules
1. Price must be positive
2. Inventory must be positive
3. Inventory must be between minimum and maximum values
4. Product price must be greater than sum of parts prices
5. Cannot build product without sufficient part inventory (respects minimum levels)
6. Cannot delete parts that are associated with products

---

## Technologies Used

- **Spring Boot** 2.6.6
- **Spring Data JPA** - ORM and database access
- **H2 Database** - Embedded database with file persistence
- **Thymeleaf** - Server-side template engine
- **Bootstrap** 5.1.3 - Frontend styling
- **Java** 17
- **Maven** - Dependency management
- **JUnit 5** - Unit testing

---

## Database Configuration

### H2 Database Details:
- **Type:** Embedded, file-based
- **File Location:** `~/spring-boot-h2-db-guitarshop.mv.db`
- **Username:** sa
- **Password:** (blank)
- **Auto-create:** Yes
- **Persistence:** Data persists between application restarts

### To Reset Database:
1. Stop the application
2. Delete the file: `~/spring-boot-h2-db-guitarshop.mv.db`
3. Restart the application
4. Sample inventory will reload automatically

---

## Testing

### Run All Tests:
```bash
mvn test
```

### Run in IntelliJ:
- Right-click on `src/test/java` folder
- Select "Run 'All Tests'"

### Test Coverage:
- Domain entity tests (Part, Product, InhousePart, OutsourcedPart)
- Repository tests (InhousePartRepository)
- Service tests (InhousePartService)
- **New:** Min/Max inventory tests in PartTest.java

---

## Git Repository Information

**Repository URL:** [https://gitlab.com/wgu-gitlab-environment/student-repos/tabra99/d287-java-frameworks.git]

**Commit History:** See separate gitlog.txt document

### Git Commands Used:
```bash
# Clone repository
git clone [https://gitlab.com/wgu-gitlab-environment/student-repos/tabra99/d287-java-frameworks.git]

# Stage changes
git add .

# Commit with message format
git commit -m "Part X: Description of changes"

# Push to remote
git push origin main

# View history
git log
```

---

## Author

**Student:** Tanner Abraham
**Project:** D287 Java Frameworks
**Customer:** Mountain Peak Guitar Shop
**Date:** January 2025

---

## Notes

- Sample inventory loads only once when database is empty
- Buy Now feature is for demonstration purposes (real system would integrate with payment processing)
- Min/Max inventory helps prevent stockouts and overstocking
- All validation messages are user-friendly and descriptive
- Code follows Spring Boot best practices and conventions

---

**Status:** ✅ All Requirements Complete (Parts A-K)
