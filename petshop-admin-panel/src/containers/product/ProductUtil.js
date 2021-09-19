export const generateCategoryStr = (categoryData) => {
    let initialArray = [];
    let str = "";
    let tempArray = [];
    tempArray = generateCategorySubArray(categoryData,initialArray);
    tempArray.reverse().forEach((c,index)=>{
        if(index !==tempArray.length-1){
            str += tempArray[index] + "->";
        } else {
            str += tempArray[index];
        }
    })
    return str;
}

const generateCategorySubArray = (category, initialArray) => {
    if(category.name){
        initialArray.push(category.name);
    }
    if (category.parent) {
        generateCategorySubArray(category.parent, initialArray);
    }
    return initialArray;
}