package com.example.razzashoppingapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.razzashoppingapp.remote.repo.RoomRepository
import com.example.razzashoppingapp.room.entity.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.MutableStateFlow


class RazzaViewModel(context: Application) : AndroidViewModel(context) {


    private val razzaRepo by lazy { RoomRepository(context) }

    private var categoryDocumentListener: ListenerRegistration? = null
    private var productDocumentListener: ListenerRegistration? = null
    private var wishlistDocumentListener: ListenerRegistration? = null
    private var cartDocumentListener: ListenerRegistration? = null
    private var addressDocumentListener: ListenerRegistration? = null
    private var reviewDocumentListener: ListenerRegistration? = null
    private var orderDocumentListener: ListenerRegistration? = null






    private var _categoriesFB = MutableStateFlow(emptyList<Category>()) //to observe what in the repo
    var categoriesFB: StateFlow<List<Category>> = _categoriesFB.asStateFlow()//to show in the composable

    private var _usersFB = MutableStateFlow(emptyList<User>()) //to observe what in the repo
    var usersFB: StateFlow<List<User>> = _usersFB.asStateFlow()//to show in the composable

    private var _ordersFB = MutableStateFlow(emptyList<Order>()) //to observe what in the repo
    var ordersFB: StateFlow<List<Order>> = _ordersFB.asStateFlow()//to show in the composable

    var _productsFB = MutableStateFlow(emptyList<Product>()) //to observe what in the repo
    var productsFB: StateFlow<List<Product>> = _productsFB.asStateFlow()//to show in the composable

    private var _orderItemsFB = MutableStateFlow(emptyList<OrderItem>()) //to observe what in the repo
    var orderItemsFB: StateFlow<List<OrderItem>> = _orderItemsFB.asStateFlow()//to show in the composable

    private var _addressesFB = MutableStateFlow(emptyList<Address>()) //to observe what in the repo
    var addressesFB: StateFlow<List<Address>> = _addressesFB.asStateFlow()

    private var _cartItemsFB = MutableStateFlow(emptyList<Item>()) //to observe what in the repo
    var cartItemsFB: StateFlow<List<Item>> = _cartItemsFB.asStateFlow()


    private var _wishlistItemsFB = MutableStateFlow(emptyList<WishListItem>()) //to observe what in the repo
    var wishlistItemsFB: StateFlow<List<WishListItem>> = _wishlistItemsFB.asStateFlow()

    private var _reviewsFB = MutableStateFlow(emptyList<Review>()) //to observe what in the repo
        var reviewsFB: StateFlow<List<Review>> = _reviewsFB.asStateFlow()

   //3-get data from the firebase and store it in an array to use it in the screens
    private fun getCategories() = viewModelScope.launch(Dispatchers.IO) {
        _categoriesFB.value=razzaRepo.getAllCategories()
       categoriesFB.value.forEach{c -> Log.v("TAG25",c.name)}
   }

    private fun getReviews() = viewModelScope.launch(Dispatchers.IO) {
        _reviewsFB.value=razzaRepo.getAllReviews()
    }
    private fun getCartItems() = viewModelScope.launch(Dispatchers.IO) {
        _cartItemsFB.value=razzaRepo.getAllCartItems()
    }
    private fun getWishlistItems() = viewModelScope.launch(Dispatchers.IO) {
        _wishlistItemsFB.value=razzaRepo.getAllWishlistItems()
    }


    private fun getAddresses() = viewModelScope.launch(Dispatchers.IO) {
        _addressesFB.value=razzaRepo.getAllAddresses()

    }

    //3-get data from the firebase and store it in an array to use it in the screens
    private fun getUsers() = viewModelScope.launch(Dispatchers.IO) {
        _usersFB.value=razzaRepo.getAllUsers()
    }
    //3-get data from the firebase and store it in an array to use it in the screens
    private fun getOrders() = viewModelScope.launch(Dispatchers.IO) {
        _ordersFB.value=razzaRepo.getAllOrders()
    }

    private fun getProducts() = viewModelScope.launch(Dispatchers.IO) {
        _productsFB.value=razzaRepo.getAllProducts()
//        productsFB.value.forEach{p -> Log.v("TAG25",p.title)}

    }

    private fun getOrderItems() = viewModelScope.launch(Dispatchers.IO) {
        _orderItemsFB.value=razzaRepo.getAllOrderedItems()
    }

    private val _product = MutableStateFlow(Product("", "",0.0,"","","",0.0))
    val product: StateFlow<Product> = _product.asStateFlow()

