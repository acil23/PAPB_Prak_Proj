package com.papb.restarurantsapp

data class Restaurant(val id: Int,
                      val title: String,
                      val description: String)

val dummyRestaurants = listOf(
    Restaurant(0, "Jupri Naldi", "225150200111015"),
    Restaurant(1, "Alfredo's dishes", "At Alfredo's, we provide the best seafood dishes."),
    Restaurant(2, "Jamie's burgers", "At Jamie's, we serve the best meat and vegan burgers!"),
    Restaurant(3, "Pizza John", "Get the best pizza in town. We also serve vegan burgers!"),
    Restaurant(4, "Dinner in the clouds", "At DitC, you can experience the full immersive dining experience."),
    Restaurant(5, "Eternity lunch", "At Eternity lunch, you will get the best American dishes."),
    Restaurant(6, "Food and coffee", "Drink your coffee and then have lunch at FaC!"),
    Restaurant(7, "Pizza and burgers Brazil", "Get your best burgers and pizza here in Brazil!"),
    Restaurant(8, "Merry Dinner", "Get the Christmas experience at Merry Dinner with Santa!"),
    Restaurant(9, "Uncle Ben's Pizza shop", "Relieve the childhood pizza experience at Uncle Ben's pizza shop, now!"),
    Restaurant(10, "Spicy Grill Toronto", "Get the best culinary experience in Toronto."),
    Restaurant(11, "Cheese Food shop", "Cheesy meals with cheesy ingredients, it's all about cheese!"),
    Restaurant(12, "Mexican spicy Food in Atlanta", "Get your spicy food dose here in Atlanta at Mexican spicy Food!"),
    Restaurant(13, "Spanish Kitchen reinvented", "Check out the true culinary experience with spanish dishes in NYC!"),
    Restaurant(14, "Mike and Ben's food pub", "Come get the best food in New Jersey, now at Mike and Ben's!"),
)
