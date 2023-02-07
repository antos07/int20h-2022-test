from django.db import models


class Meal(models.Model):
    """Represents a meal recipe."""
    name = models.CharField(max_length=100)
    instructions = models.TextField()
    image_url = models.URLField(max_length=200, null=True, blank=True)
    small_image_url = models.URLField(max_length=200, null=True, blank=True)
    video_instructions_url = models.URLField(max_length=200, null=True, blank=True)

    @property
    def youtube_embed_url(self) -> str | None:
        try:
            return self.video_instructions_url.replace('watch?v=', 'embed/')
        except AttributeError:
            return None

    def __str__(self):
        return self.name


class IngredientCategory(models.Model):
    """Represents an ingredient category"""
    name = models.CharField(max_length=100, unique=True)

    def __str__(self):
        return self.name


class Ingredient(models.Model):
    """Represents an ingredient"""
    name = models.CharField(max_length=100, unique=True)
    description = models.TextField(null=True, blank=True)
    image_url = models.URLField(max_length=200, null=True, blank=True)
    small_image_url = models.URLField(max_length=200, null=True, blank=True)
    meals = models.ManyToManyField(Meal, through='MealIngredientMeasure')
    category = models.ForeignKey(IngredientCategory, on_delete=models.SET_NULL, null=True, blank=True)

    def __str__(self):
        return self.name


class MealIngredientMeasure(models.Model):
    """An ingredient measure for a specific meal"""

    meal = models.ForeignKey(Meal, on_delete=models.CASCADE)
    ingredient = models.ForeignKey(Ingredient, on_delete=models.CASCADE)
    measure = models.CharField(max_length=100)

    class Meta:
        constraints = [
            models.UniqueConstraint(fields=['meal', 'ingredient'], name='unique_meal_ingredient_measure')
        ]

    def __str__(self):
        return f'"{self.ingredient}" in "{self.meal}": {self.measure}'


class TheMealDBMeal(models.Model):
    """Stores the meal's id at TheMealDB"""

    themealdb_id = models.PositiveBigIntegerField(primary_key=True)
    meal = models.OneToOneField(Meal, on_delete=models.CASCADE)


class TheMealDBIngredient(models.Model):
    """Stores the ingredient's id at TheMealDB"""

    themealdb_id = models.PositiveBigIntegerField(primary_key=True)
    ingredient = models.OneToOneField(Ingredient, on_delete=models.CASCADE)


class TheMealDBIngredientCategory(models.Model):
    """Stores the ingredient category's name at TheMealDB"""

    temealdb_name = models.CharField(max_length=30, primary_key=True)
    category = models.OneToOneField(IngredientCategory, on_delete=models.CASCADE)


class User(models.Model):
    uuid = models.UUIDField(primary_key=True)
    created_at = models.DateTimeField(auto_now=True)


class Fridge(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    ingredients = models.ManyToManyField(Ingredient)


class Basket(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    meals = models.ManyToManyField(Meal)


# Implementing MySQL fulltext search lookup.
# Code taken from: https://docs.djangoproject.com/en/stable/releases/1.10/#search-lookup-replacement
class Search(models.Lookup):  # noqa
    lookup_name = 'search'

    def as_mysql(self, compiler, connection):
        lhs, lhs_params = self.process_lhs(compiler, connection)
        rhs, rhs_params = self.process_rhs(compiler, connection)
        params = lhs_params + rhs_params
        return 'MATCH (%s) AGAINST (%s IN BOOLEAN MODE)' % (lhs, rhs), params


models.CharField.register_lookup(Search)
models.TextField.register_lookup(Search)