    fun setCurrentProduct(product: Product){
        _product.value = product
    }

    fun updateTitle(title: String){
        _product.value = _product.value.copy(title = title)
    }
    fun updateDesc(desc: String){
        _product.value = _product.value.copy(description = desc)
    }
    fun updatePrice(price: Double){
        _product.value = _product.value.copy(price = price)
    }
    fun updateCategory(category: String){
        _product.value = _product.value.copy(category = category)
    }
    fun updateImage(image: String){
        _product.value = _product.value.copy(image = image)
    }
    //…others
    private var _products = MutableStateFlow(emptyList<Product>()) //to observe what in the repo
    var products: StateFlow<List<Product>> = _products.asStateFlow()//to show in the composable


    fun addProduct(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        razzaRepo.addProduct(product)
        productListener()
    }
    fun addNewProduct(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        razzaRepo.addNewProduct(product)
        productListener()
    }
    fun updateProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO){
            razzaRepo.updateProduct(product)
            productListener()
        }
    }

    fun getProduct(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            razzaRepo.getProduct(id)
        }
    }
    fun deleteProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            razzaRepo.deleteProduct(product)
            productListener()
        }
    }
    fun orderProductAsc() {
        viewModelScope.launch(Dispatchers.IO) {
            razzaRepo.productsDocumentRef.orderBy("price", Query.Direction.ASCENDING)
                .addSnapshotListener { snapshot, e ->
                    if (e != null) return@addSnapshotListener
                    _productsFB.value = snapshot!!.toObjects(Product::class.java)
                }
        }
    }
//
    fun orderProductDes() {
    viewModelScope.launch(Dispatchers.IO) {
        razzaRepo.productsDocumentRef.orderBy("price", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, e ->
                if (e != null) return@addSnapshotListener
                _productsFB.value = snapshot!!.toObjects(Product::class.java)
            }
    }
    }
    //Categories:
    private val _category = MutableStateFlow(Category("", ""))
    val category: StateFlow<Category> = _category.asStateFlow()
    private var _categories = MutableStateFlow(emptyList<Category>()) //to observe what in the repo
    var ccategories: StateFlow<List<Category>> = _categories.asStateFlow()//to show in the composable

    fun setCurrentCategory(category: Category){
        _category.value = category
    }


    fun addCategory(category: Category) = viewModelScope.launch(Dispatchers.IO) {
        razzaRepo.addCategory(category)
    //registerCategoryListener()
}
     fun addNewCategory(category: Category)= viewModelScope.launch(Dispatchers.IO) {
         razzaRepo.addNewCategory(category)
         registerCategoryListener()
     }

    fun deleteCategory(category: Category){
        viewModelScope.launch(Dispatchers.IO) {
            razzaRepo.deleteCategory(category)
        }
    }

    //Reviews:

    private val _review = MutableStateFlow(Review("","","","",0))
    val review: StateFlow<Review> = _review.asStateFlow()
    private var _reviews = MutableStateFlow(emptyList<Review>()) //to observe what in the repo
    var reviews: StateFlow<List<Review>> = _reviews.asStateFlow()//to show in the composable
    fun setCurrentReview(review: Review){
        _review.value = review
    }

    fun addReview(review: Review) = viewModelScope.launch(Dispatchers.IO) {
        razzaRepo.addReview(review)
    reviewListener()
    }

    fun deleteReview(review: Review){
        viewModelScope.launch(Dispatchers.IO) {
            razzaRepo.deleteReview(review)
            reviewListener()
        }
    }
    fun updateReview(review: Review){
        viewModelScope.launch(Dispatchers.IO){
            razzaRepo.updateReview(review)
            reviewListener()
        }
    }
    fun updateReviewText(text: String){
        _review.value = _review.value.copy(text = text)
    }
    fun updateReviewRate(rating: Int){
        _review.value = _review.value.copy(rating = rating)
    }

//Users:
    private val _user = MutableStateFlow(User("","","","",""))
    val user: StateFlow<User> = _user.asStateFlow()
    private var _users = MutableStateFlow(emptyList<User>()) //to observe what in the repo
    var users: StateFlow<List<User>> = _users.asStateFlow()//to show in the composable

    fun setCurrentUser(user: User){
        _user.value = user
    }

    fun getUser(id:String)=viewModelScope.launch(Dispatchers.IO){razzaRepo.getUser(id)}
    fun addUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        razzaRepo.addUser(user)
    }

    //Address:
    private val _address = MutableStateFlow(Address("","","","",0,0,0))
    val address: StateFlow<Address> = _address.asStateFlow()
    private var _addresses = MutableStateFlow(emptyList<Address>()) //to observe what in the repo
    var addresses: StateFlow<List<Address>> = _addresses.asStateFlow()//to show in the composable

    fun setCurrentAddress(address: Address){
        _address.value = address
    }
