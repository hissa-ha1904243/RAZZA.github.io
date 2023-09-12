package com.example.razzashoppingapp.remote.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.razzashoppingapp.remote.api.RazzaAPI
//import com.example.razzashoppingapp.room.dao.ProductDao
import com.example.razzashoppingapp.room.entity.*
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RoomRepository(context: Context) {
    val BASE_URL="https://gist.githubusercontent.com/deema-da1901542/a4e4b5b3d0195c8b81066fe0a4b0931c/raw/bdd7fe6c7989519daafa37d7af47d238be47b399/"
    //Create a Retrofit Object
    val razzaAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RazzaAPI::class.java)
    }
    //Get data from the gist using these functions:
    suspend fun getCategories(): List<Category> = razzaAPI.getCategories()
    suspend fun getProducts(): List<Product> = razzaAPI.getProducts()
    suspend fun getUsers(): List<User> = razzaAPI.getUsers()
    suspend fun getAddresses(): List<Address> = razzaAPI.getAddresses()
    suspend fun getOrderedItems(): List<OrderItem> = razzaAPI.getOrderedItems()
    suspend fun getOrders(): List<Order> = razzaAPI.getOrders()
    suspend fun getReviews(): List<Review> = razzaAPI.getReviews()




    // Firebase Doc Refs:
    val db by lazy { Firebase.firestore }
    val productsDocumentRef by lazy {db.collection("products")}
    val catagoryDocumentRef by lazy {db.collection("categories")}
    val reviewsDocumentRef by lazy {db.collection("reviews")}
    val wishlistDocumentRef by lazy {db.collection("wishlistItems")}
    val usersDocumentRef by lazy {db.collection("users")}
    val addressDocumentRef by lazy {db.collection("address")}
    val cartItemsDocumentRef by lazy {db.collection("cartItems")}
    val orderedItemsDocumentRef by lazy {db.collection("orderedItems")}
    val ordersDocumentRef by lazy {db.collection("orders")}



//fun productCount(): Int = productDao.getProductCount()
//    suspend fun orderProductAsc(): Flow<List<Product>> = productDao.orderProductAsc()
//    suspend fun orderProductDes(): Flow<List<Product>> = productDao.orderProductDes()


    //Firebase Products:
    suspend fun getProduct(productID: String) {
        productsDocumentRef.document(productID).get()
    }
