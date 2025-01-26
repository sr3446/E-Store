---
geometry: margin=1in
---
# Monkey Mode Design Documentation

## Team Information
* Team name: Monkey Mode
* Team members
  * Liam Cummings
  * Susant Raut
  * Samuel Maselli
  * Jason Sigal

## Executive Summary

We created a storefront that sells previously extinct animals to people around the world. Our site has both admin and customer functionalities including searching for products, adding items to the cart, and purchasing.

### Purpose

The purpose of our project is to create a product that hits all MVP requirements, as well as creating a few extra 10% features to improve our site drastically. We intend to create a site that is user friendly, visually appealing, and allows buyers to return again in the future. We intend to add extra features such as a wishlist and a review system to improve our customer-site interaction and create a site people will enjoy using.

## Requirements

In our project, we must have different site views depending on who is logged in. If a customer is logged in, they must be able to see a list of all products, they must be able to search from the list of products, they must be able to see both their wishlist and cart, and be able to add any products to either of those pages. And if an admin is logged in, they must not be able to see any cart or wishlist pages, as well as have the ability to add, edit, or remove any products from the site.


### Definition of MVP

We consider our MVP to be a site that allows users to easily browse our list of products, and locate what they are looking for; as well as have a cart that is persistent between repeated logins, and that allows customers to easily check out the contents within their cart. Additionally, our site must be able to allow an administrator to log into their account, and be able to edit, add, or remove any products in our store. In our site, we also must have the ability for customers to add items to a wishlist, be able to remove items from their wishlist, as well as being able to send items from the wishlist to their cart easily. Finally, our MVP must be able to take product reviews from customers, and have them displayed on their respective product pages for prospective customers to see before their purchases. Our MVP must also have differing views depending on if the user is logged in as a customer, or as an admin (no cart/wishlist for admins, no product editing for customers).

### MVP Features
__Browse__ :<br> 
	Browse Products: As a buyer I want to see a list of products so that I choose what to Purchase.<br> 
	Search Products: As a buyer I want to be able to search through the products so I can find what I'm looking for.<br>
	See Details: As a buyer I want to be able to see the price of an item, stock, and reviews, so I can tell whether I'm making the right purchase.<br> 
__Review__:<br>
	Create Review: As a buyer I want to be able to review products I have bought so I can inform others that they’re making the right decision.<br> 
	Edit Review: As a Owner I want to Edit my reviews so I can have the most up to date data.<br> 
	Censor Review: As an owner, I want to delete peoples reviews so that I can stop people from writing bad things.<br> 
__Epic: Inventory Management__:<br>
	Add Products: As a Owner I want to be able to add Products so that people can purchase them.<br> 
	Remove Products: As a Owner I want to be able to remove product stock so that buyers are unable to purchase products that are unavailable.<br>
	Edit Product Information: As a Owner I want to be able to edit product info so that the page gives the buyer a better idea of what they're 		purchasing. (Edit description, price, images).<br> 

__Wishlist__:<br>
	Add Items: As a Buyer I want to be able to add items into a wishlist so I can purchase these items later.<br>
	Remove Items: As a Buyer I want to remove items from my wish list so that I can indicate I don't wish for them anymore.<br>
	Moving from wishlist to shopping cart: As a buyer, I want to move my items from my wishlist to my shopping cart to purchase them.<br> 

__Cart Management__:<br> 
	Add to Cart: As a Buyer I want to be able to add products to my cart so that they can be purchased.<br>
	Remove From Cart: As a Buyer I want to be able to remove items from my cart so that I can not buy them.<br> 
	Purchase Cart: As a Buyer I want to be able to purchase my cart so that I can have them.<br>

### Roadmap of Enhancements
1. __Browse__:<br>
	The first thing we want to accomplish is to have a functional product list that allows us to navigate around the site and have tangible items to work 	      with.
