# Generated by Django 4.1.6 on 2023-02-03 20:34

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Ingredient',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=100, unique=True)),
                ('description', models.TextField(blank=True, null=True)),
                ('image_url', models.URLField(blank=True, null=True)),
            ],
        ),
        migrations.CreateModel(
            name='IngredientCategory',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=100, unique=True)),
            ],
        ),
        migrations.CreateModel(
            name='Meal',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=100)),
                ('instructions', models.TextField()),
                ('image_url', models.URLField(blank=True, null=True)),
                ('video_instructions_url', models.URLField(blank=True, null=True)),
            ],
        ),
        migrations.CreateModel(
            name='MealIngredientMeasure',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('measure', models.CharField(max_length=100)),
                ('ingredient', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='recipes.ingredient')),
                ('meal', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='recipes.meal')),
            ],
        ),
        migrations.AddField(
            model_name='ingredient',
            name='category',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.SET_NULL, to='recipes.ingredientcategory'),
        ),
        migrations.AddField(
            model_name='ingredient',
            name='meals',
            field=models.ManyToManyField(through='recipes.MealIngredientMeasure', to='recipes.meal'),
        ),
        migrations.AddConstraint(
            model_name='mealingredientmeasure',
            constraint=models.UniqueConstraint(fields=('meal', 'ingredient'), name='unique_meal_ingredient_measure'),
        ),
    ]
