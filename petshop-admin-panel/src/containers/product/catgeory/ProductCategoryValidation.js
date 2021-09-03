import {isEmptyOrNull} from "../../../utils/validationUtil";

export let nameError = [];
export let depthError = [];
export let afterFirstUpsertAttemp = false;

const NAME_EMPTY_OR_NULL = "Kategori ismi alanı boş olamaz.\n";
const DEPTH_LESS_THAN_ZERO = "Derinlik sıfırdan küçük olamaz.\n";

export const validateName = (name) => {

    if(afterFirstUpsertAttemp) {
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

export const validateDepth = (depth) => {
    if(afterFirstUpsertAttemp) {
        if (depth < 0) {
            if (!depthError.includes(DEPTH_LESS_THAN_ZERO)) {
                depthError.push(DEPTH_LESS_THAN_ZERO);
            }
        } else {
            depthError = depthError.filter(error => {
                return error != DEPTH_LESS_THAN_ZERO;
            });
        }
    }
    return depthError;
}

export const emptyValidationErrors = () => {
    afterFirstUpsertAttemp = false;
    nameError = [];
    depthError = [];
}

export const isValid = (category) => {
    afterFirstUpsertAttemp = true;
    validateName(category.name);
    validateDepth(category.depth);
    return !(nameError.length > 0 || depthError.length > 0);
}