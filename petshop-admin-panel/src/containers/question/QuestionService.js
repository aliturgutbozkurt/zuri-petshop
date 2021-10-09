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