//    suspend fun orderProductAsc(): Flow<List<Product>>{
//        var q = query(productsDocumentRef, orderBy("price", "desc"));
//    }
    suspend fun deleteAllProducts() = productsDocumentRef.document().delete()
    suspend fun getAllProducts(): List<Product> = productsDocumentRef
            .get()
            .await()
            .toObjects(Product::class.java)


    suspend fun addProduct(product: Product) {
        val documentID=product.id
        //product.id=documentID
        productsDocumentRef.document(documentID).set(product)
    }
    suspend fun addNewProduct(product: Product) {
        val documentID=productsDocumentRef.document().id
        product.id=documentID
        productsDocumentRef.document(documentID).set(product)
    }

    suspend fun addProducts(products: List<Product>) {
        products.forEach{
            val documentID=it.id
           // it.id=documentID.toInt()
            productsDocumentRef.document(documentID).set(it)
        }

    }
    suspend fun updateProduct(product: Product) {
        productsDocumentRef.document(product.id).set(product)
    }
    suspend fun deleteProduct(product: Product) =
        productsDocumentRef.document(product.id).delete()

    //Firebase reviews
    suspend fun getAllReviews(): List<Review> =
        reviewsDocumentRef
            .get()
            .await()
            .toObjects(Review::class.java)

    suspend fun addReview(review: Review) {
        val documentID=reviewsDocumentRef.document().id
        review.id=documentID
        reviewsDocumentRef.document(documentID).set(review)
    }
   suspend fun updateReview(review: Review) {
        reviewsDocumentRef.document(review.id).set(review)
    }
   suspend fun deleteReview(review: Review) = reviewsDocumentRef
            .document(review.id).delete()

    //Firebase Wishlist
   suspend fun getAllWishlistItems(): List<WishListItem> =
        wishlistDocumentRef
            .get()
            .await()
            .toObjects(WishListItem::class.java)

    suspend fun addWishlistItem(wishlistItem: WishListItem) {
        val documentID = wishlistDocumentRef.document().id
        wishlistItem.id = documentID
        wishlistDocumentRef.document(documentID).set(wishlistItem)

    }
    suspend fun deleteWishlistItem(wishlistItem: WishListItem) = wishlistDocumentRef
            .document(wishlistItem.id).delete()

    //Firebase Catagory
    //fun getCategoryCount(): Int = categoryDao.getCategoryCount()
    private suspend fun isThereCategoryCollection() : Boolean {
        val queryResult = catagoryDocumentRef.limit(1).get().await()
        return queryResult.isEmpty
    }
    suspend fun addCategory(category: Category) {
        val documentID = category.id
        //category.id=documentID
        catagoryDocumentRef.document(documentID).set(category)
    }
    suspend fun addNewCategory(category: Category) {
        val documentID=catagoryDocumentRef.document().id
        category.id=documentID
        catagoryDocumentRef.document(documentID).set(category)
    }
    suspend fun addCategories(categories: List<Category>) {
        categories.forEach {
           val documentID = it.id
           // it.id = documentID.toInt()
            catagoryDocumentRef.document(documentID).set(it)
        }
    }
    suspend fun getAllCategories(): List<Category> =
        catagoryDocumentRef
            .get()
            .await()
            .toObjects(Category::class.java)

    suspend fun getCategory(categoryId: String) = catagoryDocumentRef.document(categoryId).get()


    suspend fun deleteCategory(category: Category) = catagoryDocumentRef
        .document(category.id).delete().await()

    //Firebase User
    //fun userCount(): Int = userDao.getUserCount()

    suspend fun getAllUsers(): List<User> =
        usersDocumentRef
            .get()
            .await()
            .toObjects(User::class.java)

    suspend fun addUser(user: User) {
        val documentID = user.id
       // user.id = documentID
        usersDocumentRef.document(documentID).set(user)
    }

    suspend fun getUser(userID: String) {
        usersDocumentRef.document(userID).get()
    }

   suspend fun updateUser(user: User) {
        usersDocumentRef.document(user.id).set(user)
            .addOnSuccessListener {
            Log.d("User", "Successfully updated")
        }.addOnFailureListener {
            it.message?.let { it1 -> Log.d("User", it1) }
        }
    }

   suspend fun deleteUser(user: User) = usersDocumentRef
            .document(user.id).delete()

    //Address User
    //suspend fun deleteAllAddress() = addressDao.deleteAllAddress()

    suspend fun getAllAddresses(): List<Address> =
        addressDocumentRef
            .get()
            .await()
            .toObjects(Address::class.java)

    suspend fun addAddress(address: Address) {
        val documentID=addressDocumentRef.document().id
        address.id=documentID
        addressDocumentRef.document(documentID).set(address)
    }

    suspend fun getAddress(addid: String) {
        addressDocumentRef.document(addid).get()
    }

   suspend fun updateAddress(address: Address) {
        addressDocumentRef.document(address.id).set(address)
    }

   suspend fun deleteAddress(address: Address) = addressDocumentRef
            .document(address.id).delete()

   suspend fun deleteAllAddress() =
        addressDocumentRef.document().delete()

    //Firebase Items:
    suspend fun deleteCartItem(item: Item) = cartItemsDocumentRef
        .document(item.id).delete()

    suspend fun addCartItem(item: Item) {
        val documentID=cartItemsDocumentRef.document().id
        item.id=documentID
       // val documentID = item.id
        //item.id = documentID
        cartItemsDocumentRef.document(documentID).set(item)
    }

    suspend fun getAllCartItems(): List<Item> = cartItemsDocumentRef
            .get()
            .await()
            .toObjects(Item::class.java)

    suspend fun getUserCartItems(userId: String) = cartItemsDocumentRef
        .whereEqualTo("userId", userId)
        .get()
        .await()
        .toObjects(Item::class.java)

    //Firebase Order
    suspend fun getAllOrders(): List<Order> =
        ordersDocumentRef
        .get()
        .await()
        .toObjects(Order::class.java)

    suspend fun getOrder(orderId: String) {
        ordersDocumentRef.document(orderId).get()
    }

    suspend fun addOrder(order: Order) {
        val documentID = order.id
        //order.id = documentID
        ordersDocumentRef.document(documentID).set(order)
    }
    suspend fun addNewOrder(order: Order) {
        val documentID=orderedItemsDocumentRef.document().id
        order.id=documentID
        ordersDocumentRef.document(documentID).set(order)
    }

    suspend fun deleteOrder(order: Order) = ordersDocumentRef
        .document(order.id).delete()

    suspend fun updateOrder(order: Order) {
        ordersDocumentRef.document(order.id).set(order)
    }

    suspend fun deleteAllOrders() =
        ordersDocumentRef.document().delete()

    suspend fun getUserOrders(userId: String) = ordersDocumentRef
        .whereEqualTo("userId", userId)
        .get()
        .await()
        .toObjects(Order::class.java)
