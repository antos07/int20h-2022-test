class Api {
    constructor(baseUrl) {
        this.baseUrl = baseUrl
    }

    getFridgeContent() {
        return [{"id": 2599, "name": "Sardines", "image_url": "https://www.themealdb.com/images/ingredients/Sardines-Small.png"}, {"id": 2, "name": "ingredient 2",
            "image_url": null}]
    }

    addIngredientToFridge(ingredientId) {
        return true
    }

    removeIngredientFromFridge(ingredientId) {
        return true
    }
}