//    fun getAllAddresses(){
//        viewModelScope.launch{//use this what to call all suspended methods
//            razzaRepo.getAllAddresses().collect{//collect is used because of the flow, it is a list of products
//                _addresses.value = it// products is a state of flow
//            }
//        }
//    }
    fun updateAddress(add: Address){
        viewModelScope.launch(Dispatchers.IO){
            razzaRepo.updateAddress(add)
            addressListener()
        }
    }
    fun getAddress(id:String)=viewModelScope.launch(Dispatchers.IO){razzaRepo.getAddress(id)}
    fun addAddress(address: Address) = viewModelScope.launch(Dispatchers.IO) {
        razzaRepo.addAddress(address)
        addressListener()
    }
    fun updateAddName(name: String){
        _address.value = _address.value.copy(name = name)
        addressListener()
    }
    fun updateAddCity(city: String){
        _address.value = _address.value.copy(city = city)
        addressListener()
    }
    fun updateAddZone(zone: Int){
        _address.value = _address.value.copy(zoneNo = zone)
        addressListener()
    }
    fun updateAddStreet(street: Int){
        _address.value = _address.value.copy(streetNo = street)
        addressListener()
    }
    fun updateAddBuilding(building: Int){
        _address.value = _address.value.copy(buildingNo = building)
        addressListener()
    }
    fun deleteAddress(address: Address){
        viewModelScope.launch(Dispatchers.IO) {
            razzaRepo.deleteAddress(address)
            addressListener()
        }
    }
//WishList:
private val _wishListItem = MutableStateFlow(WishListItem("","",""))
    val wishListItem: StateFlow<WishListItem> = _wishListItem.asStateFlow()
    private var _wishListItems = MutableStateFlow(emptyList<WishListItem>()) //to observe what in the repo
    var wishListItems: StateFlow<List<WishListItem>> = _wishListItems.asStateFlow()//to show in the composable

    private var _wishListProducts = MutableStateFlow(emptyList<Product>()) //to observe what in the repo
    var wishListProducts: StateFlow<List<Product>> = _wishListProducts.asStateFlow()//to show in the composable


    fun addWishListItem(item: WishListItem) = viewModelScope.launch(Dispatchers.IO) {
        razzaRepo.addWishlistItem(item)
        wishlistListener()
    }
    fun deleteWishListItem(item: WishListItem){
        viewModelScope.launch(Dispatchers.IO) {
            razzaRepo.deleteWishlistItem(item)
            wishlistListener()
        }
    }


    fun setCurrentWishListItem(item: WishListItem){
        _wishListItem.value = item
    }
    //Cart:
    private val _cartItem = MutableStateFlow(Item("", "","",0.0, 0, 0))
    val cartItem: StateFlow<Item> = _cartItem.asStateFlow()
    private var _cartItems = MutableStateFlow(emptyList<Item>()) //to observe what in the repo
    var cartItems: StateFlow<List<Item>> = _cartItems.asStateFlow()//to show in the composable

//    private var _cartProducts = MutableStateFlow(emptyList<Product>()) //to observe what in the repo
//    var cartProducts: StateFlow<List<Product>> = _cartProducts.asStateFlow()//to show in the composable
    fun addCartItem(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        razzaRepo.addCartItem(item)
    cartListener()
    }
    fun deleteCartItem(item: Item){
        viewModelScope.launch(Dispatchers.IO) {
            razzaRepo.deleteCartItem(item)
            cartListener()
        }
    }
    fun getUserCartItems(uid:String){
        viewModelScope.launch{//use this what to call all suspended methods
            razzaRepo.getUserCartItems(uid)
        }
    }


    private val _cart = MutableStateFlow(ShoppingCart("" ,"",0.0))
    val cart: StateFlow<ShoppingCart> = _cart.asStateFlow()
    private var _carts = MutableStateFlow(emptyList<ShoppingCart>()) //to observe what in the repo
    var carts: StateFlow<List<ShoppingCart>> = _carts.asStateFlow()
    fun setCurrentCart(cart: ShoppingCart){
        _cart.value = cart
    }
    //Orders:
    private val _order = MutableStateFlow(Order("" ,"",0.0,"", ""))
    val order: StateFlow<Order> = _order.asStateFlow()
    private var _orders = MutableStateFlow(emptyList<Order>()) //to observe what in the repo
    var orders: StateFlow<List<Order>> = _orders.asStateFlow()
    fun setCurrentOrder(order: Order){
        _order.value = order
    }
    fun addOrder(order: Order) = viewModelScope.launch(Dispatchers.IO) {
        razzaRepo.addOrder(order)
    }
    fun addNewOrder(order: Order) = viewModelScope.launch(Dispatchers.IO) {
        razzaRepo.addNewOrder(order)
        orderListener()
    }
