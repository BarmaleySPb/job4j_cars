function validateAdvert() {
    let messageCarModel = 'Enter model of car'
    let messageCarEngine = 'Enter engine'
    let messageDescription = 'Enter description'
    let carModel = $('#inputCarModel').val();
    let carEngine = $('#inputCarEngine').val();
    let carDescription = $('#inputCarDescription').val();
    if (carDescription === '') {
        alert(messageDescription);
        return false;
    }
    if (carModel === '') {
        alert(messageCarModel);
        return false;
    }
    if (carEngine === '') {
        alert(messageCarEngine);
        return false;
    }
    return true;
}