import string
from django.db import transaction

from . import db
from .constants import THEMEALDB_API_URL
from .themealdbapi import TheMealDBApi

__all__ = ['synchronize']


@transaction.atomic()
def synchronize(api_url: str = THEMEALDB_API_URL) -> None:
    """Synchronizes the local meal database with TheMealDB"""
    api = TheMealDBApi(api_url)
    _sync_ingredients(api)
    _sync_meals(api)


def _sync_ingredients(api: TheMealDBApi) -> None:
    """Synchronizes ingredients in the local DB with TheMealDB"""
    themealdb_ingredients = api.get_ingredient_list()
    for themealdb_ingredient in themealdb_ingredients:
        db.save_themealdb_ingredient(themealdb_ingredient)


def _sync_meals(api: TheMealDBApi) -> None:
    """Synchronizes meals in the local DB with TheMealDB"""
    # Getting all meals by each first letters. This is the only way to
    # get a full list of meals.
    for letter in string.ascii_lowercase:
        meal_list = api.get_meal_list_by_first_letter(letter)
        for meal in meal_list:
            db.save_themealdb_meal(meal)
