from django.views.generic import ListView

from .models import Meal


class MealListView(ListView):
    model = Meal
