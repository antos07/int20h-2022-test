from django.urls import path

from . import views

app_name = 'recipes'

urlpatterns = [
    path('recipes/', views.MealListView.as_view(), name='meal_list'),
    path('recipes/<int:pk>/', views.MealDetailView.as_view(), name='meal_details'),
    path('fridge/', views.IngredientListView.as_view(), name='fridge'),
    path('basket/', views.BasketView.as_view(), name='basket'),
]
