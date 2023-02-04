from django.urls import path

from . import views

app_name = 'recipes'

urlpatterns = [
    path('', views.MealListView.as_view(), name='index'),
    path('<int:pk>/', views.MealDetailView.as_view(), name='detail')
]