//Ordered Items:
suspend fun addOrderedItem(order: OrderItem) {
    val documentID = order.id
    //order.id = documentID
    orderedItemsDocumentRef.document(documentID).set(order)
}
    suspend fun getAllOrderedItems(): List<OrderItem> =
        orderedItemsDocumentRef
            .get()
            .await()
            .toObjects(OrderItem::class.java)

    init {
        runBlocking {
        initFB()}
        Log.d("TAG", "...Hello ")

    }
    //Add data (categories/products) from gist to firebase
    suspend private fun initFB() {
        //if (isThereCategoryCollection()) {
        if(getAllCategories().isEmpty()) {
            var catagoriesData = runBlocking { getCategories() }
            var productsData = runBlocking { getProducts() }
            var usersData = runBlocking { getUsers() }
            var addressesData = runBlocking { getAddresses() }
            var reviewsData = runBlocking { getReviews() }
            var ordersData = runBlocking { getOrders() }
            var orderedItemsData = runBlocking { getOrderedItems() }
            catagoriesData.forEach { runBlocking { addCategory(it) } }
            productsData.forEach { runBlocking { addProduct(it) } }
            usersData.forEach { runBlocking { addUser(it) } }
            addressesData.forEach { runBlocking { addAddress(it) } }
            reviewsData.forEach { runBlocking { addReview(it) } }
            ordersData.forEach { runBlocking { addOrder(it) } }
            orderedItemsData.forEach { runBlocking { addOrderedItem(it) } }
        }

         //}
    }


    //ROOM DATABASE
