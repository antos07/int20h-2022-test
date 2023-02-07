"""
This module provides simple json serializers for some models as well
as a JSONObject and a JSONDict typehints.
"""

import typing

from ..models import Meal, MealIngredientMeasure, Ingredient

__all__ = ['serialize_meal', 'serialize_ingredient', 'JSONObject', 'JSONDict']

JSONObject: typing.TypeAlias = int | float | str | bool | list['JSONObject'] | dict[str, 'JSONObject'] | None
JSONDict: typing.TypeAlias = dict[str, 'JSONObject']


def serialize_meal(meal: Meal, ingredient_measures: list[MealIngredientMeasure]) -> JSONDict:
    """
    Serializes a meal and its ingredient_measures into a json object that
    has the following fields:
        - id -- integer.
        - name -- string
        - instructions -- string
        - image_url -- string or null
        - ingredient_measures - array of the objects with the following
          fields:
            - id -- integer
            - name -- string
            - description -- string or null
            - image_url -- string or null
            - measure -- string
    """
    ingredient_measures_serialized = [
        _serialize_ingredient_measure(ingredient_measure)
        for ingredient_measure in ingredient_measures
    ]
    meal_dict = {
        'id': meal.id,
        'name': meal.name,
        'instructions': meal.instructions,
        'image_url': meal.small_image_url,  # There is no need to pass url a for a full image
        'ingredient_measures': ingredient_measures_serialized
    }
    return meal_dict


def serialize_ingredient(ingredient: Ingredient) -> JSONDict:
    """
    Serializes a meal and its ingredient_measures into a json object that
    has the following fields:
        - id -- integer
        - name -- string
        - description -- string or null
        - image_url -- string or null
    """
    return {
        'id': ingredient.id,
        'name': ingredient.name,
        'description': ingredient.description,
        'image_url': ingredient.small_image_url
    }


def _serialize_ingredient_measure(ingredient_measure: MealIngredientMeasure) -> JSONDict:
    """Extends serialization of the corresponding ingredient with the measure field"""
    serialized_ingredient = serialize_ingredient(ingredient_measure.ingredient)
    serialized_ingredient['measure'] = ingredient_measure.measure
    return serialized_ingredient
