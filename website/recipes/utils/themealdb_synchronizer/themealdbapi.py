import requests

from . import typehints
from .constants import THEMEALDB_API_URL


class TheMealDBApi:
    """A simple wrapper around the API of TheMealDB"""

    def __init__(self, api_url: str = THEMEALDB_API_URL):
        self.api_url = api_url
        self.api_session = requests.Session()

    def get_ingredient_list(self) -> list[typehints.Ingredient]:
        """Lists all the ingredients"""
        response = self._request_endpoint('list.php?i=list')
        return response['meals'] or []  # Api always returns data as the 'meals' field

    def get_meal_list_by_first_letter(self, first_letter: str) -> list[typehints.Meal]:
        """Lists all ingredients starting by first_letter"""
        if len(first_letter) > 1 or not 'a' <= first_letter <= 'z':
            raise ValueError('A single lowercase letter is expected.')

        response = self._request_endpoint(f'search.php?f={first_letter}')
        return response['meals'] or []

    def _request_endpoint(self, endpoint: str) -> dict[str]:
        """Requests given endpoint and decodes results from json"""
        response = self.api_session.get(self.api_url + endpoint)
        response.raise_for_status()
        return response.json()