//    fun getAllOrders(){
//        viewModelScope.launch{//use this what to call all suspended methods
//            razzaRepo.getAllOrders().collect{//collect is used because of the flow, it is a list of products
//                _orders.value = it// products is a state of flow
//            }
//        }
//    }
    fun getUserOrders(uid:String){
        viewModelScope.launch{//use this what to call all suspended methods
            razzaRepo.getUserOrders(uid)
        }
    }
    //Ordered items:
    private val _orderItem = MutableStateFlow(OrderItem("","",""))
    val orderItem: StateFlow<OrderItem> = _orderItem.asStateFlow()
    private var _orderedItems = MutableStateFlow(emptyList<OrderItem>()) //to observe what in the repo
    var orderedItems: StateFlow<List<OrderItem>> = _orderedItems.asStateFlow()
    fun setCurrentOrderItem(order: OrderItem){
        _orderItem.value = order
    }
//    fun getAllOrderedItems(){
//        viewModelScope.launch{//use this what to call all suspended methods
//            razzaRepo.getAllOrderedItems().collect{//collect is used because of the flow, it is a list of products
//                _orderedItems.value = it// products is a state of flow
//            }
//        }
//    }


    init {
        getReviews()
        getOrderItems()
        getCategories()
        getProducts()
        getUsers()
        getOrders()
        getCartItems()
        getWishlistItems()
        getAddresses()
    }
    private fun registerCategoryListener() {
        categoryDocumentListener = razzaRepo.catagoryDocumentRef
           // .document(categoryId)
            .addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.d("Category Listener", error.message.toString())
                return@addSnapshotListener
            }
            _categoriesFB.value = snapshot!!.toObjects(Category::class.java)
        }

    }
    private fun wishlistListener() {
        wishlistDocumentListener = razzaRepo.wishlistDocumentRef.addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.d("Wishlist Listener", error.message.toString())
                    return@addSnapshotListener
                }
                _wishlistItemsFB.value = snapshot!!.toObjects(WishListItem::class.java)
            }

    }
    private fun cartListener() {
        cartDocumentListener = razzaRepo.cartItemsDocumentRef.addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.d("cart Listener", error.message.toString())
                    return@addSnapshotListener
                }
                _cartItemsFB.value = snapshot!!.toObjects(Item::class.java)
            }

    }
    private fun productListener() {
        productDocumentListener = razzaRepo.productsDocumentRef
            // .document(categoryId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.d("product Listener", error.message.toString())
                    return@addSnapshotListener
                }
                _productsFB.value = snapshot!!.toObjects(Product::class.java)
            }

    }
    private fun addressListener() {
        addressDocumentListener = razzaRepo.addressDocumentRef.addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.d("address Listener", error.message.toString())
                    return@addSnapshotListener
                }
                _addressesFB.value = snapshot!!.toObjects(Address::class.java)
            }

    }
    private fun reviewListener() {
        reviewDocumentListener = razzaRepo.reviewsDocumentRef.addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.d("review Listener", error.message.toString())
                    return@addSnapshotListener
                }
                _reviewsFB.value = snapshot!!.toObjects(Review::class.java)
            }

    }
    private fun orderListener() {
        orderDocumentListener = razzaRepo.ordersDocumentRef.addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.d("order Listener", error.message.toString())
                    return@addSnapshotListener
                }
                _ordersFB.value = snapshot!!.toObjects(Order::class.java)
            }

    }


    override fun onCleared() {
        super.onCleared()
        categoryDocumentListener?.remove()
        addressDocumentListener?.remove()
        cartDocumentListener?.remove()
        orderDocumentListener?.remove()
        productDocumentListener?.remove()
        reviewDocumentListener?.remove()
        wishlistDocumentListener?.remove()
    }

    fun clearData() {
        _addressesFB.value = emptyList()
        _cartItemsFB.value = emptyList()
        _wishlistItemsFB.value = emptyList()
        _ordersFB.value = emptyList()
        _orderItemsFB.value = emptyList()
        _reviewsFB.value = emptyList()
    }

    }

