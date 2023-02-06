from django.views.generic import ListView, DetailView

from .models import Meal, MealIngredientMeasure


class MealListView(ListView):
    model = Meal


class MealDetailView(DetailView):
    model = Meal

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)

        # Adding MealIngredientMeasure list for the meal
        context['measures'] = MealIngredientMeasure.objects.filter(meal=context['meal'])

        return context
