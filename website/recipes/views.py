from django.http import HttpRequest, JsonResponse
from django.shortcuts import get_object_or_404
from django.views.generic import ListView, DetailView, TemplateView

from .models import Meal, MealIngredientMeasure, Ingredient
from .utils import serializers


class MealListView(ListView):
    model = Meal
    paginate_by = 50

    def get_queryset(self):
        meal_queryset = Meal.objects

        # Filtering search results, if query is present.
        if search_query := self.request.GET.get('q'):
            meal_queryset = meal_queryset.filter(name__search=search_query)

        return meal_queryset.order_by('name')

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)

        # Default value for search_query
        context['search_query'] = ''

        # Setting search query from query parameter, if query is present.
        if search_query := self.request.GET.get('q'):
            context['search_query'] = search_query

        return context


class MealDetailView(DetailView):
    model = Meal

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)

        # Adding MealIngredientMeasure list for the meal
        context['measures'] = MealIngredientMeasure.objects.filter(meal=context['meal'])

        return context


class IngredientListView(ListView):
    model = Ingredient
    ordering = ['-category', 'name']


class BasketView(TemplateView):
    template_name = 'recipes/basket.html'


def get_meal_json(request: HttpRequest, meal_id: int):
    """Responses with json-serialized meal. Look at
    serializers.serialize_meal doc for schema info"""
    meal = get_object_or_404(Meal, pk=meal_id)
    ingredient_measures = MealIngredientMeasure.objects.filter(meal=meal).all()

    serialized_meal = serializers.serialize_meal(meal, ingredient_measures)
    return JsonResponse(serialized_meal)
