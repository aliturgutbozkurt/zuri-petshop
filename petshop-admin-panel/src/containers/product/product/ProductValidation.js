import {isEmptyOrNull} from "../../../utils/validationUtil.js";

export let activeCategoryError = [];
export let nameError = [];
export let aboutError = [];
export let oldPriceError = [];
export let priceError = [];
export let allPhotoError = [];
export let afterFirstUpsertAttemp = false;

const CATEGORY_EMPTY_OR_NULL = "Kategori alanı boş olamaz.\n";
const NAME_EMPTY_OR_NULL = "Ürün adı alanı boş olamaz.\n";
const ABOUT_EMPTY_OR_NULL = "Ürün açıklama alanı boş olamaz.\n";
const OLD_PRICE_LESS_THAN_ZERO = "Eski fiyat sıfırdan küçük olamaz.\n";
const OLD_PRICE_BIGGER_THAN_PRICE = "Eski fiyat, fiyattan büyük olmalıdır.\n";
const PRICE_LESS_THAN_ZERO = "Fiyat sıfırdan küçük olamaz.\n";
const PRICE_EMPTY_OR_NULL = "Fiyat alanı boş olamaz.\n";
const PHOTO_EMPTY_OR_NULL = "En az bir foto yüklenmelidir\n";


export const validateCategory = (categoryId) => {

    if (afterFirstUpsertAttemp) {
        if (isEmptyOrNull(categoryId)) {
            if (!activeCategoryError.includes(CATEGORY_EMPTY_OR_NULL)) {
                activeCategoryError.push(CATEGORY_EMPTY_OR_NULL);
            }
        } else {
            activeCategoryError = activeCategoryError.filter(error => {
                return error != CATEGORY_EMPTY_OR_NULL;
            });
        }
    }
    return activeCategoryError;
}

export const validateName = (name) => {

    if (afterFirstUpsertAttemp) {
        if (isEmptyOrNull(name)) {
            if (!nameError.includes(NAME_EMPTY_OR_NULL)) {
                nameError.push(NAME_EMPTY_OR_NULL);
            }
        } else {
            nameError = nameError.filter(error => {
                return error != NAME_EMPTY_OR_NULL;
            });
        }
    }
    return nameError;
}

export const validateAbout = (about) => {

    if (afterFirstUpsertAttemp) {
        if (isEmptyOrNull(about)) {
            if (!aboutError.includes(ABOUT_EMPTY_OR_NULL)) {
                aboutError.push(ABOUT_EMPTY_OR_NULL);
            }
        } else {
            aboutError = aboutError.filter(error => {
                return error != ABOUT_EMPTY_OR_NULL;
            });
        }
    }
    return aboutError;
}

export const validateOldPrice = (oldPrice, price) => {
    if (afterFirstUpsertAttemp) {
        if (oldPrice < 0) {
            if (!oldPriceError.includes(OLD_PRICE_LESS_THAN_ZERO)) {
                oldPriceError.push(OLD_PRICE_LESS_THAN_ZERO);
            }
        } else {
            oldPriceError = oldPriceError.filter(error => {
                return error != OLD_PRICE_LESS_THAN_ZERO;
            });
        }
        compareOldAndNePrice(oldPrice, price);
    }
    return oldPriceError;
}


export const validatePrice = (price, oldPrice) => {
    if (afterFirstUpsertAttemp) {
        if (isEmptyOrNull(price)) {
            if (!priceError.includes(PRICE_EMPTY_OR_NULL)) {
                priceError.push(PRICE_EMPTY_OR_NULL);
            }
        } else {
            priceError = priceError.filter(error => {
                return error != PRICE_EMPTY_OR_NULL;
            });
        }
        if (price < 0) {
            if (!priceError.includes(PRICE_LESS_THAN_ZERO)) {
                priceError.push(PRICE_LESS_THAN_ZERO);
            }
        } else {
            priceError = priceError.filter(error => {
                return error != PRICE_LESS_THAN_ZERO;
            });
        }
        compareOldAndNePrice(oldPrice, price);

    }
    return priceError;
}

function compareOldAndNePrice(oldPrice, price) {
    if (oldPrice > price) {
        if (!oldPriceError.includes(OLD_PRICE_BIGGER_THAN_PRICE)) {
            oldPriceError.push(OLD_PRICE_BIGGER_THAN_PRICE);
        }
    } else {
        oldPriceError = oldPriceError.filter(error => {
            return error != OLD_PRICE_BIGGER_THAN_PRICE;
        });
    }
}

export const validatePhoto = (images) => {
    if (afterFirstUpsertAttemp) {
        if (images.length < 1) {
            if (!allPhotoError.includes(PHOTO_EMPTY_OR_NULL)) {
                allPhotoError.push(PHOTO_EMPTY_OR_NULL);
            }
        } else {
            allPhotoError = allPhotoError.filter(error => {
                return error != PHOTO_EMPTY_OR_NULL;
            });
        }
    }
    return allPhotoError;
}

export const emptyValidationErrors = () => {
    activeCategoryError = [];
    nameError = [];
    aboutError = [];
    oldPriceError = [];
    priceError = [];
    allPhotoError = [];
    afterFirstUpsertAttemp = false;
}

export const isValid = (product) => {
    afterFirstUpsertAttemp = true;
    validateCategory(product.categoryId);
    validateName(product.name);
    validateAbout(product.about);
    validateOldPrice(product.oldPrice, product.price);
    validatePrice(product.price);
    validatePhoto(product.images);
    return !(activeCategoryError.length > 0 ||
        nameError.length > 0 ||
        aboutError.length > 0 ||
        oldPriceError.length > 0 ||
        priceError.length > 0 ||
        allPhotoError.length > 0);
}