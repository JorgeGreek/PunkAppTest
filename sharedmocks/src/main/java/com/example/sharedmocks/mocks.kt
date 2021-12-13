import com.example.domain.BeersItem
import com.example.domain.ResponseBeers


val mockedBeerData = BeersItem(
    description = "Our Unleash the Yeast series was an epic experiment into the differences in aroma and flavour provided by switching up your yeast. We brewed up a wort with a light caramel note and some toasty biscuit flavour, and hopped it with Amarillo and Centennial for a citrusy bitterness. Everything else is down to the yeast. Pilsner yeast ferments with no fruity esters or spicy phenols, although it can add a hint of butterscotch.",
    id = 4,
    image_url = "https://images.punkapi.com/v2/4.png",
    name = "Pilsen Lager",
    abv = 6.3,
    tagline = "Unleash the Yeast Series.",
    first_brewed = "09/2013"
)



val mockedResponse = listOf<BeersItem>(mockedBeerData)