/*
    //Database
    private val roomDb by lazy {
        RazzaDataBase.getDatabase(context)
    }

    //Daos
    private val productDao by lazy {
        roomDb.ProductDao()
    }
    private val addressDao by lazy {
        roomDb.AddressDao()
    }
    private val paymentDao by lazy {
        roomDb.PaymentDao()
    }
    private val reviewDao by lazy {
        roomDb.ReviewDao()
    }

    private val orderDao by lazy {
        roomDb.OrderDao()
    }
    private val userDao by lazy {
        roomDb.UserDao()
    }
    private val wishlistDao by lazy {
        roomDb.WishListDao()
    }
    private val categoryDao by lazy {
        roomDb.CategoryDao()
    }
    private val orderItemDao by lazy {
        roomDb.OrderItemDao()
    }
    private val itemDao by lazy {
        roomDb.ItemDao()
    }
    private val shoppingCartDao by lazy {
        roomDb.ShoppingCartDao()
    }

    //Product:
    fun productCount(): Int = productDao.getProductCount()
//    suspend fun addProducts(products: List<Product>) = productDao.addProducts(products)
//    suspend fun addProduct(product: Product) = productDao.addProduct(product)
//    suspend fun getAllProducts(): Flow<List<Product>> = productDao.getAllProducts()
    suspend fun getProducts(name: String): Flow<Product> =
        productDao.getProducts(name)
    suspend fun getProducts(id: Int): Flow<Product> =
        productDao.getProduct(id)

    suspend fun getProductsByCategory(categoryID: Int): Flow<List<Product>> =
        productDao.getProductsByCategory(categoryID)


    suspend fun orderProductAsc(): Flow<List<Product>> = productDao.orderProductAsc()

    suspend fun orderProductDes(): Flow<List<Product>> = productDao.orderProductDes()
//
//    suspend fun getProduct(productID: Int): Flow<Product> = productDao.getProduct(productID)
//    suspend fun updateProduct(product: Product) = productDao.updateProduct(product)
//    suspend fun deleteProduct(product: Product) = productDao.deleteProduct(product)
//    suspend fun deleteAllProducts() = productDao.deleteAllProducts()

    //Address:
//    suspend fun addAddress(address: Address) = addressDao.addAddress(address)
//    suspend fun getAddress(addid: Int): Flow<Address> = addressDao.getAddress(addid)
//    suspend fun deleteAddress(address: Address) = addressDao.deleteAddress(address)
//    suspend fun updateAddress(address: Address) = addressDao.updateAddress(address)
//    suspend fun deleteAllAddress() = addressDao.deleteAllAddress()
//    suspend fun getAllAddresses() = addressDao.getAllAddresses()

    //Category:
    fun getCategoryCount(): Int = categoryDao.getCategoryCount()
//    suspend fun addCategories(categories: List<Category>) = categoryDao.addCategories(categories)
//    suspend fun addCategory(category: Category) = categoryDao.addCategory(category)
//    suspend fun getAllCategories(): Flow<List<Category>> = categoryDao.getAllCategories()
    suspend fun getCategory(categoryId: String): Flow<Category> =
        categoryDao.getCategory(categoryId)

    suspend fun updateCategory(category: Category) = categoryDao.updateCategory(category)
//    suspend fun deleteCategory(category: Category) = categoryDao.deleteCategory(category)
    suspend fun deleteAllCategories() = categoryDao.deleteAllCategories()

    //Payment:
    suspend fun addPayment(payment: Payment) = paymentDao.addPayment(payment)
    suspend fun getAllPayments(): Flow<List<Payment>> = paymentDao.getAllPayments()

    //suspend fun getPayment(paymentID: Int): Flow<Payment> = paymentDao.getPayment(paymentID)
    suspend fun updatePayment(payment: Payment) = paymentDao.updatePayment(payment)
    suspend fun deletePayment(payment: Payment) = paymentDao.deletePayment(payment)
    suspend fun deleteAllPayment() = paymentDao.deleteAllPayments()

    //Review:
    suspend fun getReviewCount(): Int = reviewDao.getReviewCount()
//    suspend fun addReview(review: Review) = reviewDao.addReview(review)
//    suspend fun getAllReviews(): Flow<List<Review>> = reviewDao.getAllReviews()
    suspend fun getReview(rid: Int): Flow<Review> = reviewDao.getReview(rid)
//    suspend fun deleteReview(review: Review) = reviewDao.deleteReview(review)
//    suspend fun updateReview(review: Review) = reviewDao.updateReview(review)
    suspend fun deleteAllReviews() = reviewDao.deleteAllReviews()
    suspend fun getProductReviews(pid: Int): Flow<List<Review>> = reviewDao.getProductReviews(pid)

    //ShoppingCart
    suspend fun addCart(cart: ShoppingCart) = shoppingCartDao.addCart(cart)
    suspend fun getUserCart(uid: Int) = shoppingCartDao.getUserCart(uid)

    //ShoppingCartItem
    //suspend fun deleteCartItem(item: Item) = itemDao.deleteCartItem(item)
    //suspend fun addCartItem(item: Item) = itemDao.addCartItem(item)
    //suspend fun getAllCartItems(): Flow<List<Item>> = itemDao.getAllCartItems()
    //suspend fun getUserCartItems(uid:Int)=itemDao.getUserCartItems(uid)

    //User:
    fun userCount(): Int = userDao.getUserCount()
//    suspend fun getAllUsers() = userDao.getAllUsers()
//    suspend fun addUser(user: User) = userDao.addUser(user)
//    suspend fun getUser(userID: Int): User = userDao.getUser(userID)
//    suspend fun updateUser(user: User) = userDao.updateUser(user)
//    suspend fun deleteUser(user: User) = userDao.deleteUser(user)

    //Wishlist:
    fun wishlistCount(): Int = wishlistDao.getWishlistCount()
    suspend fun addWishlistItems(wishListItems: List<WishListItem>) =
        wishlistDao.addWishlistItems(wishListItems)

//    suspend fun addWishlistItem(wishListItem: WishListItem) =
//        wishlistDao.addWishlistItem(wishListItem)
//
//    suspend fun getAllWishlistItems(): Flow<List<WishListItem>> = wishlistDao.getAllWishlistItems()
//    suspend fun getWishlistItem(wishlistID: Int): Flow<WishListItem> =
//        wishlistDao.getWishlistItem(wishlistID)
//
//    suspend fun deleteWishlistItem(wishListItem: WishListItem) =
//        wishlistDao.deleteWishlistItem(wishListItem)

    suspend fun deleteAllWishlistItems() = wishlistDao.deleteAllWishlistItems()

   //Order:

    fun orderCount(): Int = orderDao.getOrderCount()
//    suspend fun getAllOrders(): Flow<List<Order>> = orderDao.getAllOrders()
//    suspend fun getOrder(orderId: Int): Flow<Order> = orderDao.getOrder(orderId)
//    suspend fun addOrder(order: Order) = orderDao.addOrder(order)
//    suspend fun deleteOrder(order: Order) = orderDao.deleteOrder(order)
//    suspend fun updateOrder(order: Order) = orderDao.updateOrder(order)
//    suspend fun deleteAllOrder() = orderDao.deleteAllOrder()
    suspend fun getProcessingOrders(): Flow<List<Order>> = orderDao.getProcessingOrders()
    suspend fun getShippedOrders(): Flow<List<Order>> = orderDao.getShippedOrders()
    suspend fun getDeliveredOrders(): Flow<List<Order>> = orderDao.getDeliveredOrders()
//    suspend fun getUserOrders(uid: Int): Flow<List<Order>> = orderDao.getUserOrders(uid)

    //Oredered Items:
    fun orderItemsCount(): Int = orderItemDao.getOrderItemCount()
    suspend fun getAllOrderedItems(): Flow<List<OrderItem>> = orderItemDao.getAllOrderedItems()
    suspend fun addOrderItem(order: OrderItem) = orderItemDao.addOrder(order)
*/

