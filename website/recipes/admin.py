from django.contrib import admin

from . import models


# Default ModelAdmin implementation does not show many-to-many
# relationships via intermediary models. So this requires using of
# inline admin models. As Meal model has many-to-many relationship
# to Ingredient through MealIngredientMeasure, I have to explicitly
# define MealIngredientMeasureInline admin model for measures, which
# is included into MealAdmin model.
# Source: https://docs.djangoproject.com/en/dev/ref/contrib/admin/#working-with-many-to-many-intermediary-models
class MealIngredientMeasureInline(admin.TabularInline):
    model = models.MealIngredientMeasure
    extra = 0


class MealAdmin(admin.ModelAdmin):
    inlines = [MealIngredientMeasureInline]


admin.site.register(models.Meal, MealAdmin)
admin.site.register(models.Ingredient)
admin.site.register(models.MealIngredientMeasure)
admin.site.register(models.IngredientCategory)
