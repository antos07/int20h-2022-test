import html


def ingredient_image_url_by_name(ingredient_name: str) -> str:
    """Builds ingredient image url by its name"""
    ingredient_name = html.escape(ingredient_name)
    return f'www.themealdb.com/images/ingredients/{ingredient_name}.png'


def ingredient_small_image_url_by_name(ingredient_name: str) -> str:
    """Builds ingredient image url by its name"""
    ingredient_name = html.escape(ingredient_name)
    return f'www.themealdb.com/images/ingredients/{ingredient_name}-Small.png'
