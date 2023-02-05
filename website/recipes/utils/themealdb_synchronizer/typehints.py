from typing import TypedDict

__all__ = ['Ingredient', 'Meal']

OptionalString = str | None


class Ingredient(TypedDict):
    idIngredient: str
    strIngredient: str
    strDescription: OptionalString
    strType: OptionalString


class Meal(TypedDict):
    idMeal: str
    strMeal: str
    strDrinkAlternate: OptionalString
    strCategory: OptionalString
    strArea: OptionalString
    strInstructions: str
    strMealThumb: str
    strTags: OptionalString
    strYoutube: OptionalString
    strIngredient1: OptionalString
    strIngredient2: OptionalString
    strIngredient3: OptionalString
    strIngredient4: OptionalString
    strIngredient5: OptionalString
    strIngredient6: OptionalString
    strIngredient7: OptionalString
    strIngredient8: OptionalString
    strIngredient9: OptionalString
    strIngredient10: OptionalString
    strIngredient11: OptionalString
    strIngredient12: OptionalString
    strIngredient13: OptionalString
    strIngredient14: OptionalString
    strIngredient15: OptionalString
    strIngredient16: OptionalString
    strIngredient17: OptionalString
    strIngredient18: OptionalString
    strIngredient19: OptionalString
    strIngredient20: OptionalString
    strMeasure1: OptionalString
    strMeasure2: OptionalString
    strMeasure3: OptionalString
    strMeasure4: OptionalString
    strMeasure5: OptionalString
    strMeasure6: OptionalString
    strMeasure7: OptionalString
    strMeasure8: OptionalString
    strMeasure9: OptionalString
    strMeasure10: OptionalString
    strMeasure11: OptionalString
    strMeasure12: OptionalString
    strMeasure13: OptionalString
    strMeasure14: OptionalString
    strMeasure15: OptionalString
    strMeasure16: OptionalString
    strMeasure17: OptionalString
    strMeasure18: OptionalString
    strMeasure19: OptionalString
    strMeasure20: OptionalString
    strSource: OptionalString
    strImageSource: OptionalString
    strCreativeCommonsConfirmed: OptionalString
    dateModified: OptionalString
