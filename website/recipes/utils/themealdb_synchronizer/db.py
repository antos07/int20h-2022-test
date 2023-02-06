from . import typehints, utils
from ... import models


def save_themealdb_ingredient(themealdb_ingredient_dict: typehints.Ingredient) -> None:
    """Saves an ingredient dict from TheMealDB API into the database"""
    themealdb_ingredient_id = int(themealdb_ingredient_dict['idIngredient'])
    try:
        themealdb_ingredient = models.TheMealDBIngredient.objects.get(pk=themealdb_ingredient_id)
    except models.TheMealDBIngredient.DoesNotExist:
        themealdb_ingredient = models.TheMealDBIngredient.objects.create(
            themealdb_id=themealdb_ingredient_id,
            ingredient=_create_ingredient_from_dict(themealdb_ingredient_dict)
        )
    ingredient = themealdb_ingredient.ingredient

    # Skipping adding of the category.
    if not themealdb_ingredient_dict['strType']:
        return

    try:
        themealdb_category = models.TheMealDBIngredientCategory.objects.get(pk=themealdb_ingredient_dict['strType'])
    except models.TheMealDBIngredientCategory.DoesNotExist:
        themealdb_category = models.TheMealDBIngredientCategory.objects.create(
            temealdb_name=themealdb_ingredient_dict['strType'],
            category=models.IngredientCategory.objects.create(name=themealdb_ingredient_dict['strType'].capitalize())
        )
    ingredient.category = themealdb_category.category
    ingredient.save()


def save_themealdb_meal(themealdb_meal_dict: typehints.Meal) -> None:
    """Saves a meal dict from TheMealDB API into the database"""
    themealdb_meal_id = int(themealdb_meal_dict['idMeal'])
    try:
        themealdb_meal = models.TheMealDBMeal.objects.get(pk=themealdb_meal_id)
    except models.TheMealDBMeal.DoesNotExist:
        themealdb_meal = models.TheMealDBMeal.objects.create(
            themealdb_id=themealdb_meal_id,
            meal=_create_meal_from_dict(themealdb_meal_dict)
        )
    else:
        # Not updating ingredients for already existing meals.
        return

    meal = themealdb_meal.meal
    measures = _extract_normalized_ingredient_measures(themealdb_meal_dict)
    for name, measure in measures.items():
        ingredient, _ = models.Ingredient.objects.get_or_create(name=name)

        measure = models.MealIngredientMeasure(
            meal=meal,
            ingredient=ingredient,
            measure=measure
        )
        measure.save()
    meal.save()


def _create_ingredient_from_dict(themealdb_ingredient_dict: typehints.Ingredient) -> models.Ingredient:
    """Creates an Ingredient model without a category"""
    return models.Ingredient.objects.create(
        name=themealdb_ingredient_dict['strIngredient'].capitalize(),
        description=themealdb_ingredient_dict['strDescription'],
        image_url=utils.ingredient_image_url_by_name(themealdb_ingredient_dict['strIngredient']),
        small_image_url=utils.ingredient_small_image_url_by_name(themealdb_ingredient_dict['strIngredient'])
    )


def _create_meal_from_dict(themealdb_meal_dict: typehints.Meal) -> models.Meal:
    """Creates a Meal model without ingredients"""
    return models.Meal.objects.create(
        name=themealdb_meal_dict['strMeal'].capitalize(),
        instructions=themealdb_meal_dict['strInstructions'],
        image_url=themealdb_meal_dict['strMealThumb'],
        small_image_url=themealdb_meal_dict['strMealThumb'] + '/preview',
        video_instructions_url=themealdb_meal_dict['strYoutube']
    )


def _extract_normalized_ingredient_measures(themealdb_meal_dict: typehints) -> dict[str, str]:
    """Extracts measures from meal dict as dict, where all duplicated measures are added up"""
    measures = {}
    for i in range(1, 21):  # Iterating through all 20 possible ingredients
        name = themealdb_meal_dict[f'strIngredient{i}']  # noqa
        measure = themealdb_meal_dict[f'strMeasure{i}']  # noqa
        if not name:
            break

        name = name.capitalize()
        if name not in measures:
            measures[name] = measure
        else:
            measures[name] += ' + ' + measure
    return measures