/*
    suspend fun initProductDbFromJson(context: Context): Unit {
        if (roomDb == null) {
            return
        }
        if (productDao == null) {
            return
        }
        var products = listOf<Product>()
        if (productDao.getProductCount() == 0) {
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
            val productJson = context.assets.open("products.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
            products = json.decodeFromString(productJson)
            products.forEach {
                Log.d("test", "product")
                runBlocking {
                    this.launch { productDao.addProduct(it) }
                }
            }
        }
    }

    suspend fun initOrderedItemsDbFromJson(context: Context): Unit {
        if (roomDb == null) {
            return
        }
        if (orderItemDao == null) {
            return
        }
        var order = listOf<OrderItem>()
        if (orderItemDao.getOrderItemCount() == 0) {
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
            val orderJson = context.assets.open("ordereditems.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
            order = json.decodeFromString(orderJson)
            order.forEach {
                runBlocking {
                    this.launch { orderItemDao.addOrder(it) }
                }
            }
        }
    }

    suspend fun initCategoryDbFromJson(context: Context): Unit {
        if (roomDb == null) {
            return
        }
        if (categoryDao == null) {
            return
        }
        var categories = listOf<Category>()
        if (categoryDao.getCategoryCount() == 0) {
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
            val categoryJson = context.assets.open("categories.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
            categories = json.decodeFromString(categoryJson)
            categories.forEach {
                runBlocking {
                    this.launch { categoryDao.addCategory(it) }
                }
            }
        }
    }

    suspend fun initReviewsDbFromJson(context: Context): Unit {
        if (roomDb == null) {
            return
        }
        if (reviewDao == null) {
            return
        }
        var reviews = listOf<Review>()
        if (reviewDao.getReviewCount() == 0) {
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
            val reviewJson = context.assets.open("reviews.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
            reviews = json.decodeFromString(reviewJson)
            reviews.forEach {
                runBlocking {
                    this.launch { reviewDao.addReview(it) }
                }
            }
        }
    }

    suspend fun initUsersDbFromJson(context: Context): Unit {
        if (roomDb == null) {
            return
        }
        if (userDao == null) {
            return
        }
        var users = listOf<User>()
        if (userDao.getUserCount() == 0) {
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
            val reviewJson = context.assets.open("users.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
            users = json.decodeFromString(reviewJson)
            users.forEach {
                runBlocking {
                    this.launch { userDao.addUser(it) }
                }
            }
        }
    }

    suspend fun initOrderDbFromJson(context: Context): Unit {
        if (roomDb == null) {
            return
        }
        if (orderDao == null) {
            return
        }
        var orders = listOf<Order>()
        if (orderDao.getOrderCount() == 0) {
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
            val orderJson = context.assets.open("orders.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
            orders = json.decodeFromString(orderJson)
            orders.forEach {
                runBlocking {
                    this.launch { orderDao.addOrder(it) }
                }
            }
        }
    }

    suspend fun initAddressesDbFromJson(context: Context): Unit {
        if (roomDb == null) {
            return
        }
        if (addressDao == null) {
            return
        }
        var addresses = listOf<Address>()
        if (addressDao.getAddressCount() == 0) {
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
            val addressJson = context.assets.open("addresses.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
            addresses = json.decodeFromString(addressJson)
            addresses.forEach {
                runBlocking {
                    this.launch { addressDao.addAddress(it) }
                }
            }
        }
    }

*/

    }
