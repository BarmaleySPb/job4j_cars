function validateAdvert() {
    let messageCarModel = 'Enter model of car'
    let messageDescription = 'Enter description'
    let carModel = $('#inputCarModel').val();
    let carDescription = $('#inputCarDescription').val();
    if (carDescription === '') {
        alert(messageDescription);
        return false;
    }
    if (carModel === '') {
        alert(messageCarModel);
        return false;
    }
    return true;
}