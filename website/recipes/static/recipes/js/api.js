class Api {
    constructor(baseUrl) {
        this.baseUrl = baseUrl
    }

    getFridgeContent() {
        return []
    }

    addIngredientToFridge(ingredientId) {
        return true
    }

    removeIngredientFromFridge(ingredientId) {
        return true
    }
}