2. __Cart Management__:<br>
	The next thing we add is going to be the cart, along with its functionality. Once we have the basic product list and cart working, we can start 	working on the more intricate parts of the site.
3. __Inventory Management__:<br>
	After the cart is functioning, we need to implement the admin’s ability to manage the products on the site.
4. __UI Development__:<br>
	Now that we have the basic site functioning, our next goal is to improve upon the site’s UI to make navigation easier.
5. __Wishlist and Epic: Reviews__:<br>
	Once the rest of the main project is functioning, we can begin to add our 10% features. We will likely do these two implementations around the same 	    time as they are largely independent of one another.



## Application Domain

![Domain Model](https://github.com/RIT-SWEN-261-04/team-project-2225-swen-261-04-h-monkeymode/blob/main/docs/SWEN%20Team%20Domain%20Analysis.png?raw=true)

Our Server will admit users based on their username and password. If the username and password are new, a new user will be created, otherwise the user will be admitted to the E-Store with all of their past data saved. Any items in either their wishlist or shopping cart will be saved. The Admin will sign in using their specific username and password. The admin had the ability to add, edit, and remove products from the inventory. The edit included their description, price, stock, image, as well as being able to remove any review about a product they wish. The user is able to browse and search for all available products. Users can add their desired products to either their wishlist or directly to their shopping cart. Items in the wishlist can be removed from the wishlist or added to the shopping cart. The User has full control of adding and removing their desired products to and from wishlist and shopping cart. A User may leave a review on a product if they wish, and other users can see all reviews on a product. When a User purchases their cart, the current inventory of products is changed.

## Architecture and Design

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture.

![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

The e-store web application, is built using the Model–View–ViewModel (MVVM) architecture pattern. 

The Model stores the application data objects including any functionality to provide persistance. 

The View is the client-side SPA built with Angular utilizing HTML, CSS and TypeScript. The ViewModel provides RESTful APIs to the client (View) as well as any logic required to manipulate the data objects from the Model.

Both the ViewModel and Model are built using Java and Spring Framework. Details of the components within these tiers are supplied below.


### Overview of User Interface

The User will first see a login page where they will provide their username and password and click submit, when they click submit they will receive a notification that they have logged in successfully if they already made an account, or they will see a notification saying a new user has been created. The user will then be sent to the homepage (browse) of the e-store, in this page at the top there is a tools bar that includes the button to go to the Homepage, the wishlist page, the cart page, and also have the option to logout of their account. In the browse page the users will have the option to search for a certain animal, and they will also be able to view the animal. When the user clicks on the name of the animal they want to view they will be sent to that animal's product page where they will find all the information about the animal including an image. The user in the product page will have an option to add that animal to their cart, or to add the animal to their wishlist or both. If the user clicks on ‘add to wishlist’ button the user then can click on the ‘Home’ button to return to the Home page, or they can click on the ‘wishlist’ button on the tools bar at the top, the user also has an option to click on the ‘cart’ button where the user will be sent to their cart page where the user can view items in their cart, and have an option to checkout from the cart, if they checkout their cart will be cleared to signify that they have successfully purchased their desired items. In the cart the users will also have an option to remove any undesired item in the cart, the cart page also has a feature where the user will be able to see the total price of all their items. If the user clicks on the ‘wishlist’ button the user will be sent to the wishlist page where the user will see all the items that they added to the wishlist and they will have an option to send the entire wishlist to the cart where they will have the option to purchase the item, in the wishlist the users also have the option to ‘remove’ an item from the wishlist via the ‘remove’ button. After the user is done with their transaction they can then click on the ‘Logout’ button at the top, which will send the user back to the login page. This concludes the different interaction the users can have with our program or e-store. 

### View Tier

Our view tier consists of typescript, HTML, Angular, and css. On each of our pages, we have angular components for different menus, and html tags such as buttons and hyperlinks that develop the general layout and functionality of each individual page. If one of these components are interacted with, then typescript is used by the internals of our site to handle the individual functions our site needs to call to complete a process. The final layout and look of our site are then decided by each page's individual css and HTML files.

On the “Browse” page we have a multitude of products listed out. The name of each product is a button that can be clicked, and when clicked the HTML will use the hyperlink to call the product page for the individual item that has been clicked and the user will then be sent to the product page. Most of the structure for all of the pages are created via the css files that are used by the HTML to put it together, this includes all the buttons, and visual aspects of the webpage.


![Add to Cart Sequence Diagram](https://github.com/RIT-SWEN-261-04/team-project-2225-swen-261-04-h-monkeymode/blob/main/docs/SequenceDiagramCartNew.png)


### ViewModel Tier

The view's communication with the model is primarily operated by 3 classes. The user directly interacts with the html and angular components. Those angular components interact with the AnimalService class on the front end. The AnimalService class is the main communication layer for sending and receiving http requests to the back end. The back end communicates using the EstoreController class, which is mainly for sending and receiving through http. The controller then accesses the DAO for editing the products and user class files in ram. The DAO then finally saves to disk, and the process starts over again going backwards towards the end user.

![ViewModel Sequence Diagram](https://github.com/RIT-SWEN-261-04/team-project-2225-swen-261-04-h-monkeymode/blob/main/docs/sequence%20diagram.png)


### Model Tier

The model has four low coupled classes that each represent real objects or concepts. These are Animal, CartItem, User, and Review. Cart item stores the id of an animal that acts as a pointer to that animal, allowing animals to be stored in the cart more efficiently. Meanwhile, review allows for more organization within the user class by setting each review apart into its own object. Alone these classes do not do much, but they provide the basis for all of the storage systems for saving data into JSON files. The model is the first step in getting data from the files to the end user.

![Model Tier](https://github.com/RIT-SWEN-261-04/team-project-2225-swen-261-04-h-monkeymode/blob/main/docs/model%20class%20uml.png)

### Static Code Analysis/Design Improvements

If we were to continue to work on our product, the next thing we would want to add is the ability for admins to add discount codes to products, and for the users to be able to use these codes on their cart page to decrease the price they would have to pay for specific products. The other thing we would want to add to our project would be the introduction of security features. This would allow us to create a closer relationship with our customers by establishing the safety of our site by preventing hackers from stealing private information such as credit card information, identifying information, among other things.

## Testing
We made the unit test for the model, controller, data persistence, and user stories. To test the model we tested the major aspect of the model which includes the user and the animal classes, to test the animal, user, review, and cartitem class we tested all of its getter and setter methods as well as constructors. To test persistence we used a mock file to store the data, and check if the data is still there as the user surfs through the different functions of the program. To test the user stories we used the acceptance test plan where we check for all of the user stories and to test if they function in our code or not. We had to keep updating the acceptance tests to better match our goals in accordance with backlog refinement.

### Acceptance Testing
Nineteen tests passed while five tests failed and one went untested. The one user story that was untested was one that we labeled as optional and was not implemented. While acceptance testing we found many issues. One major problem was the page not refreshing, while the test technically passed, it was very inconvenient and confusing for the user because it seemed as if their inputs were not working, even though they were. Another issue we encountered was feedback. Sometimes the user would press a button and it would work, but it would give no feedback so it would seem like the button did not work.

### Unit Testing and Code Coverage
We tried our best to test every single method we created, although that task was large. Because we spent so much time writing tests for simpler methods that were likely to work, we did not spend enough time on other ones. Specifically, we have no unit tests for the controller and we couldn’t figure out how to write tests for that. We had to test that manually. We only realized that we had little tests for the controller when we ran code coverage.

Our code coverage percentage was 73% but our goal was 80%. Part of our issue was not understanding testing fully. We tested many branches of code and ensured the code would succeed with when given expected inputs, but never tested to see if the code would fail given inputs that were expected to fail. Essentially, our tests didn't ensure that errors would be thrown when they are supposed to.
