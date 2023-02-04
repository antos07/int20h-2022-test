from django.views.generic import ListView, DetailView

from .models import Meal


class MealListView(ListView):
    model = Meal


class MealDetailView(DetailView):
    model = Meal
