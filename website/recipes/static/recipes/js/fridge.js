function setupFridge() {
    // loading fridge content
    const rawFridgeContent = api.getFridgeContent();
    window.fridgeContent = new Set()
    for (const ingredient of rawFridgeContent) {
        fridgeContent.add(ingredient.id)
    }

    setupHandlers()
    setCurrentIngredients()
}

function setCurrentIngredients() {
    for (const ingredientId of window.fridgeContent) {
        copyIngredientToCurrent(ingredientId)
        markIngredientIsInFridge(ingredientId)
    }
}

function markIngredientIsInFridge(ingredientId) {
    const ingredientButtonElements = document.querySelectorAll(`.ingredientButton_${ingredientId}`)
    for (const ingredientButtonElement of ingredientButtonElements) {
        ingredientButtonElement.innerText = '-'
    }
}

function markIngredientIsOutOfFridge(ingredientId) {
    const ingredientButtonElements = document.querySelectorAll(`.ingredientButton_${ingredientId}`)
    for (const ingredientButtonElement of ingredientButtonElements) {
        ingredientButtonElement.innerText = '+'
    }
}

function addToFridge(ingredientId) {
    if (!api.addIngredientToFridge(ingredientId))
    {
        alert('Something went wrong')
        return
    }
    window.fridgeContent.add(ingredientId)
    markIngredientIsInFridge(ingredientId)
    copyIngredientToCurrent(ingredientId)
}

function removeFromFridge(ingredientId) {
    if (!api.removeIngredientFromFridge(ingredientId))
    {
        alert('Something went wrong')
        return
    }
    window.fridgeContent.delete(ingredientId)
    markIngredientIsOutOfFridge(ingredientId)
    removeIngredientFromCurrent(ingredientId)
}

function setupHandlers() {
    const buttons = document.querySelectorAll('.mini-button-add-remove')
    for (const button of buttons) {
        button.addEventListener('click', addRemoveButtonClicked)
    }
}

function addRemoveButtonClicked(e)
{
    const ingredientId = getIngredientIdFromButton(e.target)
    if (e.target.innerText.trim() === '+')
        addToFridge(ingredientId)
    else if (e.target.innerText.trim() === '-')
        removeFromFridge(ingredientId)
}

function copyIngredientToCurrent(ingredientId) {
    const elements = document.querySelectorAll(`.ingredient_${ingredientId}`)
    if (elements.length !== 1)
        return

    const element = elements[0];
    const clonedElement = element.cloneNode(true)
    document.querySelector('#current').appendChild(clonedElement)
    clonedElement.querySelector('button').addEventListener('click', addRemoveButtonClicked)
}

function removeIngredientFromCurrent(ingredientId) {
    const elements = document.querySelectorAll(`.ingredient_${ingredientId}`)
    if (elements.length !== 2)
        return

    const element = elements[0];
    element.remove()
}

function getIngredientIdFromButton(buttonElement) {
    for (const classListElement of buttonElement.classList) {
        if (classListElement.startsWith('ingredientButton'))
        {
            return +classListElement.split('_')[1]
        }
    }
}

setupFridge()


function redirectToQuery(base_url) {
    const ingredients = new Array(0)
    for (const ingredientId of window.fridgeContent)
    {
        ingredients.push(ingredientId)
    }
    const jsonIngredients = JSON.stringify(ingredients)
    window.location.href =`${base_url}?require=${jsonIngredients}`
}
