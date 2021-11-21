import request from "../../common/request";

export const getQuestionById = (questionId)=>{

    const questionRequest= {
        method: "GET",
        path: "/api/v1/question/get-question-by-id",
        params: ["questionId"],
        values: [questionId]
    }
    return request(questionRequest)

}

export const getAllQuestions = (page,size) => {

    const questionGetAllQuestion = {
        method: "GET",
        path: "/api/v1/question/get-all-questions",
        params: ["page","size"],
        values: [page,size]

    }
    return request(questionGetAllQuestion);

}