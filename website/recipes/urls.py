from django.urls import path

from . import views

app_name = 'recipes'

urlpatterns = [
    path('recipes/', views.MealListView.as_view(), name='index'),
    path('recipes/<int:pk>/', views.MealDetailView.as_view(), name='detail'),
    path('fridge/', views.IngredientListView.as_view(), name='fridge'),
]
